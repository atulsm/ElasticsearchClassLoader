# ElasticsearchClassLoader
The purpose of this project is to demonstrate how an application can work with both elasticsearch 1.x and 2.x client jars

# Approach

* MainProject:
MainClass.java  (which executes business logic)
CustomClassLoader (which loads specific jar ahead of parent classloader)

* ESCommon:
ElasticSearchInterface

* ESv1:
ElasticSearchV1 implements BaseInterface

* ESv2:
ElasticSearchV2 implements BaseInterface

* command-line:
java -classpath ESCommon.jar MainClass
//Do not put ESv1 nor ESv1 into the parent classloader classpath

* MainClass Logic:

loader1 = new CustomClassLoader(new URL[] {new File("ESv1.jar").toURL()}, Thread.currentThread().getContextClassLoader());
loader2 = new CustomClassLoader(new URL[] {new File("ESv2.jar").toURL()}, Thread.currentThread().getContextClassLoader());


Class<?> c1 = loader1.loadClass("com.atul.esloader.ElasticSearchV1");
Class<?> c2 = loader2.loadClass("com.atul.esloader.ElasticSearchV2");


ElasticSearchInterface es1 = (ElasticSearchInterface) c1.newInstance();
ElasticSearchInterface es2 = (ElasticSearchInterface) c2.newInstance();


es1.search() // Queries ElasticSearch1.x
es2.search() // Queries ElasticSearch2.x
