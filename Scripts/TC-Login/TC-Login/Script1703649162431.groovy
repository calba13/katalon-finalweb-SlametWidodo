import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase('00 Common/Open Browser'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('00 Navigasi/Nav-User'))

//WebUI.click(findTestObject('01 Homepage/Form Login/chbox_rememberMe'))
//WebUI.click(findTestObject('01 Homepage/Form Login/btn_CREATE NEW ACCOUNT'))
not_run: WebUI.delay(2)

//WebUI.scrollToPosition(0, 450)
WebUI.setText(findTestObject('01 Homepage/Form Login/inp_username'), username)

WebUI.setText(findTestObject('01 Homepage/Form Login/inp_password'), password)

WebUI.click(findTestObject('01 Homepage/Form Login/btn_Sign In'))

