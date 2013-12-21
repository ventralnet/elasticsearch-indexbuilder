elasticsearch-indexbuilder
==========================

IndexBuilder java API for ElasticSearch

Code Sample
-----------

The idea is to have a builder API to create elastic search indexes. This
provides compile time validation of your indexes intitialization code. The
api will look something like this

```java
IndexBuilder builder = new IndexBuilder(client).setIndexName("my_index")
                  .setNumberOfShards(30)
                  .setNumberOfReplicas(5)
                  .addMapping(new LongIndexField("my_field",true,true));

indexBuilder.build();
```
