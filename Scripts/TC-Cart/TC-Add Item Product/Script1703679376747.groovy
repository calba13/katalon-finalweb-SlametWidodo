import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

'STEP : Open Browser + Set URL'
WebUI.callTestCase(findTestCase('00 Common/Open Browser'), [:], FailureHandling.STOP_ON_FAILURE)

'STEP : LOGIN'
WebUI.callTestCase(findTestCase('TC-Login/TC-Login'), [('username') : GlobalVariable.USERNAME, ('password') : GlobalVariable.PASSWORD], 
    FailureHandling.STOP_ON_FAILURE)

'STEP : Select & ADD Product'
CustomKeywords.'finalProjectKW.searchFeature.addItemProduct'(keywordCategory, productName, quantity)

