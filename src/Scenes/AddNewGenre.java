package Scenes;

import Module.DataBase;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddNewGenre {

    Stage windowCS;
    Label instructionL;
    TextField genreTF;
    Label errorL;
    Button addButton;
    Button exitButton;
    HBox buttonsHBox;
    VBox layout;

    public void showScene()
    {
        windowCS = new Stage();
        windowCS.initModality(Modality.APPLICATION_MODAL);

        instructionL = new Label("Enter genre name:");
        //instructionL.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #eeeeee;");
        instructionL.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        instructionL.setAlignment(Pos.CENTER);

        genreTF = new TextField();
        genreTF.setPromptText("Genre");
        //genreTF.setStyle("-fx-background-color: #505050; -fx-text-fill: #eeeeee; -fx-prompt-text-fill: #888888;");

        errorL = new Label("");
        errorL.setStyle("-fx-text-fill: #ff5050;");

        VBox inputVBox = new VBox(5);
        inputVBox.getChildren().addAll(genreTF, errorL);

        addButton = new Button("Add");
        addButton.setPrefWidth(96);
        addButton.setStyle("-fx-background-color: #108010; -fx-text-fill: #eeeeee;");
        addButton.setOnAction(actionEvent -> add());

        exitButton = new Button("Cancel");
        exitButton.setPrefWidth(96);
        exitButton.setStyle("-fx-background-color: #bb2020; -fx-text-fill: #eeeeee;");
        exitButton.setOnAction(actionEvent -> windowCS.close());

        buttonsHBox = new HBox(30);
        buttonsHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        buttonsHBox.setAlignment(Pos.CENTER);
        buttonsHBox.getChildren().addAll(exitButton, addButton);

        layout = new VBox(30);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #F1F0EB;");
        layout.getChildren().addAll(instructionL, inputVBox, buttonsHBox);
        //layout.setStyle("-fx-background-color: #303030;");

        Scene scene = new Scene(layout, 480, 200);
        windowCS.setScene(scene);
        windowCS.showAndWait();
    }

    void add()
    {
        if (genreTF.getText().isEmpty())
        {
            errorL.setText("Please Enter A Genre!");
        }
        else
        {
            errorL.setText("");
            DataBase.getGenre().add(genreTF.getText());
            windowCS.close();
        }
    }
}
