(ns elucidate.main
  (:require [clojure.edn]
            [clojure.pprint]
            [lambdaisland.deep-diff2 :as ddiff]))

(defn extract [form]
  (when (and (list? form)
             (= 2 (count form))
             (= 'not (first form)))
    (second form)))

(defn -main [& _args]
  (let [data (clojure.edn/read-string (slurp *in*))
        form (extract data)]
    (when-not form
      (do
        (println "Didn't understand form")
        (System/exit 1)))
    (cond
      (and (list? (second data))
           (= 3 (count (second data)))
           (= '= (first (second data))))
      (let [[_ a b] (second data)]
        (ddiff/pretty-print (ddiff/diff a b)))
      :else
      (do
        (println "Didn't understand form")
        (System/exit 1)))))
