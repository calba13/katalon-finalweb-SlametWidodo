package finalProjectKW

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static org.assertj.core.api.Assertions.*

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

public class searchFeature {

	@Keyword
	def typeKeywordWithDuration(String keywordSearch) {

		for (char c : keywordSearch.toCharArray()) {
			WebUI.sendKeys(findTestObject('00 Navigasi/subNavbar-Search/inp_SearchKeyword'), String.valueOf(c))
			Thread.sleep(100) // Penundaan 100 milidetik (0.1 detik)
		}
	}


	@Keyword
	def vefikasiTop6Product() {
		def defaultVar = new customKW.common().defaultVar();
		String tcTitle = "vefikasi List Top 6 Product Search"

		try {

			WebUI.delay(GlobalVariable.TIMEOUT_Loading)

			// new customKW.common().cetak("check point");
			WebDriver driver = DriverFactory.getWebDriver()


			/* Ambil Data Top 6 Search untuk di jadikan Expected
			 * --------------------------------------------------------------------------------------------------------------------------------------*/	
			WebElement ListProduct = driver.findElement(By.xpath("//div[@id='searchSection']/div[@id='output']/div/div[@class='top6Products']"))
			List<WebElement> productItems = ListProduct.findElements(By.xpath("//a[@class='product ng-scope']/p"))
			int totalList = productItems.size()
			// new customKW.common().cetak("Total Product ada  : ${rows_count}");

			List<String> dataExpected = []
			for (int i = 0; i < totalList; i++) {
				dataExpected << productItems.get(i).getText()
			}

			/* end
			 * --------------------------------------------------------------------------------------------------------------------------------------*/


			WebUI.click(findTestObject('00 Navigasi/subNavbar-Search/link_View_All'))
			WebUI.click(findTestObject('00 Navigasi/subNavbar-Search/btn_Close Input Search'))



			/* Ambil Data dari hasil Search 
			 * --------------------------------------------------------------------------------------------------------------------------------------*/
			WebElement listProductONScreenSearch = driver.findElement(By.xpath("//article[@id='searchPage']/div[@class='noPromotedProductDiv']/*/*/div[@class='cell categoryRight']/*/li"))
			List<WebElement> productItemsONScreenSearch = listProductONScreenSearch.findElements(By.xpath("//p/a[@class='productName ng-binding']"))
			int TotalData = productItemsONScreenSearch.size()

			List<String> dataResultSearch = []
			for (int i = 0; i < TotalData; i++) {
				dataResultSearch << productItemsONScreenSearch.get(i).getText()
			}

			/* end
			 * --------------------------------------------------------------------------------------------------------------------------------------*/
			//new customKW.common().cetak("dataResultSearch : ${dataResultSearch}");

			String total = WebUI.getText(findTestObject('Object Repository/02 Search/lbl_Total Product yang di temukan'))
			String[] stringArray = total.split(" ")
			int expectedTotal = Integer.parseInt(stringArray[0])


			// VERIFIKASI : List Product
			for (String valueToCheck : dataExpected) {

				boolean found = dataResultSearch.any { it.equalsIgnoreCase(valueToCheck) }

				// Output hasil pemeriksaan
				if (found) {
					
					defaultVar["messageList"] << ["message" : "TestCase : ${tcTitle}"]
					defaultVar["messageList"] << ["message" : "ProductName : ${valueToCheck}, ada di dalam Screen Search"]
					defaultVar["messageList"] << ["message" : "QC STATUS : GOOD"]
					defaultVar["messageList"] << ["message" : ""]
					
				} else {
					
					defaultVar["isSuccess"] = false;
					defaultVar["messageList"] << ["message" : "TestCase : ${tcTitle}"]
					defaultVar["messageList"] << ["message" : "ProductName : ${valueToCheck}, Tidak ada di dalam Screen Search"]
					defaultVar["messageList"] << ["message" : "QC STATUS : NOT GOOD"]
					defaultVar["messageList"] << ["message" : ""]
				}
			}

			// VERIFIKASI : Total Data yang di dapat
			assertThat(TotalData).isEqualTo(expectedTotal)

			//new customKW.common().cetak("total : ${total}");
			//new customKW.common().cetak("expectedTotal : ${stringArray[0]}");
			//new customKW.common().cetak("Product data  : ${dataExpected}");
			//new customKW.common().cetak("Product dataResultSearch  : ${dataResultSearch}");
			
		} catch (Exception e) {

			defaultVar["isSuccess"] = false;
			defaultVar["messageList"] << ["message" : "TestCase : ${tcTitle}"]
			defaultVar["messageList"] << ["message" : "Remark : Error Exception_ ${e}"]
			defaultVar["messageList"] << ["message" : "QC STATUS : NOT GOOD"]
			defaultVar["messageList"] << ["message" : ""]
		}

		new customKW.common().cetak("defaultVar : ${defaultVar}");
		new customKW.common().generateStepReport(defaultVar["isSuccess"], defaultVar["messageList"])
	}
	
	
	
	@Keyword
	def addItemProduct(productName, quantity) {
		def defaultVar = new customKW.common().defaultVar();
		String tcTitle = "add Item Product to Cart"

		try {

			WebUI.click(findTestObject('00 Navigasi/subNavbar-Search/link_View_All'))
			WebUI.click(findTestObject('00 Navigasi/subNavbar-Search/btn_Close Input Search'))

			WebDriver driver = DriverFactory.getWebDriver()
			WebElement listProductONScreenSearch = driver.findElement(By.xpath("//article[@id='searchPage']/div[@class='noPromotedProductDiv']/*/*/div[@class='cell categoryRight']/*/li"))
			List<WebElement> productItemsONScreenSearch = listProductONScreenSearch.findElements(By.xpath("//p/a[@class='productName ng-binding']"))
			int TotalData = productItemsONScreenSearch.size()
			
			/* SELECT PRODUCT HANDLING */
			for (int i = 0; i < TotalData; i++) {
				boolean found = assertThat(productName.equalsIgnoreCase(productItemsONScreenSearch.get(i).getText()))
				if(found) {
					productItemsONScreenSearch.get(i).click()
				}
			}
			
			/* ADD QUANTITY HANDLING */ 
			if(quantity != 1 && quantity > 0) {
				for(int i=1; i < quantity; i++) {
					WebUI.click(findTestObject('Object Repository/03 Product Detail/btn_Plus_Quantity'))
				}
			}
			
			WebUI.click(findTestObject('Object Repository/03 Product Detail/btn_ADD TO CART'))
			
			
			defaultVar["messageList"] << ["message" : "TestCase : ${tcTitle}"]
			defaultVar["messageList"] << ["message" : "ProductName : ${productName}"]
			defaultVar["messageList"] << ["message" : "quantity : ${quantity}"]
			defaultVar["messageList"] << ["message" : "QC STATUS : GOOD"]
			defaultVar["messageList"] << ["message" : ""]
			//new customKW.common().cetak("dataResultSearch : ${dataResultSearch}");

		} catch (Exception e) {

			defaultVar["isSuccess"] = false;
			defaultVar["messageList"] << ["message" : "TestCase : ${tcTitle}"]
			defaultVar["messageList"] << ["message" : "Remark : Error Exception_ ${e}"]
			defaultVar["messageList"] << ["message" : "QC STATUS : NOT GOOD"]
			defaultVar["messageList"] << ["message" : ""]
		}
	}
}
