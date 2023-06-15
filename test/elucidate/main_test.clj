(ns elucidate.main-test
  (:require
   [clojure.test :refer [deftest is]]
   [elucidate.main :as x]))

(deftest ->form
  (is (= '(not (= 0 1)) (x/->form "(not (= 0 1))"))))

(deftest ->form-2
  (is (= '(not (= 0 1)) (x/->form "  (not (= 0 1))"))))

(deftest ->form-3
  (is (= '(not (= 0 1)) (x/->form "  actual: (not (= 0 1))"))))

(deftest ->form-4
  (is (= '(not (= 0 1)) (x/->form "user=> (is (= 0 1))\n\nFAIL in () (NO_SOURCE_FILE:1)\nexpected: (= 0 1)\n  actual: (not (= 0 1))\nfalse"))))

(deftest extract-null
  (is (= nil (x/extract '()))))

(deftest extract-simple
  (is (= '(= 0 1) (x/extract '(not (= 0 1))))))

(deftest ab-=
  (is (= [0 1] (x/ab '(= 0 1)))))

(deftest ab-submap?
  (is (= [{:a 1} {:a 2}] (x/ab '(submap? {:a 1} {:a 2 :b 1})))))
