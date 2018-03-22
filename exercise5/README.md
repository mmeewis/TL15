# AEM Forms portal custom draft/submit behavior

As mentioned in [exercise 4](exercise4/README.md), the default forms portal draft/submit is to store data in the CRX repository. However, as users interact with forms through AEM publish instance, which is generally outside the enterprise firewall, organizations may want to customize data storage for it to be more secure and reliable.

AEM forms portal components provides data services that allow you to customize the implementation of storing user data for drafts and submissions. For example, you can store the data in a data store currently implemented in your organization.

To customize the storage of user data, you need to implement the Draft Data and Submission Data services.

## Documentation

* https://helpx.adobe.com/aem-forms/6/custom-draft-submission-data-services.html
* Javadoc DratftDataService : https://helpx.adobe.com/aem-forms/6/javadocs/com/adobe/fd/fp/service/DraftDataService.html

## Custom DraftDataService

For this lab, we wil use a DraftDataService that will store saved forms data on the file system instead of the CRX repository. Our enterprise backend is a filesystem ;). In reality this will be a more sophisticated enterprise system.

We will not start to code the custom data service "FileDraftDataServiceImpl", the purpose of this exercise is to deploy and configure the custom service.

* Explore the [FileDraftDataServiceImpl.java](resources/FileDraftDataServiceImpl.java) source code.

## Deploy the DraftDataService bundle

* Download the bundle from github and deploy it via http://localhost:4502/system/console, make sure the bundle is started

## Configure the "Forms Portal Draft and Submission Configuration"

* Open http://localhost:4502/system/console/configMgr and look for "Forms Portal Draft and Submission Configuration"
* The default value is : com.adobe.fd.fp.service.impl.DraftDataServiceImpl
* Set "Forms Portal Draft Data Service" to summit.tl15formsportal.file.draft.dataservice. This corresponds with the value for "aem.formsportal.impl.prop" set in the OSGi service "FileDraftDataServiceImpl" and save the configuration. In this lab we will only focus on the "drafts". To handle your "submits", you have to do implement a SubmitDataService.

## Fill and save the form

* Access the form via the "Form" page defined in Exercise 3.
* Enter some data and save (no submit)
* You should now find a file in your Mac's Desktop folder named <number>.json
* Open the file and have a look a the data.
* Change one of the fields (for example firstname)
    
## Open the form via the Forms Portal 

* Navigate to your site's "Portal" page and locate your form under the "Drafts"-tab.
* Open the form
* Your form is prefilled with the draft data (including the change made in previous step), the draft data is loaded using the custom OSGi File based DraftDataService.  

