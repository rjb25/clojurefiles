(ns clojureweb.handlers 
  (:require [hiccup.core :as hiccup]
            [clojureweb.database :refer [return-json]]
            [clojureweb.formative :refer [page]]
            [hiccup.page :refer [html5 include-css include-js]]))

(defn form [req]
page)

(defn response [req]
  (return-json))
