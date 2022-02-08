package Scenes;

import Module.Manager;
import Module.Cashier;
import Module.DataBase;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MainMenu {

    static TextField usernameField;
    static PasswordField passwordField;

    static Button loginButton;
    static Button exitButton;
    static Label incorrectMessage;

    static Stage windowF;
    static Scene sceneF;

    public static void showScene(Stage primaryWindow) throws FileNotFoundException {

        MainMenu.windowF = primaryWindow;

        Image image = new Image(new FileInputStream("resources/Images/main2.0.jpg"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(480);
        imageView.setPreserveRatio(true);

        usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setStyle("-fx-background-color: #C5CBE3; -fx-text-fill: #282828; -fx-prompt-text-fill: #eeeeee;");

        passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setStyle("-fx-background-color: #C5CBE3; -fx-text-fill: #282828; -fx-prompt-text-fill: #eeeeee;");

        incorrectMessage = new Label("");
        incorrectMessage.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        incorrectMessage.setAlignment(Pos.CENTER);
        incorrectMessage.setStyle("-fx-text-fill: #ff5050;");

        loginButton = new Button("Login");
        loginButton.setMaxSize(160, 98);
        loginButton.setStyle("-fx-background-color: #4056A1; -fx-text-fill: #eeeeee;");

        loginButton.setOnAction(actionEvent -> {
            try {
                login(usernameField.getText(), passwordField.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        exitButton = new Button("Exit");
        exitButton.setMaxSize(160, 98);
        exitButton.setStyle("-fx-background-color: #F13C20; -fx-text-fill: #eeeeee;");
        exitButton.setOnAction(actionEvent -> {
            DataBase.save();
            primaryWindow.close();
        });


        VBox inputVBox = new VBox(10);
        inputVBox.setPadding(new Insets(10, 0, 0, 0));
        inputVBox.getChildren().addAll(usernameField, passwordField, incorrectMessage);

        VBox buttonsVBox = new VBox(10);
        buttonsVBox.setAlignment(Pos.CENTER);
        buttonsVBox.getChildren().addAll(loginButton, exitButton);
        buttonsVBox.setPadding(new Insets(0, 50, 0 ,50));

        /*VBox imageBox = new VBox(20);
        imageBox.setAlignment(Pos.CENTER);
        imageBox.setPadding(new Insets(30, 40, 10, 40));
        imageBox.getChildren().add(imageView);
        imageBox.setStyle("-fx-background-color: #483D8B");*/

        VBox layout = new VBox(35);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30, 40, 10, 40));
        layout.getChildren().addAll(imageView, inputVBox, buttonsVBox);
        layout.setStyle("-fx-background-color: #F1F0EB;");                   //#303030

        sceneF = new Scene(layout, 720, 640);
        MainMenu.windowF.setScene(sceneF);

    }

    static void login(String username, String password) throws FileNotFoundException {
        if (username.equals("admin"))
        {
            if (password.equals("admin123"))
            {
                new AdminScene().showScene(windowF);
                System.out.println("adminScene");
                return;
            }
        }

        for(Cashier c : DataBase.getCashiers())
            if(username.equals(c.getUsername()))
                if(password.equals(c.getPassword()))
                {
                    new CashierScene().showScene(c, windowF);
                    //new CashierScene().showScene(c, windowF);
                    System.out.println("cashier ********************************************");
                    //todo cashierScene
                    return;
                }

        for(Manager e : DataBase.getManagers())
            if(username.equals(e.getUsername()))
                if(password.equals(e.getPassword()))
                {
                    System.out.println("manager**********************************");
                    new ManagerScene().showSceneM(e, windowF);
                    return;
                }

        incorrectMessage.setText("Incorrect username or password.");
    }
}
