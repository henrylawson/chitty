(ns chitty.core)

(defn vector-of
	[size contents]
	(vec (repeat size contents)))

(defn build-board
	[row-count column-count]
	(vector-of row-count 
		(vector-of column-count nil))) 

(defn board
	[]
	(build-board 6 8))