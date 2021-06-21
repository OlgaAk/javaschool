package io.github.olgaak.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SeleniumTest {

    private SeleniumConfig config;
    private String url = "http://localhost:8081/";

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

    //Submit search form and wait for server response 1sec
    public void searchForResult() {
        sendSearchRequest();
        waitMS(1000);
    }

    private void waitMS(long timeMillis) {
        try {
            synchronized (this.config.getDriver()) {
                this.config.getDriver().wait(timeMillis);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Fill search form with test data and submit
    private void sendSearchRequest() {
        JavascriptExecutor js = (JavascriptExecutor) config.getDriver();
        js.executeScript("document.getElementById('departure-station-input').dataset.stationid = '3'");
        js.executeScript("document.getElementById('arrival-station-input').dataset.stationid = '1'");
        js.executeScript("document.getElementById('departure-date-input').value = '2021-05-10'");
        WebElement searchButton = this.config.getDriver()
                .findElement(By.id("train-search-btn"));
        searchButton.click();
    }

    private List<WebElement> getResultRoutes() {
        WebElement searchResultContainer = this.config.getDriver()
                .findElement(By.id("train-search-result-container"));
        return searchResultContainer.findElements(By.className("train-search-result-item"));
    }

    public boolean isSearchResultDisplayed() {
        List<WebElement> resultItems = getResultRoutes();
        return resultItems.size() > 1; // first item is a template item to clone with js
    }

    public boolean isSearchResultTimeDisplayedCorrectly() {
        List<WebElement> resultItems = getResultRoutes();
        if (resultItems.size() > 1) {
            String pattern = "^\\d{2}:\\d{2}"; // departure time format like 18:32
            return resultItems.get(1).findElement(By.className("train-search-result-item-time")).getText().matches(pattern);
        }
        return false;
    }

    public void clickBuyTicketButton() {
        JavascriptExecutor js = (JavascriptExecutor) config.getDriver();
        js.executeScript("document.querySelectorAll('.buy-ticket-link')[1].click()");
        waitMS(1000);
    }

    public String getCurrantUrl() {
        return this.config.getDriver().getCurrentUrl();
    }

    //Fill login form and submit
    public void submitLoginForm() {
        String testEmail = "1@mail.ru";
        String testPassword = "000";
        JavascriptExecutor js = (JavascriptExecutor) config.getDriver();
        js.executeScript(String.format("document.getElementById('login-input-email').value ='%s'",testEmail));
        js.executeScript(String.format("document.getElementById('login-input-password').value ='%s'",testPassword));
        js.executeScript("document.getElementById('login-form-btn').click()");
        waitMS(1000);
    }

    public String getPurchasePageTitle() {
        WebElement title = this.config.getDriver()
                .findElement(By.className("main-container-home"))
                .findElement(By.tagName("h2"));
        return  title.getText();
    }
}
