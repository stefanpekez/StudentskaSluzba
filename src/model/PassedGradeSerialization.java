package model;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
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
	
	private ArrayList<PassedGradeRelation> relation;
	
	public PassedGradeSerialization() {
		this.relation = new ArrayList<PassedGradeRelation>();
	}
	
	public void addRelation(PassedGradeRelation rel) {
		this.relation.add(rel);
	}
	
	public void flush() {
		this.relation.clear();
	}
	
	public ArrayList<PassedGradeRelation> getRelations2() {
		return this.relation;
	}
	
	public void serialize() throws IOException {
		File f = new File("saves\\grades.json");
		OutputStream os = new BufferedOutputStream(new FileOutputStream(f));
		
		try {
			XStream xs = new XStream(new JettisonMappedXmlDriver());
			xs.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
			xs.addPermission(AnyTypePermission.ANY);

			String s = xs.toXML(this.relation);
			xs.toXML(this.relation, os);
		} finally {
			os.close();
		}
	}
	
	public void deserialize() throws IOException{
		FileInputStream f = new FileInputStream("saves\\grades.json");
		try {
			XStream xstream = new XStream(new JettisonMappedXmlDriver());
			xstream.addPermission(AnyTypePermission.ANY);
			
			this.relation = (ArrayList<PassedGradeRelation>) xstream.fromXML(f);
			
		}
		finally {
			f.close();
		}
	}
}
