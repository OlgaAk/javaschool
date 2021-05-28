package io.github.olgaak.selenium;

import org.junit.*;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
    public void test1_whenHomepageIsLoaded_thenTitleIs(){
        String actualTitle = seleniumTest.getTitle();
        Assert.assertNotNull(actualTitle);
        Assert.assertEquals(expectedTitle, actualTitle);
    }

    @Test
    public void test2_whenTrainSearched_thenSearchResultAppears(){
        seleniumTest.searchForResult();
        Assert.assertTrue(seleniumTest.isSearchResultDisplayed());
        Assert.assertTrue(seleniumTest.isSearchResultTimeDisplayedCorrectly());
    }

    @Test
    public void test3_whenBuyTicketBtnClicked_thenRedirectToLogin(){
        String expectedLoginUrl = "http://localhost:8080/user/login";
//        seleniumTest.searchForResult();
        seleniumTest.clickBuyTicketButton();
        Assert.assertEquals( expectedLoginUrl, seleniumTest.getCurrantUrl());
    }

    @Test
    public void test4_whenLoginSubmitted_thenRedirectToPurchase(){
//        seleniumTest.searchForResult();
//        seleniumTest.clickBuyTicketButton();
        seleniumTest.submitLoginForm();
        String expectedPurchaseUrl = "http://localhost:8080/user/purchase";
        System.out.println(seleniumTest.getCurrantUrl());
        Assert.assertTrue(seleniumTest.getCurrantUrl().contains(expectedPurchaseUrl) );
    }



}
