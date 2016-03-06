;;This program is for calculating your block.io bit balance in USD
(ns handydandy.bit
  (:gen-class)
  (:require [clj-http.client :as client])
  (:require [clojure.data.json :as json]))

(defn bit-balance 
  "Function that returns bit balance in USD"
  []
  (defn api-request [method path body]
    (:body
     (client/request
      {:method method
       :url (str "https://block.io/api/v2" path)
       :content-type "text/plain"
       :body body})))

  ;;Definition names describe what is done here
  (def json-balance (api-request :get "/get_balance/?api_key=*MY-API-KEY*&label=default" ""))
  (def balance (Double/parseDouble (:available_balance (:data (json/read-str json-balance :key-fn keyword)))))
  (def json-base-prices (api-request :get "/get_current_price/?api_key=*MY-API-KEY*&price_base=USD" ""))
  (def base-prices (map :price (:prices (:data (json/read-str json-base-prices :key-fn keyword)))))
  (def amount-base-prices (count base-prices))
  (def sum-base-prices (apply + (map #(Double/parseDouble %) (into [] base-prices))))
  (def avg-base-price (/ sum-base-prices amount-base-prices))
  (def basic-balance-USD (str (* balance avg-base-price)))
  (def fancy-balance-USD (str "$" (format "%.2f" (* balance avg-base-price)) " USD"))

  (identity fancy-balance-USD))

(bit-balance);;=> "$118.67 USD"
