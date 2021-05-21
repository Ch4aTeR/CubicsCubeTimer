
//the imports
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.sql.*;

public class App extends Application {
    public static void main(String[] args) throws Exception {
        // launching app
        App.launch();
    }

    // integers for the time
    int mins = 0;
    int secs = 0;
    int millis = 0;
    boolean sos = true;
    // text to display time
    Text text;

    void change(Text text) {
        if (millis == 100) {
            secs++;
            millis = 0;
        }
        if (secs == 60) {
            mins++;
            secs = 0;
        }
        if (mins >= 1) {
            text.setText((((mins / 10) == 0) ? "0" : "") + mins + ":" + (((secs / 10) == 0) ? "0" : "") + secs + "."
                    + (((millis / 10) == 0) ? "0" : "") + millis++);
        } else {
            text.setText((((secs / 10) == 0) ? "0" : "") + secs + "." + (((millis / 10) == 0) ? "0" : "") + millis++);
        }
    }


    public void dbConnection() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/LucasTimer", "root", "Zli12345");
            // here sonoo is database name, root is username and password
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            System.out.println(rs.toString());
            while (rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        text = new Text("0.00");
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                change(text);
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(false);
        // new vbox element to act as main parent
        VBox root = new VBox();
        root.setAlignment(Pos.BASELINE_CENTER);
        // decode button
        Button toggleButton = new Button("Start");
        toggleButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (sos) {
                    mins = 0;
                    secs = 0;
                    millis = 0;
                    text.setText("00:00:000");
                    timeline.play();
                    sos = false;
                    toggleButton.setText("Stop");
                } else {
                    timeline.pause();
                    time = "time";
                    sos = true;
                    toggleButton.setText("Start");
                    dbConnection();
                }
            }
        });
        // adding children to root
        root.getChildren().addAll(text, toggleButton);
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("LucasTimer");
        primaryStage.show();
    }
}