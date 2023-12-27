import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

//'STEP : Open Browser + Set URL'
//WebUI.callTestCase(findTestCase('00 Common/Open Browser'), [:], FailureHandling.STOP_ON_FAILURE)

'STEP : Click Navigation User'
WebUI.click(findTestObject('00 Navigasi/Nav-User'))

'STEP : Input Username'
WebUI.setText(findTestObject('01 Homepage/Form Login/inp_username'), username)

'STEP : Input Password'
WebUI.setText(findTestObject('01 Homepage/Form Login/inp_password'), password)

'STEP : Click Sign In'
WebUI.click(findTestObject('01 Homepage/Form Login/btn_Sign In'))

