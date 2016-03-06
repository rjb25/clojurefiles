;;Appendages approved by way of code review: http://codereview.stackexchange.com/questions/121621/adding-augmented-maps-to-a-vector-based-on-regex-text-grabbing
;;Program for adding limbs to a map of body parts

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
(next-appendage {:name "arm-1"} 1);;=> {:name "arm-2"}

(defn add-appendages
  [times part]
  (cons part (map (partial next-appendage part) (range times))))
(add-appendages 2 {:name "tentacle-3"}) ;;=>({:name "tentacle-3"} {:name "tentacle-4"} {:name "tentacle-5})
