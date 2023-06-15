(ns elucidate.main
  (:require [clojure.edn]
            [clojure.pprint]
            [lambdaisland.deep-diff2 :as ddiff]))

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
  (let [data (clojure.edn/read-string (slurp *in*))
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
