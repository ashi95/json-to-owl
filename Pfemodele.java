/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pfe;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static javafx.beans.binding.Bindings.select;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.swing.text.html.HTMLDocument.Iterator;
import static org.apache.jena.assembler.JA.OntModel;
import static org.apache.jena.enhanced.BuiltinPersonalities.model;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntDocumentManager;
import static org.apache.jena.ontology.OntDocumentManager.NS;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import static org.apache.jena.ontology.OntModelSpec.OWL_MEM;
import static org.apache.jena.ontology.OntModelSpec.OWL_MEM_MICRO_RULE_INF;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.ModelMaker;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFFormat;
import static org.apache.jena.tdb.sys.FileRef.file;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.OWL;
import org.apache.jena.vocabulary.VCARD;

/**
 *
 * @author alucard
 */
public class Pfemodele {
  

                
    public static void readJson(File file) throws FileNotFoundException,
            IOException, ParseException 
    {
        File file1 = new File("c:\\users\\alucard\\hellbornhell.txt");

	      if (file1.createNewFile()){
	        System.out.println("File is created!");
	      }else{
	        System.out.println("File already exists.");
                file1.delete();
                file1.createNewFile();
        PrintStream out = new PrintStream(new FileOutputStream("c:\\users\\alucard\\hellbornhell.txt", true));
    System.setOut(out);
        try{
        FileInputStream in = new FileInputStream(file);
        BufferedReader reader =new BufferedReader(new InputStreamReader(in));
        JsonParser parser = Json.createParser(in);
    
while (parser.hasNext()) {
    
   JsonParser.Event event = parser.next();
 
   switch(event) {
       
       
      case START_ARRAY: 
      case END_ARRAY: 
      case START_OBJECT: 
      case END_OBJECT: 
      case VALUE_FALSE: 
      case VALUE_NULL: 
      case VALUE_TRUE: 
         
       
    System.out.println(event.toString());
         break;
      case KEY_NAME:
         
         System.out.print(event.toString() + " " +
                          parser.getString() + " - ");
         break;
      case VALUE_STRING:
      case VALUE_NUMBER:
         
         System.out.println(event.toString() + " " +
                            parser.getString());
         break;
        
   }

}


        }
        catch (NullPointerException e )
        {
            e.printStackTrace();
        }

    /**
     *
     */
   
}
    }
  
public static void convert()   {
        // ontologyIn contains a InputStream to the .owl file
        
       org.apache.log4j.BasicConfigurator.configure();
       OntModel m = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        OntClass ClassMere = null;
        OntClass ClassFille;
       File file = new File("c:\\users\\alucard\\hellbornhell.txt");
       
      Model base = m.getBaseModel();
String lane = null;
        String lune = null;

try {
    Scanner scanner = new Scanner(file);
int lineNum = 0;
    while (scanner.hasNextLine()) {
        int flagS=0;
        boolean flagE=false;
        String line = scanner.nextLine();
        StringTokenizer st = new StringTokenizer(line);
       
        lineNum++;
        if(line.contains("START_OBJECT")) { 
            flagS++;
                    }
        if(line.contains("END_OBJECT"))
        {
            flagS--;
        }
        if(line.contains("KEY_NAME")||line.contains("Value_")){
            Scanner s = new Scanner(line).useDelimiter("\\s*KEY_NAME\\s*");
            Scanner s1 =new Scanner(line).useDelimiter("\\s*VALUE\\s*");
             lune=s.nextLine().substring(9);
            lane=s1.nextLine().substring(12);
            
           
        }
       if(flagS<2)
       {
           
          ClassMere = m.createClass( lune );
         
       }
      else 
       {
            ClassFille=m.createClass(lune);
            ClassMere.addSubClass(ClassFille);
            Property worksIn = m.createOntProperty(lane);
       }
       if(line.contains("START_ARRAY"))
       {
           flagE=true;
       }
         if(line.contains("START_END"))
         {
             flagE=false;
         }
         if(flagE)
         {
              Property worksIn2 = m.createOntProperty(lane);
         }
    }
} catch(FileNotFoundException e) { 
    e.printStackTrace();
}
m.write(System.out, "Turtle");
}

    /**
     *
     */
}
   







