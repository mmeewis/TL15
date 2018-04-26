# Submit Actions (5')

A submit action is triggered when a user clicks the Submit button on an adaptive form. You can configure the submit action on adaptive form. Adaptive forms provide a few out of the box submit actions. You can copy and extend the default submit actions to create you own submit action. However, based on your requirements, you can write and register your own submit action to process data in the submitted form. 

## AEM Forms Default Submit Actions

The default submit actions available with adaptive forms are:

* Submit to REST endpoint
* Send Email
* Send PDF via Email
* Invoke a Forms Workflow
* Submit using Form Data Model
* **Forms Portal Submit Action**
* Invoke an AEM Workflow

The **Forms Portal Submit Action** has been covered in the previous exercises.

[AEM Forms Available Submit Actions](https://helpx.adobe.com/experience-manager/6-3/forms/using/configuring-submit-actions.html)


## The form submission process

The flowchart depicts the workflow for a Submit action that is triggered when you click the Submit button in an adaptive form. The files in the File Attachment component are uploaded to the server, and the form data is updated with the URLs of the uploaded files. Within the client, the data is stored in the JSON format. The client sends an Ajax request to an internal servlet that massages the data you specified and returns it in the XML format. The client collates this data with action fields. It submits the data to the final servlet (Guide Submit servlet) through a Form Submit action. Then, the servlet forwards the control to the Submit action. The Submit action can forward the request to a different sling resource or redirect the browser to another URL. 

![aem-forms-submit.png](../resources/aem-forms-submit.png)
