package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
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
public class NotePage {
    @FindBy(id = "nav-notes-tab")
    private WebElement navNoteTab;
    @FindBy(id = "add-note")
    private WebElement addNote;
    @FindBy(id = "noteTitle")
    private WebElement noteTitle;
    @FindBy(id = "noteDescription")
    private WebElement noteDescription;
    @FindBy(id = "noteSave")
    private WebElement noteSave;
    @FindBy(className = "get-note-description")
    private WebElement getNoteDescription;
    @FindBy(className = "get-note-title")
    private WebElement getNoteTitle;
    @FindBy(id = "editNote")
    private WebElement editNoteButton;
    @FindBy(id = "deleteNote")
    private WebElement deleteNote;

    private final WebDriverWait wait;

    public NotePage(WebDriver webDriver){
        PageFactory.initElements(webDriver, this);
        this.wait = new WebDriverWait(webDriver,20);
    }
    public void NavNoteTab(){
        this.navNoteTab.click();
    }
    public void addNewNoteButton(){
        this.addNote=wait.until(ExpectedConditions.elementToBeClickable(By.id("add-note")));
        this.addNote.click();
    }
    public void createNote(String noteTitle, String noteDescription){
        this.noteTitle=wait.until(ExpectedConditions.elementToBeClickable(By.id("noteTitle")));
        this.noteTitle.sendKeys(noteTitle);
        this.noteDescription.sendKeys(noteDescription);
        this.noteSave.click();
    }

    public Note getNote(){
        this.getNoteTitle=wait.until(ExpectedConditions.elementToBeClickable(By.className("get-note-title")));
        return new Note(this.getNoteTitle.getText(), this.getNoteDescription.getText());
    }

    public void editNoteTitle(String noteTitle){
        this.noteTitle=wait.until(ExpectedConditions.elementToBeClickable(By.id("noteTitle")));
        this.noteTitle.clear();
        this.noteTitle.sendKeys(noteTitle);
    }
    public void editNoteDescription(String noteDescription){
        this.noteDescription.clear();
        this.noteDescription.sendKeys(noteDescription);
    }
    public void editNoteButton(){
        this.editNoteButton=wait.until(ExpectedConditions.elementToBeClickable(By.id("editNote")));
        this.editNoteButton.click();
    }
    public void deleteNote(){
        this.editNoteButton=wait.until(ExpectedConditions.elementToBeClickable(By.id("deleteNote")));
        this.deleteNote.click();
    }
    public void noteSaveChanges(){
        this.noteSave.click();
    }

    public boolean findNote(WebDriver webDriver){
        try{
            webDriver.findElement(By.className("get-note-title"));
            webDriver.findElement(By.className("get-note-description"));
            return true;
        }
        catch (NoSuchElementException e){
            return false;
        }
    }
}
