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

public class UnpassedSerialization {
		private static UnpassedSerialization instance;
		
		public static UnpassedSerialization getInstance() {
			if(instance == null) instance = new UnpassedSerialization();
			
			return instance;
		}
		
		private ArrayList<UnpassedExamRelation> relations;
		
		public UnpassedSerialization() {
			this.relations = new ArrayList<UnpassedExamRelation>();
		}
		
		public void flush() {
			this.relations.clear();
		}
		
		public void addRelation(UnpassedExamRelation rel) {
			this.relations.add(rel);
		}
		
		public ArrayList<UnpassedExamRelation> getRelations1() {
			return this.relations;
		}
		
		public void serialize() throws IOException {
			File f = new File("saves" + File.separator + File.separator + "unpassed.json");
			OutputStream os = new BufferedOutputStream(new FileOutputStream(f));
			
			try {
				XStream xs = new XStream(new JettisonMappedXmlDriver());
				xs.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
				xs.addPermission(AnyTypePermission.ANY);

				String s = xs.toXML(this.relations);
				xs.toXML(this.relations, os);
			} finally {
				os.close();
			}
		}
		
		public void deserialize() throws IOException{
			FileInputStream f = new FileInputStream("saves" + File.separator + File.separator + "unpassed.json");
			try {
				XStream xstream = new XStream(new JettisonMappedXmlDriver());
				xstream.addPermission(AnyTypePermission.ANY);
				
				relations = (ArrayList<UnpassedExamRelation>) xstream.fromXML(f);
				
			}
			finally {
				f.close();
			}
		}
		
}

