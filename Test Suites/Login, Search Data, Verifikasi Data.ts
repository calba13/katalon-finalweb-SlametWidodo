<?xml version="1.0" encoding="UTF-8"?>
<TestSuiteEntity>
   <description></description>
   <name>Login, Search Data, Verifikasi Data</name>
   <tag></tag>
   <isRerun>false</isRerun>
   <mailRecipient></mailRecipient>
   <numberOfRerun>3</numberOfRerun>
   <pageLoadTimeout>30</pageLoadTimeout>
   <pageLoadTimeoutDefault>true</pageLoadTimeoutDefault>
   <rerunFailedTestCasesOnly>false</rerunFailedTestCasesOnly>
   <rerunImmediately>true</rerunImmediately>
   <testSuiteGuid>0dec829a-4bd7-45ba-952c-f0534bfc6284</testSuiteGuid>
   <testCaseLink>
      <guid>8514d327-8f3f-4ba9-954a-bd3c7ea61029</guid>
      <isReuseDriver>false</isReuseDriver>
      <isRun>true</isRun>
      <testCaseId>Test Cases/00 Common/Open Browser</testCaseId>
      <usingDataBindingAtTestSuiteLevel>true</usingDataBindingAtTestSuiteLevel>
   </testCaseLink>
   <testCaseLink>
      <guid>64bab570-dd15-48e6-9ea8-4ab663eec196</guid>
      <isReuseDriver>false</isReuseDriver>
      <isRun>true</isRun>
      <testCaseId>Test Cases/TC-Login/TC-Login</testCaseId>
      <usingDataBindingAtTestSuiteLevel>true</usingDataBindingAtTestSuiteLevel>
      <variableLink>
         <testDataLinkId></testDataLinkId>
         <type>DEFAULT</type>
         <value></value>
         <variableId>cc9d38da-eaec-4328-b81d-9723559f37d7</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId></testDataLinkId>
         <type>DEFAULT</type>
         <value></value>
         <variableId>613eb99d-01a2-454b-91dd-23e4def58140</variableId>
      </variableLink>
   </testCaseLink>
   <testCaseLink>
      <guid>c6ed980a-bce6-45c7-9f8b-6c9f7104636b</guid>
      <isReuseDriver>false</isReuseDriver>
      <isRun>true</isRun>
      <testCaseId>Test Cases/TC-Search/TC-Search Item</testCaseId>
      <testDataLink>
         <combinationType>ONE</combinationType>
         <id>edd8153b-704a-4c82-b4eb-7e03040ffc23</id>
         <iterationEntity>
            <iterationType>RANGE</iterationType>
            <value>1-1</value>
         </iterationEntity>
         <testDataId>Data Files/cartDatas</testDataId>
      </testDataLink>
      <usingDataBindingAtTestSuiteLevel>true</usingDataBindingAtTestSuiteLevel>
      <variableLink>
         <testDataLinkId>edd8153b-704a-4c82-b4eb-7e03040ffc23</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>categoryName</value>
         <variableId>4e7be0e7-7de4-4fef-8e65-1d7799a3c2e8</variableId>
      </variableLink>
   </testCaseLink>
   <testCaseLink>
      <guid>d1f4c58d-9e34-4bc1-bfc5-0b142a519aba</guid>
      <isReuseDriver>false</isReuseDriver>
      <isRun>true</isRun>
      <testCaseId>Test Cases/TC-Search/TC-Verifikasi Item Search</testCaseId>
      <usingDataBindingAtTestSuiteLevel>true</usingDataBindingAtTestSuiteLevel>
   </testCaseLink>
</TestSuiteEntity>
