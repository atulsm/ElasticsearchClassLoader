package com.atul.esloader;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class MainClass {

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

}
