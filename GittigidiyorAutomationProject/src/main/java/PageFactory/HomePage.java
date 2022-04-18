package PageFactory;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class HomePage extends BasePage {

	private static final Logger logger = LogManager.getLogger(HomePage.class);
	static final String secondPageURL = "https://www.gittigidiyor.com/masaustu-desktop-bilgisayar?k=Bilgisayar&qm=1&sf=2";
	

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public void goToSecondPage(String secondPageURL) {
		driver.get(secondPageURL);
	}
	
	public void searchProductInSecondPage(String productName) {

		WebElement searchInputWE = find(
				By.xpath("//*[@id=\"main-header\"]/div[3]/div/div/div/div[2]/form/div/div[1]/div[2]/input"));
		WebElement findButtonWE = find(
				By.xpath("//*[@id=\"main-header\"]/div[3]/div/div/div/div[2]/form/div/div[2]/button"));

		// searchProduct
		searchInputWE.click();
		searchInputWE.sendKeys(productName);
		findButtonWE.click();
		logger.info("search product success");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		goToSecondPage(secondPageURL);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	public void selectRandomProductInSecondPage() {
		getRandomElement().click();		
	}

	public WebElement getRandomElement() {

		WebElement productsUL = find(By.xpath("//div[@data-testid='content']"));
		List<WebElement> productList = productsUL.findElements(By.xpath("//article[@data-cy= 'product-card-item']"));

		Random rand = new Random();
		int randomProductNumber = rand.nextInt(productList.size() - 1);
		logger.info("Product size: " + productList.size() + "- random value: " + randomProductNumber);
		return productList.get(randomProductNumber);
	}
}
