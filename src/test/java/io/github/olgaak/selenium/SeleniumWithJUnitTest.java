package io.github.olgaak.selenium;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class SeleniumWithJUnitTest {

    private static SeleniumTest seleniumTest;
    private String expectedTitle = "Home";

    @BeforeClass
    public static void setup(){
        seleniumTest = new SeleniumTest();
    }

    @AfterClass
    public static void tearDown(){
        seleniumTest.closeWindow();
    }

    @Test
    public void whenHomepageIsLoaded_thenTitleIs(){
        String actualTitle = seleniumTest.getTitle();
        Assert.assertNotNull(actualTitle);
        Assert.assertEquals(expectedTitle, actualTitle);
    }

    @Test
    public void whenTrainSearched_thenSearchResultAppears(){
        seleniumTest.searchForResult();
        Assert.assertTrue(seleniumTest.isSearchResultDisplayed());
    }
}
