package mk.ukim.finki.wp.lab.selenium;

import lombok.Getter;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class CoursePage extends AbstractPage {

    @FindBy(css = "tr[class=courses]")
    private List<WebElement> productRows;


    @FindBy(css = ".delete-course")
    private List<WebElement> deleteButtons;


    @FindBy(className = "edit-course")
    private List<WebElement> editButtons;


    @FindBy(css = ".add-course-btn")
    private List<WebElement> addProductButton;

    public CoursePage(WebDriver driver) {
        super(driver);
    }

    public static CoursePage to(WebDriver driver) {
        get(driver, "/products");
        return PageFactory.initElements(driver, CoursePage.class);
    }

    public void assertElemts(int courseNumber, int deleteButtons, int editButtons, int addButtons) {
        Assert.assertEquals("rows do not match", courseNumber, this.getProductRows().size());
        Assert.assertEquals("delete do not match", deleteButtons, this.getDeleteButtons().size());
        Assert.assertEquals("edit do not match", editButtons, this.getEditButtons().size());
        Assert.assertEquals("add is visible", addButtons, this.getAddProductButton().size());
    }
}

