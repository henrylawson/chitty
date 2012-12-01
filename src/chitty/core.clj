(ns chitty.core)

(defn vector-with [size contents]
	(vec (repeat size contents)))

(defn build-board [row-count column-count]
	(vector-with column-count 
		(vector-with row-count nil))) 

(defn board	[]
	(build-board 6 8))

(defn nil-count	[column]
	(count (for [slot column 
				 :when (nil? slot)] 
			slot)))

(defn next-free-slot [column]
	(- (nil-count column) 1))

(defn drop-into	[column player]
	(assoc column (next-free-slot column) player))

(defn swap-player [player]
	(if (= player 0)
		1
		0))

(defn place	[board player column-number]
	(assoc board column-number (drop-into (board column-number) player)))

(defn is-game-won? [board]
	false)

(defn is-game-over? [board]
	false)