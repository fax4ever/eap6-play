package it.redhat.demo.integration;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class BikeIT {
	
	@Deployment
	public static JavaArchive jar() {
		
		return ShrinkWrap.create(JavaArchive.class)
			.addPackages(true, "it.redhat.demo")
			.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
		
	}
	
	@Test
	public void go() {
		
	}

}
