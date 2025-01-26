This microservice

* receives API calls that are routed from gateway service
* receives API calls that are already Authenticated
* does not receive Authorization Header in any API calls since it is removed from Gateway
* receives user context via Headers and converts them to Thread Local which can be accessed from any API
* User Context Thread Local is set before API call reaches Controller and is unset after it leaves Controller
* User Context is different for every API call
* has Flyway which will run on boot
* has a config which can disable flyway from running during start-up