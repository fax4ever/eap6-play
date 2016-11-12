package it.redhat.demo.funct;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.archive.importer.MavenImporter;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by fabio on 11/11/16.
 */

@RunWith(Arquillian.class)
public class WebTest {

    @Deployment(testable = false)
    public static WebArchive createDeployment() {

        WebArchive war = ShrinkWrap.create(MavenImporter.class).loadPomFromFile("pom.xml").importBuildOutput()
                .as(WebArchive.class);

        System.out.println(war);

        return war;

    }

    @Test
    @RunAsClient
    public void test() {

    }

}
