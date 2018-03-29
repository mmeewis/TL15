# 7. Autosave Forms

You can configure an adaptive form to automatically start saving the content based on an event or a pre-defined time-interval. By default, contents of an adaptive form are saved on a user action, such as on pressing the save button. The auto save option is helpful in:

* Automatically saving the content for anonymous and logged-in users
* Saving the content of a form without or minimal user intervention
* Start saving content of a form based on a user event
* Saving the content of a form repeatedly after a specified time interval

## Autosave whenever a field changes

See [How to activate the configuration panel for a form component](../generic/README.md)

### Configure

* Open the form you created as part of [exercise2](../exercise2/README.md) in edit mode
* Select "Adaptive Form Container" and select "Configure) (5)
* Scroll down in the configuration panel
* Open and "enable" autosave
* Set "Adaptive Form Event" equal 1
* Trigger is "Event Based"
* Set the "Strategy Configuration"
** Auto save after this event = elementValueChanged (see [GuideBridge Doc](https://helpx.adobe.com/experience-manager/6-3/forms/javascript-api/GuideBridge.html) for a list of available GuideBridge events.
** Enable auto save for anonymous user = yes

### Test

* Preview the form
* Fill a field
* We are still using our custom DraftDataService which saves the form data on your Desktop, open the file and see how it is updated automatically as you fill more fields.

## Autosave when a specific field is changed (e.g. email)




https://helpx.adobe.com/experience-manager/6-3/forms/javascript-api/GuideBridge.html
