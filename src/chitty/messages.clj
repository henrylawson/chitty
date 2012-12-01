(ns chitty.messages)

(defn message-player-move [player]
	(str ">> P" player " what is your move?"))

(defn message-invalid-command []
	">> Invalid command")

(defn messages-player-won [player]
	(str ">> P" player " has won!"))

(defn messages-game-over []
	">> Game over!")