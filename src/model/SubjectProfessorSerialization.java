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

public class SubjectProfessorSerialization {
	private static SubjectProfessorSerialization instance;
	
	public static SubjectProfessorSerialization getInstance() {
		if(instance == null) instance = new SubjectProfessorSerialization();
		
		return instance;
	}
	
	private ArrayList<SubjectHeadRelation> headRelations;
	private ArrayList<SubjectTeachersRelation> teachersRelation;
	
	private SubjectProfessorSerialization() {
		headRelations = new ArrayList<SubjectHeadRelation>();
		teachersRelation = new ArrayList<SubjectTeachersRelation>();
	}
	
	
	public void addHead(SubjectHeadRelation rel) {
		headRelations.add(rel);
	}
	
	public void addTeacher(SubjectTeachersRelation rel) {
		teachersRelation.add(rel);
	}
	
	public void flushHead() {
		this.headRelations.clear();
	}
	public void flushTeachers() {
		this.teachersRelation.clear();
	}
	
	public ArrayList<SubjectHeadRelation> getHeadRelations() {
		return headRelations;
	}


	public void setHeadRelations(ArrayList<SubjectHeadRelation> headRelations) {
		this.headRelations = headRelations;
	}


	public ArrayList<SubjectTeachersRelation> getTeachersRelation() {
		return teachersRelation;
	}


	public void setTeachersRelation(ArrayList<SubjectTeachersRelation> teachersRelation) {
		this.teachersRelation = teachersRelation;
	}


	public void serializeHead() throws IOException{
		File f = new File("saves" + File.separator + File.separator + "subjectHeads.json");
		OutputStream os = new BufferedOutputStream(new FileOutputStream(f));
		
		try {
			XStream xs = new XStream(new JettisonMappedXmlDriver());
			xs.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
			xs.addPermission(AnyTypePermission.ANY);

			String s = xs.toXML(headRelations);
			xs.toXML(headRelations, os);
		} finally {
			os.close();
		}
	}
	
	public void serializeTeachers() throws IOException{
		File f = new File("saves" + File.separator + File.separator + "subjectTeachers.json");
		OutputStream os = new BufferedOutputStream(new FileOutputStream(f));
		
		try {
			XStream xs = new XStream(new JettisonMappedXmlDriver());
			xs.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
			xs.addPermission(AnyTypePermission.ANY);

			String s = xs.toXML(teachersRelation);
			xs.toXML(teachersRelation, os);
		} finally {
			os.close();
		}
	}
	
	public void deserializeHead() throws IOException{
		FileInputStream f = new FileInputStream("saves" + File.separator + File.separator + "subjectHeads.json");
		try {
			XStream xstream = new XStream(new JettisonMappedXmlDriver());
			xstream.addPermission(AnyTypePermission.ANY);
			
			headRelations = (ArrayList<SubjectHeadRelation>) xstream.fromXML(f);
			
			}
		finally {
			f.close();
		}
	}
	
	public void deserializeTeachers() throws IOException{
		FileInputStream f = new FileInputStream("saves" + File.separator + File.separator + "subjectTeachers.json");
		try {
			XStream xstream = new XStream(new JettisonMappedXmlDriver());
			xstream.addPermission(AnyTypePermission.ANY);
			
			teachersRelation = (ArrayList<SubjectTeachersRelation>) xstream.fromXML(f);
			
			}
		finally {
			f.close();
		}
	}
	
}
