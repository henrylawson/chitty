(ns chitty.main
	(:gen-class)
	(:use [chitty.display :only (print-board)])
	(:use [chitty.core :only (board place swap-player is-game-won? is-game-over?)])
	(:use [chitty.messages]))

(defn parse-int [number-string]
  (Integer. (re-find #"[0-9]*" number-string)))

(defn get-input [prompt]
	(println prompt)
	(read-line))

(defn -main [& args]
	(with-local-vars [game (board)]
		(print-board @game)
		(loop [input (get-input (message-player-move 0)) player 0]
			(try
				(var-set game (place @game player (parse-int input)))
				(catch Exception e (println (message-invalid-command)))
				(finally (print-board @game)))
		    (cond 
		    	(is-game-won? @game)
		    	(println (messages-player-won player))
		    	(is-game-over? @game)
	    		(println (messages-game-over))
	    		(not= input "exit")
	    		(let [next-player (swap-player player)]
		    		(recur (get-input (message-player-move next-player)) next-player))))))