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

public class DeleteProductScene {

    Stage window;
    Label titleLabel;
    Label errorLabel;
    Button deleteButton;
    Button cancelButton;
    Scene scene;
    VBox layout;

    ObservableList<Product> products;
    TableView<Product> productTableView;

    public void showScene()
    {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        titleLabel = new Label("Select user");
        titleLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #bb2020;");

        setTable();


        errorLabel = new Label("");
        errorLabel.setStyle("-fx-text-fill: #ff5050;");

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        deleteButton = new Button("Delete");
        deleteButton.setStyle("-fx-background-color: #bb2020; -fx-text-fill: #eeeeee;");
        deleteButton.setOnAction(actionEvent -> {
            deleteProduct();
        });
        HBox deleteHBox = new HBox(20);
        deleteHBox.getChildren().addAll(spacer, errorLabel, deleteButton);

        cancelButton = new Button("Exit");
        cancelButton.setStyle("-fx-background-color: #bb2020; -fx-text-fill: #eeeeee;");
        cancelButton.setOnAction(actionEvent -> window.close());

        Pane spacer1 = new Pane();
        HBox.setHgrow(spacer1, Priority.ALWAYS);
        HBox titleHBox = new HBox();
        titleHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        titleHBox.setAlignment(Pos.CENTER);
        titleHBox.setPadding(new Insets(5, 0, 5, 0));
        titleHBox.getChildren().addAll(titleLabel, spacer1, cancelButton);

        layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(titleHBox, productTableView, deleteHBox);
        layout.setStyle("-fx-background-color: #F1F0EB;");

        scene = new Scene(layout, 480, 540);
        window.setScene(scene);
        window.showAndWait();

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


        products = FXCollections.observableArrayList();
        products.addAll(DataBase.getProducts());

        productTableView = new TableView<Product>();
        productTableView.setItems(products);
        productTableView.getColumns().addAll(idColumn, nameColumn, singerColumn);

    }

    private void deleteProduct()
    {
        String name = "";

        if (!productTableView.getSelectionModel().isEmpty())
        {
            name = productTableView.getSelectionModel().getSelectedItem().getName();
            errorLabel.setText("");
        }
        else
        {
            errorLabel.setText("Select a CD");
            return;
        }

        for (int i=0; i < DataBase.getProducts().size(); i++)
        {
            Product product = DataBase.getProducts().get(i);

            if (name.equals(product.getName()))
            {
                DataBase.getProducts().remove(i);
                setTable();
                layout.getChildren().set(1, productTableView);
                return;
            }
        }
    }

}

