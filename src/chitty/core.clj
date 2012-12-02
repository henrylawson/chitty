(ns chitty.core)

(defn vector-with [size contents]
	(vec (repeat size contents)))

(defn build-board [row-count column-count]
	(vector-with column-count 
		(vector-with row-count nil))) 

(defn board	[]
	(build-board 6 8))

(defn nil-count	[column]
	(count (for [slot column :when (nil? slot)] slot)))

(defn next-free-slot [column]
	(dec (nil-count column)))

(defn drop-into	[column player]
	(assoc column (next-free-slot column) player))

(defn swap-player [player]
	(if (= player 0) 1 0))

(defn place	[board player column-number]
	(assoc board column-number (drop-into (board column-number) player)))

(defn inc-to [upper-limit value]
	(if (= upper-limit value) 0 (inc value)))

(defn inc-when [value other-value number]
	(if (= value other-value) (inc number) number))

(defn token-equal? [token other-token]
	(and 
		(= token other-token) 
		(not (nil? token)) 
		(not (nil? other-token))))

(defn vertical-win? [column]
	(loop [tokens column last-token nil token-count 1]
		(let [token (first tokens) remaining (rest tokens)]
			(cond 
				(= token-count 4)
				true
				(empty? tokens)
				false
				(token-equal? token last-token)
 				(recur remaining token (inc token-count))
				:else
				(recur remaining token 1)))))

(defn any-vertical-win? [board]
	(true? (some vertical-win? board)))

(defn any-horizontal-win? [board]
	(let [	board2d (to-array-2d board)
			column-max (dec (count board))
 			row-max (dec (count (first board)))]
		(loop [column 0 row 0 last-token nil token-count 1]
			(let [	token (aget board2d column row)
					next-column (inc-to column-max column)
					next-row (inc-when column-max column row)]
				(cond
					(= token-count 4)
					true
					(> next-row row-max)
					false
					(= column column-max)
					(recur next-column next-row nil 1)
					(token-equal? token last-token)
 					(recur next-column next-row token (inc token-count))
					:else
					(recur next-column next-row token 1))))))

(defn has-diagnol? [board column row]
	(let [	token (aget board column row)
			column-max (dec (count board))
 			row-max (dec (count (first board)))]
 		(cond
 			(or
 				(nil? token)
 				(> (+ column 3) column-max))
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

(defn any-diagnol-win? [board]
	(let [	board2d (to-array-2d board)
			column-max (dec (count board))
 			row-max (dec (count (first board)))]
		(loop [column 0 row 0]
			(let [	next-column (inc-to column-max column)
					next-row (inc-when column-max column row)]
				(cond 
					(has-diagnol? board2d column row)
					true
					(> next-row row-max)
					false
					:else
					(recur next-column next-row))))))

(defn is-game-won? [board]
	(or 
		(any-vertical-win? board) 
		(any-horizontal-win? board)
		(any-diagnol-win? board)))

(defn is-column-full? [column]
	(= (nil-count column) 0))

(defn is-game-over? [board]
	(every? is-column-full? board))