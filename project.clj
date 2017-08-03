(defproject rest-test "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [ring/ring-core "1.2.1"]
                 [ring/ring-jetty-adapter "1.2.1"]
                 [org.clojure/java.jdbc "0.7.0"]
                 [ring/ring-json "0.4.0"]
                 [mysql/mysql-connector-java "5.1.18"]
                 [compojure "1.1.6"]]
  :main ^:skip-aot rest-test.core
  :profiles {:uberjar {:aot :all}})
