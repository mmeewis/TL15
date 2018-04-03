package be.adobe.presales.summit.lab.tl15.impl;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.osgi.framework.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import be.adobe.presales.adobe.io.jwt.utils.core.AdobeIORequestProcessor;
import be.adobe.presales.summit.lab.tl15.ACSConnector;

@Component(immediate = true)
@Service(value = ACSConnector.class)
@Properties({
	 @Property(name = Constants.SERVICE_VENDOR, value = "Adobe Belux Presales"),
	 @Property(name = Constants.SERVICE_DESCRIPTION, value = "Summit 2018 - TL15 - ACS Connector")
	})
public class ACSConnectorImpl implements ACSConnector {
	
	private static final Logger logger = LoggerFactory.getLogger(ACSConnector.class);
	
	@Reference
	private AdobeIORequestProcessor adobeIORequestProcessor;

	public String createProfile(String formdata) throws Exception {
		
		String profileResponse = adobeIORequestProcessor.createUpdateProfileByLabMachineLabel(formdata);
		
		logger.info("ACS profileResponse : " + profileResponse);
		
		return "profileResponse";
		
	}

	public String getProfileByLabMachineLabel(String labMachineLabel)
			throws Exception {
		
		String profileResponse = adobeIORequestProcessor.getProfileByLabMachineLabel(labMachineLabel);
		
		return "profileResponse";
	}

}
