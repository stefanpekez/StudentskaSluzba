package model;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

public class PassedGradeSerialization {
	private static PassedGradeSerialization instance;
	
	public static PassedGradeSerialization getInstance() {
		if(instance == null) instance = new PassedGradeSerialization();
		
		return instance;
	}
	
	ArrayList<PassedGradeRelation> relations;
	
	public PassedGradeSerialization() {
		relations = new ArrayList<PassedGradeRelation>();
	}
	
	public void addRelation(PassedGradeRelation rel) {
		relations.add(rel);
	}
	
	public ArrayList<PassedGradeRelation> getRelations() {
		return relations;
	}
	
	public void serialize() throws IOException {
		File f = new File("saves\\grades.json");
		OutputStream os = new BufferedOutputStream(new FileOutputStream(f));
		
		try {
			XStream xs = new XStream(new JettisonMappedXmlDriver());
			xs.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
			xs.addPermission(AnyTypePermission.ANY);

			String s = xs.toXML(relations);
			xs.toXML(relations, os);
		} finally {
			os.close();
		}
	}
}
