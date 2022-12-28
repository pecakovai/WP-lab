package mk.ukim.finki.wp.lab.selenium;

import mk.ukim.finki.wp.lab.model.enumerations.Type;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class AddOrEditCourse extends AbstractPage {

    private WebElement name;
    private WebElement description;
    private WebElement type;
    private WebElement submit;

    public AddOrEditCourse(WebDriver driver) {
        super(driver);
    }

    public static CoursePage addCourse(WebDriver driver, String name, String description, Type type) {
        get(driver, "/course/add-form");
        AddOrEditCourse addOrEditCourse = PageFactory.initElements(driver, AddOrEditCourse.class);
        addOrEditCourse.name.sendKeys(name);
        addOrEditCourse.description.sendKeys(description);
        addOrEditCourse.type.click();
        addOrEditCourse.type.findElement(By.xpath("//option[. = '" + type + "']")).click();

        addOrEditCourse.submit.click();
        return PageFactory.initElements(driver, CoursePage.class);
    }

    public static CoursePage editCourse(WebDriver driver, WebElement editButton, String name, String description, Type type) {
        editButton.click();
        System.out.println(driver.getCurrentUrl());
        AddOrEditCourse addOrEditCourse = PageFactory.initElements(driver, AddOrEditCourse.class);
        addOrEditCourse.name.sendKeys(name);
        addOrEditCourse.description.sendKeys(description);
        addOrEditCourse.type.click();
        addOrEditCourse.type.findElement(By.xpath("//option[. = '" + type + "']")).click();

        addOrEditCourse.submit.click();
        return PageFactory.initElements(driver, CoursePage.class);
    }


}

