(ns chitty.main
	(:gen-class)
	(:use [chitty.display :only (print-board)])
	(:use [chitty.core :only (board place swap-player is-game-won? is-game-over?)])
	(:use [chitty.messages]))

(defn- parse-int [number-string]
	(Integer. number-string))

(defn- get-input [prompt]
	(println prompt)
	(read-line))

(defn- prompt-for-move [player]
	(parse-int (get-input (message-player-move player))))

(defn- play-game [player next-player move board]
	(let [new-board (place board player move)]
		(print-board new-board)
		(cond 
			(is-game-won? new-board player)
	    	(println (messages-player-won player))
	    	(is-game-over? new-board)
	   		(println (messages-game-over))
	   		:else
	   		(recur next-player player (prompt-for-move next-player) new-board))))

(defn -main [& args]
	(let [board (board)	player 0 next-player 1]
		(print-board board)
		(play-game player next-player (prompt-for-move player) board)))
	