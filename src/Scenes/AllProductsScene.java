package Scenes;

import Module.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Scale;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class AllProductsScene {

    Stage windowAP;
    Label titleLabel;
    Label cdLabel;
    Button cancelButton;
    VBox cdVBox;
    Scene scene;
    VBox layout;
    ObservableList<Product> products;
    TableView<Product> productTableView;

    public void showScene()
    {
        windowAP = new Stage();
        windowAP.initModality(Modality.APPLICATION_MODAL);

        titleLabel = new Label("All Available CDs");
        titleLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #BB2020;");

        setProductsTable();

        cancelButton = new Button("Exit");
        cancelButton.setStyle("-fx-background-color: #bb2020; -fx-text-fill: #eeeeee;");
        cancelButton.setOnAction(actionEvent -> {
            windowAP.close();
        });

        cdLabel = new Label("CDs");
        cdLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #BB2020;");
        cdVBox = new VBox(10);
        cdVBox.getChildren().addAll(cdLabel, productTableView);

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox titleHBox = new HBox();
        titleHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        titleHBox.setAlignment(Pos.CENTER);
        titleHBox.setPadding(new Insets(5, 5, 10, 5));
        titleHBox.getChildren().addAll(titleLabel, spacer, cancelButton);

        layout = new VBox(20);
        layout.setPadding(new Insets(25, 15, 25, 15));
        layout.setStyle("-fx-background-color: #F1F0EB;");
        layout.getChildren().addAll(titleHBox, cdVBox);

        scene = new Scene(layout,890, 500 );
        windowAP.setScene(scene);
        windowAP.showAndWait();

    }

    void setProductsTable()
    {
        TableColumn<Product, String> idColumn = new TableColumn<Product, String>("ID");
        idColumn.setMinWidth(100);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));

        TableColumn<Product, String> nameColumn = new TableColumn<Product, String>("Name");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Product, String> genreColumn = new TableColumn<Product, String>("Genre");
        genreColumn.setMinWidth(100);
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));

        TableColumn<Product ,String> singerColumn = new TableColumn<Product, String>("Singer");
        singerColumn.setMinWidth(100);
        singerColumn.setCellValueFactory(new PropertyValueFactory<>("singer"));

        TableColumn<Product, String> priceColumn = new TableColumn<Product, String>("Price");
        priceColumn.setMinWidth(100);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Product, String> quantityColumn = new TableColumn<Product, String>("Quantity");
        quantityColumn.setMinWidth(100);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<Product, String> barcodeColumn = new TableColumn<Product, String>("Barcode");
        barcodeColumn.setMinWidth(150);
        barcodeColumn.setCellValueFactory(new PropertyValueFactory<>("barcode"));

        TableColumn<Product, String> dateColumn = new TableColumn<Product, String>("release Date");
        dateColumn.setMinWidth(130);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));


        products = FXCollections.observableArrayList();
        products.addAll(DataBase.getProducts());

        productTableView = new TableView<Product>();
        productTableView.setItems(products);
        productTableView.getColumns().addAll(idColumn, nameColumn, genreColumn, singerColumn, priceColumn, quantityColumn, barcodeColumn, dateColumn);
    }
}
