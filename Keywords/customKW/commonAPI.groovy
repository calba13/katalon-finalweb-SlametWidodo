package customKW

import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import groovy.json.JsonSlurper

public class commonAPI {

	@Keyword
	def execute_API(RequestObject request) {
		def defaultVar = new customKW.common().defaultVar();
		String tcTitle = "execute_API & Verification Code "

		try {

			if (request instanceof RequestObject) {
				RequestObject requestObj = (RequestObject) request
				ResponseObject response  = WS.sendRequest(requestObj)

				/* PROSES VERIFIKASI STATUS RESPONSE */
				WS.verifyResponseStatusCode(response, 200)
				assertThat(response.getStatusCode()).isIn(Arrays.asList(200, 201, 202))

				/* Track STEP */
				defaultVar["messageList"] << ["message" : "TestCase : ${tcTitle}"]
				defaultVar["messageList"] << ["message" : "QC STATUS : GOOD"]
				defaultVar["messageList"] << ["message" : ""]

				JsonSlurper jsonSlurper = new JsonSlurper()
				return jsonSlurper.parseText(response.getResponseText())
			} else {
				defaultVar["messageList"] << ["message" : "TestCase : ${tcTitle} | Others Case"]
			}
		} catch (Exception e) {

			defaultVar["isSuccess"] = false;
			defaultVar["messageList"] << ["message" : "TestCase : ${tcTitle}"]
			defaultVar["messageList"] << ["message" : "Remark : Error Exception_ ${e}"]
			defaultVar["messageList"] << ["message" : "QC STATUS : NOT GOOD"]
			defaultVar["messageList"] << ["message" : ""]
		}

		new customKW.common().cetak("defaultVar : ${defaultVar}");
	}
}
