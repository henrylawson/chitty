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
	(with-local-vars [game (board) player 1]
		(print-board @game)
		(loop [input (get-input (message-player-move @player))]
		    (cond 
		    	(is-game-won? board)
		    	(let [previous-player (swap-player @player)]
		    		(println (messages-player-won previous-player)))
		    	(is-game-over? board)
	    		(println (messages-game-over))
	    		(not= input "exit")
		    	(do
		    		(try
		    			(do
		    				(var-set game (place @game @player (parse-int input)))
		    				(var-set player (swap-player @player)))
		    			(catch Exception e (println (message-invalid-command)))
		    			(finally (print-board @game)))
		    		(recur (get-input (message-player-move @player))))))))