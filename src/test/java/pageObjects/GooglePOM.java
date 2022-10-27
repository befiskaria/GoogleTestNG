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

public class GooglePOM extends Base {

	@FindBy(partialLinkText = "About")
	WebElement About;
	@FindBy(xpath = "//a[@data-g-cta_text='View all our products']")
	WebElement viewAllOurProducts;
	@FindBy(xpath = "//a[@class='cookieBarButton cookieBarConsentButton']")
	WebElement okToCookieBarButton;
	@FindBy(xpath = "//*[text()='Shop Google Store']")
	WebElement Shopgooglestore;
	@FindBy(id = "//a[@mqn-autotrack-label='/product/pixel_7']")
	WebElement pixel7;
	@FindBy(xpath = "//bento-dynamic-pricing[@doc-id='_pixel_phone_7_snow_128gb_unlocked']//div[@part='price']")
	WebElement printPrice;
	@FindBy(xpath = "//*[@class=' mqn-opt--ds-mid-emphasis-grey']//a[@aria-label='Buy Pixel 7']")
	WebElement Buy;
	@FindBy(xpath = "//*[@aria-label='Lemongrass']")
	WebElement LemonGrass;
	@FindBy(xpath = "//button[@class='HuudXd ORlDpd Xbiu9']")
	WebElement Storage;
	@FindBy(xpath = "//div[@xpath='1']")
	WebElement getEstimate;
	@FindBy(xpath = "//span[@xpath='1']")
	WebElement verifyPrice;
	@FindBy(partialLinkText = "Terms")
	WebElement TermsLink;

	public GooglePOM() {

		PageFactory.initElements(driver, this);
	}

	public void clickTerms() {

		TermsLink.click();
	}

	public void clickAbout() {

		About.click();
	}

	public void verifyViewProductsTitle() {
		try {
			while (true) {
				Thread.sleep(2000);
				okToCookieBarButton.click();
			}
		} catch (Exception ignored) {
		}
		viewAllOurProducts.click();
		String actualTitle = driver.getTitle();
		String expectedTitle = "Google - About Google, Our Culture & Company News";
		Assert.assertEquals(actualTitle, expectedTitle);
	}

	public void clickViewProducts() {
		try {
			while (true) {
				Thread.sleep(2000);
				okToCookieBarButton.click();
			}
		} catch (Exception ignored) {
		}
		viewAllOurProducts.click();
	}

	public void clickShopstore() {
		Shopgooglestore.click();

	}

	public void selectPixel7() {

		pixel7.click();
	}

	public void pricePrint() throws InterruptedException {
		Thread.sleep(2000);
		String pixelPrice = printPrice.getText();
		System.out.println("The price for the pixel 7 is: " + pixelPrice);

	}

	public void clickBuy() {

		Buy.click();
	}

	public void selectColor() {

		LemonGrass.click();
	}

	public void selectStorage() {

		Storage.click();
	}

	public void clickEstimate() {

		getEstimate.click();
	}

	public void verifyEstimatePrice() {

		verifyPrice.getText();
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
