(ns swagger-schema-validator.core
  (:require [clojure.java.io :as io]
            [cheshire.core])
  (:import [com.github.bjansen]
           (java.io ByteArrayInputStream)
           (com.github.bjansen.ssv SwaggerValidator)
           (com.github.fge.jsonschema.core.report ProcessingReport))
  (:gen-class))

(defn- string->stream
  [s]
  (-> (.getBytes s "UTF-8")
      (ByteArrayInputStream.)
      io/reader))

(defn- ^ProcessingReport validate [schema definition json]
  (-> (SwaggerValidator/forJsonSchema (string->stream schema))
      (.validate json definition)))

(defn explain [schema definition json]
  (-> (validate schema definition json)
      .asJson
      str
      (cheshire.core/parse-string keyword)))

(defn valid? [schema definition json]
  (.isSuccess (validate schema definition json)))


(defn -main [& [schema-filename definition json-filename]]
  (let [schema (slurp schema-filename)
        json (slurp json-filename)]
    (binding [*out* *err*]
      (doseq [e (explain schema definition json)]
        (println (cheshire.core/generate-string e))))
    (if (valid? schema definition json)
      (System/exit 0)
      (System/exit 1))))

