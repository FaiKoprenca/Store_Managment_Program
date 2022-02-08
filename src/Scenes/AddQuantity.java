package Scenes;

import Module.DataBase;
import Module.BoughtProduct;
import Module.InputCheck;
import Module.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class AddQuantity {

    Stage windowAP;
    Scene scene;
    Label title;
    Button addButton;
    TextField quantityTF;
    Label selItemErrorL;
    Label notIntErrorL;
    VBox tableVBox;
    Button closeButton;
    HBox quantityHBox;
    VBox layout;

    ObservableList<Product> cdList;
    TableView<Product> cdTable;


    public void showScene()
    {
        windowAP = new Stage();
        windowAP.initModality(Modality.APPLICATION_MODAL);

        title = new Label("Add Product Quantity");
        title.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #fd5e53;");

        cdList = FXCollections.observableArrayList();
        cdList.addAll(DataBase.getProducts());

        setTable();

        selItemErrorL = new Label("");
        selItemErrorL.setStyle("-fx-text-fill: #fd5e53;");

        tableVBox = new VBox(5);
        tableVBox.getChildren().addAll(cdTable, selItemErrorL);

        quantityTF = new TextField();
        quantityTF.setPromptText("Quantity");
        //quantityTF.setStyle("-fx-background-color: #505050; -fx-text-fill: #eeeeee; -fx-prompt-text-fill: #888888;");

        notIntErrorL = new Label("");
        notIntErrorL.setStyle("-fx-text-fill: #fd5e53;");

        addButton = new Button("Add");
        addButton.setPrefWidth(76);
        //addButton.setStyle("-fx-background-color: #2050bb; -fx-text-fill: #eeeeee;");
        addButton.setOnAction(actionEvent -> {
            addCDQuantity();
        });

        quantityHBox = new HBox(10);
        quantityHBox.setMaxWidth(Double.MAX_VALUE);
        quantityHBox.setAlignment(Pos.CENTER_RIGHT);
        quantityHBox.getChildren().addAll(notIntErrorL, quantityTF, addButton);

        closeButton = new Button("Close");
        closeButton.setPrefWidth(76);
        //closeButton.setStyle("-fx-background-color: #bb2020; -fx-text-fill: #eeeeee;");
        closeButton.setOnAction(actionEvent -> windowAP.close());

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox titleHBox = new HBox();
        titleHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        titleHBox.setPadding(new Insets(0, 0, 10, 0));
        titleHBox.setAlignment(Pos.CENTER);
        titleHBox.getChildren().addAll(title, spacer, closeButton);

        layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #F1F0EB;");
        layout.getChildren().addAll(titleHBox, tableVBox, quantityHBox);

        scene = new Scene(layout, 376, 568);
        windowAP.setScene(scene);
        windowAP.showAndWait();

    }

    void setTable()
    {
        TableColumn<Product, String> nameC = new TableColumn<Product, String>("Name");
        nameC.setMinWidth(200);
        nameC.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Product, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(70);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        cdTable = new TableView<Product>();
        cdTable.setItems(cdList);
        cdTable.getColumns().addAll(nameC, quantityColumn);
    }

    void addCDQuantity()
    {
        String name = "";
        int quantity = 0;
        String input = quantityTF.getText();
        boolean isFieldCorrect = true;

        if(!cdTable.getSelectionModel().isEmpty())
        {
            name = cdTable.getSelectionModel().getSelectedItem().getName();
            selItemErrorL.setText("");
        }
        else
        {
            selItemErrorL.setText("Select a CD!");
            isFieldCorrect = false;
        }

        if(!input.isEmpty() && InputCheck.isInt(input))
        {
            quantity = Integer.parseInt(input);
            notIntErrorL.setText("");
        }
        else
        {
            notIntErrorL.setText("Enter a number!");
            isFieldCorrect = false;
        }


        if(isFieldCorrect)
        {
            for(Product addedProduct : DataBase.getProducts())
                if(name.equals(addedProduct.getName()))
                {
                    addedProduct.setQuantity(addedProduct.getQuantity() + quantity);

                    DataBase.getBoughtProducts().add(new BoughtProduct());
                    int addedBoughtProdIndex = DataBase.getBoughtProducts().size() - 1;
                    BoughtProduct addedBoughtProd = DataBase.getBoughtProducts().get(addedBoughtProdIndex);

                    addedBoughtProd.setName(addedProduct.getName());
                    addedBoughtProd.setSinger(addedProduct.getSinger());
                    addedBoughtProd.setGenre(addedProduct.getGenre());
                    addedBoughtProd.setID(addedProduct.getID());
                    addedBoughtProd.setSupplierID(addedProduct.getSupplierID());
                    addedBoughtProd.setPrice(addedProduct.getPrice());
                    addedBoughtProd.setBarcode(addedProduct.getBarcode());
                    addedBoughtProd.setQuantity(addedProduct.getQuantity());
                    addedBoughtProd.setReleaseDate(addedProduct.getReleaseDate());

                    break;
                }

            setTable();
            tableVBox.getChildren().set(0, cdTable);
        }

        quantityTF.clear();
    }

}
