;;This program creates a hash code for a given DNA file
(ns DNA.core
  (:require (clojure [string :as s])))

(use 'clojure.java.io)

;;For finding where input stream is looking for protein file
(System/getProperty "user.dir")

;;Where the protein file is placed relative to the output of the above function call
(def protein "src/DNA/protein")

(defn get-lines 
 "Accepts a filename and returns a vector of its lines as strings" 
  [name]
  (let [rdr (reader name)]
    (loop
        [line (.readLine rdr)
         list []]
      (if (nil? line)
        list
        (recur (.readLine rdr)
               (into list (vector line)))))))
(get-lines protein)

(comment "In this example the lines containing proteins are signalled by
 a blank first character in the line.")
(defn blank-start?
  "Boolean test to see if a line starts with a blank character"
  [line] 
  (= " " (subs line 0 1)))
(blank-start? " hello")

(defn get-proteins
  "Gets the proteins from a file"
  [filename]
  (reduce into (map #(s/split (s/trim %) #" ") (filter blank-start? (get-lines filename)))))
(get-proteins protein)

(defn hash-add-absolute
  "Adds hash values of strings and gives the absolute value"
  [filename]
  (str (Math/abs (reduce #(+ %1 (hash %2)) 0 (get-proteins filename)))))
(hash-add-absolute protein)

(comment "This function exists in case somehow the addition of positive and negative
 hashes is less than four charachters long")
(defn add-zeros
  "Adds a given amount of zeroes to a string"
  [missing short-string]
  (str short-string
       (loop
           [zerostring ""
            times missing]
           (if (> times 0) (recur (str "0" zerostring) (dec times))
               zerostring))))
(add-zeros 4 "hy")

;;How long you want the DNA code to be
(def code-length 4)

(defn hash-add-fill
  "A wrapper of hash-add that tests for correct length then fills in the necessary zeroes"
  [filename]
  (let [min-length code-length
        hash-added (hash-add-absolute filename)
        numbers-missing (- min-length (count hash-added))]
    (if (< (count hash-added) min-length)
      (add-zeros numbers-missing hash-added)
      hash-added)))
(hash-add-fill protein)

(defn hash-code 
  "Generates a hash code for a DNA file"
  [filename] 
  (let [hash-string (hash-add-fill filename)]
    (subs hash-string  (- (count hash-string) code-length) (count hash-string))))
(hash-code protein)
