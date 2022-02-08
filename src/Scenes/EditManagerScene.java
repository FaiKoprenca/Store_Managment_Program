package Scenes;

import Module.DataBase;
import Module.Manager;
import Module.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EditManagerScene {

    Stage windowMS;
    Label titleL;
    Button cancelB;
    Scene scene;
    VBox layout;

    Button chUsernameB;
    Button chPasswordB;
    Button chPhoneNrB;
    Button chEmailB;
    Button chSalaryB;
    VBox buttonsVBox;

    String username;

    Manager manager;

    public void showScene(User user)
    {
        username = user.getUsername();
        getUser();

        windowMS = new Stage();
        windowMS.initModality(Modality.APPLICATION_MODAL);

        titleL = new Label("Edit Manager " + manager.getName());
        titleL.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #bb2020;");

        chUsernameB = new Button("Change username");
        chUsernameB.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        chUsernameB.setStyle("-fx-background-color: #2050bb; -fx-text-fill: #eeeeee;");
        chUsernameB.setOnAction(actionEvent -> {
            String newUsername = new GetInputScene().showScene("username");
            System.out.println(newUsername);
            changeUsername(newUsername);
            DataBase.save();
        });

        chPasswordB = new Button("Change password");
        chPasswordB.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        chPasswordB.setStyle("-fx-background-color: #2050bb; -fx-text-fill: #eeeeee;");
        chPasswordB.setOnAction(actionEvent -> {
            String newPassword = new ChangePassScene().showScene(manager.getPassword());
            changePassword(newPassword);
            DataBase.save();
        });

        chPhoneNrB = new Button("Change phone number");
        chPhoneNrB.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        chPhoneNrB.setStyle("-fx-background-color: #2050bb; -fx-text-fill: #eeeeee;");
        chPhoneNrB.setOnAction(actionEvent -> {
            String newPhoneNr = new GetInputScene().showScene("phone number");
            changePhoneNr(newPhoneNr);
            DataBase.save();
        });

        chEmailB = new Button("Change email");
        chEmailB.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        chEmailB.setStyle("-fx-background-color: #2050bb; -fx-text-fill: #eeeeee;");
        chEmailB.setOnAction(actionEvent -> {
            String newEmail = new GetInputScene().showScene("email");
            changeEmail(newEmail);
            DataBase.save();
        });

        chSalaryB = new Button("Change salary");
        chSalaryB.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        chSalaryB.setStyle("-fx-background-color: #2050bb; -fx-text-fill: #eeeeee;");
        chSalaryB.setOnAction(actionEvent -> {
            String newSalary = new GetInputScene().showScene("salary");
            changeSalary(newSalary);
            DataBase.save();
        });

        buttonsVBox = new VBox(10);
        buttonsVBox.getChildren().addAll(chUsernameB, chPasswordB, chPhoneNrB, chEmailB, chSalaryB);

        cancelB = new Button("Exit");
        cancelB.setStyle("-fx-background-color: #bb2020; -fx-text-fill: #eeeeee;");
        cancelB.setOnAction(actionEvent -> {
            windowMS.close();
            DataBase.save();
        });

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox titleHBox = new HBox();
        titleHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        titleHBox.setAlignment(Pos.CENTER);
        titleHBox.getChildren().addAll(titleL, spacer, cancelB);

        layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(titleHBox, buttonsVBox);
        layout.setStyle("-fx-background-color: #F1F0EB;");

        scene = new Scene(layout, 380, 280);
        windowMS.setScene(scene);
        windowMS.showAndWait();
    }

    void changeUsername(String newUsername)
    {
        if(!newUsername.equals(""))
            manager.setUsername(newUsername);
    }

    void changePassword(String newPassword)
    {
        if(!newPassword.equals(""))
            manager.setPassword(newPassword);
    }

    void changePhoneNr(String newPhoneNr)
    {
        if(!newPhoneNr.equals(""))
            manager.setPhoneNumber(newPhoneNr);
    }

    void changeEmail(String newEmail)
    {
        if(!newEmail.equals(""))
            manager.setEmail(newEmail);
    }

    void changeSalary(String newSalary)
    {
        if(!newSalary.equals(""))
            manager.setSalary(Double.parseDouble(newSalary));
    }

    void getUser()
    {
        manager = new Manager();

        for (Manager e : DataBase.getManagers())
        {
            if (username.equals(e.getUsername()))
            {
                manager = e;
                return;
            }
        }
    }

}

