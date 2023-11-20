package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@RequiredArgsConstructor
public class CredentialPage {
    @FindBy(id = "nav-credentials-tab")
    private WebElement navCredentialsTab;
    @FindBy(id = "add-new-credential")
    private WebElement addNewCredentialButton;
    @FindBy(id = "edit-credential")
    private WebElement editCredentialButton;
    @FindBy(id = "delete-credential")
    private WebElement deleteCredentialButton;
    @FindBy(className = "get-credential-url")
    private WebElement getCredentialUrl;
    @FindBy(id = "get-credential-username")
    private WebElement getCredentialUsername;
    @FindBy(id = "get-credential-password")
    private WebElement getCredentialPassword;
    @FindBy(id = "credentialUrl")
    private WebElement credentialUrlField;
    @FindBy(id = "credentialUsername")
    private WebElement credentialUsernameField;
    @FindBy(id = "credentialPassword")
    private WebElement credentialPasswordField;
    @FindBy(id = "credentialSaveButton")
    private WebElement credentialSaveButton;
    private final WebDriverWait wait;
    public CredentialPage(WebDriver webDriver){
        PageFactory.initElements(webDriver, this);
        this.wait=new WebDriverWait(webDriver,20);
    }

    public void navCredentialsTab(){
        this.navCredentialsTab.click();
    }
    public void addNewCredentialButton(){
        this.addNewCredentialButton=wait.until(ExpectedConditions.elementToBeClickable(By.id("add-new-credential")));
        this.addNewCredentialButton.click();
    }
    public void createCredential(String url, String username, String password){
        this.credentialUrlField= wait.until(ExpectedConditions.elementToBeClickable(By.id("credentialUrl")));
        this.credentialUrlField.sendKeys(url);
        this.credentialUsernameField.sendKeys(username);
        this.credentialPasswordField.sendKeys(password);
        this.credentialSaveButton.click();
    }
    public Credential getCredential(){
        this.getCredentialUrl=wait.until(ExpectedConditions.elementToBeClickable(By.className("get-credential-url")));
        return new Credential(this.getCredentialUrl.getText(), this.getCredentialUsername.getText(), this.getCredentialPassword.getText());
    }
    public String verifyPassword(){
        this.credentialPasswordField=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credentialPassword")));
        return this.credentialPasswordField.getAttribute("value");
    }
    public void editCredentialButton(){
        this.editCredentialButton=wait.until(ExpectedConditions.elementToBeClickable(By.id("edit-credential")));
        this.editCredentialButton.click();
    }
    public void editCredentialUrl(String url){
        this.credentialUrlField=wait.until(ExpectedConditions.elementToBeClickable(By.id("credentialUrl")));
        this.credentialUrlField.clear();
        this.credentialUrlField.sendKeys(url);
    }

    public void editCredentialUsername(String username){
        this.credentialUsernameField=wait.until(ExpectedConditions.elementToBeClickable(By.id("credentialUsername")));
        this.credentialUsernameField.clear();
        this.credentialUsernameField.sendKeys(username);
    }
    public void editCredentialPassword(String password){
        this.credentialPasswordField=wait.until(ExpectedConditions.elementToBeClickable(By.id("credentialPassword")));
        this.credentialPasswordField.clear();
        this.credentialPasswordField.sendKeys(password);
    }
    public void credentialSaveButton(){
        this.credentialSaveButton.click();
    }
    public void deleteCredentialButton(){
        this.deleteCredentialButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-credentials-tab")));
        this.deleteCredentialButton.click();
    }
    public boolean findCredential(WebDriver webDriver){
        try {
            webDriver.findElement(By.className("get-credential-url"));
            webDriver.findElement(By.id("get-cCredentialUsername"));
            webDriver.findElement(By.id("get-credential-password"));
            return true;
        }

        catch (NoSuchElementException e){
            return false;
        }
    }
}
