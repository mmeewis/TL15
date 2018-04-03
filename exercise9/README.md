# 9. Custom Submit Actions

In this exercise we will create a submit action that registers our profile with Adobe Campaign. The submit action will trigger an Adobe IO call to create a profile in Adobe Campaign.

# The anatomy of a custom submit action

A Submit action is a sling:Folder that includes the following: 

* addfields.jsp: This script provides the action fields that are added to the HTML file during rendition. Use this script to add hidden input parameters required during submission in the post.POST.jsp script.
* dialog.xml: This script is similar to the CQ Component dialog. It provides configuration information that the author customizes. The fields are displayed in the Submit Actions Tab in the Adaptive Form Edit dialog when you select the Submit action.
* post.POST.jsp: The Submit servlet calls this script with the data that you submit and the additional data in the previous sections. Any mention of running an action in this page implies running the post.POST.jsp script. To register the Submit action with the adaptive forms to display in the Adaptive Form Edit dialog, add these properties to the sling:Folder:
     * guideComponentType of type String and value fd/af/components/guidesubmittype
     * guideDataModel of type String that specifies the type of adaptive form for which the Submit action is applicable. xfa is supported for XFA-based adaptive forms while xsd is supported for XSD-based adaptive forms. basic is supported for adaptive forms that do not use XDP or XSD. To display the action on multiple types of adaptive forms, add the corresponding strings. Separate each string by a comma. For example, to make an action visible on XFA- and XSD-based adaptive forms, specify the values xfa and xsd respectively.
     * jcr:description of type String. The value of this property is displayed in the Submit action list in the Submit Actions Tab of the Adaptive Form Edit dialog. The OOTB actions are present in the CRX repository at the location /libs/fd/af/components/guidesubmittype.

