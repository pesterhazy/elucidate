(ns elucidate.main-test
  (:require
   [clojure.test :refer [deftest is]]
   [elucidate.main :as x]))

(deftest extract-null
  (is (= nil (x/extract '()))))

(deftest extract-simple
  (is (= '(= 0 1) (x/extract '(not (= 0 1))))))
