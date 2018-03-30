## [4. Add Your form to a page](exercise3/README.md)
* e

## [5. The Default draft/submit](exercise4/README.md)

Adaptive forms require Submit actions to process user-specified data. A Submit action determines the task performed on the data that you submit using an adaptive form. Adobe Experience Manager (AEM) includes OOTB Submit actions that demonstrate custom tasks you can perform using the user-submitted data. For example, you can perform tasks, such as sending email or storing the data.


https://helpx.adobe.com/experience-manager/6-3/forms/using/custom-submit-action-form.html

## [6. The Custom draft/submit ](exercise5/README.md)

A submit action is triggered when a user clicks the Submit button on an adaptive form. You can configure the submit action on adaptive form. Adaptive forms provide a few out of the box submit actions. You can copy and extend the default submit actions to create you own submit action. However, based on your requirements, you can write and register your own submit action to process data in the submitted form. 

https://helpx.adobe.com/experience-manager/6-3/forms/using/custom-draft-submission-data-services.html

AEM Forms allows users to save an adaptive form as a draft. The draft functionality provides users with the option to maintain a work-in-progress form. A user can then complete and submit the form at any time from any device.

By default, AEM Forms stores the user data associated with the draft and submission on the Publish instance in the /content/forms/fp node.

However, AEM Forms portal components provides data services that allow you to customize the implementation of storing user data for drafts and submissions. For example, you can store the data in a data store currently implemented in your organization.

To customize the storage of user data, you need to implement the Draft Data and Submission Data services.

==========

With AEM Forms, you can store:

Drafts: A work-in-progress form that end users fill and save for later, and submit afterwards.
Submissions: Submitted forms containing user provided data.
AEM Forms Portal data and metadata services provide support for drafts and submissions. By default the data is stored in the publish instance, which is then reverse-replicated to configured author instance to be available for percolation to other publish instances.

The concern with the existing out-of-the-box approach is, that it stores all the data on publish instance, including the data which can be Personal Identifiable Information (PII).

In addition to above mentioned default approach, an alternative implementation is also available for directly pushing the form data to processing instead of saving it locally. Customers having concerns about storage of potentially sensitive data on publish instance can choose the alternative implementation in which the data is sent to a processing server. Since processing happens on the author instance, it typically stays in a secure zone.

https://helpx.adobe.com/experience-manager/6-3/forms/using/configuring-draft-submission-storage.html

https://localhost:4502/system/console/configMgr => Forms Portal Draft and Submission Configuration 
