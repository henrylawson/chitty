(ns chitty.core)

(defn- vector-with [size contents]
	(vec (repeat size contents)))

(defn- build-board [row-count column-count]
	(vector-with column-count 
		(vector-with row-count nil))) 

(defn board	[]
	(build-board 6 8))

(defn- nil-count	[column]
	(count (for [slot column :when (nil? slot)] slot)))

(defn- next-free-slot [column]
	(dec (nil-count column)))

(defn- drop-into	[column player]
	(assoc column (next-free-slot column) player))

(defn place	[board player column-number]
	(assoc board column-number (drop-into (board column-number) player)))

(defn swap-player [player]
	(if (= player 0) 1 0))

(defn- inc-to [upper-limit value]
	(if (= upper-limit value) 0 (inc value)))

(defn- inc-when [value other-value number]
	(if (= value other-value) (inc number) number))

(defn- has-diagnol? [board column row]
	(let [	token (aget board column row)
			column-max (dec (count board))
 			row-max (dec (count (first board)))]
 		(cond
 			(> (+ column 3) column-max)
 			false
 			(and 
 				(<= (+ row 3) row-max)
 				(= token (aget board (+ column 1) (+ row 1)))
 				(= token (aget board (+ column 2) (+ row 2)))
 				(= token (aget board (+ column 3) (+ row 3))))
 			true
 			(and 
 				(>= (- row 3) 0)
 				(= token (aget board (+ column 1) (- row 1)))
 				(= token (aget board (+ column 2) (- row 2)))
 				(= token (aget board (+ column 3) (- row 3))))
 			true)))

(defn- has-vertical? [board column row]
	(let [	token (aget board column row)
 			row-max (dec (count (first board)))]
 		(and 
			(<= (+ row 3) row-max)
			(= token (aget board column (+ row 1)))
			(= token (aget board column (+ row 2)))
			(= token (aget board column (+ row 3))))))

(defn- has-horizontal? [board column row]
	(let [	token (aget board column row)
			column-max (dec (count board))]
 		(and 
			(<= (+ column 3) column-max)
			(= token (aget board (+ column 1) row))
			(= token (aget board (+ column 2) row))
			(= token (aget board (+ column 3) row)))))

(defn- has-a-win? [board column row]
	(and
		(not (nil? (aget board column row)))
		(or 
			(has-diagnol? board column row)
			(has-vertical? board column row)
			(has-horizontal? board column row))))

(defn is-game-won? [board]
	(let [	board2d (to-array-2d board)
			column-max (dec (count board))
 			row-max (dec (count (first board)))]
		(loop [column 0 row 0]
			(let [	next-column (inc-to column-max column)
					next-row (inc-when column-max column row)]
				(cond 
					(has-a-win? board2d column row)
					true
					(> next-row row-max)
					false
					:else
					(recur next-column next-row))))))

(defn- is-column-full? [column]
	(= (nil-count column) 0))

(defn is-game-over? [board]
	(every? is-column-full? board))