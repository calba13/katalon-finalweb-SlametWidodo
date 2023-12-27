package customKW

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.util.KeywordUtil

import groovy.json.JsonSlurper

public class common {



	@Keyword
	def cetak(def msg) {
		KeywordUtil.logInfo("(DO-->*) ${msg}")
	}

	@Keyword
	def defaultVar() {
		boolean isSuccess = true
		List<String> messageList = []
		return [ "isSuccess" : isSuccess, "messageList" : messageList ]
	}

	@Keyword
	def customStepReport(def messageList) {
		String rslMessage = ""

		for(int i=0; i < messageList.size(); i++) {
			rslMessage = "${rslMessage} \n${messageList[i]["message"]}"
		}
		return rslMessage
	}

	@Keyword
	def generateStepReport(boolean isSuccess, def messageList) {

		def output = customStepReport(messageList)

		if (isSuccess) {
			KeywordUtil.markPassed("${output} \n..")
		} else {
			KeywordUtil.markFailed("${output} \n..")
		}
	}

	@Keyword
	def toJSONByKey(TestData data,String key=null){
		String []columnNames = data.getColumnNames()
		JsonSlurper slurper = new JsonSlurper()

		Map dataJSON = key == null ? [:] : slurper.parseText('{}')
		for (def index : (1..data.getRowNumbers())){
			dataJSON[key == null ? index-1 : data.getValue(key, index)]=slurper.parseText('{}')
			for (def col : (1..data.getColumnNumbers())) {
				String columnName = columnNames[col-1]
				String cellValue = data.getValue(col,index).trim()
				dataJSON[key == null ? index-1 : data.getValue(key, index)][columnName]=cellValue
			}
		}
		return dataJSON
	}
}
