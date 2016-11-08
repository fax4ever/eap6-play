package it.redhat.demo.integration;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

public class BaseIT {

	@Deployment
	public static JavaArchive jar() {
		
		return ShrinkWrap.create(JavaArchive.class)
			.addPackages(true, "it.redhat.demo")
			.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
		
	}

	public BaseIT() {
		super();
	}

}