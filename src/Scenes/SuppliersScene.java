package Scenes;

import Module.DataBase;
import Module.Supplier;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SuppliersScene {

    Stage windowS;
    Scene scene;
    Label title;
    Label errorL;
    Button viewB;
    Button closeB;
    VBox layout;

    ListView<String> supplierListView;

    public void showScene()
    {
        windowS = new Stage();
        windowS.initModality(Modality.APPLICATION_MODAL);

        title = new Label("Suppliers");
        title.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #fd5e53;");

        setSupplierListView();

        errorL = new Label("");
        errorL.setStyle("-fx-text-fill: #ff5050;");

        viewB = new Button("View Products");
        viewB.setStyle("-fx-background-color: #2050bb; -fx-text-fill: #eeeeee;");
        viewB.setPrefWidth(92);
        viewB.setOnAction(actionEvent -> viewButtonPressed());

        Pane spacer2 = new Pane();
        HBox.setHgrow(spacer2, Priority.ALWAYS);
        HBox viewHBox = new HBox(15);
        viewHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        viewHBox.setAlignment(Pos.CENTER);
        viewHBox.setPadding(new Insets(5, 0, 5, 0));
        viewHBox.getChildren().addAll(spacer2, errorL, viewB);

        closeB = new Button("Close");
        closeB.setStyle("-fx-background-color: #bb2020; -fx-text-fill: #eeeeee;");
        closeB.setPrefWidth(76);
        closeB.setOnAction(actionEvent -> windowS.close());

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox titleHBox = new HBox();
        titleHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        titleHBox.setAlignment(Pos.CENTER);
        titleHBox.setPadding(new Insets(5, 0, 5, 0));
        titleHBox.getChildren().addAll(title, spacer, closeB);

        layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #F1F0EB;");
        layout.getChildren().addAll(titleHBox, supplierListView, viewHBox);

        scene = new Scene(layout, 360, 552);
        windowS.setScene(scene);
        windowS.showAndWait();
    }


    void setSupplierListView()
    {
        supplierListView = new ListView<>();

        for (Supplier s : DataBase.getSuppliers())
        {
            supplierListView.getItems().add(s.getName());

        }
    }

    void viewButtonPressed()
    {
        String name = "";

        if(!supplierListView.getSelectionModel().isEmpty())
        {
            errorL.setText("");
            name = supplierListView.getSelectionModel().getSelectedItem();
            new SuppliersCDScene().showScene(name);
        }
        else
            errorL.setText("Select a supplier!");
    }

}
