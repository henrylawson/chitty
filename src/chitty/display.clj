(ns chitty.display
	(:use [chitty.core :only (vector-with)]))

(defn print-item [item]
	(print " " item))

(defn to-icon [slot]
	(cond 
		(nil? slot) "."
		(= slot 0) "O"
		(= slot 1) "X"))

(defn print-board-header [board-width]
	(dotimes [col board-width]
		(print-item col))
	(println ""))

(defn print-board-body [board]
	(dotimes [row-count (count (first board))]
		(doseq [column board]
			(print-item (to-icon (column row-count))))
		(println "")))

(defn print-board [board]
	(println "")
	(print-board-header (count board))
	(print-board-body board)
	(println ""))