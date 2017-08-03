(ns rest-test.core
  (:require [clojure.java.jdbc :as jdbc]
            [ring.adapter.jetty :refer :all]
            [ring.util.response :refer [response]]
            [ring.middleware.json :refer [wrap-json-response]]
            [compojure.core :refer :all]
            [compojure.route :as route])
  (:gen-class))

(def mysql-db {:dbtype "mysql"
               :dbname "salesandservicedb"
               :user "sassyadmin"
               :password "kWdbmf5Z"
               :host "salesandservicedb-cluster.cluster-cvg63gqqyfjt.us-east-1.rds.amazonaws.com"
               :port "3306"})

(defn fetch-all-from-db [db]
  (jdbc/query db ["select * from requests"]))

(defn fetch-from-db
  [db id]
  (first (jdbc/query db ["select * from requests where request_id = ?" id])))

(defn insert-to-db! [db f-name l-name]
  (first (jdbc/insert! db :requests {:contact_f_name f-name :contact_l_name l-name :contact_num (str f-name l-name)})))

(defn update-in-db! [db id f-name l-name]
  {:success (first (jdbc/update! db :requests {:contact_f_name f-name :contact_l_name l-name} ["request_id = ?" id]))})

(defn delete-in-db! [db id]
  {:success (first (jdbc/delete! db :requests ["request_id = ?" id]))})

(defroutes app
  (GET "/" [] "<h1>Hello World!</h1>")
  (GET "/read" [] (response (fetch-all-from-db mysql-db)))
  (GET "/read/:id" [id] (response (fetch-from-db mysql-db id)))
  (GET "/insert/:f-name/:l-name" [f-name l-name] (response (insert-to-db! mysql-db f-name l-name)))
  (GET "/update/:id/:f-name/:l-name" [id f-name l-name] (response (update-in-db! mysql-db id f-name l-name)))
  (GET "/delete/:id" [id] (str id))
  (route/not-found "<h1>Page not found</h1>"))

(defn handler [request]
  (response (fetch-from-db mysql-db)))

(def app*
  (wrap-json-response app))

(defonce server (atom nil))

(defn start-server []
  (reset! server (run-jetty #'app* {:port 8080})))

(defn -main [& args]
  (start-server))
