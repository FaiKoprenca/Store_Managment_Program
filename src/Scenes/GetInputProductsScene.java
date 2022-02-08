package Scenes;

import Module.DataBase;
import Module.Product;
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

public class GetInputProductsScene {
    Stage windowGI;
    Label titleL;
    TextField input;
    Label errorL;
    Button confirmB;
    Button cancelB;
    HBox buttonsHBox;
    Scene scene;
    VBox layout;
    String value;
    String instruction;

    public String showScene(String instruction) {
        this.instruction = instruction;
        value = "";

        windowGI = new Stage();
        windowGI.initModality(Modality.APPLICATION_MODAL);

        titleL = new Label("Change " + instruction);
        titleL.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #eeeeee;");
        titleL.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        titleL.setAlignment(Pos.CENTER);

        input = new TextField();
        input.setPromptText(instruction);
        input.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #eeeeee; -fx-prompt-text-fill: #888888;");

        errorL = new Label("");
        errorL.setStyle("-fx-text-fill: #ff5050;");

        VBox inputVBox = new VBox(5);
        inputVBox.getChildren().addAll(input, errorL);

        confirmB = new Button("Confirm");
        confirmB.setPrefWidth(96);
        confirmB.setStyle("-fx-background-color: #108010; -fx-text-fill: #eeeeee;");
        confirmB.setOnAction(actionEvent -> {
            setValue();
        });

        cancelB = new Button("Cancel");
        cancelB.setPrefWidth(96);
        cancelB.setStyle("-fx-background-color: #bb2020; -fx-text-fill: #eeeeee;");
        cancelB.setOnAction(actionEvent -> windowGI.close());

        buttonsHBox = new HBox(30);
        buttonsHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        buttonsHBox.setAlignment(Pos.CENTER);
        buttonsHBox.getChildren().addAll(cancelB, confirmB);

        layout = new VBox(30);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(titleL, inputVBox, buttonsHBox);
        layout.setStyle("-fx-background-color: #F1F0EB;");

        scene = new Scene(layout, 480, 200);
        windowGI.setScene(scene);
        windowGI.showAndWait();

        return value;
    }

    private void setValue()
    {
        if (instruction.equals("singer"))
        {
            if (!input.getText().isEmpty())
            {
                errorL.setText("please enter the user name");
            }
        }
        else
        {
            errorL.setText("Correct the Singer name (this is not error)");
        }

        if (instruction.equals("genre"))
        {
            if (!input.getText().isEmpty())
            {
                errorL.setText("notRight");
            }
        }
        else
        {
            errorL.setText("enter a new Genre");
        }
    }
}
