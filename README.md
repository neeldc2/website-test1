This microservice

* receives API calls that are routed from gateway service
* receives API calls that are already Authenticated
* does not receive Authorization Header in any API calls since it is removed from Gateway
* receives user context via Headers and converts them to Thread Local which can be accessed from any API
* User Context Thread Local is set before API call reaches Controller and is unset after it leaves Controller
* User Context is different for every API call
* has Flyway which will run on boot
* has a config which can disable flyway from running during start-up

There are many configs in this service that helps in connecting to the right database for the tenant.
These configs are explained below

1. DataSourceConfig and DataSourceRouting class.
* Using the above classes, to add a tenant, a restart will be required.
* To enable this, uncomment the code in DataSourceConfig class and in DataSourceRouting.
* In DataSourceConfig class, the routing was not done for tenantId, it was done for userId.

2. TenantAwareRoutingDataSource and TenantTransactionManagerConfig class.
* Using above classes, a restart won't be needed if a new tenant is added, unlike DataSourceRouting/DataSourceConfig
  class implementation.
* Con with this approach is that it needs a default database to connect to, which is not a tenant database.
* It also uses DataSourceManager class to store DataSources.
* To enable this, uncomment the code in DataSourceManager, TenantAwareRoutingDataSource and
  TenantTransactionManagerConfig classes.

3. One attempt was using CurrentTenantIdentifierResolverImpl and DatabasePerTenantConfig classes.
   I was not successful in implementing using these classes.


4. The implementation which I have liked as of now is using DynamicMultiTenantConnectionProvider and
   DynamicMultiTenantConnectionProviderConfig classes.
* It also uses TenantDataSourceRegistry class to store DataSources.
* It uses CurrentTenantIdentifierResolverImpl2 class to identify the tenant info
