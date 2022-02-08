package Scenes;

import Module.*;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class Control extends Application {

    static Stage window;

    @Override
    public void start(Stage stage) throws Exception {

        window = stage;

        Image icon = new Image(new FileInputStream("resources/Images/logoFinal.png"));
        window.getIcons().add(icon);

        window.setTitle("CD-Store");

        window.setOnCloseRequest(windowEvent ->
        {
            DataBase.save();
        });

        MainMenu.showScene(window);
        window.show();

    }
}
