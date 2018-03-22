# Excerice 2 - Create a Form and use a JSON Schema as the form model

AEM Forms supports creation of an adaptive form by using an existing JSON Schema as the form model. This JSON Schema represents the structure in which data is produced or consumed by the back-end system in your organization. The JSON Schema you use should be compliant with v4 specifications.  

The key features of using an JSON Schema are:

The structure of the JSON is displayed as a tree in the Content Finder tab in the authoring mode for an adaptive form. You can drag and add element from the JSON hierarchy to the adaptive form.
You can pre-populate the form using JSON that is compliant with the associated schema.
On submission, the data entered by the user is submitted as JSON that aligns with the associated schema.
An JSON Schema consists of simple and complex element types. The elements have attributes that add rules to the element. When these elements and attributes are dragged onto an adaptive form, they are automatically mapped to the corresponding adaptive form component.

https://helpx.adobe.com/experience-manager/6-3/forms/using/adaptive-form-json-schema-form-model.html

## Step 1 - Download your JSON Schema

Download the identity.schema.json file from github https://raw.githubusercontent.com/mmeewis/TL15/master/identity.schema.json
and save it to your "Desktop".


## Step 2 - Create a form and assign the schema

* Navigate to forms (http://localhost:4502/aem/forms.html/content/dam/formsanddocuments) and create a folder Summit2018
* Open de Summit2018 folder
* Create an Adaptive Form
* Select the "Blank" template
* Name the form "Form Exercise 2"
* Select "Form model"
* Select From : Schema
* Upload from disk 
* Click "Upload Schema Definition"
* Select the downloaded in Step 1
* You should see "Valid Schema Selected"
* Create and Open form

## Step 3 - Add the form fields

* Toggle the side panel
* Select "Root Panel" from the available Form Objects
* Click the "Wrench" icon
* Select "Tabs on Top" panel layout
* Confirm

* Select the "Root panel" component on the right and click "..."
* Add Child panel "Identity"
* Repeat
* Add Child panel "Address"
* Repeat
* Add Toolbar
* Select the "Toolbar for Pane: : Root Panel" component
* Use the "+" to add a next, previous, save and submit button

* Select the top tab "Identity"
* Click the "Content" icon on the left
* Select "Data Model Objects"
* Drag and drop all the "Identity" fields from the model onto the "Identity" component

* Select the top tab "Address"
* Drag and drop all the "Address" fields from the model onto the "Address" component
