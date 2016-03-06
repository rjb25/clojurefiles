;;Program for adding limbs to a map of body parts
(comment "Defines a function that gives the next limb")
(def next-appendage #(hash-map :name (clojure.string/replace (:name %1) #"[0-9]" (str (+ 1 %2 (read-string (re-find #"[0-9]" (:name %1))))))))
(next-appendage {:name "arm-1"} 0);;=> {:name "arm-2"}

(defn add-appendages
  "Adds given ammount of appendages"
  [times part]
  (loop [done 0
         final-appendages [part]]
    (if (= done times)
      final-appendages 
      (recur 
       (inc done) 
       (conj final-appendages
             (next-appendage part done))))))
  
(add-appendages 2 {:name "tentacle-3"}) ;;OUTPUT->[{:name "tentacle-3"} {:name "tentacle-4"} {:name "tentacle-5"}]
