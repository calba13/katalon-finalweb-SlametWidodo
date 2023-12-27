import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

'STEP : Click Navigation User'
WebUI.click(findTestObject('00 Navigasi/Nav-User'))

'STEP : Verify Element Sub Nav is Ready'
WebUI.verifyElementAttributeValue(findTestObject('00 Navigasi/subNavbar-User/div_subNavbar_Login'), 'style', 'display: block;', 5)

'STEP : Click Log Out'
WebUI.click(findTestObject('00 Navigasi/subNavbar-User/btn_Logout'))

