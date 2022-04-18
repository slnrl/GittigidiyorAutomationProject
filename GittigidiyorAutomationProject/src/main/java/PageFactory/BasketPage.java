package PageFactory;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class BasketPage extends BasePage {
	public BasketPage(WebDriver driver) {
        super(driver);
    }
	
	static Integer firstProductCount = 0;
	static Integer lastProductCount = 0;
	
	public String getFirstProductPrice() {
		
		WebElement productPriceWE = find(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/form[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[6]/div[3]/div[2]/div[1]/div[5]/div[1]/div[2]"));
		return productPriceWE.getText();
	}
	
	public String increaseProductCount(){
		WebElement productCountWE = find(By.xpath("//select[@class='amount']"));
		firstProductCount = Integer.valueOf(productCountWE.getAttribute("value"));
		//productCountWE.click();
		firstProductCount++;
		productCountWE.sendKeys(firstProductCount.toString());
		lastProductCount = Integer.valueOf(productCountWE.getAttribute("value"));
		return lastProductCount.toString();
	}
	
	public void deleteFirstProductFromBasket() {
		WebElement deleteButtontWE = find(By.className("btn-delete"));
		deleteButtontWE.click();
		
	}
	
	public String checkEmptyBasket() {
		WebElement messageOfBasketWE = find(By.xpath("//*[@id=\"empty-cart-container\"]/div[1]/div[1]/div/div[2]/h2"));
		return messageOfBasketWE.getText();
	}
}
