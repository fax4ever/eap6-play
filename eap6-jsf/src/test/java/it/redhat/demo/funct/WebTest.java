package it.redhat.demo.funct;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.archive.importer.MavenImporter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.net.URL;

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

    @ArquillianResource
    private URL deploymentUrl;

    @Drone
    private WebDriver browser;

    @Test
    @RunAsClient
    public void test() {

        System.out.println(deploymentUrl);
        System.out.println(browser);

        browser.get(deploymentUrl.toExternalForm() + "person.jsf");

        WebElement pageButton = browser.findElement(By.id("form:page"));

        pageButton.click();

        WebElement redColorElement = browser.findElement(By.id("form:color:0"));
        WebElement okElement = browser.findElement(By.id("form:ok"));
        WebElement ageElement = browser.findElement(By.id("form:age"));
        WebElement personButton = browser.findElement(By.id("form:person"));

        redColorElement.click();
        okElement.click();
        ageElement.sendKeys("37");
        personButton.click();

        WebElement previewColor = browser.findElement(By.id("preview-color"));
        WebElement previewOk = browser.findElement(By.id("preview-ok"));
        WebElement previewAge = browser.findElement(By.id("preview-age"));

        String previewColorText = previewColor.getText();
        String previewOkText = previewOk.getText();
        String previewAgeText = previewAge.getText();

        System.out.println(previewColorText);
        System.out.println(previewOkText);
        System.out.println(previewAgeText);

        Assert.assertEquals("Red", previewColorText);
        Assert.assertEquals("true", previewOkText);
        Assert.assertEquals("37", previewAgeText);

    }

}
