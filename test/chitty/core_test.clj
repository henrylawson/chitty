(ns chitty.core-test
  (:use clojure.test
        chitty.core))

(deftest board-rows
	(testing "board should have correct row count"
		(doseq
			[column (board)]
			(is (= (count column) 6)))))

(deftest board-columns
	(testing "board should have correct column count"
		(is (= (count (board)) 8))))