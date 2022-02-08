package Scenes;

import Module.DataBase;
import Module.Product;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ModifyProductScene {

    Stage windowModify;
    Label titleL;
    Label errorL;
    Button editB;
    Button cancelB;
    Scene scene;
    VBox layout;

    ObservableList<Product> products;
    TableView<Product> productTableView;

    void showScene()
    {
        windowModify = new Stage();
        windowModify.initModality(Modality.APPLICATION_MODAL);

        titleL = new Label("Users");
        titleL.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #BB2020;");

        products = FXCollections.observableArrayList();
        products.addAll(DataBase.getProducts());

        setTable();

        errorL = new Label("");
        errorL.setStyle("-fx-text-fill: #ff5050;");

        editB = new Button("Edit");
        editB.setStyle("-fx-background-color: #2050bb; -fx-text-fill: #eeeeee;");
        editB.setPrefWidth(64);
        editB.setOnAction(actionEvent -> {
            editProduct();
        });

        Pane spacer2 = new Pane();
        HBox.setHgrow(spacer2, Priority.ALWAYS);
        HBox addHBox = new HBox(15);
        addHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        addHBox.setAlignment(Pos.CENTER);
        addHBox.getChildren().addAll(spacer2, errorL, editB);

        cancelB = new Button("Exit");
        cancelB.setStyle("-fx-background-color: #bb2020; -fx-text-fill: #eeeeee;");
        cancelB.setPrefWidth(64);
        cancelB.setOnAction(actionEvent -> windowModify.close());

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox titleHBox = new HBox();
        titleHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        titleHBox.setAlignment(Pos.CENTER);
        titleHBox.getChildren().addAll(titleL, spacer, cancelB);

        layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(titleHBox, productTableView, addHBox);
        layout.setStyle("-fx-background-color: #F1F0EB;");

        scene = new Scene(layout, 380, 520);
        windowModify.setScene(scene);
        windowModify.showAndWait();


    }

    private void editProduct()
    {
        if (!productTableView.getSelectionModel().isEmpty())
        {
            errorL.setText("");
            Product product = productTableView.getSelectionModel().getSelectedItem();

            new EditProductsScene().showScene(product);

        }
    }

    void setTable()
    {
        TableColumn<Product, String> idColumn = new TableColumn<Product, String>("ID");
        idColumn.setMinWidth(100);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));

        TableColumn<Product, String> nameColumn = new TableColumn<Product, String>("Title");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Product, String> singerColumn = new TableColumn<Product, String>("Singer");
        singerColumn.setMinWidth(100);
        singerColumn.setCellValueFactory(new PropertyValueFactory<>("singer"));

        TableColumn<Product, String> genreColumn = new TableColumn<Product, String>("Genre");
        genreColumn.setMinWidth(100);
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));


        products = FXCollections.observableArrayList();
        products.addAll(DataBase.getProducts());

        productTableView = new TableView<Product>();
        productTableView.setItems(products);
        productTableView.getColumns().addAll(idColumn, nameColumn, singerColumn);

    }

}
