# 10. AEM Forms Prefill

You can prefill the fields of an Adaptive form using existing data. When a user opens a form, the values for those fields are prefilled. You can achieve this with adaptive forms with or without a form template, or using XML or JSON schema as the form model. To accomplish this, the user data should be available as a prefill XML / JSON in a specific format that adheres to adaptive forms.

You can use a dataRef url parameter to point the form to the prefill data. This url will be used by the forms prefill service to load the data and fill the corresponding fields. The dataRef parameter allows you to specify different protocols:

* crx://     : refers to a node in the CRX repository, the specified node must have a property called jcr:data
* file://    : refers to a file on the server
* http:// 
* service:// : refers to the name of the OSGI prefill service

In this lab we will use yet another option to prefill a form by setting the "data" attribute in "slingRequest". In our example the form is integrated into our site. It is the page component that will set the "data" attribute. As part of the page url we specify the "lab machine label" parameter. This parameter will be used to do a callout to Adobe Campaign to retrieve the profile information. Remember we have created a profile in Adobe Campaign as part of the [Custom Submit Action exercise 9](../exercise9/README.md). And we did setup a "prefil" page on our site using the "Lab TL15 Prefill Content Page" while the others pages were created using the "Lab TL15 Content Page".

As part of this exercise we will 

* create a component that will fetch the Adobe Campaign profile data based upon the "lab machine label"
* embed the component in our "Lab TL15 Prefill Content Page"


## The prefill component

The prefill component identifies the "Lab Machine Label" from page url parameter "lml" and uses it to fetch the campaign profile using the ACSConnector service 

```java
String prefillResponse = acsConnector.getProfileByLabMachineLabel(labmachinelabel, ACSConnector.OUTPUT_FORMS);
```
The full code is shown below:

```java
<%--

  Prefill By Machine Label component.

--%>
<%--

  Form Prefill component.

    // This component creates an attribute on the slingRequest. The form prefill service 
    // will look for such an attribute, and the form will get prefilled.
    // This component shoud be added to the page header script 
    // for example :
    //
    // <sly data-sly-include="/apps/summit-2018/tl15/components/prefillbymachinelabel/prefillbymachinelabel.jsp" />


--%>
<%
%><%@include file="/libs/foundation/global.jsp"%><%
%><%@page session="false" import="be.adobe.presales.summit.lab.tl15.ACSConnector" %><%
%><%

	String labmachinelabel = request.getParameter("lml");

    System.out.println("fecthing prefill data for labmachinelabel : " + labmachinelabel);

    ACSConnector acsConnector = sling.getService(ACSConnector.class);

    String prefillResponse = acsConnector.getProfileByLabMachineLabel(labmachinelabel, ACSConnector.OUTPUT_FORMS);

    System.out.println("prefillResponse afData : " + prefillResponse);

    slingRequest.setAttribute("data", prefillResponse);


%>
```
