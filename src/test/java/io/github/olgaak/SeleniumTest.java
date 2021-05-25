package io.github.olgaak;

import io.github.olgaak.config.SeleniumConfig;

public class SeleniumTest {

    private SeleniumConfig config;
    private String url = "http://localhost:8080/";

    public SeleniumTest(){
        config = new SeleniumConfig();
        config.getDriver().getTitle();
    }

}
