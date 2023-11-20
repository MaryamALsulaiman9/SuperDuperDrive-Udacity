package com.udacity.jwdnd.course1.cloudstorage;


import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@RequiredArgsConstructor
public class LoginPage {

    @FindBy(id = "inputUsername")
    private WebElement inputUserName;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "buttonLogin")
    private WebElement loginButton;

    public LoginPage(WebDriver webDriver){
        PageFactory.initElements(webDriver, this);
    }

    public void login(String username, String password){
        this.inputUserName.sendKeys(username);
        this.inputPassword.sendKeys(password);
        this.loginButton.click();
    }
}
