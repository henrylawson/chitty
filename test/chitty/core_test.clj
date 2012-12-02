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

(deftest is-game-won?-vertical-items-1
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

(deftest is-game-won?-vertical-items-2
	(testing "game is won when a single player has 4 vertical items"
		(let [game [[nil nil 0   0   0   0  ]
					[nil nil nil nil nil nil]
					[nil nil nil nil nil nil]
					[nil nil nil nil nil nil]
					[nil nil nil nil nil nil]
					[nil nil nil nil nil nil]
					[nil nil nil nil nil nil]
					[nil nil nil nil nil nil]]]
			(is (true? (is-game-won? game))))))

(deftest is-game-won?-horizontal-items-1
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

(deftest is-game-won?-horizontal-items-2
	(testing "game is won when a single player has 4 horizontal items"
		(let [game [[nil nil nil nil nil 0  ]
					[nil nil nil nil nil 0  ]
					[nil nil nil nil nil 0  ]
					[nil nil nil nil nil 0  ]
					[nil nil nil nil nil nil]
					[nil nil nil nil nil nil]
					[nil nil nil nil nil nil]
					[nil nil nil nil nil nil]]]
			(is (true? (is-game-won? game))))))

(deftest is-game-won?-diagnol-items-1
	(testing "game is won when a single player has 4 diagnol items"
		(let [game [[nil nil nil nil nil nil]
					[nil nil nil nil 0   nil]
					[nil nil nil 0   nil nil]
					[nil nil 0   nil nil nil]
					[nil 0   nil nil nil nil]
					[nil nil nil nil nil nil]
					[nil nil nil nil nil nil]
					[nil nil nil nil nil nil]]]
			(is (true? (is-game-won? game))))))

(deftest is-game-won?-diagnol-items-2
	(testing "game is won when a single player has 4 diagnol items"
		(let [game [[nil nil nil nil nil nil]
					[nil nil nil nil nil nil]
					[nil nil 0   nil nil nil]
					[nil nil nil 0   nil nil]
					[nil nil nil nil 0   nil]
					[nil nil nil nil nil 0  ]
					[nil nil nil nil nil nil]
					[nil nil nil nil nil nil]]]
			(is (true? (is-game-won? game))))))

(deftest is-game-over?-when-full
	(testing "game is over when all places are filled"
		(let [game [[1   1   1   1   1   1  ]
					[1   1   1   0   1   1  ]
					[1   1   0   1   1   1  ]
					[1   1   1   0   1   1  ]
					[1   1   1   1   0   1  ]
					[1   1   1   1   1   0  ]
					[1   1   1   1   1   1  ]
					[1   1   1   1   1   1  ]]]
			(is (true? (is-game-over? game))))))

(deftest is-game-over?-when-not-full
	(testing "game is not over when not all places are filled"
		(let [game [[nil nil 1   1   1   1  ]
					[nil nil 1   0   1   1  ]
					[nil nil 0   1   1   1  ]
					[nil nil 1   0   1   1  ]
					[nil nil 1   1   0   1  ]
					[nil nil 1   1   1   0  ]
					[nil nil 1   1   1   1  ]
					[nil nil 1   1   1   1  ]]]
			(is (false? (is-game-over? game))))))

(deftest swap-player-tests
	(testing "should swap to player 1 when intial is 0"
		(is (= (swap-player 0) 1)))
	(testing "should swap to player 0 when intial is 1"
		(is (= (swap-player 1) 0))))