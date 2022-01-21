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

public class DepartmentHeadSerialization {
	private static DepartmentHeadSerialization instance;
	
	public static DepartmentHeadSerialization getInstance() {
		if(instance == null) instance = new DepartmentHeadSerialization();
		
		return instance;
	}
	
	private ArrayList<DepartmentHeadRelation> relations1;
	
	public DepartmentHeadSerialization() {
		relations1 = new ArrayList<DepartmentHeadRelation>();
	}
	
	public void addRelation(DepartmentHeadRelation rel) {
		relations1.add(rel);
	}
	
	public void flush() {
		this.relations1.clear();
	}
	
	public ArrayList<DepartmentHeadRelation> getRelations3() {
		return relations1;
	}
	
	public void serialize() throws IOException {
		File f = new File("saves\\departmentHeads.json");
		OutputStream os = new BufferedOutputStream(new FileOutputStream(f));
		
		try {
			XStream xs = new XStream(new JettisonMappedXmlDriver());
			xs.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
			xs.addPermission(AnyTypePermission.ANY);

			String s = xs.toXML(relations1);
			xs.toXML(relations1, os);
		} finally {
			os.close();
		}
	}
	
	public void deserialize() throws IOException{
		FileInputStream f = new FileInputStream("saves\\departmentHeads.json");
		try {
			XStream xstream = new XStream(new JettisonMappedXmlDriver());
			xstream.addPermission(AnyTypePermission.ANY);
			
			relations1 = (ArrayList<DepartmentHeadRelation>) xstream.fromXML(f);
			
		}
		finally {
			f.close();
		}
	}
}
