(ns clojureweb.formative
  (:require [formative.core :as f]
            [formative.parse :as fp]))
(use 'hiccup.core)
(def example-form
  {:fields [{:name :count :type :int}
            {:name :day :type :int}
            {:name :month :type :int}
            {:name :year :type :int}]
   :validations [:required [:count :day :month :year]]
})
(def page (html (f/render-form example-form)))
