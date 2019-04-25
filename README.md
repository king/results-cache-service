Results Cache Service
===================================
Reference implementation of the Cache Service to be used in the Results Cache Plugin.

Implemented API
--------------

* GET /job-results/{hash}: Returns a cached result from its job hash. Returns 'NOT_BUILT' if the job hash is not found.
* POST /job-results/{hash}/{result}: Adds a new result in the cache for the provided job hash
* REMOVE /job-results/clear: Removes all the cache values

Latest release
--------------
1.0.1 - Basic implementation of the API using EHCache and SpringBoot

How to build
------------
`./gradlew bootJar`

How to execute it
-----------------
* using gradle: `./gradlew bootRun`
* using built jar file: `java -jar ./build/libs/results-cache-service-<release>.jar`
