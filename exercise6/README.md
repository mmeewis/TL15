# Send user a link to the saved form

To improve the form filling experience we will allow the user to indicate if he/she wants to receive an email with a link to teh draft form. We will send the email using Adobe Campaign

## Step 1 : Create a client library

* Import [draft-email-handler-clientlib](resources/draft-email-handler-clientlib.zip) via [package manager](http://localhost:4502/crx/packmgr/index.jsp)
* Explore /apps/summit-2018/tl15/draft-email-handler-clientlib/js/draftEmailHandler.js
* We recover the draftId using the guidebrigde-object : guideBridge.customContextProperty('draftID')
* And pass it as a parameter to a url that maps to a custom servlet

## Step 2 : The 
