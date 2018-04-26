# TL15 AEM Forms for developers: To submit or not to submit?

Adobe Experience Manager (AEM) provides an easy-to-use solution to create, manage, publish, and update complex digital forms while integrating with back-end processes, business rules, and data.
 
In this session we will focus on the data-related aspects of AEM Forms. You will learn about saving/auto-saving forms and trigger an email with a link to the saved form using Adobe Campaign. We will discuss form prefilling and the submission of forms towards an external system. This session requires some familiarity with component development in Adobe Experience Manager.
 
In this lab you’ll get hands on experience doing in the form of 10 exercises:

* Data  integration
* Custom submit actions
* Setup prefilling

Outline on the lab

Javadoc : https://helpx.adobe.com/aem-forms/6/javadocs/index.html

## [1. Setup your Environment](exercise1/README.md) (10')

Before we start, we will deploy our website and add a couple of pages required for this lab.

## [2. Create a (JSON Schema based) Form](exercise2/README.md) (10')

Create a form that we will use throughout the lab. A JSON Schema will be used as the form data model.

## [3. Embed adaptive form in AEM sites page](exercise3/README.md) (5')

Make the form experience an integral part of your site.

## [4. AEM Forms portal default draft/submit behavior](exercise4/README.md) (5')

Save (draft) data using the default "Forms Portal Submit Action".

## [5. AEM Forms portal custom draft/submit behavior](exercise5/README.md) (10')

Save (draft) data using a custom "Forms Portal Submit Action".

## [6. Send user a link to the saved form](exercise6/README.md) (10')

When the user clicks the "Save" button, we will show a dialog asking if a link to the form should be sent via email (Adobe Campaign)

## [7. Autosave forms](exercise7/README.md) (10')

Define events that should trigger auto save of forms.

## [8. Submit actions](exercise8/README.md) (5')

Next to the default Forms portal submit action, find out about other out-of-the-box submit actions. 

## [9. Custom submit action](exercise9/README.md) (10')

Create a custom submit action that sends an "Identity" to Adobe Campaign using Adobe.io.

## [10. Prefill AEM Forms](exercise10/README.md) (10')

Create a custom submit action that sends an "Identity" to Adobe Campaign using Adobe.io.

# The end
