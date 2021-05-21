package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {
    public Stage primaryStage;
    public File currentfile;


    public static void main(String[] args) throws Exception {
        Main.launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage;
        buildLoginScene();
        this.primaryStage.show();

    }

    private void buildLoginScene() {
        VBox root = new VBox();
        TextField nameinput = new TextField();
        TextField nachnameinput = new TextField();

        Button loginButton = new Button("Login");

        Label namelabel = new Label();
        namelabel.setText("Name:");
        Label nachnamelabel = new Label();
        nachnamelabel.setText("Nachname:");

        root.getChildren().add(namelabel);
        root.getChildren().add(nameinput);
        root.getChildren().add(nachnamelabel);
        root.getChildren().add(nachnameinput);
        root.getChildren().add(loginButton);
        Scene scene = new Scene(root, 400, 300);
        this.primaryStage.setScene(scene);
        this.primaryStage.setTitle("Login");
        this.primaryStage.show();

        loginButton.setOnAction(login -> {

            String input1 = nameinput.getText();
            String input2 = nachnameinput.getText();
            File tempFile = new File(input1 + ".json");
            boolean exists = tempFile.exists();
            buildMainScene();
            if (exists == true){
                System.out.println("File existst already");
                currentfile = new File((input1 + ".json"));
            }else{
                try (FileWriter file = new FileWriter(input1 + ".json")) {

                } catch (IOException e) {
                    e.printStackTrace();
                }
                }




            this.primaryStage.setTitle("Eingelogt als: " + input1 + " " + input2);
        });
    }
        public void buildMainScene () {
            Button logout = new Button("Ausloggen");
            BorderPane borderPane = new BorderPane();

            VBox pane = new VBox();
            pane.setSpacing(10);
            borderPane.setCenter(pane);
            pane.setAlignment(Pos.TOP_LEFT);

            HBox pane1 = new HBox();
            borderPane.setTop(pane1);
            pane1.setAlignment(Pos.TOP_LEFT);

            HBox outlog = new HBox();
            outlog.getChildren().add(logout);
            borderPane.setBottom(outlog);
            outlog.setAlignment(Pos.BOTTOM_RIGHT);


            Scene loginscene = new Scene(borderPane, 400, 300);
            this.primaryStage.setScene(loginscene);

            logout.setOnAction(out->{
                buildLoginScene();
            });

        }

    }