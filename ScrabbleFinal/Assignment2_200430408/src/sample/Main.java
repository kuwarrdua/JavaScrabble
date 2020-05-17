package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {

    static Scene startPage,mainPage,rulePage;

    @Override
    public void start(Stage primaryStage) {
        try {

            Parent root1 = FXMLLoader.load(getClass().getResource("start.fxml"));
            startPage = new Scene(root1);
            Parent root2 = FXMLLoader.load(getClass().getResource("app.fxml"));
            mainPage = new Scene(root2);
            Parent root3 = FXMLLoader.load(getClass().getResource("rules.fxml"));
            rulePage = new Scene(root3);

            primaryStage.setScene(startPage);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        launch(args);
    }

    public static Scene getStartPage() {
        return startPage;
    }

    public static Scene getMainPage() {
        return mainPage;
    }

    public static Scene getRulePage() {
        return rulePage;
    }


}
