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

public class DepartmentHeadSerialization {
	private static DepartmentHeadSerialization instance;
	
	public static DepartmentHeadSerialization getInstance() {
		if(instance == null) instance = new DepartmentHeadSerialization();
		
		return instance;
	}
	
	ArrayList<DepartmentHeadRelation> relations;
	
	public DepartmentHeadSerialization() {
		relations = new ArrayList<DepartmentHeadRelation>();
	}
	
	public void addRelation(DepartmentHeadRelation rel) {
		relations.add(rel);
	}
	
	public ArrayList<DepartmentHeadRelation> getRelations() {
		return relations;
	}
	
	public void serialize() throws IOException {
		File f = new File("saves\\departmentHeads.json");
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
