package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Pane root = new Pane();
        Scene scene = new Scene(root, Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);

        //root.setPrefWidth(Settings.FIELD_WIDTH);
        //root.setPrefHeight(Settings.FIELD_HEIGHT);

        primaryStage.setTitle("Space Explorer Garage");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(Settings.FULLSCREEN);
        primaryStage.show();

        Controller controller = new Controller(root,scene);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
