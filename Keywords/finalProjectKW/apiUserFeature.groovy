package finalProjectKW

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import static org.assertj.core.api.Assertions.*
import internal.GlobalVariable

public class apiUserFeature {

	@Keyword
	def getListUsers(RequestObject request) {
		def defaultVar = new customKW.common().defaultVar();
		String tcTitle = "Execute API ListUsers, Verify Response Code "

		try {

			def jsonResponse = new customKW.commonAPI().execute_API(request)

			/* Track STEP */
			defaultVar["messageList"] << ["message" : "TestCase : ${tcTitle}"]
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
	def getUserbyID(RequestObject request, id, name, username, email, street, suite, city, zipcode, geo_lat, geo_lng, phone, website, compname, catchPhrase, compbs) {
		def defaultVar = new customKW.common().defaultVar();
		String tcTitle = "Execute API UserbyID, Verify Response Code "

		try {

			def jsonResponse = new customKW.commonAPI().execute_API(request)

			/* VERIFY DATA RESPONSE*/
			assertThat(jsonResponse.id).isEqualTo(id)
			assertThat(jsonResponse.name).isEqualTo(name)
			assertThat(jsonResponse.username).isEqualTo(username)
			assertThat(jsonResponse.email).isEqualTo(email)
			assertThat(jsonResponse.address.street).isEqualTo(street)
			assertThat(jsonResponse.address.suite).isEqualTo(suite)
			assertThat(jsonResponse.address.city).isEqualTo(city)
			assertThat(jsonResponse.address.zipcode).isEqualTo(zipcode)
			assertThat(jsonResponse.address.geo.lat).isEqualTo(geo_lat)
			assertThat(jsonResponse.address.geo.lng).isEqualTo(geo_lng)
			assertThat(jsonResponse.phone).isEqualTo(phone)
			assertThat(jsonResponse.website).isEqualTo(website)
			assertThat(jsonResponse.company.name).isEqualTo(compname)
			assertThat(jsonResponse.company.catchPhrase).isEqualTo(catchPhrase)
			assertThat(jsonResponse.company.bs).isEqualTo(compbs)

			/* Track STEP */
			defaultVar["messageList"] << ["message" : "TestCase : ${tcTitle}"]
			defaultVar["messageList"] << ["message" : "Name : ${jsonResponse.name}"]
			defaultVar["messageList"] << ["message" : "Email : ${jsonResponse.email}"]
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
	def patchUserbyID(RequestObject request, id, name, email) {
		def defaultVar = new customKW.common().defaultVar();
		String tcTitle = "Execute API patchUserbyID, Verify Response Code "

		try {

			def jsonResponse = new customKW.commonAPI().execute_API(request)

			/* VERIFY DATA RESPONSE*/
			assertThat(jsonResponse.id).isEqualTo(id)
			assertThat(jsonResponse.name).isEqualTo(name)
			assertThat(jsonResponse.email).isEqualTo(email)

			/* Track STEP */
			defaultVar["messageList"] << ["message" : "TestCase : ${tcTitle}"]
			defaultVar["messageList"] << ["message" : "Name : ${jsonResponse.name}"]
			defaultVar["messageList"] << ["message" : "Email : ${jsonResponse.email}"]
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
