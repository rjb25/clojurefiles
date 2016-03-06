;;Replicates the + function using the inc function.
(ns handydandy.core
  (:gen-class))

(defn join
  "two number addition"
  [number1 number2]
(loop [times number1
       total number2]
  (if (> times 0)
    (do (recur (dec times) (inc total)))
    total)))
(join 1 3);;=> 4

(defn add
  "dumb multi number addition"
  ([initial other & left]
  (loop [sum initial
         toadd other
         remaining left]
    (if (nil? toadd)
      sum
      (recur (join sum toadd)
       (first remaining) (rest remaining)))))
  ([initial]
  initial))
(add 1 3 5);;=> 9

(defn add
  "smart multi number addition"
  [initial & leftover] 
  (reduce join initial leftover))
(add 1 3 5);;=> 9
