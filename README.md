# rest-test

### Building

You'll need leiningen installed.

In project root, run `lein uberjar`.
The standalone jar will be created in the `/target` directory.

### Running the Jar

`java -jar /path/to/rest-test-0.1.0-STANDALONE.jar`

### Service

There routing table can be found in the core namespace.

```clojure
(defroutes app
  (GET "/" [] "<h1>Hello World!</h1>")
  (GET "/read" [] (response (fetch-all-from-db mysql-db))) ; fetch everything from requests table
  (GET "/read/:id" [id] (response (fetch-from-db mysql-db id))) :fetch by ID
  (GET "/insert/:f-name/:l-name" [f-name l-name] (response (insert-to-db! mysql-db f-name l-name))) ; create a record
  (GET "/update/:id/:f-name/:l-name" [id f-name l-name] (response (update-in-db! mysql-db id f-name l-name))) ; update
  (GET "/delete/:id" [id] (str id)) ; delete by ID
  (route/not-found "<h1>Page not found</h1>"))
```
