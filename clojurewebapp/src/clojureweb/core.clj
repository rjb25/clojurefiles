(ns clojureweb.core
  (:use [org.httpkit.server :only [run-server]]
        [compojure.route :only [resources not-found]])
  (:require [ring.middleware.reload :as reload]
            [ring.middleware.logger :as logger]
            [compojure.handler :as handler]
            [compojure.core :refer :all]
            [clojureweb.database :refer [create-entry]]
            [clojureweb.handlers :refer [form, response]]))

(defn entry [request]
  (apply create-entry (reduce #(conj %1 (Integer/parseInt (get (get request :params) %2))) [] '(:count :day :month :year))))
(defn param->vector
  [vector param request]
(conj vector (Integer/parseInt (get (get request :params) param))))
(defroutes simple-routes
  (GET "/", [],  form)
  (POST "/", [], entry)
  (GET "/attendance", [], response)
  (resources "/")
  (not-found "404"))
(def app
  (handler/site #'simple-routes))

(defn serve [& args]
  (org.apache.log4j.BasicConfigurator/configure)
  
  (run-server
   (logger/wrap-with-logger
    (reload/wrap-reload app))
   {:port 8080}))
