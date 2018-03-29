# AEM Forms portal default draft/submit behavior

By default, a form uses the "Forms Portal Submit Action" to handle the saved or submitted data, we will later on in the lab how you can change the submit behavior of a form.

AEM Forms portal drafts and submissions component allows users to save their forms as drafts and submit later from any device. Also, users can view their submitted forms on portal. To enable this functionality, AEM Forms provides data and metadata services to store the data filled in by a user in the form and the form metadata associated with drafts and submitted forms. This data is stored in the CRX repository, by default. However, as users interact with forms through AEM publish instance, which is generally outside the enterprise firewall, organizations may want to customize data storage for it to be more secure and reliable.

In this excercise we will expplore the default behavior, in [Exercise 5](exercise5/README.md) we will implement a custom "Forms Portal Submit Action"

## Fill the form and save a draft

Navigate to the AEM Site's "Form"-page [Exercise 2](exercise2/README.md) and fill some fields and "Save" the form (do not submit the form)

## Explore the form data

The draft data is saved in the crx repository. To access the data, navigate to http://localhost:4502/crx/de/index.jsp#/content/forms/fp/admin/drafts
View the jcr:data node of a entry in the data folder (click the view link)

## Access the form from the forms portal

* Navigate to your "Portal" page created in [exercise 1](exercise1/README.md)
* Select the "Draft Forms" tab
* Your form will be listed here
* Open the form, it will be prefilled with the saved data

## Submit the form

The submitted data is saved in the crx repository. To access the data, navigate to http://localhost:4502/crx/de/index.jsp#/content/forms/fp/admin/submit
View the jcr:data node of a entry in the data folder (click the view link)

Similar to the custom DraftDataService, you could also create a custom implementation for the SubmitDataService.
