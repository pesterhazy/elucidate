(ns elucidate.main
  (:require [clojure.edn]
            [clojure.pprint]
            [clojure.string]
            [lambdaisland.deep-diff2 :as ddiff]))

(defn ->form [s]
  (if (re-find #"^\s*\(" s)
    (clojure.edn/read-string s)
    (if-let [m (re-find #"actual: (\(.+)" s)]
      (clojure.edn/read-string (second m))
      nil)))

(defn extract [form]
  (when (and (list? form)
             (= 2 (count form))
             (= 'not (first form)))
    (second form)))

(defn ab [form]
  (cond
    (and (list? form)
         (= 3 (count form))
         (= '= (first form)))
    (let [[_ a b] form]
      [a b])
    (and (list? form)
         (= 3 (count form))
         (= 'submap? (first form)))
    (let [[_ a b] form]
      [a (select-keys b (keys a))])
    :else
    nil))

(defn -main [& _args]
  (let [data (->form (slurp *in*))
        form (extract data)]
    (when-not form
      (do
        (println "Didn't understand form")
        (System/exit 1)))
    (let [[a b :as result] (ab form)]
      (if result
        (ddiff/pretty-print (ddiff/diff a b))
        (do
          (println "Didn't understand form")
          (System/exit 1))))))
