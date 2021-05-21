package sample;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.sql.*;

public class Login extends Application {
    public Stage primaryStage;
    public static void main(String[] args) throws Exception {
        Login.launch();
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
            buildTimerScene();
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
    private void buildTimerScene(){
        text = new Text("0.00");
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                change(text);
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(false);

        //new vbox element to act as main parent
        VBox root = new VBox();
        root.setAlignment(Pos.BASELINE_CENTER);
        //new hbox child
        HBox hroot = new HBox(3);
        hroot.setAlignment(Pos.BASELINE_CENTER);

        //plus2btn
        Button plus2Button = new Button("+2");
        //dnfbtn
        Button dnfButton = new Button("DNF");
        //decode button
        Button toggleButton = new Button("Start");
        toggleButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(sos) {
                    mins = 0;
                    secs = 0;
                    millis = 0;
                    text.setText("00:00:000");
                    timeline.play();
                    sos = false;
                    toggleButton.setText("Stop");
                    //disabling +2 btn
                    plus2Button.setDisable(true);
                    dnfButton.setDisable(true);
                } else {
                    timeline.pause();
                    sos = true;
                    toggleButton.setText("Start");
                    //action for +2 btn
                    //enabling btns
                    plus2Button.setDisable(false);
                    dnfButton.setDisable(false);
                    plus2Button.setOnAction(event1 -> {
                        //checking if the time is over one minute
                        if(text.getText().length() >= 8){
                            //getting time in substrings
                            System.out.println("Over a minute");
                            stopMinutes = Integer.parseInt(text.getText().substring(0, 2));//int for minutes
                            stopSeconds = Integer.parseInt(text.getText().substring(3, 5));
                            stopHunderths = Integer.parseInt(text.getText().substring(6, 8));
                            System.out.println("Mins: "+stopMinutes+"\n"+"secs:"+stopSeconds+"\n"+"hunderths:"+stopHunderths);
                        }else{
                            //getting time in substrings
                            stopSeconds = Integer.parseInt(text.getText().substring(0, 2));
                            stopHunderths = Integer.parseInt(text.getText().substring(3, 5));
                            System.out.println("secs:"+stopSeconds+"\n"+"hunderths:"+stopHunderths);
                        }
                        //printing text plus two seconds
                        //deciding if minutes have to be printed too
                        if(text.getText().length() >= 8 && stopSeconds >= 58){
                            //adding one minute
                            stopMinutes+= 1;
                            //switch for the seconds
                            switch (stopSeconds){
                                case 58:
                                    stopSeconds = 00;
                                    break;
                                case 59:
                                    stopSeconds = 01;
                                    break;

                            }
                            if(stopSeconds >= 10){
                                if(stopMinutes >= 10){
                                    if(stopHunderths >= 10){
                                        text.setText(stopMinutes+":"+stopSeconds+"."+stopHunderths);
                                    }else{
                                        text.setText(stopMinutes+":"+stopSeconds+"."+"0"+stopHunderths);
                                    }
                                }else{
                                    if(stopHunderths >= 10){
                                        text.setText("0"+stopMinutes+":"+stopSeconds+"."+stopHunderths);
                                    }else{
                                        text.setText("0"+stopMinutes+":"+stopSeconds+"."+"0"+stopHunderths);
                                    }
                                }
                            }else{
                                if(stopMinutes >= 10){
                                    if(stopHunderths >= 10){
                                        text.setText(stopMinutes+":"+stopSeconds+"."+stopHunderths);
                                    }else{
                                        text.setText(stopMinutes+":"+stopSeconds+"."+"0"+stopHunderths);
                                    }
                                }else{
                                    if(stopHunderths >= 10){
                                        text.setText("0"+stopMinutes+":"+stopSeconds+"."+stopHunderths);
                                    }else{
                                        text.setText("0"+stopMinutes+":"+stopSeconds+"."+"0"+stopHunderths);
                                    }
                                }
                            }
                        }else if(text.getText().length() >= 8){
                            //adding 2 seconds
                            stopSeconds += 2;
                            if(stopSeconds >= 10){
                                if(stopMinutes >= 10){
                                    if(stopHunderths >= 10){
                                        text.setText(stopMinutes+":"+stopSeconds+"."+stopHunderths);
                                    }else{
                                        text.setText(stopMinutes+":"+stopSeconds+"."+"0"+stopHunderths);
                                    }
                                }else{
                                    if(stopHunderths >= 10){
                                        text.setText("0"+stopMinutes+":"+stopSeconds+"."+stopHunderths);
                                    }else{
                                        text.setText("0"+stopMinutes+":"+stopSeconds+"."+"0"+stopHunderths);
                                    }
                                }
                            }else{
                                if(stopMinutes >= 10){
                                    if(stopHunderths >= 10){
                                        text.setText(stopMinutes+":"+"0"+stopSeconds+"."+stopHunderths);
                                    }else{
                                        text.setText(stopMinutes+":"+"0"+stopSeconds+"."+"0"+stopHunderths);
                                    }
                                }else{
                                    if(stopHunderths >= 10){
                                        text.setText("0"+stopMinutes+":"+"0"+stopSeconds+"."+stopHunderths);
                                    }else{
                                        text.setText("0"+stopMinutes+":"+"0"+stopSeconds+"."+"0"+stopHunderths);
                                    }
                                }
                            }
                        }else if(stopSeconds >= 58){
                            //changing to one minute
                            stopMinutes = 01;
                            //switch for the seconds
                            switch (stopSeconds){
                                case 58:
                                    stopSeconds = 00;
                                    break;
                                case 59:
                                    stopSeconds = 01;
                                    break;

                            }
                            if(stopSeconds >= 10){
                                if(stopHunderths >= 10){
                                    text.setText("0"+stopMinutes+":"+stopSeconds+"."+stopHunderths);
                                }else{
                                    text.setText("0"+stopMinutes+":"+stopSeconds+"."+"0"+stopHunderths);
                                }
                            }else{
                                if(stopHunderths >= 10){
                                    text.setText("0"+stopMinutes+":"+"0"+stopSeconds+"."+stopHunderths);
                                }else{
                                    text.setText("0"+stopMinutes+":"+"0"+stopSeconds+"."+"0"+stopHunderths);
                                }
                            }
                        }else{
                            //adding 2 seconds
                            stopSeconds += 2;
                            if(stopSeconds >= 10){
                                if(stopHunderths >= 10){
                                    text.setText(stopSeconds+"."+stopHunderths);
                                }else{
                                    text.setText(stopSeconds+"."+"0"+stopHunderths);
                                }
                            }else{
                                if(stopHunderths >= 10){
                                    text.setText("0"+stopSeconds+"."+stopHunderths);
                                }else{
                                    text.setText("0"+stopSeconds+"."+"0"+stopHunderths);
                                }
                            }
                        }
                        plus2Button.setDisable(true);
                    });
                    System.out.println(text.getText());
                    dbConnection();
                }
            }
        });
        //adding children to hroot
        hroot.getChildren().addAll(toggleButton, plus2Button, dnfButton);
        //adding children to root
        root.getChildren().addAll(text, hroot);
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("LucasTimer");
        primaryStage.show();
        //dbConnection();
    }
    int userId = 1;
    public void dbConnection(){
        String timeString = text.getText();
        float timeFloat = Float.parseFloat(timeString);
        System.out.println(timeFloat);
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/LucasTimer", "root", "Zli12345");
            // here sonoo is database name, root is username and password
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            System.out.println(rs.toString());
            while (rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
            String sql = " INSERT INTO solves (time, user_id)" + " values (?, ?)";
            PreparedStatement preparedStmt = con.prepareStatement(sql);
            preparedStmt.setFloat(1, timeFloat);
            preparedStmt.setInt(2, userId);

            // execute the preparedstatement
            preparedStmt.execute();
            con.close();
        } catch (Exception e){
            System.out.println(e);
        }
    }
    //integers for the time
    int mins = 0;
    int secs = 0;
    int millis = 0;
    //integers for stopped time
    int stopMinutes;
    int stopSeconds;
    int stopHunderths;

    boolean sos = true;
    //text to display time
    Text text;
    void change(Text text) {
        if(millis == 100) {
            secs++;
            millis = 0;
        }
        if(secs == 60) {
            mins++;
            secs = 0;
        }
        if(mins >= 1){
            text.setText((((mins/10) == 0) ? "0" : "") + mins + ":"
                    + (((secs/10) == 0) ? "0" : "") + secs + "."
                    + (((millis/10) == 0) ? "0" : "") + millis++);
        }
        else{
            text.setText((((secs/10) == 0) ? "0" : "") + secs + "."
                    + (((millis/10) == 0) ? "0" : "") + millis++);
        }
    }
}
