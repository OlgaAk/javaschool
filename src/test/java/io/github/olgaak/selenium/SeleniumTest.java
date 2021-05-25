package io.github.olgaak.selenium;

import io.github.olgaak.selenium.SeleniumConfig;

public class SeleniumTest {

    private SeleniumConfig config;
    private String url = "http://localhost:8080/";

    public SeleniumTest(){
        config = new SeleniumConfig();
        config.getDriver().get(url);
    }

    public void closeWindow(){
        this.config.getDriver().close();
    }

    public String getTitle(){
        return this.config.getDriver().getTitle();
    }
}
