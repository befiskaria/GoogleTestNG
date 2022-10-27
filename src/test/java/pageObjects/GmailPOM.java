package pageObjects;

import java.util.ArrayList;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utils.Base;

public class GmailPOM extends Base {

	@FindBy(partialLinkText = "Learn more")
	WebElement LearnMore;
	@FindBy(xpath = "Terms")
	WebElement TermsLink;

	public GmailPOM() {

		PageFactory.initElements(driver, this);
	}

	public void clickTerms() {

		TermsLink.click();
	}

	public void verifyLearnMorePageTitle() {
		String actualTitle = driver.getTitle();
		String expectedTitle = "Browse Chrome as a guest - Computer - Google Chrome Help";
		Assert.assertEquals(actualTitle, expectedTitle);
	}

	public void clickLearnMore() {

		LearnMore.click();
	}

	public void SwitchToNewTab() {
		ArrayList<String> allTabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(allTabs.get(1));
	}

	public void SwitchToOldTab() {
		ArrayList<String> Tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(Tabs.get(0));
	}

	public void pressEnter() {
		Actions action;
		action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
	}

	public void pressTab() {
		Actions action;
		action = new Actions(driver);
		action.sendKeys(Keys.TAB).build().perform();
	}

	public void refresh(WebDriver driver) {
		driver.navigate().refresh();
	}
}
