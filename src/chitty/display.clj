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
	(print "\n"))

(defn print-col-title
	[board-width]
	(dotimes [col board-width]
		(print-item col))
	(println ""))

(defn print-board
	[board]
	(println "")
	(print-col-title (count (first board)))
	(doseq [row board]
		(print-row row))
	(println ""))