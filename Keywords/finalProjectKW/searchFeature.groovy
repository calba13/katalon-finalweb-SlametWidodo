package finalProjectKW

import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static org.assertj.core.api.Assertions.*

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
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
			new customKW.common().cetak("Total Product ada  : ${totalList}");

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
			new customKW.common().cetak("TotalData : ${TotalData}");
			
			List<String> dataResultSearch = []
			for (int i = 0; i < TotalData; i++) {
				dataResultSearch << productItemsONScreenSearch.get(i).getText()
			}

			/* end
			 * --------------------------------------------------------------------------------------------------------------------------------------*/
			new customKW.common().cetak("dataResultSearch : ${dataResultSearch}");
			new customKW.common().cetak("dataExpected : ${dataExpected}");

			String total = WebUI.getText(findTestObject('Object Repository/02 Search/lbl_Total Product yang di temukan'))
			String[] stringArray = total.split(" ")
			int expectedTotal = Integer.parseInt(stringArray[0])
			
			new customKW.common().cetak("dataResultSearch : ${dataResultSearch}");

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
	def addItemProduct(String keywordCategory, String productName, String qty) {
		def defaultVar = new customKW.common().defaultVar();
		String tcTitle = "add Item Product to Cart"

		try {

			'STEP : Click Navigation Search & Input Keyword'
			WebUI.callTestCase(findTestCase('TC-Search/TC-Search Item'), [('keywordSearch') : keywordCategory], FailureHandling.STOP_ON_FAILURE)

			WebUI.click(findTestObject('00 Navigasi/subNavbar-Search/link_View_All'))
			WebUI.click(findTestObject('00 Navigasi/subNavbar-Search/btn_Close Input Search'))
		
			WebDriver driver = DriverFactory.getWebDriver()
			WebElement listProductONScreenSearch = driver.findElement(By.xpath("//div[@class='cell categoryRight']/ul/li"))
			List<WebElement> productItemsONScreenSearch = listProductONScreenSearch.findElements(By.xpath("//p/a[@class='productName ng-binding']"))
			int TotalData = productItemsONScreenSearch.size()
			new customKW.common().cetak("TotalData : ${TotalData}");
			
			'SELECT PRODUCT - HANDLING'
			for (int i = 0; i < TotalData; i++) {
				
				new customKW.common().cetak("dataResultSearchAtas : ${productName} | ${productItemsONScreenSearch.get(i).getText()}");
				
				if (productName.equalsIgnoreCase(productItemsONScreenSearch.get(i).getText())) {
					new customKW.common().cetak("dataResultSearchDalem : ${productItemsONScreenSearch.get(i).getText()}");
					productItemsONScreenSearch.get(i).click()
				}
			}

			
			'ADD QUANTITY - HANDLING'
//			WebUI.waitForElementVisible(findTestObject('Object Repository/03 Product Detail/btn_Plus_Quantity'), 10)
			int quantity = Integer.parseInt(qty)
			if(quantity != 1 && quantity > 0) {
				for(int i=1; i < quantity; i++) {
					new customKW.common().cetak("quantity : ${quantity}");
					WebUI.click(findTestObject('Object Repository/03 Product Detail/btn_Plus_Quantity'))
				}
			}

			// WebUI.delay(GlobalVariable.TIMEOUT_Loading)
			WebUI.click(findTestObject('Object Repository/03 Product Detail/btn_ADD TO CART'))
			
			WebUI.takeScreenshot()
			
			WebUI.click(findTestObject('Object Repository/01 Homepage/LOGO'))
			WebUI.delay(GlobalVariable.TIMEOUT_Loading)
			
			defaultVar["messageList"] << ["message" : "TestCase : ${tcTitle}"]
			defaultVar["messageList"] << ["message" : "DataBinding : ${productName} | qty : ${qty}"]
			defaultVar["messageList"] << ["message" : "QC STATUS : GOOD"]
			defaultVar["messageList"] << ["message" : ""]
			
			
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
	def verifyCart() {
		def defaultVar = new customKW.common().defaultVar();
		String tcTitle = "Verify Product on Cart"

		try {

			'SETUP TestObeject Table'
			WebDriver driver = DriverFactory.getWebDriver()
			WebElement Table = driver.findElement(By.xpath("//div[@id='shoppingCart']/table/tbody"))
			List<WebElement> rows_table = Table.findElements(By.tagName('tr'))
			int rows_count = rows_table.size()
			'-------------------------------------------------------------------------------------------'

			'BACA DATA BINDING'
			def allTestData = new customKW.common().toJSONByKey(findTestData('Data Files/cartDatas'))
			'-------------------------------------------------------------------------------------------'

			// new customKW.common().cetak("allTestData ${allTestData}");
			for (int i = 0; i < allTestData.size(); i++) {
				'Baca Table per Row'
				for (int row = 0; row < rows_count; row++) {

					'To locate columns(cells) of that specific row'
					List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName('td'))

					'Verify Quantity DataBinding VS Table Value'
					boolean found = assertThat(allTestData[i].productName.equalsIgnoreCase(Columns_row.get(1).getText()))
					if(found) {
						'Verify Quantity DataBinding VS Table Value'
						assertThat(allTestData[i].quantity.equals(Integer.parseInt(Columns_row.get(4).getText())))

						'JIKA salah 1 Data Binding ada yangtidak Sesuai, maka Semua FLow dianggap NG'
						if (!defaultVar["isSuccess"]) {
							defaultVar["isSuccess"] = false;
						}

						defaultVar["messageList"] << ["message" : "TestCase : ${tcTitle}"]
						defaultVar["messageList"] << ["message" : "ProductName : ${Columns_row.get(1).getText()} | expected : ${allTestData[i].productName} "]
						defaultVar["messageList"] << ["message" : "quantity : ${Columns_row.get(4).getText()} | expected : ${allTestData[i].quantity} "]
						defaultVar["messageList"] << ["message" : "QC STATUS : GOOD"]
						defaultVar["messageList"] << ["message" : ""]
					} else {

						defaultVar["isSuccess"] = false;
						defaultVar["messageList"] << ["message" : "TestCase : ${tcTitle}"]
						defaultVar["messageList"] << ["message" : "ProductName : ${Columns_row.get(1).getText()} | expected : ${allTestData[i].productName} "]
						defaultVar["messageList"] << ["message" : "quantity : ${Columns_row.get(4).getText()} | expected : ${allTestData[i].quantity} "]
						defaultVar["messageList"] << ["message" : "QC STATUS : NOT GOOD"]
						defaultVar["messageList"] << ["message" : ""]
					}
				}
			}
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
}
