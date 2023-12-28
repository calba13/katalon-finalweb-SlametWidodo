import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

'STEP : Check Cart'
WebUI.click(findTestObject('00 Navigasi/Nav-Cart'))

'STEP : Select & ADD Product'
CustomKeywords.'finalProjectKW.searchFeature.verifyCart'()

'STEP : Evidence'
WebUI.takeScreenshot()