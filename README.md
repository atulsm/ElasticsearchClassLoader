# ElasticsearchClassLoader
The purpose of this project is to demonstrate how an application can work with both elasticsearch 1.x and 2.x client jars

# Approach

* MainProject:
  * MainClass.java  (which executes business logic)

* ESCommon:
  * ElasticSearchInterface

* ESv1:
  * ElasticSearchV1 implements ElasticSearchInterface

* ESv2:
  * ElasticSearchV2 implements ElasticSearchInterface

* How to execute the class:
  * java -cp MainProject.jar;ESCommon.jar MainClass
  * //Do not put ESv1 or ESv2 into the parent classloader classpath

* MainClass Logic:

      	public static void main(String[] args) throws Exception {
    	    ClassLoader loader1 = new URLClassLoader(new URL[] {new File("..\\ESv1\\target\\ESv1-0.0.1-SNAPSHOT.jar").toURL()});
    	    ClassLoader loader2 = new URLClassLoader(new URL[] {new File("..\\ESv2\\target\\ESv2-0.0.1-SNAPSHOT.jar").toURL()});
    
    	    Class<?> c1 = loader1.loadClass("com.atul.esloader.ElasticSearchV1");
    	    Class<?> c2 = loader2.loadClass("com.atul.esloader.ElasticSearchV2");
    
    	    ElasticSearchInterface es1 = (ElasticSearchInterface) c1.newInstance();
    	    ElasticSearchInterface es2 = (ElasticSearchInterface) c2.newInstance();
    
    	    es1.search(null); // Queries ElasticSearch1.x
    	    es2.search(null); // Queries ElasticSearch2.x
    	}

# Output

    <ElasticsearchClassLoader\MainProject> java -cp ..\ESCommon\target\ESCommon-0.0.1-SNAPSHOT.jar;target\MainProject-0.0.1-SNAPSHOT.jar com.atul.esloader.MainClass
    Connected to elasticsearch v1
    Got 8 hits
    
    Connected to elasticsearch v2
    Got 4 hits