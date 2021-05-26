import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.File;
import javafx.scene.control.Button;
import java.sql.*;

public class Login {
    public Stage primaryStage;
    public File currentfile;
    public App app;

    public void start(App app) throws Exception {
        this.app = app;
        this.primaryStage = app.primaryStage;
        buildLoginScene();
    }

    String username = null;

    public void dbConnection(){
        
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/LucasTimer", "root", "Zli12345");
            String sql = " INSERT INTO users (username)" + " values (?)";
            PreparedStatement preparedStmt = con.prepareStatement(sql);
            preparedStmt.setString(1, username);
            preparedStmt.execute();
            con.close();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    private void buildLoginScene() {
        VBox root = new VBox();
        TextField usernameinput = new TextField();

        Button loginButton = new Button("Login");

        Label usernamelabel = new Label();
        usernamelabel.setText("Username:");

        root.getChildren().add(usernamelabel);
        root.getChildren().add(usernameinput);
        root.getChildren().add(loginButton);
        Scene scene = new Scene(root, 400, 300);
        this.primaryStage.setScene(scene);
        this.primaryStage.setTitle("Login");
        this.primaryStage.show();

        loginButton.setOnAction(login -> {
            username = usernameinput.getText();
            app.showTimer();
            dbConnection();
        });
    }
}