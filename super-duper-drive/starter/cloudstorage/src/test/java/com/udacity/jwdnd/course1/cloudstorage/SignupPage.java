package com.udacity.jwdnd.course1.cloudstorage;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@RequiredArgsConstructor
public class SignupPage {
    @FindBy(id = "inputFirstName")
    private WebElement inputFirstName;

    @FindBy(id = "inputLastName")
    private WebElement inputLastName;

    @FindBy(id = "inputUsername")
    private WebElement inputUserName;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "buttonSignUp")
    private WebElement signupButton;

    public SignupPage(WebDriver webDriver){

        PageFactory.initElements(webDriver, this);
    }

    public void signup(String firstName, String lastName, String username, String password){
        this.inputFirstName.sendKeys(firstName);
        this.inputLastName.sendKeys(lastName);
        this.inputUserName.sendKeys(username);
        this.inputPassword.sendKeys(password);
        this.signupButton.click();
    }
}
