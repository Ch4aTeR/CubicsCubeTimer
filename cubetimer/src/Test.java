//the imports
import java.io.IOException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Test extends Application {
    public static void main(String[] args) throws Exception {
        //launching app
        App.launch();
    }

    
    
    Button helloButton = new Button("Hello");
    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox root = new VBox();
        setGlobalEventHandler(root);
        
        helloButton.setOnAction(event1 -> {
            primaryStage.hide();
            });


        root.getChildren().add(helloButton);
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("LucasTimer");
        primaryStage.show();
    }

    private void setGlobalEventHandler(VBox root) {
        root.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
               helloButton.fire();
               ev.consume(); 
        });
    }
}