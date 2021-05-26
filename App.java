import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    public Stage primaryStage;
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        showLogin();
        this.primaryStage.show();
    }
    public void showLogin() throws Exception {
        Login login = new Login();
        login.start(this);
    }
    public static void main(String[] args) throws Exception {
        launch();
    }
    public void showTimer() {
        Timer timer = new Timer();
        try {
            timer.start(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void showScores() {
        Scores scores = new Scores();
        try {
            scores.startScores(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}