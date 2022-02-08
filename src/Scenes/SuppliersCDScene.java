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

import java.util.ArrayList;

public class SuppliersCDScene {

    Stage windowSS;
    Scene scene;
    Label title;
    Button closeButton;
    VBox layout;

    String supplierName;

    ListView<String> stringListView;

    public void showScene(String supplierName)
    {
        this.supplierName = supplierName;

        windowSS = new Stage();
        windowSS.initModality(Modality.APPLICATION_MODAL);

        title = new Label("Products");
        title.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #fd5e53;");

        setSuppliersL();

        closeButton = new Button("Close");
        //closeButton.setStyle("-fx-background-color: #bb2020; -fx-text-fill: #eeeeee;");
        closeButton.setPrefWidth(76);
        closeButton.setOnAction(actionEvent -> windowSS.close());

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox titleHBox = new HBox();
        titleHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        titleHBox.setAlignment(Pos.CENTER);
        titleHBox.setPadding(new Insets(5, 0, 5, 0));
        titleHBox.getChildren().addAll(title, spacer, closeButton);

        layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(titleHBox, stringListView);
        layout.setStyle("-fx-background-color: #F1F0EB;");

        scene = new Scene(layout, 360, 512);
        windowSS.setScene(scene);
        windowSS.showAndWait();
    }

    void setSuppliersL()
    {
        stringListView = new ListView<>();
        Supplier selectedSupplier = new Supplier("");

        for(Supplier s : DataBase.getSuppliers())
            if(supplierName.equals(s.getName()))
                selectedSupplier = s;

        ArrayList<String> supplierProducts = selectedSupplier.getProductsOffered();

        for(String s : supplierProducts)
            stringListView.getItems().add(s);

    }
}
