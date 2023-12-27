import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

'STEP : Click Navigation Search'
WebUI.click(findTestObject('00 Navigasi/Nav-Search'))

'STEP : Input Keyword'
WebUI.sendKeys(findTestObject('00 Navigasi/subNavbar-Search/inp_SearchKeyword'), 'mice')

