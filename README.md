Results Cache Service
===================================
Reference implementation of the Cache Service to be used in the Results Cache Plugin.

Implemented API
--------------
### Version 2
* `GET /job-results/v2/{hash}`: Returns a JSON with the cached result and build number from its job hash. Returns 'NOT_BUILT' and '-1' respectively, if the job hash is not found.
* `POST /job-results/v2/{hash}`: Adds a new result, and related build number, in the cache for the provided job hash.
    - Body:
        - Content-type: application/json
        - Example: `{"result": "SUCCESS", "build": 10}`
* `DELETE /job-results/clear`: Removes all the cache values.

### Version 1 (_Deprecated API_)
* `GET /job-results/{hash}`: Returns a cached result from its job hash. Returns 'NOT_BUILT' if the job hash is not found.
* `POST /job-results/{hash}/{result}`: Adds a new result in the cache for the provided job hash.

Latest release
--------------
1.1.0 - Every cached result now also contains build number.

Version history
===============
Version 1.1.0 (July 15, 2021)
---------------------------
* Every cached result now also contains build number.
* Deprecated version 1 operations.

Version 1.0.1 (April 25, 2019)
---------------------------
* Basic implementation of the API using EHCache and SpringBoot.

How to build
------------
`./gradlew bootJar`

How to execute it
-----------------
* using gradle: `./gradlew bootRun`
* using built jar file: `java -jar ./build/libs/results-cache-service-<release>.jar`
