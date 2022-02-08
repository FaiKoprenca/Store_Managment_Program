package Scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ChangePassScene {

    Stage windowChP;
    Label titleL;
    Label oldPassErrL;
    Label newPassErrL;
    PasswordField oldPassTF;
    PasswordField newPassTF;
    Button confirmB;
    Button cancelB;
    VBox oldPassHBox;
    VBox newPassHBox;
    HBox buttonsHBox;
    Scene scene;
    VBox layout;
    String value;
    String oldPassword;

    public String showScene(String oldPassword)
    {
        value = "";
        this.oldPassword = oldPassword;

        windowChP = new Stage();
        windowChP.initModality(Modality.APPLICATION_MODAL);


        titleL = new Label("Change password");
        titleL.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #eeeeee;");
        titleL.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        titleL.setAlignment(Pos.CENTER);

        oldPassTF = new PasswordField();
        oldPassTF.setPromptText("Current password");
        //oldPassTF.setStyle("-fx-background-color: #505050; -fx-text-fill: #eeeeee; -fx-prompt-text-fill: #888888;");
        oldPassErrL = new Label("");
        //oldPassErrL.setStyle("-fx-text-fill: #ff5050;");

        oldPassHBox = new VBox(5);
        oldPassHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        oldPassHBox.getChildren().addAll(oldPassTF, oldPassErrL);

        newPassTF = new PasswordField();
        newPassTF.setPromptText("New password");
        //newPassTF.setStyle("-fx-background-color: #505050; -fx-text-fill: #eeeeee; -fx-prompt-text-fill: #888888;");
        newPassErrL = new Label("");
        //newPassErrL.setStyle("-fx-text-fill: #ff5050;");

        newPassHBox = new VBox(5);
        newPassHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        newPassHBox.getChildren().addAll(newPassTF, newPassErrL);


        confirmB = new Button("Confirm");
        confirmB.setStyle("-fx-background-color: #108010; -fx-text-fill: #eeeeee;");
        confirmB.setPrefWidth(96);
        confirmB.setOnAction(actionEvent -> setValue());

        cancelB = new Button("Cancel");
        cancelB.setStyle("-fx-background-color: #bb2020; -fx-text-fill: #eeeeee;");
        cancelB.setPrefWidth(96);
        cancelB.setOnAction(actionEvent -> windowChP.close());

        buttonsHBox = new HBox(30);
        buttonsHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        buttonsHBox.setAlignment(Pos.CENTER);
        buttonsHBox.getChildren().addAll(cancelB, confirmB);

        layout = new VBox(30);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(titleL, oldPassHBox, newPassHBox, buttonsHBox);
        layout.setStyle("-fx-background-color: #303030;");

        scene = new Scene(layout, 480, 360);
        windowChP.setScene(scene);
        windowChP.showAndWait();

        return value;
    }

    void setValue()
    {
        boolean areFieldsCorrect = true;

        if(oldPassTF.getText().equals(oldPassword))
            oldPassErrL.setText("");
        else
        {
            areFieldsCorrect = false;
            oldPassErrL.setText("Incorrect password");
        }

        if(!newPassTF.getText().isEmpty())
            newPassErrL.setText("");
        else
        {
            areFieldsCorrect = false;
            newPassErrL.setText("Enter the new password!");
        }

        if(areFieldsCorrect)
        {
            value = newPassTF.getText();
            windowChP.close();
        }
    }
}
