Excerice 2 - Create a form based on a json schema




Step 1 

Download the identity.schema.json file from github https://raw.githubusercontent.com/mmeewis/TL15/master/identity.schema.json
and save it to your "Desktop".


Step 2 - Create a form and assign the schema

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

Step 3 - Add the form fields

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
