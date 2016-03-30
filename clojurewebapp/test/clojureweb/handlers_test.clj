(ns clojureweb.handlers-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [clojureweb.core :refer :all]
            [cheshire.core :as cheshire]
            [clojureweb.database :as database]))
(database/migrate)
(database/flushdb {:confirm true})

(deftest test-ui-handler
  (testing "index GET"
    (let [response (app (mock/request :get "/"))]
      (is (= (:status response) 200)))))

(deftest create-repmax
  (testing "index POST"
    (let [response (app (mock/request :post "/"))]
      (is (= (:status response) 200))
      (is (= (:body response) "<p>post index</p>")))))

(deftest display-for-user
  (testing "repmaxes-for-users GET"
    (let [response (app (mock/request :get "/sir-test-a-lot/"))]
      (is (= (:status response) 200))
      (is (= (:body response) "<p>Hello sir-test-a-lot</p>")))))

;; load up the refresh tool
(require '[clojure.tools.namespace.repl :refer [refresh]])
;; load up clojure test runner
(require '[clojure.test :refer [run-tests]])
;; load up our tests
(require 'clojureweb.handlers-test)
;; now call our tests
(run-tests 'clojureweb.handlers-test)
;; when required, reload the files
(refresh)
