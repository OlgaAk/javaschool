package io.github.olgaak.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SeleniumTest {

    private SeleniumConfig config;
    private String url = "http://localhost:8080/";

    public SeleniumTest() {
        config = new SeleniumConfig();
        config.getDriver().get(url);
    }

    public void closeWindow() {
        this.config.getDriver().close();
    }

    public String getTitle() {
        return this.config.getDriver().getTitle();
    }

    public void searchForResult() {
        sendSearchRequest();
        try {
            synchronized (this.config.getDriver()) {
                this.config.getDriver().wait(10000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sendSearchRequest() {
//        WebElement departureStationInput = this.config.getDriver()
//                .findElement(By.id("departure-station-input"));
//        departureStationInput.sendKeys("Moscow" +Keys.ENTER);
//        WebElement arrivalStationInput = this.config.getDriver()
//                .findElement(By.id("arrival-station-input"));
//        arrivalStationInput.sendKeys("Saint Petersburg" + Keys.ENTER);
//        WebElement departureDateInput = this.config.getDriver()
//                .findElement(By.id("departure-date-input"));
//        departureDateInput.sendKeys("2021-05-10");
        JavascriptExecutor js = (JavascriptExecutor) config.getDriver();
        js.executeScript("document.getElementById('departure-station-input').dataset.stationid = '3'");
        js.executeScript("document.getElementById('arrival-station-input').dataset.stationid = '1'");
        js.executeScript("document.getElementById('departure-date-input').value = '2021-05-10'");
        WebElement searchButton = this.config.getDriver()
                .findElement(By.id("train-search-btn"));
        searchButton.click();
    }

    public boolean isSearchResultDisplayed() {
        WebElement searchResultContainer = this.config.getDriver()
                .findElement(By.id("train-search-result-container"));
        List<WebElement> resultItems = searchResultContainer.findElements(By.className("train-search-result-item"));
        return resultItems.size() > 1;
    }
}
