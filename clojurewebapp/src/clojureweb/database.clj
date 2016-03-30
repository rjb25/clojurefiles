(ns clojureweb.database
  (:require [clojure.java.jdbc :as sql] [cheshire.core :refer :all]))

;; hard code the DSN for now
(def db 
  "returns a connection string for the database"
  {:subprotocol "postgresql"
   :subname "//localhost:5432/attendance"
   :user "postgres"
   :password "NOTFORYOU!"})

(defn migrated? []
  (-> (sql/query db
                 [(str "select count(*) from information_schema.tables "
                       "where table_name='attendance'")])
      first :count pos?))

(defn migrate []
  (when (not (migrated?))
    (print "Creating database structure...") (flush)
    (sql/db-do-commands db
                        (sql/create-table-ddl
                         'attendance
                         [:id :serial "PRIMARY KEY"]
                         [:count :smallint "NOT NULL"]
                         [:day :smallint "NOT NULL"]
                         [:month :smallint "NOT NULL"]
                         [:year :smallint "NOT NULL"]))
    (println " done")))

(defn create-entry [count day month year]
  (try
    (sql/insert!
     db
     :attendance
     {:count count
      :day day
      :month month
      :year year})
    (catch Exception e
      {:error (str "caught exception: " (.getMessage e))})))

(defn select-all []
  (into
   []
   (sql/query
    db
    ["SELECT * FROM attendance"])))

(defn delete-all []
  (into
   []
   (sql/execute!
    db
    ["TRUNCATE table attendance"])))

(defn return-json []
 (map generate-string  (select-all)))

