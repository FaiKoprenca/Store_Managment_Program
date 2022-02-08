package Scenes;

import Module.Product;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SearchedProductScene {

    Product product;
    Stage windowPD;
    Scene sceneP;
    VBox layout;
    Label productL;
    Label quantityL;
    Label spinnerL;
    Label errorL;
    Spinner<Integer> quantitySpinner;
    Button addB;
    Button cancelB;
    int quantity;

    public int showScene(Product product)
    {
        this.product = product;

        windowPD = new Stage();
        windowPD.initModality(Modality.APPLICATION_MODAL);

        setLayout();
        quantity = 0;

        addB.setOnAction(actionEvent -> addButtonClicked());
        cancelB.setOnAction(actionEvent -> windowPD.close());

        layout.setPadding(new Insets(28, 20, 0, 10));
        layout.setStyle("-fx-background-color: #F1F0EB;");
        sceneP = new Scene(layout, 360, 275);
        windowPD.setScene(sceneP);
        windowPD.showAndWait();

        return quantity;
    }

    void setLayout()
    {
        layout = new VBox(20);
        //layout.setStyle("-fx-background-color: #303030;");

        productL = new Label("");
        productL.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        productL.setAlignment(Pos.CENTER);
        productL.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #fd5e53;");

        quantityL = new Label("");
        quantityL.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        quantityL.setAlignment(Pos.CENTER);
        quantityL.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #fd5e53;");

        spinnerL = new Label("Select quantity:");

        errorL = new Label("");
        errorL.setStyle("-fx-text-fill: #ff5050;");
        errorL.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        errorL.setAlignment(Pos.CENTER_RIGHT);

        addB = new Button("Add");
        addB.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        addB.setStyle("-fx-background-color: #4056A1; -fx-text-fill: #eeeeee;");

        cancelB = new Button("Cancel");
        cancelB.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        cancelB.setStyle("-fx-background-color: #F13C20; -fx-text-fill: #eeeeee;");

        if(product.getName().equals(""))
        {
            productL.setText("No results");
            Pane spacer = new Pane();
            VBox.setVgrow(spacer, Priority.ALWAYS);
            VBox vBox = new VBox(10);
            vBox.setPadding(new Insets(90));
            vBox.getChildren().addAll(productL, spacer, cancelB);
            layout.getChildren().addAll(vBox);
        }
        else
        {
            productL.setText(product.getName() + " by: " + product.getSinger() /*+"\n" + "from: " + product.getSupplierID()*/);

            if(product.getQuantity() > 0)
            {
                quantityL.setText("Available: " + product.getQuantity());
                quantitySpinner = new Spinner<>(0, product.getQuantity(), 0);

                /*Pane spacer = new Pane();
                HBox.setHgrow(spacer, Priority.ALWAYS);
                Pane spacer1 = new Pane();
                HBox.setHgrow(spacer1, Priority.ALWAYS);
                Pane spacer2 = new Pane();
                HBox.setHgrow(spacer2, Priority.ALWAYS);*/

                HBox productHBox = new HBox(10);
                productHBox.setAlignment(Pos.CENTER);
                productHBox.getChildren().addAll( productL, quantityL);

                VBox spinnerVBox = new VBox(10);
                spinnerVBox.setAlignment(Pos.CENTER);
                spinnerVBox.getChildren().addAll(spinnerL, quantitySpinner, errorL);
                HBox spinnerHBox = new HBox();
                spinnerHBox.setAlignment(Pos.CENTER);
                spinnerHBox.getChildren().addAll( spinnerVBox);

                VBox buttonsVBox = new VBox(15);
                //buttonsVBox.setAlignment(Pos.CENTER);
                buttonsVBox.getChildren().addAll(addB, cancelB);
                HBox buttonsHBox = new HBox();
                buttonsHBox.setAlignment(Pos.CENTER);
                buttonsHBox.getChildren().addAll( buttonsVBox);

                layout.getChildren().addAll(productHBox, spinnerHBox, buttonsHBox);
            }
            else
            {
                quantityL.setText("This product is out of stock.");
                VBox vBox = new VBox(20);
                vBox.setPadding(new Insets(30));
                vBox.getChildren().addAll(productL, quantityL, cancelB);
                layout.getChildren().addAll(vBox);
            }
        }
    }

    void addButtonClicked()
    {
        if(quantitySpinner.getValue() < 1)
            errorL.setText("Select a number!");
        else
        {
            quantity = quantitySpinner.getValue();
            windowPD.close();
        }
    }

}
