import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

'STEP : Click Nav Cart'
WebUI.click(findTestObject('00 Navigasi/Nav-Cart'))

'STEP : Verify Cart List'
CustomKeywords.'finalProjectKW.searchFeature.verifyCart'()

'STEP : Evidence'
WebUI.takeScreenshot()