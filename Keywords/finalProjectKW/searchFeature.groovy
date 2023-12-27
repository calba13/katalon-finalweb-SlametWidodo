package finalProjectKW

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static org.assertj.core.api.Assertions.*

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

public class searchFeature {

	@Keyword
	def vefikasiTop6Product() {
		def defaultVar = new customKW.common().defaultVar();
		String tcTitle = "vefikasi List Top 6 Product Search"

		try {

			new customKW.common().cetak("check point");
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
			List<WebElement> productItemsONScreenSearch = ListProduct.findElements(By.xpath("//p/a[@class='productName ng-binding']"))
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
					new customKW.common().cetak("Nilai ada dalam array : ${valueToCheck}");
					defaultVar["messageList"] << ["message" : "TestCase : ${tcTitle}"]
					defaultVar["messageList"] << ["message" : "ProductName : ${valueToCheck}, ada di dalam Screen Search"]
					defaultVar["messageList"] << ["message" : "QC STATUS : GOOD"]
					defaultVar["messageList"] << ["message" : ""]
					
				} else {
					new customKW.common().cetak("${valueToCheck} - Nilai tidak ada dalam array : ");
					
					defaultVar["isSuccess"] = false;
					defaultVar["messageList"] << ["message" : "TestCase : ${tcTitle}"]
					defaultVar["messageList"] << ["message" : "ProductName : ${valueToCheck}, Tidak ada di dalam Screen Search"]
					defaultVar["messageList"] << ["message" : "QC STATUS : NOT GOOD"]
					defaultVar["messageList"] << ["message" : ""]
					
				}
			}
			
			
			
			// VERIFIKASI : Total Data yang di dapat  
			assertThat(TotalData).isEqualTo(expectedTotal)
			

			new customKW.common().cetak("total : ${total}");
			new customKW.common().cetak("expectedTotal : ${stringArray[0]}");

			new customKW.common().cetak("Product data  : ${dataExpected}");
			new customKW.common().cetak("Product dataResultSearch  : ${dataResultSearch}");
			
		} catch (Exception e) {

			defaultVar["isSuccess"] = false;
			defaultVar["messageList"] << ["message" : "TestCase : ${tcTitle}"]
			defaultVar["messageList"] << ["message" : "Remark : Error Exception_ ${e}"]
			defaultVar["messageList"] << ["message" : "QC STATUS : NOT GOOD"]
			defaultVar["messageList"] << ["message" : ""]
		}

		//		new customKW.common().cetak("defaultVar : ${defaultVar}");
		//		new customKW.common().generateStepReport(defaultVar["isSuccess"], defaultVar["messageList"])
	}
}
