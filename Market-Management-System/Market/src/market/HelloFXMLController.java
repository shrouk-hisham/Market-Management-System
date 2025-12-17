/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package market;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mediatech
 */

public class HelloFXMLController implements Initializable {
    @FXML
   
    private Label fxxxv;
    @FXML
    private AnchorPane main_form;
    @FXML
    private Label mylabel;
    @FXML
    private Label mylabel1;
    @FXML
    private AnchorPane admin_form;
    @FXML
    private TextField admin_username;
    @FXML
    private Button admin_loginbtn;
    @FXML
    private Hyperlink admin_hyperlink;
    @FXML
    private PasswordField admin_password;
    @FXML
    private AnchorPane employee_form;
    @FXML
    private TextField employee_id;
    @FXML
    private Button employee_loginbtn;
    @FXML
    private Hyperlink employee_hyperlink;
    @FXML
    private PasswordField employee_password;

    /**
     * Initializes the controller class.
     * @param event
     */
    
    private Connection connecnt;
    private ResultSet result;
    private PreparedStatement prepare;
    
    public void employeeLogin(){
        String employeeData = "SELECT * FROM employee WHERE employee_id = ? and password=?";
        
        Connection connect = database.connectDB();
        try{
            
            Alert alert;
            
            prepare =connect .prepareStatement(employeeData);
            
            if(employee_id.getText().isEmpty()
                    || employee_password.getText().isEmpty()){
                alert =new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            
            
            }else{
                 prepare .setString(1, employee_id.getText());
                 prepare .setString(2,  employee_password.getText());
                 
                 result = prepare.executeQuery();
                 
                  if(result.next()){
                      
                      alert = new Alert(AlertType.INFORMATION);
                      alert.setHeaderText(null);
                      alert.setContentText("Successfully Login!");
                      alert.showAndWait();
                     employee_loginbtn.getScene().getWindow().hide();
                     Parent root = FXMLLoader.load(getClass().getResource("employeeDashboarde.fxml"));
                     
                     Stage stage = new Stage ();
                     Scene scene = new Scene(root);
                     
                     stage.setScene(scene);
                     stage.show();
                  
                  }else{
                  
                    alert =new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong EmployeeID/password");
                    alert.showAndWait();
                  
                  }
                 
            
              }
            
        }catch(Exception e){e.printStackTrace();}
        
        
    }
    
    public void adminLogin(){
        String adminData = "SELECT * FROM admin WHERE username = ? and password = ?";
        
        Connection connect = database.connectDB();
        
        
        try{
             Alert alert;
             if(admin_username.getText().isEmpty()
                     || admin_password.getText().isEmpty()){
                 alert =new Alert(AlertType.ERROR);
                 alert.setHeaderText(null);
                 alert.setContentText("please fillall blank fields");
                 alert.showAndWait();
                }else{
             
                prepare = connect.prepareStatement(adminData);
                prepare .setString(1, admin_username.getText());
                prepare .setString(2, admin_password.getText());
                result = prepare.executeQuery();
                
                if(result.next()){
                    
                    //THEN LETS PROCEED TO DASHBOARD FORMسوف نقوم بعملوحة التحكم للمسؤل :)
                    //اخفاء نموزج تسجيل الدخول
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setHeaderText("Successfully Login!");
                    alert.showAndWait();
                    
                    admin_loginbtn.getScene().getWindow().hide();
       //الان سوف نقوم بى انشاء فورم الموظفين             
                    
                     Parent root = FXMLLoader.load(getClass().getResource("adminDashboard.fxml"));
                     
                    
                    Stage stage = new Stage();
                    Scene scene =new Scene(root);
                    
                    
                    stage.setScene(scene);
                    stage.show();
                   
                }else{
                    //اذا ادخلت بيانات خطاء سوف تظهر رسالة خطاء
                    alert =new Alert(AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong Username/password");
                    alert.showAndWait();
                    
                }
                
        
            }
        }catch(Exception e){e.printStackTrace();}
        
    }
    
    public void switchForm(ActionEvent event){
         
        if(event.getSource()== admin_hyperlink){
            admin_form.setVisible(false);
            employee_form.setVisible(true);
        }else if (event.getSource() == employee_hyperlink){
             admin_form.setVisible(true);
            employee_form.setVisible(false);
        }
    }

    public void close()
    
    {
        System.exit(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

}
