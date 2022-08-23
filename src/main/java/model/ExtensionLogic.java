package model;

import java.util.regex.Pattern;

import javax.servlet.http.Part;

public class ExtensionLogic {
	public boolean Extension(Part part) {
		if(part.getSize()!=0) {
			String filename=part.getSubmittedFileName();
			String[] split=filename.split(Pattern.quote("."));
			
			if(split[1].equals("jpg") || split[1].equals("png") || split[1].equals("jpeg")){
				return true;
			}else {
				return false;
			}
		}else {
	 		return false;
	 	}

	}
}
