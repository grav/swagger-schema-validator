(defproject swagger-schema-validator "0.2.0"
  :description "Validate JSON objects against Swagger schemas"
  :url "https://github.com/grav/swagger-schema-validator"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :main swagger-schema-validator.core
  :aot [swagger-schema-validator.core]
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [com.github.bjansen/swagger-schema-validator "0.1.2"]
                 [cheshire "5.8.0"]
                 [org.slf4j/slf4j-nop "1.7.25"]])

