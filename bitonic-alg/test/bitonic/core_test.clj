(ns bitonic.core-test
  (:require [clojure.test :refer :all]
            [bitonic.core :refer [bitonic-array]]))

(deftest bitonic-array-tests
  (testing "basic valid cases"
    (is (= [9 10 9 8 7] (bitonic-array 5 3 10)))
    (is (= [2 3 4 5 4 3 2] (bitonic-array 7 2 5)))
    (is (= [4 5 4 3] (bitonic-array 4 2 5))))

  (testing "impossible cases"
    (is (= [-1] (bitonic-array 15 1 5)))
    (is (= [-1] (bitonic-array 20 10 12))))

  (testing "edge ranges"
    ;; n = exactly the maximum possible
    (is (= (count (bitonic-array 9 1 5)) 9))
    ;; small range that still works
    (is (= [2 3 2] (bitonic-array 3 2 3)))))
