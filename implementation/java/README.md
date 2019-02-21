# Vanilla Spring boot

[Bootstrapped](https://start.spring.io)

* POJO generator from [swagger-codegen](https://github.com/swagger-api/swagger-codegen/tree/master/modules/swagger-codegen-maven-plugin)
* JPA power to create DB schema.
* http://modelmapper.org

* Build ```mvn clean install```
* Run ```mvn spring-boot:run``` from inside vehicle-api

* ```http POST :8080/vehicle plate=139AWD```
* ```http :8080/vehicles```
* ```http post :8080/vehicle/<ID_VEHICLE>/location lat=19.433333 lon=-99.133333```
