# 7. Autosave Forms (10')

You can configure an adaptive form to automatically start saving the content based on an event or a pre-defined time-interval. By default, contents of an adaptive form are saved on a user action, such as on pressing the save button. The auto save option is helpful in:

* Automatically saving the content for anonymous and logged-in users
* Saving the content of a form without or minimal user intervention
* Start saving content of a form based on a user event
* Saving the content of a form repeatedly after a specified time interval

**We are still using our custom DraftDataService which saves the form data on your Desktop, open the file and see how it is updated as you fill more fields.**

## Part 1 : Autosave whenever a field changes

See [How to activate the configuration panel for a form component](../generic/README.md)

### Configure the form

* Open the form you created as part of [exercise2](../exercise2/README.md) in edit mode
* Select "Adaptive Form Container" and select "Configure) (5)
* Scroll down in the configuration panel
* Open and "enable" autosave
* Set "Adaptive Form Event" equal 1
* Trigger is "Event Based"
* Set the "Strategy Configuration"
  - Auto save after this event = elementValueChanged (see [GuideBridge Doc](https://helpx.adobe.com/experience-manager/6-3/forms/javascript-api/GuideBridge.html) for a list of available GuideBridge events).
  - Enable auto save for anonymous user = yes

### Test

* Preview the form
* Fill a field
* Check the most recent .json file on your Desktop
* Fill a field
* Check the .json file again and see how it is autosaved for each field

### Solution for Part 1

* The solution for this exercise is available under TL15-Master/solution/tl15-solution-exercise7-autosave-for-all-fields-1.0.zip folder on you desktop or you can donwload (righ-click/save) [tl15-solution-exercise7-autosave-for-all-fields-1.0.zip](../solutions/tl15-solution-exercise7-autosave-for-all-fields-1.0.zip)
* Install the package via [package manager](http://localhost:4502/crx/packmgr/index.jsp).



## Part 2 : Autosave when a specific field is changed (e.g. email)

In the previous exercise, we auto saved the form after each field value change, in this part of the exercise we would on like to trigger the autosave only when a specific field (for example email) is changed.

### Create a client library

* locate [/apps/summit-2018/tl15/draft-email-handler-clientlib/js/customAutoSaveTriggerEmail.js](http://localhost:4502/crx/de/index.jsp#/apps/summit-2018/tl15/draft-email-handler-clientlib/js/customAutoSaveTriggerEmail.js)
* Make sure it contains the code listed below

```javascript
window.addEventListener("bridgeInitializeStart", function (){   
    guideBridge.connect(function () { guideBridge.on("elementValueChanged", function (event,data) { 
        console.log("data.target.name : " + data.target.name);
        if(data.target.name === 'email') {
            console.log("trigger => emailValueChanged");
            guideBridge.trigger("emailValueChanged");
        }
    });
   });
});
```
* This file is part of the same client library "draft-email-handler-clientlib" created in [exercise6](../exercise6/README.md) and will be loaded when the form loads.

### Configure the form

* Open the form you created as part of [exercise2](../exercise2/README.md) in edit mode
* Select "Adaptive Form Container" and select "Configure) (5)
* Scroll down in the configuration panel
* Open autosave
* Trigger is "Event Based"
* Set the "Strategy Configuration"
  - Auto save after this event = **emailValueChanged** (see [GuideBridge Doc](https://helpx.adobe.com/experience-manager/6-3/forms/javascript-api/GuideBridge.html) for a list of available GuideBridge events).

### Test

* Preview the form
* Fill a field (not email)
* Check the most recent .json file on your desktop
* Fill another field (not email)
* Check the .json file again, it has not changed
* Enter a value for the "email" field and leave the field
* Check the .json file again, all the entered form fields should be present

```
### Solution for Part 2

* The solution for this exercise is available under TL15-Master/solution/tl15-solution-exercise7-autosave-for-email-1.0.zip 
folder on you desktop or you can donwload (righ-click/save) [tl15-solution-exercise7-autosave-for-email-1.0.zip](../solutions/tl15-solution-exercise7-autosave-for-email-1.0.zip)
* Install the package via [package manager](http://localhost:4502/crx/packmgr/index.jsp).


