# 10. Prefill AEM Forms 

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

## Setup the prefill component

* Deploy the package [packages/tl15-prefill-component-1.0.zip](../packages/tl15-prefill-component-1.0.zip) using [package manager](http://localhost:4502/crx/packmgr/index.jsp)
* Install the package


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

Open [/apps/summit-2018/tl15/components/prefillbymachinelabel/prefillbymachinelabel.jsp](http://localhost:4502/crx/de/index.jsp#/apps/summit-2018/tl15/components/prefillbymachinelabel/prefillbymachinelabel.jsp) in CRX DE and past the code above.

## Modify the page component

We need to modify the "Lab TL15 Prefill Content Page"'s component to invoke the prefill component. As you can see the prefillpage components [/apps/lab-tl15/components/structure/prefillpage](http://localhost:4502/crx/de/index.jsp#/apps/lab-tl15/components/structure/prefillpage) extends [/apps/lab-tl15/components/structure/page](http://localhost:4502/crx/de/index.jsp#/apps/lab-tl15/components/structure/page) and just overrides the "headlibs.html"

Open [/apps/lab-tl15/components/structure/prefillpage/partials/headlibs.html](http://localhost:4502/crx/de/index.jsp#/apps/lab-tl15/components/structure/prefillpage/partials/headlibs.html) in CRX DE and add the following line:

```java
<sly data-sly-include="/apps/summit-2018/tl15/components/prefillbymachinelabel/prefillbymachinelabel.jsp" />
```

So that the code looks like:

```java
<!--/*
    Copyright 2016 Adobe Systems Incorporated

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/-->

<!--/* Include the site client libraries (loading only the CSS in the header, JS will be loaded in the footer) */-->
<sly data-sly-use.wcmInit="wcm/foundation/components/page/initwcm.js" data-sly-use.clientlib="/libs/granite/sightly/templates/clientlib.html">
    <sly data-sly-call="${clientlib.css @ categories=pageProperties['bw-theme'] || 'lab-tl15.site'}"/>
    <sly data-sly-call="${clientLib.css @ categories=wcmInit.templateCategories}" />
</sly>

<!--/* Initialize the Authoring UI */-->
<sly data-sly-include="author.html" />

<!--/* Initialize Cloud Services */-->
<sly data-sly-include="/libs/cq/cloudserviceconfigs/components/servicelibs/servicelibs.jsp" />

<sly data-sly-include="/apps/summit-2018/tl15/components/prefillbymachinelabel/prefillbymachinelabel.jsp" />

<script src="https://use.typekit.net/xml3nlr.js"></script>
<script>try{Typekit.load({ async: true });}catch(e){}</script>
```

## Enable form prefilling

* Navigate to [http://localhost:4502/aem/forms.html/content/dam/formsanddocuments/summit-2018](http://localhost:4502/aem/forms.html/content/dam/formsanddocuments/summit-2018)
* Open the form you created as part of [exercise 2](../exercise2/README.md) in edit mode
* Open the configuration panel for "Adaptive Form container"
* Select "Form Data Model Prefill service" from the "Prefill Service" dropdown
* Save your changes

## Add the form to your prefill page

* Open [http://localhost:4502/editor.html/content/lab-tl15/language-masters/en/prefill.html](http://localhost:4502/editor.html/content/lab-tl15/language-masters/en/prefill.html) in edit mode
* Edit the form component (added as part of [exercise 1](../exercise1/README.md))
* Select your form edited in the previous step and that has been created as part of [exercise 2](../exercise2/README.md)
* Select "Forms covers entire width of the page"

## Test the prefill

* Open [http://localhost:4502/editor.html/content/lab-tl15/language-masters/en/prefill.html](http://localhost:4502/editor.html/content/lab-tl15/language-masters/en/prefill.html) in preview mode
* Add ?lml=CSXXX-XX to you page URL, replace CSXXX-XX with your "Lab Machine Label" or the label that you used to identify your profile in Adobe Campaign

You should see the form prefilled with data, you can change and submit your data again.



