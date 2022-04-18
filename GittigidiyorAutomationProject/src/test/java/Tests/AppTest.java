package Tests;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

import PageFactory.BasketPage;
import PageFactory.HomePage;
import PageFactory.ProductPage;

public class AppTest extends BaseTest {

	static final String secondPageURL = "https://www.gittigidiyor.com/masaustu-desktop-bilgisayar?k=Bilgisayar&qm=1&sf=2";

	private static final Logger logger = LogManager.getLogger(HomePage.class);

	public AppTest() {
		// TODO Auto-generated constructor stub
	}

	@Test
	public void searchAndAddProduct() {
		HomePage homePage = new HomePage(driver);
		ProductPage productPage = new ProductPage(driver);
		BasketPage basketPage = new BasketPage(driver);

		homePage.searchProductInSecondPage("Bilgisayar");

		assertEquals(driver.getCurrentUrl(), secondPageURL);
		homePage.selectRandomProductInSecondPage();

		productPage.saveProductInfo();

		productPage.addToBasket();

		productPage.switchToBasketPage();

		assertEquals(getProductPriceFromFile("ProductInfo.txt"), basketPage.getFirstProductPrice());

		assertEquals(basketPage.increaseProductCount(), "2");
		
		basketPage.deleteFirstProductFromBasket();
		
		assertEquals(basketPage.checkEmptyBasket(), "Sepetinizde ürün bulunmamaktadır.");
	}

	public String getProductPriceFromFile(String filePath) {

		String productName = null;
		String productPrice = null;

		try {
			FileReader readerProductInfo = new FileReader(filePath);
			BufferedReader br = new BufferedReader(readerProductInfo);

			productName = br.readLine();
			productPrice = br.readLine();

			readerProductInfo.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return productPrice;
	}

}
