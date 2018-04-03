# 9. Custom Submit Actions

In this exercise we will create a submit action that registers our profile with Adobe Campaign. The submit action will trigger an Adobe IO call to create a profile in Adobe Campaign.

# The anatomy of a custom submit action

A Submit action is a sling:Folder that includes the following: 

* addfields.jsp: This script provides the action fields that are added to the HTML file during rendition. Use this script to add hidden input parameters required during submission in the post.POST.jsp script.
* dialog.xml: This script is similar to the CQ Component dialog. It provides configuration information that the author customizes. The fields are displayed in the Submit Actions Tab in the Adaptive Form Edit dialog when you select the Submit action.
* post.POST.jsp: The Submit servlet calls this script with the data that you submit and the additional data in the previous sections. Any mention of running an action in this page implies running the post.POST.jsp script. To register the Submit action with the adaptive forms to display in the Adaptive Form Edit dialog, add these properties to the sling:Folder:

     * guideComponentType of type String and value fd/af/components/guidesubmittype
     * guideDataModel of type String that specifies the type of adaptive form for which the Submit action is applicable. xfa is supported for XFA-based adaptive forms while xsd is supported for XSD-based adaptive forms. basic is supported for adaptive forms that do not use XDP or XSD. To display the action on multiple types of adaptive forms, add the corresponding strings. Separate each string by a comma. For example, to make an action visible on XFA- and XSD-based adaptive forms, specify the values xfa and xsd respectively.
     * jcr:description of type String. The value of this property is displayed in the Submit action list in the Submit Actions Tab of the Adaptive Form Edit dialog. The OOTB actions are present in the CRX repository at the location /libs/fd/af/components/guidesubmittype.

## Setup Custom submit action

* Deploy the package [tl15-custom-submit-actions-1.0.zip](../resources/tl15-custom-submit-actions-1.0.zip) using [package manager](http://localhost:4502/crx/packmgr/index.jsp)
* Install the package

## Configure the submit action

* Navigate to [/apps/summit-2018/tl15/components/guidesubmittype/acssubmit](http://localhost:4502/crx/de/index.jsp#/apps/summit-2018/tl15/components/guidesubmittype/acssubmit) in CRX DE
* Open /apps/summit-2018/tl15/components/guidesubmittype/acssubmit/post.POST.jsp

```java
<%

    logger.log("ACS Submit Action triggered");

    // Obtain your ACS Connector Service

    // Get the form data

    // Create the profile in Adobe Campaign (Adobe IO)

    // Get the redirect parameters

    // Perform the required redirect 
    
    logger.log("ACS Submit Action done");

%>
```

Add the following code snippets to your post.POST.jsp.

### Obtain your ACS Connector Service

We wil use a wrapper service (ACSConnector) around an Adobe IO library that will create/update or profile in Adobe Campaign. A

``` java
// Obtain your ACS Connector Service
ACSConnector acsConnector = sling.getService(ACSConnector.class);

```

### Get the form data that was submitted

The FormSubmitInfo object can be retrieved from the request object. The getData() method returns the form data.

```java
// Get the form data
FormSubmitInfo submitInfo = (FormSubmitInfo) request.getAttribute(GuideConstants.FORM_SUBMIT_INFO);
logger.log("Form submit data : " + submitInfo.getData());
```

### Create the ACS profile from the form data

The ACS Connector's createProfile method is a wrapper around an Adobe IO library which exposes REST methods as plain java calls.

```java
// Create the profile in Adobe Campaign (Adobe IO)
String acsResponse = acsConnector.createProfile(submitInfo.getData());
logger.log("acsResponse : " + acsResponse);
```

The snippet below show the implementation of the ACSConnector's createProfile method:

```java
@Component(immediate = true)
@Service(value = ACSConnector.class)
@Properties({
	 @Property(name = Constants.SERVICE_VENDOR, value = "Adobe Belux Presales"),
	 @Property(name = Constants.SERVICE_DESCRIPTION, value = "Summit 2018 - TL15 - ACS Connector")
	})
public class ACSConnectorImpl implements ACSConnector {
	
	private static final Logger logger = LoggerFactory.getLogger(ACSConnector.class);
	
	@Reference
	private AdobeIORequestProcessor adobeIORequestProcessor;

	public String createProfile(String formdata) throws Exception {
		
		String profileResponse = adobeIORequestProcessor.createUpdateProfileByLabMachineLabel(formdata);
		
		logger.info("ACS profileResponse : " + profileResponse);
		
		return "profileResponse";
		
	}
```

### Retrieve the redirect parameters

```java
// Get the redirect parameters
Map<String,String> redirectParameters;
redirectParameters = GuideSubmitUtils.getRedirectParameters(slingRequest);
if(redirectParameters==null) {
	redirectParameters = new HashMap<String, String>();
}
```

### Peform the required redirect

```java
// Perform the required redirect 
GuideSubmitUtils.setRedirectParameters(slingRequest,redirectParameters);
```
