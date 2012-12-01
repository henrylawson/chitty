(ns chitty.main
	(:gen-class)
	(:use [chitty.display :only (print-board)])
	(:use [chitty.core :only (board)])
	(:use [chitty.core :only (place)]))

(defn -main [& args]
	(with-local-vars 
		[game (board)]
		(do
			(print-board (var-get game))
			(var-set game (place (var-get game) 0 3))
			(print-board (var-get game))
			(var-set game (place (var-get game) 1 3))
			(print-board (var-get game))
			(var-set game (place (var-get game) 0 3))
			(print-board (var-get game))
			)))
	