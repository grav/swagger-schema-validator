# swagger-schema-validator

This Clojure library validates JSON objects against models defined in the definitions section of a Swagger 2 specification

[![Build Status](https://travis-ci.org/grav/swagger-schema-validator.svg?branch=master)](https://travis-ci.org/grav/swagger-schema-validator)

It is a simple wrapper for the Java library at
https://github.com/bjansen/swagger-schema-validator

## Usage

API has two functions:

`swagger-schema-validator.core/valid?`

and

`swagger-schema-validator.core/explain`

These both take the following arguments:
 - a swagger schema as a json string
 - a pointer to the definition to use (eg `"/definitions/item"`)
 - a blob of json as a string

The `explain` function returns a (possibly empty) list of errors and warnings.

See [the test namespace](https://github.com/grav/swagger-schema-validator/blob/master/test/validate_swagger_schema/core_test.clj) for usage examples.

## CLI usage

The library can also be used as a command line tool:

```
$ lein uberjar

...


$ java -jar target/swagger-schema-validator-0.2.0-standalone.jar  <( echo '{"definitions":{"mydef": {"type":"integer"}}}' ) "/definitions/mydef" <( echo '42')

{"level":"error","schema":{"loadingURI":"#","pointer":"/definitions/mydef"},"instance":{"pointer":""},"domain":"validation","keyword":"type","message":"instance type (string) does not match any allowed primitive type (allowed: [\"integer\"])","found":"string","expected":["integer"]}


```

This will output any warnings and errors to stderr.

In case of errors, it will exit with code 1.


## TODO

- Accept schema as yaml

## License

Copyright © 2018 Mikkel Gravgaard

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
