import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

'STEP : Click Navigation User'
WebUI.click(findTestObject('00 Navigasi/Nav-User'))

'STEP : Verify Element Button CREATE NEW ACCOUNT'
WebUI.verifyElementText(findTestObject('01 Homepage/Form Login/btn_CREATE NEW ACCOUNT'), 'CREATE NEW ACCOUNT')

'STEP : Capture'
WebUI.takeScreenshot()

