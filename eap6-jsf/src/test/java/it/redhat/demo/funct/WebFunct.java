package it.redhat.demo.funct;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.archive.importer.MavenImporter;
import org.junit.runner.RunWith;

/**
 * Created by fabio on 11/11/16.
 */

@RunWith(Arquillian.class)
public class WebFunct {

    @Deployment
    public static WebArchive createDeployment() {

        return ShrinkWrap.create(MavenImporter.class).loadPomFromFile("pom.xml").importBuildOutput()
                .as(WebArchive.class);

    }

}
