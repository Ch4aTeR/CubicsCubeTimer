import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

public class Scores{
    public Stage primaryStage;
    public ObservableList<Score> scores = FXCollections.observableArrayList();

    TableView<Score> table = new TableView<>();
    
    public String sql = "SELECT solves.id, solves.user_id, users.id, users.username, solves.time FROM solves JOIN users ON solves.user_id = users.id ORDER BY time ASC";

    public void dbSolves(){
    
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/LucasTimer", "root", "Zli12345");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);){
            int i = 0;		      
            while(rs.next()){
                i++;
                String user = rs.getString("users.username");
                Double time = rs.getDouble("solves.time");
                this.scores.add(new Score(i, user, time));
            }
        }catch (SQLException e){
         e.printStackTrace();
        } 
    }

    public void startScores(App app) throws Exception {
        this.primaryStage = app.primaryStage;

        dbSolves();

        VBox root = new VBox();
        HBox hbox1 = new HBox();
        Button backButton = new Button("Back");
        
        root.getChildren().add(hbox1);
        root.getChildren().add(backButton);

        TableColumn<Score, String> numberColumn = new TableColumn<>("Rank");
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        numberColumn.setMinWidth(50);
        numberColumn.setPrefWidth(50);

        TableColumn<Score, String> userColumn = new TableColumn<>("Username");
        userColumn.setCellValueFactory(new PropertyValueFactory<>("user"));
        userColumn.setMinWidth(175);
        userColumn.setPrefWidth(175);

        TableColumn<Score, String> timeColumn = new TableColumn<>("Time");
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        timeColumn.setMinWidth(175);
        timeColumn.setPrefWidth(175);

        table.getColumns().setAll(numberColumn, userColumn, timeColumn);
        table.setItems(this.scores);
        hbox1.getChildren().add(table);

        backButton.setOnAction(e -> {
            app.showTimer();
        });

        Scene scene = new Scene(root, 400, 300);

        primaryStage.show();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Scores");
    }
}