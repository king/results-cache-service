Results Cache Service
===================================
Reference implementation of the Cache Service to be used in the Results Cache Plugin.

Implemented API
--------------

* GET /job-results/{hash}: Returns a JSON with the cached result and build number from its job hash. Returns 'NOT_BUILT' and '-1' respectively, if the job hash is not found.
* POST /job-results/{hash}: Adds a new result, and related build number, in the cache for the provided job hash
    - Body:
        - Content-type: application/json
        - Example: `{"result": "SUCCESS", "build": 10}`
* DELETE /job-results/clear: Removes all the cache values

Latest release
--------------
2.0.0 - Every cached result now also contains build number

Version history
===============
Version 2.0.0 (July 15, 2021)
---------------------------
* Every cached result now also contains build number
* Compatible with [results-cache-plugin version 2.0.0](https://github.com/jenkinsci/results-cache-plugin/tree/2.0.0)

Version 1.0.1 (April 25, 2019)
---------------------------
* Basic implementation of the API using EHCache and SpringBoot
* Compatible with [results-cache-plugin version 1.2.0](https://github.com/jenkinsci/results-cache-plugin/tree/1.2.0)

How to build
------------
`./gradlew bootJar`

How to execute it
-----------------
* using gradle: `./gradlew bootRun`
* using built jar file: `java -jar ./build/libs/results-cache-service-<release>.jar`
