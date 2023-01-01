## PCC CarRental

A Platform for car booking and rental.

## What functionalities does PCC CarRental have？

* New user generation
* Create and manage your Car Models
* Create and manage your Rental Transaction

## Architecture of PCC CarRental

PCC CarRental is a monolithic application based on Spring Boot. Referencing the theory of DDD, a four-layered code arrange (controller/service/manager/repository) is designed.



## How to Use

Make sure JDK 11 was installed in your machine and a local database (e.g. pcc_carrental) has been set up. Run the following commands to pull PCC CarRental and build it locally:

```
git clone https://github.com/EricLiuCoder/pcc-carrental.git
cd pcc-carrental
./mvnw clean install -DskipTests
```

If you try to run unit tests, a database named testbed should be created in advance.

## APIs

Nested APIs description will be found at http://{host:port}/swagger-ui/index.html


## Feedback

If you have any question or feedback, please find me by the following methods

* email: yifanliu#hotmail.com, replace#with@