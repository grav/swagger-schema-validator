# swagger-schema-validator

This Clojure library validates JSON objects against models defined in the definitions section of a Swagger 2 specification

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

## TODO

- Make a command line interface
- Accept schema as yaml
- Deploy to Clojars :-)

## License

Copyright © 2018 Mikkel Gravgaard

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
