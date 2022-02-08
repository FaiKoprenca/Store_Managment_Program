package Scenes;

import Module.Manager;
import Module.Product;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class ManagerScene {

    Manager manager;
    Scene scene;
    Label title;

    Button loggOutB;
    Button addProductButton;
    Button addQuantityButton;
    Button addGenreB;
    Button showCashierStatsButton;
    Button showProductsSoldButton;
    Button showProductsBoughtButton;
    Button showSuppliersButton;

    Button modifyProductsB;
    Button allProducts;
    Button deleteProductB;

    VBox statsVbox;
    VBox addVBox;
    HBox titleHBox;
    HBox allButtons;
    VBox layout;
    VBox productVbox;


    public void showSceneM(Manager manager, Stage mStage)
    {
        this.manager = manager;

        title = new Label(manager.getName());
        setManagerLayout(mStage);

        layout = new VBox(35);
        layout.setPadding(new Insets(10, 30, 10, 30));
        layout.getChildren().addAll(titleHBox, allButtons);
        layout.setStyle("-fx-background-color: #F1F0EB;");

        scene = new Scene(layout, 460, 280);
        mStage.setScene(scene);

        //todo lowQuantityProds   check Products Quantity
    }

    void setManagerLayout(Stage window)
    {
        title.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #5157A0;");

        addProductButton = new Button("Add New CD");
        addProductButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        addProductButton.setPrefWidth(128);
        addProductButton.setAlignment(Pos.CENTER);
        addProductButton.setStyle("-fx-background-color: #F13C20; -fx-text-fill: #eeeeee;");
        addProductButton.setOnAction(actionEvent -> {
            new AddProductScene().showScene();
        });

        allProducts = new Button("All Products");
        allProducts.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        allProducts.setAlignment(Pos.CENTER);
        allProducts.setStyle("-fx-background-color: #F13C20; -fx-text-fill: #eeeeee;");
        allProducts.setOnAction(actionEvent -> {
            new AllProductsScene().showScene();
        });

        modifyProductsB = new Button("Modify Products");
        modifyProductsB.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        modifyProductsB.setPrefWidth(128);
        modifyProductsB.setStyle("-fx-background-color: #F13C20; -fx-text-fill: #eeeeee;");
        modifyProductsB.setAlignment(Pos.CENTER);
        modifyProductsB.setOnAction(actionEvent -> {
            new ModifyProductScene().showScene();
        });

        addQuantityButton = new Button("Add Quantity");
        addQuantityButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        addQuantityButton.setPrefWidth(128);
        addQuantityButton.setAlignment(Pos.CENTER);
        addQuantityButton.setStyle("-fx-background-color: #2050bb; -fx-text-fill: #eeeeee;");
        addQuantityButton.setOnAction(actionEvent -> {
            new AddQuantity().showScene();
        });

        addGenreB = new Button("Add Genre");
        addGenreB.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        addGenreB.setPrefWidth(128);
        addGenreB.setAlignment(Pos.CENTER);
        addGenreB.setStyle("-fx-background-color: #2050bb; -fx-text-fill: #eeeeee;");
        addGenreB.setOnAction(actionEvent -> {
            new AddNewGenre().showScene();
        });

        showCashierStatsButton = new Button("Cashier stats");
        showCashierStatsButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        showCashierStatsButton.setPrefWidth(128);
        showCashierStatsButton.setAlignment(Pos.CENTER);
        showCashierStatsButton.setStyle("-fx-background-color: #2050bb; -fx-text-fill: #eeeeee;");
        showCashierStatsButton.setOnAction(actionEvent -> {
            new CashierStatsSC().showScene();
        });


        showProductsSoldButton = new Button("Products sold");
        showProductsSoldButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        showProductsSoldButton.setPrefWidth(128);
        showProductsSoldButton.setAlignment(Pos.CENTER);
        showProductsSoldButton.setStyle("-fx-background-color: #2050bb; -fx-text-fill: #eeeeee;");
        showProductsSoldButton.setOnAction(actionEvent -> {
            new CDSoldScene().showScene();
        });


        showProductsBoughtButton = new Button("Products bought");
        showProductsBoughtButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        showProductsBoughtButton.setPrefWidth(128);
        showProductsBoughtButton.setAlignment(Pos.CENTER);
        showProductsBoughtButton.setStyle("-fx-background-color: #2050bb; -fx-text-fill: #eeeeee;");
        showProductsBoughtButton.setOnAction(actionEvent -> {
            new CDBoughtScene().showScene();
        });


        showSuppliersButton = new Button("Suppliers");
        showSuppliersButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        showSuppliersButton.setPrefWidth(128);
        showSuppliersButton.setAlignment(Pos.CENTER);
        showSuppliersButton.setStyle("-fx-background-color: #2050bb; -fx-text-fill: #eeeeee;");
        showSuppliersButton.setOnAction(actionEvent -> {
            new SuppliersScene().showScene();
        });

        deleteProductB = new Button("Delete Product");
        deleteProductB.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        deleteProductB.setPrefWidth(128);
        deleteProductB.setAlignment(Pos.CENTER);
        deleteProductB.setStyle("-fx-background-color: #F13C20; -fx-text-fill: #eeeeee;");
        deleteProductB.setOnAction(actionEvent -> {
            new DeleteProductScene().showScene();
        });

        loggOutB = new Button("LogOut");
        loggOutB.setStyle(("-fx-background-color: #bb2020; -fx-text-fill: #eeeeee;"));
        loggOutB.setOnAction(actionEvent -> {
            try {
                MainMenu.showScene(window);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        titleHBox = new HBox();
        titleHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        titleHBox.setAlignment(Pos.CENTER);
        titleHBox.setPadding(new Insets(5, 0, 5, 0));
        titleHBox.getChildren().addAll(title, spacer, loggOutB);

        statsVbox = new VBox(10);
        statsVbox.getChildren().addAll(showCashierStatsButton, showProductsSoldButton, showProductsBoughtButton, showSuppliersButton);

        productVbox = new VBox(10);
        productVbox.getChildren().addAll(allProducts, addProductButton, modifyProductsB, deleteProductB);

        addVBox = new VBox(10);
        addVBox.getChildren().addAll(/*allProducts, addProductButton, modifyProductsB,*/ addQuantityButton, addGenreB/*, deleteProductB*/);

        Pane spacer2 = new Pane();
        HBox.setHgrow(spacer2, Priority.ALWAYS);
        Pane spacer3 = new Pane();
        HBox.setHgrow(spacer3, Priority.ALWAYS);
        allButtons = new HBox();
        allButtons.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        allButtons.setAlignment(Pos.CENTER);
        allButtons.getChildren().addAll(statsVbox, spacer2, addVBox, spacer3, productVbox);
    }
}
