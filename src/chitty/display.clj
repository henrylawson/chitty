(ns chitty.display)

(defn print-item
	[item]
	(print " " item))

(defn to-icon
	[slot]
	(cond 
		(nil? slot) "."
		(= slot 0) "X"
		(= slot 1) "O"))

(defn print-row
	[row]
	(doseq [slot row]
		(print-item (to-icon slot)))
	(println ""))

(defn print-board-header
	[board-width]
	(dotimes [col board-width]
		(print-item col))
	(println ""))

(defn print-board-body
	[board]
	(doseq [row board]
		(print-row row)))

(defn print-board
	[board]
	(println "")
	(print-board-header (count (first board)))
	(print-board-body board)
	(println ""))