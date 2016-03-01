(def next-apendage 
  #(hash-map :name (clojure.string/replace (:name %1) #"[0-9]" (str (+ 1 %2 (read-string (re-find #"[0-9]" (:name %1))))))))
(hash-map :name "hello")

(defn add-apendages
  [times part]
  (loop [done 0
         final-apendages [part]]
    (if (= done times)
      final-apendages 
      (recur 
       (inc done) 
       (conj final-apendages
             (next-apendage part done))))))
  
(add-apendages 2 {:name "tentacle-3"}) ;;OUTPUT->[{:name "tentacle-3"} {:name "tentacle-4"} {:name "tentacle-5"}]
