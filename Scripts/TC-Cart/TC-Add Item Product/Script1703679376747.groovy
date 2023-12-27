import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

'STEP : Click Navigation Search & Input Keyword'
WebUI.callTestCase(findTestCase('TC-Search/TC-Search Item'), [('keywordSearch') : keywordCategory], FailureHandling.STOP_ON_FAILURE)

'STEP : Select & ADD Product'
CustomKeywords.'finalProjectKW.searchFeature.addItemProduct'(productName, quantity)

'STEP : Check Cart'
WebUI.click(findTestObject('00 Navigasi/Nav-Cart'))

'STEP : Evidence'
WebUI.takeScreenshot()
