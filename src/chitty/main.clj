(ns chitty.main
	(:gen-class)
	(:use [chitty.display :only (print-board)])
	(:use [chitty.core :only (board place swap-player is-game-won? is-game-over?)])
	(:use [chitty.messages]))

(defn parse-int [number-string]
	(try
		(Integer. (re-find #"[0-9]*" number-string))
		(catch Exception e nil)))

(defn get-input [prompt]
	(println prompt)
	(read-line))

(defn prompt-for-move [player]
	(parse-int
		(get-input (message-player-move player))))

(defn -main [& args]
	(loop [	board (board)
			move (prompt-for-move 0)
			player 0]
		(let [	next-player (swap-player player)
				new-board (place board player move)]
			(print-board new-board)
			(cond 
				(is-game-won? new-board)
		    	(println (messages-player-won player))
		    	(is-game-over? new-board)
	    		(println (messages-game-over))
	    		:else
	    		(recur new-board (prompt-for-move next-player) next-player)))))