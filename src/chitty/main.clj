(ns chitty.main
	(:gen-class)
	(:use [chitty.display :only (print-board)])
	(:use [chitty.core :only (board)]))

(defn -main [& args]
	(let 
		[x (board)]
		(print-board x)))