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
		
		JSONObject identityJson = formDataJson.getJSONObject("afData").getJSONObject("afBoundData").getJSONObject("data").getJSONObject("profile").getJSONObject("identity");
		
		String email = identityJson.getString("email");
		String labmachinelabel = identityJson.getString("labmachinelabel");
		
		logger.info("email : " + email);
		logger.info("labmachinelabel : " + labmachinelabel);
		
		JSONObject confirmationEmailJson = new JSONObject();
		confirmationEmailJson.put("mcEvent", "EVTSendFormsDraftLink"); // EVTttsTicketConfirmation

		JSONObject mcPayloadEmailJson = new JSONObject();
		confirmationEmailJson.put("mcPayload", mcPayloadEmailJson);
		
		mcPayloadEmailJson.put("email", email);
		
		JSONObject emailCtx = new JSONObject();
		
		// emailCtx.put("cryptedId", profileResponseJSON.getString("cryptedId"));			
		emailCtx.put("email", email);			
		emailCtx.put("draftId", draftId);
		emailCtx.put("labmachinelabel", labmachinelabel);
		
		mcPayloadEmailJson.put("ctx", emailCtx);
		
		logger.info("confirmationEmailJson >> " + confirmationEmailJson.toString());
		
		return confirmationEmailJson;
		
	}
	
	
	
}
