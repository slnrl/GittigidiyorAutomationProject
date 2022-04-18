package PageFactory;


import java.io.FileWriter;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;



public class ProductPage extends BasePage {
	public ProductPage(WebDriver driver) {
        super(driver);
    }
	
	JavascriptExecutor js = (JavascriptExecutor) driver;
	
	public void saveProductInfo() {
		
		WebElement productNameWE = find(By.id("sp-title"));
		WebElement productPriceWE = find(By.id("sp-price-lowPrice"));
		
		writeProductInfoToFile(productNameWE.getText(),productPriceWE.getText());
		
	}
	
	public static void writeProductInfoToFile(String productName, String productPrice) {
		
		try {
			FileWriter writerProductInfo = new FileWriter("ProductInfo.txt");
			
			writerProductInfo.write(productName);
			writerProductInfo.write("\n");
			writerProductInfo.write(productPrice);
			writerProductInfo.write("\n");
			
			writerProductInfo.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void addToBasket() {
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"add-to-basket\"]")));
		WebElement addToBasketWE = find(By.xpath("//*[@id=\"add-to-basket\"]"));
		WebElement productGeneralInfoWE = find(By.xpath("//*[@id=\"product-general-info\"]"));
		js.executeScript("arguments[0].scrollIntoView(false);", productGeneralInfoWE);		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
		addToBasketWE.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
	}
	
	public void switchToBasketPage(){
		WebElement basketButtonWE = find(By.xpath("//*[@id=\"header_wrapper\"]/div[4]/div[3]"));		
		
		Actions action = new Actions(driver);		
		action.moveToElement(basketButtonWE).click().build().perform();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
	}
}
