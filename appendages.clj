(ns appendages.core
  (:require (clojure [string :as s])))
(->> "hello" (println "heyyyyj"))
(->> "hello") 
(->> (#(do (println %) "he") "hello") println nil?)

(do (println %) "he")
(#(->> (println)) "hey")
(ns appendages.core
  (:require (clojure [string :as s])))

(defn next-appendage
  [part done]
  (update part :name
          #(->> (re-find #"[0-9]" %)
                Long/parseLong
                (+ 1 done)
                str
                (s/replace % #"[0-9]"))))

(defn add-appendages
  [times part]
  (cons part (map (partial next-appendage part) (range times))))
(add-appendages 2 {:name "tentacle-3"}) ;;OUTPUT->[{:name "tentacle-3"} {:name "tentacle-4"} {:name "tentacle-5"}]

(defn next-appendage
  [part done]
  (let [old-name (:name part)  
       new-number (str (+ 1 done (Long/parseLong (re-find #"[0-9]" old-name))))
       new-name (clojure.string/replace old-name #"[0-9]" new-number)]
    (hash-map :name new-name)))
(range 10 20 )
(defn add-appendages
  [times part]
  (loop [done 0
         final-appendages [part]]
    (if (= done times)
      final-appendages 
      (recur 
       (inc done) 
       (into []  (conj final-appendages
                       (next-appendage part done)))))))
  
(add-appendages 2 {:name "tentacle-3"}) ;;OUTPUT->[{:name "tentacle-3"} {:name "tentacle-4"} {:name "tentacle-5"}]

(defn add-appendages [times part]
  (let [ ;; find the prefix and initial number
        [_ prefix number-str] (first (re-seq #"(.*)-([0-9]+)$" (:name part)))
         ;; parse the number, using Java interop
        initial-number (Integer/parseInt number-str)]
    ;; Build the list of results using a simple map operation
    (map (fn [n] {:name (str prefix "-" n)})
         (range initial-number (+ 1 initial-number times)))))
(mapcat "hey" ["yo" "yo" "sonny"])
(cons 1 2)
(reverse [1 2 3 4])
(concat '("hello" "hey") '("y"))
(repeat 5 5)
