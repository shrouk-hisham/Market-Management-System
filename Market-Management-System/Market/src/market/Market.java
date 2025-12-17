
package Market;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.StageStyle;

public class Market extends Application{
    
    private double x=0;
    private double y=0;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("employeeDashboarde.fxml"));
        Scene scene =new Scene(root);
        
        
        
//        LES DESGN OUT LOGINFORM USING CSS:

    root.setOnMousePressed((MouseEvent  event) ->{
        x =event.getSceneX();
        y =event.getSceneY();
        
    });
        
    root.setOnMouseDragged((MouseEvent event) ->{
        stage.setX(event.getScreenX() -x);
        stage.setY(event.getScreenY() -y);
        stage.setOpacity(.8);
    });
   
    root.setOnMouseReleased((MouseEvent event) ->{
        stage.setOpacity(1);
    });
    
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("suprmarkt");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args){
        launch(args);
      }

    
    }
