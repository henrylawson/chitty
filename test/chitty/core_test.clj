(ns chitty.core-test
  (:use clojure.test
        chitty.core))

(deftest board-row-count
	(testing "board should have correct row count"
		(let [game (board)]
			(is (= (count (game 0)) 6))
			(is (= (count (game 1)) 6))
			(is (= (count (game 2)) 6))
			(is (= (count (game 3)) 6))
			(is (= (count (game 4)) 6))
			(is (= (count (game 5)) 6))
			(is (= (count (game 6)) 6))
			(is (= (count (game 7)) 6)))))

(deftest board-column-count
	(testing "board should have correct column count"
		(is (= (count (board)) 8))))

(deftest is-game-won?-empty-board
	(testing "game is not won when there are no items"
		(let [game [[nil nil nil nil nil nil]
					[nil nil nil nil nil nil]
					[nil nil nil nil nil nil]
					[nil nil nil nil nil nil]
					[nil nil nil nil nil nil]
					[nil nil nil nil nil nil]
					[nil nil nil nil nil nil]
					[nil nil nil nil nil nil]]]
			(is (false? (is-game-won? game))))))

(deftest is-game-won?-vertical-items
	(testing "game is won when a single player has 4 vertical items"
		(let [game [[nil nil nil nil nil nil]
					[nil nil nil nil nil nil]
					[nil nil nil nil nil nil]
					[0   0   0   0   nil nil]
					[nil nil nil nil nil nil]
					[nil nil nil nil nil nil]
					[nil nil nil nil nil nil]
					[nil nil nil nil nil nil]]]
			(is (true? (is-game-won? game))))))


(deftest is-game-won?-horizontal-items
	(testing "game is won when a single player has 4 horizontal items"
		(let [game [[nil nil nil nil nil nil]
					[nil 0   nil nil nil nil]
					[nil 0   nil nil nil nil]
					[nil 0   nil nil nil nil]
					[nil 0   nil nil nil nil]
					[nil nil nil nil nil nil]
					[nil nil nil nil nil nil]
					[nil nil nil nil nil nil]]]
			(is (true? (is-game-won? game))))))