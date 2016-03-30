(defproject clojureweb "0.1.0-SNAPSHOT"
  :description "clojure web app"
  :url "http://badshell.com/"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
	[http-kit "2.1.18"]
	[compojure "1.5.0"]
                 [hiccup "1.0.5"]
                 [ring/ring-mock "0.3.0"]
                 [javax.servlet/servlet-api "2.5"]
                 [formative "0.8.8"]                                                                    
                   [cheshire "5.5.0"]
                 [postgresql "9.3-1102.jdbc41"]
                 [org.clojure/java.jdbc "0.4.2"]
	[ring.middleware.logger "0.5.0"]
	[ring/ring-devel "1.4.0"]
        [ring/ring-core "1.4.0"]
                 [clj-time "0.11.0"]]

  :main ^:skip-aot clojureweb.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
