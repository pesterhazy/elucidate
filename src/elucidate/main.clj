(ns elucidate.main
  (:require [clojure.edn]
            [clojure.pprint]
            [lambdaisland.deep-diff2 :as ddiff]))

(defn -main [& _args]
  (let [data (clojure.edn/read-string (slurp *in*))]
    (assert (and (list? data)
                 (= 2 (count data))
                 (= 'not (first data))
                 (list? (second data))
                 (= 3 (count (second data)))
                 (= '= (first (second data)))))
    (let [[_ a b] (second data)]
      (ddiff/pretty-print (ddiff/diff a b)))))
