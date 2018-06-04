(ns swagger-schema-validator.core-test
  (:require [clojure.test :refer :all]
            [swagger-schema-validator.core :refer :all]))

(def json-good
  "{\"name\": \"Eggs\", \"price\": 12.23}")

(def json-bad
  "{\"name\": \"Eggs\", \"price\": \"12.23\"}")

(def schema
  "{\"definitions\":{\"item\":{\"type\": \"object\", \"properties\":{\"price\":{\"type\":\"number\"},\"name\":{\"type\":\"string\"}}}}}")

(def schema-unsupported
  "{\"definitions\":{\"item\":{\"type\": \"object\", \"properties\":{\"price\":{\"type\":\"number\"},\"name\":{\"type\":\"string\",\"format\": \"int32\"}}}}}")

(def definition "/definitions/item")

(deftest test-valid?
  (testing "valid?"
    (is (not (valid? schema definition json-bad)))
    (is (valid? schema definition json-good))
    (is (valid? schema-unsupported definition json-good))))

(deftest test-explain
  (testing "explain errors and warnings"
    (let [result (explain schema definition json-bad)]
      (is (not-empty result))
      (is (= "error" (:level (first result)))))
    (let [result (explain schema definition json-good)]
      (is (empty? result)))
    (let [result (explain schema-unsupported definition json-good)]
      (is (not-empty result))
      (is (= "warning" (:level (first result)))))))

