# Send user a link to the saved form

To improve the form filling experience we will allow the user to indicate if he/she wants to receive an email with a link to the draft form. We will send the email using Adobe Campaign. To provide this functionality, we have to override the default client side behavior of the "save" button and we have to provide a service (servlet) to send the email. I

we need to change the default behavior of the "save" button. When the save button is pressed, a dialog will be shown to the user, asking if a link to the form should be sent via email. After confirmation, the dialog will invoke a servlet that will handle the email sending. The servlet will only receive the form's "draftId" and will use our custom DraftDataService [exercise](exercise/README.md) and standard DraftMetadataDataService to obtain all the required form data. The servlet extracts the email address from the form data and uses

## Step 1 : Override client-side "save"

In exercises [exercise4](exercise4/README.md) and [exercise5](exercise5/README.md) we have discussed the default server-side behavior and implemented a custom server-side behavior for saving drafts. In this exercise we will have to change the client-side behavior because we want to show the user a dialog after clicking the save button. This dialog will ask the user if he/she wants to receive an email with a link to the draft form. This of cause, will only work when the user have provided an email in the email field.

* Import [draft-email-handler-clientlib](resources/draft-email-handler-clientlib.zip) via [package manager](http://localhost:4502/crx/packmgr/index.jsp)
* Explore [/apps/summit-2018/tl15/draft-email-handler-clientlib/js/draftEmailHandler.js](http://localhost:4502/crx/de/index.jsp#/apps/summit-2018/tl15/draft-email-handler-clientlib/js/draftEmailHandler.js)

```javascript
$(function() {

    var draftModal = '<div id=\"draftModal\" class=\"modal fade\"><div class=\"modal-dialog\"><div class=\"modal-content\"><div class=\"modal-header\"><button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\" style=\"color:black;\">&times;</button><h4 class=\"modal-title\">Draft Saved</h4></div><div class=\"modal-body\"><p>We have saved a draft of the application filled by you.</p><p class=\"text-warning\">Would you like us to email this draft to you for future reference?</p></div><div class=\"modal-footer\"><button type=\"button\" class=\"btn btn-primary sendEmail moveNext\" style=\"padding:16px 12px;background: white;    color: #2995c9;\">Send Email</button><button type=\"button\" class=\"btn btn-primary closeBox\" style=\"display:none;\">Okay</button></div></div></div></div>';
    $('body').append(draftModal);

       $("#draftModal .sendEmail").on('click', function() {
        	console.log('In sendEmail section')
        	var draftID = guideBridge.customContextProperty('draftID')

        	var url = "/services/api/draft/mail?draftId=" + draftID;


           	$.ajax(url, {
               dataType: "text",
               success: function(rawData, status, xhr) {
                   var serverResponse;
                   try {
                       serverResponse = $.parseJSON(rawData);
                       
                       if (serverResponse.status == 'success') {
                           $('#draftModal .modal-body').html('<p>We have sent an email to you with the link to your application draft. <br>This draft will expire after 15 days.</p>')
                           $('.modal-title').html('<h4 class="modal-title">Draft Email Sent</h4>')
                           $('.btn-primary.closeBox').css('display', '').css('padding','16px 12px');
                           $(".btn-primary.sendEmail").css('display', 'none');
                       }else{
                           
                           $('#draftModal .modal-body').html('<p>Email with the link to your application draft has been sent already.</p>')
                           $('.modal-title').html('<h4 class="modal-title">Message</h4>')
                           $(".btn-primary.sendEmail").css('display', 'none');
                           
                       }
                       
                       
                   } catch (err) {
                       failure(err);
                   }
               },
               error: function(xhr, status, err) {
                   failure(err);
               }
           	});


    	});

        $('.modal-header > .close').on('click', function() {
			  $('#draftModal .modal-body').html('<p>We have saved a draft of the application filled by you.</p><p class="text-warning">Would you like us to email this draft to you for future reference?</p>')
              $('.modal-title').html('<h4 class="modal-title">Draft Saved</h4>')
              $('.btn-primary.closeBox').css('display', 'none');
              $(".btn-primary.sendEmail").css('display', '');
   		});

     	$(".btn-primary.closeBox").on('click', function() {
           $('.modal-header > .close').click();

   		});

});

```

* We recover the draftId using the guidebrigde-object : guideBridge.customContextProperty('draftID')
* And pass it as a parameter to a url that maps to a custom servlet

## Step 2 : Deliver the email
* [TL15UtilityServlet.java](resources/TL15UtilityServlet.java)

```java
package be.adobe.presales.summit.lab.tl15.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.commons.json.JSONObject;
import org.apache.sling.commons.json.JSONTokener;
import org.osgi.framework.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.adobe.presales.adobe.io.jwt.utils.core.AdobeIORequestProcessor;

import com.adobe.fd.fp.service.DraftDataService;
import com.adobe.fd.fp.service.DraftMetadataService;

@Properties({
 @Property(name = Constants.SERVICE_VENDOR, value = "Adobe Benelux Presales"),
 @Property(name = Constants.SERVICE_DESCRIPTION, value = "Summit 2018 - TL15 - Utilities Servlet")
})
@SlingServlet(
     paths={"/services/api/draft/mail"}
)
public class TL15UtilityServlet extends SlingSafeMethodsServlet {

	private static final Logger logger = LoggerFactory.getLogger(TL15UtilityServlet.class);
	private static final String APPLICATION_JSON = "application/json";

	// Lookup custom FileDraftDataServiceImpl
	@Reference(target="(aem.formsportal.impl.prop=summit.tl15formsportal.file.draft.dataservice)")
	private DraftDataService draftDataService;
	
	// Lookup standard crx draft metadata implementation
	@Reference(target="(aem.formsportal.impl.prop=com.adobe.fd.fp.service.impl.DraftMetadataServiceImpl)")
	private DraftMetadataService draftMetadataService;
	
	@Reference
	private AdobeIORequestProcessor adobeIORequestProcessor;

	@Override
	protected void doGet(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws ServletException,
			IOException {
	
		System.out.println("sendSavedFormLink - start");
		
		try {
			String draftId = request.getParameter("draftId");
									
			// Use the configured draftMetadataService (default = crx)
			
			String userdataID = draftMetadataService.getProperty(draftId,"userdataID")[0];
			
			logger.info("userdataID : " + userdataID );
			
			// Use the configured draftDataService (custom = file based for lab TL15)
			
			byte draftData[] = draftDataService.getData(userdataID);
			
			// Transform the formdata into a JSON object that is compliant with the structure required by the
			// Adobe Campaign event configured for our purpose
			
			JSONObject transactionalMessagePayload = getTransactionalMessagePayload(draftId, draftData);
			
			// Invoke the Adobe Campaign transactional message api via Adobe IO
			
			String ignore = adobeIORequestProcessor.processMC(transactionalMessagePayload.toString());
			
			// For this lab TL15 we ignore the errors and always return status = success ;)
			
			JSONObject responseJson = new JSONObject();
			responseJson.put("status", "success");
			
			response.setContentType(APPLICATION_JSON);
			PrintWriter pw = response.getWriter();
			
			pw.print(responseJson);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e.getMessage());
		}
		
		System.out.println("sendSavedFormLink - done");
		
	}
	
		
	private JSONObject getTransactionalMessagePayload(String draftId, byte data[]) throws Exception {
	
		String formDataString = new String(data);
		
		JSONTokener jT = new JSONTokener(formDataString);
		
		JSONObject formDataJson = new JSONObject(jT);
		
		String email = formDataJson.getJSONObject("afData").getJSONObject("afBoundData").getJSONObject("data").getJSONObject("profile").getJSONObject("identity").getString("email");
		
		JSONObject confirmationEmailJson = new JSONObject();
		confirmationEmailJson.put("mcEvent", "EVTSendFormsDraftLink"); // EVTttsTicketConfirmation

		JSONObject mcPayloadEmailJson = new JSONObject();
		confirmationEmailJson.put("mcPayload", mcPayloadEmailJson);
		
		mcPayloadEmailJson.put("email", email);
		
		JSONObject emailCtx = new JSONObject();
		
		// emailCtx.put("cryptedId", profileResponseJSON.getString("cryptedId"));			
		emailCtx.put("email", email);			
		emailCtx.put("draftId", draftId);
		
		mcPayloadEmailJson.put("ctx", emailCtx);
		
		logger.info("confirmationEmailJson >> " + confirmationEmailJson.toString());
		
		return confirmationEmailJson;
		
	}
	
	
	
}

```
