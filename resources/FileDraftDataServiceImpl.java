package be.adobe.presales.summit.lab.tl15.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.fd.fp.exception.FormsPortalException;
import com.adobe.fd.fp.service.DraftDataService;
import com.adobe.forms.common.service.FormsException;


@Component(immediate = true)
@Service(value = DraftDataService.class)
@Property(name = "aem.formsportal.impl.prop", value = "summit.tl15formsportal.file.draft.dataservice")
public class FileDraftDataServiceImpl implements DraftDataService {

	private final static Logger logger = LoggerFactory.getLogger(FileDraftDataServiceImpl.class);
	
	private final static String DESKTOP = "Desktop";
	
	private String getId() {
        return String.valueOf(System.nanoTime());
    }
	
	private String getFilename(String id) {
		return System.getProperty("user.home") + File.separator + DESKTOP + File.separator + id + ".json";
	}
	
	public byte[] getData(String id) throws FormsPortalException {
		
		byte[] data;
		
		String fileName = getFilename(id);
		
		logger.info("Getting data for file : " + fileName);
		
		try {
			
			File draftFile = new File(fileName);
			
			data = Files.readAllBytes(draftFile.toPath());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new FormsException(e);
		}
		
		return data;
		
	}


	public String saveData(String id, String formName, String formdata)
			throws FormsPortalException {
		
		logger.info("id => " + id);
		logger.info("formName => " + formName);
		logger.info("formData => " + formdata);
		
		try {
			
			if ((id == null) || (id.isEmpty())) {	
				id = getId();
			}
			
			String fileName = getFilename(id);
			
			logger.info("Writing file in " + fileName);
			
			File draftFile = new File(fileName);
			FileWriter fw = new FileWriter(draftFile);
			
			fw.write(formdata);
			
			fw.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new FormsException(e);
		}
		
		logger.info("Finished");
		
		
		return id;
	}
	
	
	
	public boolean deleteAttachment(String id) throws FormsPortalException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteData(String id) throws FormsPortalException {
		// TODO Auto-generated method stub
		return false;
	}

	public byte[] getAttachment(String id) throws FormsPortalException {
		// TODO Auto-generated method stub
		return null;
	}

	public String saveAttachment(byte[] attachmentBytes) throws FormsPortalException {
		// TODO Auto-generated method stub
		return null;
	}


}
