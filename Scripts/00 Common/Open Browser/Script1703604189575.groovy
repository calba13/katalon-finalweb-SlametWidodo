import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

'STEP : Open Browser + Set URL'
WebUI.openBrowser(GlobalVariable.BASEURL)

'STEP : MaxsizeWindows'
not_run: WebUI.maximizeWindow()

