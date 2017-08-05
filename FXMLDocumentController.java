/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pfe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import static pfe.Pfemodele.readJson;
import static pfe.Pfemodele.convert;


/**
 *
 * @author alucard
 */
public class FXMLDocumentController implements Initializable {
    @FXML private Label label1;
   
    
    private void handleButtonAction(ActionEvent event) {
       
        
    }
    public void handleButtonClicked(MouseEvent mouseEvent) throws FileNotFoundException,IOException, ParseException {
     
      try{  
      FileChooser chooser = new FileChooser();
    chooser.setTitle("Open File");
   
    File file = chooser.showOpenDialog(new Stage());
   String filename = file.getName();
 
   if( !filename.contains(".json")) {
    label1.setText("you must select a json file ");
    
   }
   else
   {
       label1.setText(" ");
   readJson(file);
   
 convert();   
   }
    }
   catch(NullPointerException e)
   {
       label1.setText("you must select a file ");
   }
   catch(RuntimeException e)
   {
    e.printStackTrace();
   }
   }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    
}
