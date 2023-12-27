package finalProjectKW

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.RequestObject
import static org.assertj.core.api.Assertions.*
public class apiTodosFeature {

	@Keyword
	def getTodosbyID(RequestObject request, id, title, completed) {
		def defaultVar = new customKW.common().defaultVar();
		String tcTitle = "Execute API getTodosbyID, Verify Response Code "

		try {

			def jsonResponse = new customKW.commonAPI().execute_API(request)

			/* VERIFY DATA RESPONSE*/
			assertThat(jsonResponse.id).isEqualTo(id)
			assertThat(jsonResponse.title).isEqualTo(title)
			assertThat(jsonResponse.completed).isEqualTo(completed)

			/* Track STEP */
			defaultVar["messageList"] << ["message" : "TestCase : ${tcTitle}"]
			defaultVar["messageList"] << ["message" : "title : ${jsonResponse.title}"]
			defaultVar["messageList"] << ["message" : "completed : ${jsonResponse.completed}"]
			defaultVar["messageList"] << ["message" : "QC STATUS : GOOD"]
			defaultVar["messageList"] << ["message" : "RESPONSE : ${jsonResponse}"]
			defaultVar["messageList"] << ["message" : ""]

			// new customKW.common().cetak("jsonResponse : ${jsonResponse}");
		} catch (Exception e) {

			defaultVar["isSuccess"] = false;
			defaultVar["messageList"] << ["message" : "TestCase : ${tcTitle}"]
			defaultVar["messageList"] << ["message" : "Remark : Error Exception_ ${e}"]
			defaultVar["messageList"] << ["message" : "QC STATUS : NOT GOOD"]
			defaultVar["messageList"] << ["message" : ""]
		}

		new customKW.common().cetak("defaultVar : ${defaultVar}");
		new customKW.common().generateStepReport(defaultVar["isSuccess"], defaultVar["messageList"])
	}


	@Keyword
	def patchTodosbyID(RequestObject request, id, title, completed) {
		def defaultVar = new customKW.common().defaultVar();
		String tcTitle = "Execute API patchTodosbyID, Verify Response Code "

		try {

			def jsonResponse = new customKW.commonAPI().execute_API(request)

			/* VERIFY DATA RESPONSE*/
			assertThat(jsonResponse.id).isEqualTo(id)
			assertThat(jsonResponse.title).isEqualTo(title)
			assertThat(jsonResponse.completed).isEqualTo(completed)

			/* Track STEP */
			defaultVar["messageList"] << ["message" : "TestCase : ${tcTitle}"]
			defaultVar["messageList"] << ["message" : "title : ${jsonResponse.title}"]
			defaultVar["messageList"] << ["message" : "completed : ${jsonResponse.completed}"]
			defaultVar["messageList"] << ["message" : "QC STATUS : GOOD"]
			defaultVar["messageList"] << ["message" : "RESPONSE : ${jsonResponse}"]
			defaultVar["messageList"] << ["message" : ""]

			// new customKW.common().cetak("jsonResponse : ${jsonResponse}");
		} catch (Exception e) {

			defaultVar["isSuccess"] = false;
			defaultVar["messageList"] << ["message" : "TestCase : ${tcTitle}"]
			defaultVar["messageList"] << ["message" : "Remark : Error Exception_ ${e}"]
			defaultVar["messageList"] << ["message" : "QC STATUS : NOT GOOD"]
			defaultVar["messageList"] << ["message" : ""]
		}

		new customKW.common().cetak("defaultVar : ${defaultVar}");
		new customKW.common().generateStepReport(defaultVar["isSuccess"], defaultVar["messageList"])
	}
}
