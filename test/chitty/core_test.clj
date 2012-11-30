(ns chitty.core-test
  (:use clojure.test
        chitty.core))

(deftest board-rows
	(testing "board should have correct row count"
		(is (= (count (board)) 6))))

(deftest board-columns
	(testing "board should have correct column count"
		(doseq
			[row (board)]
			(is (= (count row) 8)))))