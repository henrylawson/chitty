(ns chitty.core-test
  (:use clojure.test
        chitty.core))

(deftest board-tests
	(testing "board should have correct row count"
		(let [game (board)]
			(is (= (count (game 0)) 6))
			(is (= (count (game 1)) 6))
			(is (= (count (game 2)) 6))
			(is (= (count (game 3)) 6))
			(is (= (count (game 4)) 6))
			(is (= (count (game 5)) 6))
			(is (= (count (game 6)) 6))
			(is (= (count (game 7)) 6))))

	(testing "board should have correct column count"
		(is (= (count (board)) 8))))

(deftest is-game-won?-tests
	(testing "game is not won when there are no items"
		(let [game [[nil nil nil nil nil nil]
					[nil nil nil nil nil nil]
					[nil nil nil nil nil nil]
					[nil nil nil nil nil nil]
					[nil nil nil nil nil nil]
					[nil nil nil nil nil nil]
					[nil nil nil nil nil nil]
					[nil nil nil nil nil nil]]]
			(is (false? (is-game-won? game)))))

	(testing "game is won when a single player has 4 vertical items"
		(let [game [[nil nil nil nil nil nil]
					[nil nil nil nil nil nil]
					[nil nil nil nil nil nil]
					[0   0   0   0   nil nil]
					[nil nil nil nil nil nil]
					[nil nil nil nil nil nil]
					[nil nil nil nil nil nil]
					[nil nil nil nil nil nil]]]
			(is (true? (is-game-won? game)))))

	(testing "game is won when a single player has 4 vertical items"
		(let [game [[nil nil 0   0   0   0  ]
					[nil nil nil nil nil nil]
					[nil nil nil nil nil nil]
					[nil nil nil nil nil nil]
					[nil nil nil nil nil nil]
					[nil nil nil nil nil nil]
					[nil nil nil nil nil nil]
					[nil nil nil nil nil nil]]]
			(is (true? (is-game-won? game)))))

	(testing "game is won when a single player has 4 horizontal items"
		(let [game [[nil nil nil nil nil nil]
					[nil 0   nil nil nil nil]
					[nil 0   nil nil nil nil]
					[nil 0   nil nil nil nil]
					[nil 0   nil nil nil nil]
					[nil nil nil nil nil nil]
					[nil nil nil nil nil nil]
					[nil nil nil nil nil nil]]]
			(is (true? (is-game-won? game)))))

	(testing "game is won when a single player has 4 horizontal items"
		(let [game [[nil nil nil nil nil 0  ]
					[nil nil nil nil nil 0  ]
					[nil nil nil nil nil 0  ]
					[nil nil nil nil nil 0  ]
					[nil nil nil nil nil nil]
					[nil nil nil nil nil nil]
					[nil nil nil nil nil nil]
					[nil nil nil nil nil nil]]]
			(is (true? (is-game-won? game)))))

	(testing "game is won when a single player has 4 diagnol items"
		(let [game [[nil nil nil nil nil nil]
					[nil nil nil nil 0   nil]
					[nil nil nil 0   nil nil]
					[nil nil 0   nil nil nil]
					[nil 0   nil nil nil nil]
					[nil nil nil nil nil nil]
					[nil nil nil nil nil nil]
					[nil nil nil nil nil nil]]]
			(is (true? (is-game-won? game)))))

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

(deftest is-game-over?-tests
	(testing "game is over when all places are filled"
		(let [game [[1   1   1   1   1   1  ]
					[1   1   1   0   1   1  ]
					[1   1   0   1   1   1  ]
					[1   1   1   0   1   1  ]
					[1   1   1   1   0   1  ]
					[1   1   1   1   1   0  ]
					[1   1   1   1   1   1  ]
					[1   1   1   1   1   1  ]]]
			(is (true? (is-game-over? game)))))

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

(deftest place-tests
	(testing "should insert into the provided column"
		(let [	player 1
				chosen-column 2
				board (place (board) player chosen-column)]
			(is (= ((board chosen-column) 5) player))))

	(testing "should stak the token into the provided column, if a token already exists"
		(let [	player-one 0
				player-two 1
				chosen-column 2
				board-one (place (board) player-one chosen-column)
				board-two (place board-one player-two chosen-column)]
			(is (= ((board-two chosen-column) 4) player-two)))))