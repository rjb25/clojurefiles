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

(defn add
  "smart multi number addition"
  [initial & leftover] 
  (reduce together initial leftover))
(join 1 2)
(add 5 6 7 8 8)
