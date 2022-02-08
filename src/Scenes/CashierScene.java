package Scenes;

import Module.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class CashierScene {

    Stage window;
    Cashier cashier;
    Product searchedProduct;
    Scene scene;
    VBox billVBox;
    VBox layout;
    Label nameLabel;

    TextField searchBar;
    Button searchButton;
    Button deleteButton;
    Button printBillButton;
    Button logoutButton;
    ObservableList<ProductInCart> cart;
    TableView<ProductInCart> cartView;
    String billPath;

    Button allProducts;

    public void showScene(Cashier cashier, Stage window) throws FileNotFoundException
    {
        this.window = window;
        this.cashier = cashier;

        nameLabel = new Label(cashier.getName());
        nameLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        nameLabel.setAlignment(Pos.CENTER_LEFT);
        nameLabel.setPadding(new Insets(5));
        nameLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #bb2020;");

        cart = FXCollections.observableArrayList();
        setTable();

        searchBar = new TextField();
        searchBar.setPromptText("Search product");
        //searchBar.setStyle("-fx-background-color: #505050; -fx-text-fill: #eeeeee; -fx-prompt-text-fill: #888888;");

        searchButton = new Button("Search");
        //searchButton.setStyle("-fx-background-color: #2050bb; -fx-text-fill: #eeeeee;");
        searchButton.setOnAction(actionEvent -> {
            searchedProduct = findSearchedProduct(searchBar.getText());
            int quantity = new SearchedProductScene().showScene(searchedProduct);
            addProductToCart(searchedProduct, quantity);
        });

        Pane pane1 = new Pane();
        HBox.setHgrow(pane1, Priority.ALWAYS);
        HBox searchHBox = new HBox();
        searchHBox.getChildren().addAll(pane1, searchButton);

        VBox searchVBox = new VBox(10);
        searchVBox.getChildren().addAll(searchBar, searchHBox);

        deleteButton = new Button("Remove");
        deleteButton.setDisable(true);
        deleteButton.setStyle("-fx-background-color: #F13C20; -fx-text-fill: #eeeeee;");
        deleteButton.setOnAction(actionEvent -> removeFromCart());

        allProducts = new Button("All Products");
        allProducts.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        allProducts.setAlignment(Pos.CENTER);
        allProducts.setStyle("-fx-background-color: #bb2020; -fx-text-fill: #eeeeee;");
        allProducts.setOnAction(actionEvent -> {
            new AllProductsScene().showScene();
        });

        printBillButton = new Button("Print bill");
        printBillButton.setDisable(true);
        printBillButton.setStyle("-fx-background-color: #4056A1; -fx-text-fill: #eeeeee;");
        printBillButton.setOnAction(actionEvent -> {
            printBill();
        });

        logoutButton = new Button("Logout");
        logoutButton.setStyle("-fx-background-color: #F13C20; -fx-text-fill: #eeeeee;");
        logoutButton.setOnAction(actionEvent -> {
            onExit();
            window.setOnCloseRequest(e -> DataBase.save());
            try { MainMenu.showScene(window); }
            catch(FileNotFoundException e) { e.printStackTrace(); }
        });

        this.window.setOnCloseRequest(e -> {
            onExit();
            DataBase.save();
        });

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox titleHBox = new HBox();
        titleHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        titleHBox.setAlignment(Pos.CENTER);
        titleHBox.setPadding(new Insets(5, 0, 5, 0));
        titleHBox.getChildren().addAll(allProducts, spacer, logoutButton);

        Pane spacer2 = new Pane();
        HBox.setHgrow(spacer2, Priority.ALWAYS);

        HBox buttonsHBox = new HBox(15);
        buttonsHBox.getChildren().addAll(spacer2, deleteButton, printBillButton);

        billVBox = new VBox(15);
        billVBox.getChildren().addAll(cartView, buttonsHBox);

        layout = new VBox(35);
        layout.setPadding(new Insets(10, 30, 10, 30));
        layout.getChildren().addAll(titleHBox, searchVBox, billVBox);
        layout.setStyle("-fx-background-color: #F1F0EB;");

        scene = new Scene(layout, 762, 650);
        this.window.setScene(scene);
    }

    void onExit()
    {
        for(ProductInCart pInCart : cart)
        {
            int quantity = pInCart.getQuantity();

            for (Product p : DataBase.getProducts())
                if(pInCart.getName().equals(p.getName()))
                {
                    p.setQuantity(p.getQuantity() + quantity);
                    break;
                }
        }
        cart.clear();
    }

    Product findSearchedProduct(String input)
    {
        Product product = new Product();
        product.setName("");
        product.setQuantity(0);
        product.setPrice(0.0);

        String productName = input.toLowerCase();

        for( Product p : DataBase.getProducts())
        {
            String pName = p.getName().toLowerCase();

            if (productName.equals(pName)) {
                product = p;
                break;
            }
        }

        return product;
    }

    void addProductToCart(Product searchedProduct, int quantity)
    {
        if(quantity > 0)
        {
            deleteButton.setDisable(false);
            printBillButton.setDisable(false);

            boolean isProdInCart = false;

            for (ProductInCart productInCart : cart)
                if (searchedProduct.getName().equals(productInCart.getName()))
                {
                    productInCart.setQuantity(productInCart.getQuantity() + quantity);
                    isProdInCart = true;
                    break;
                }

            if(!isProdInCart)
                cart.add(new ProductInCart(searchedProduct.getName(), searchedProduct.getID(), searchedProduct.getPrice(), quantity));

            searchedProduct.setQuantity(searchedProduct.getQuantity() - quantity);

            setTable();
            billVBox.getChildren().set(0, cartView);
        }

        searchBar.clear();
    }

    void removeFromCart()
    {
        ObservableList<ProductInCart> productsInCarts = cart;
        ObservableList<ProductInCart> productSelected = cartView.getSelectionModel().getSelectedItems();

        for(ProductInCart pInCart : productSelected)
        {
            int quantity = pInCart.getQuantity();

            for (Product p : DataBase.getProducts())
                if(pInCart.getName().equals(p.getName()))
                {
                    p.setQuantity(p.getQuantity() + quantity);
                    break;
                }
        }

        productsInCarts.removeAll(productSelected);

        if(cart.size() < 1)
        {
            deleteButton.setDisable(true);
            printBillButton.setDisable(true);
        }
    }

    void printBill()
    {
        if(cart.size() > 0)
        {
            cashier.getBills().add(new Bill());
            int addedBillIndex = cashier.getBills().size() - 1;
            Bill bill = cashier.getBills().get(addedBillIndex);

            double totalBillPrice = 0;

            for(ProductInCart productInCart : cart)
            {
                totalBillPrice += productInCart.getTotalPrice();
                boolean doesProdExist = false;

                for(SoldProduct soldProduct : cashier.getSoldProduct())
                    if (productInCart.getName().equals(soldProduct.getName()))
                    {
                        doesProdExist = true;
                        soldProduct.setQuantity(soldProduct.getQuantity() + productInCart.getQuantity());
                    }

                if(!doesProdExist)
                {
                    cashier.getSoldProduct().add(new SoldProduct());
                    int addedProductIndex = cashier.getSoldProduct().size() - 1;
                    SoldProduct sp = cashier.getSoldProduct().get(addedProductIndex);

                    sp.setName(productInCart.getName());
                    sp.setProductID(productInCart.getProductID());
                    sp.setQuantity(productInCart.getQuantity());
                    sp.setPrice(productInCart.getPrice());
                    sp.setBillNumber(bill.getBillNo());
                }
            }

            bill.setPrice(totalBillPrice);
            billPath = "resources/Bills/bill" + bill.getBillNo() + ".txt";

            try
            {
                FileWriter fw = new FileWriter(billPath);
                BufferedWriter bw = new BufferedWriter(fw);

                for(ProductInCart pInCart : cart)
                    bw.write(pInCart.getName() + " | Quantity: " + pInCart.getQuantity() + " | Unit Price: " +
                            pInCart.getPrice() + "$ | Total Price: " + pInCart.getTotalPrice() + "$\n");
                bw.write("Total price: " + bill.getPrice() + "$");

                bw.close();
                fw.close();
            }
            catch (Exception e)
            {
                System.out.println("Something went wrong while printing the bill");
            }

            cart.clear();
            deleteButton.setDisable(true);
            printBillButton.setDisable(true);
            setTable();
        }
    }

    void setTable()
    {
        TableColumn<ProductInCart, String> nameColumn = new TableColumn<>("Product");
        nameColumn.setMinWidth(250);
        //nameColumn.setStyle("-fx-background-color: linear-gradient(to bottom, #EFE2BA, #eeeeee);");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<ProductInCart, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(150 );
        //quantityColumn.setStyle("-fx-background-color: linear-gradient(to bottom, #EFE2BA, #eeeeee);");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<ProductInCart, Double> priceColumn = new TableColumn<>("CD Price");
        priceColumn.setMinWidth(150);
        //priceColumn.setStyle("-fx-background-color: linear-gradient(to bottom, #EFE2BA, #eeeeee);");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<ProductInCart, Double> totalColumn = new TableColumn<>("Total Price");
        totalColumn.setMinWidth(150);
        totalColumn.setStyle("-fx-color: #4056A1;");
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        cartView = new TableView<>();
        cartView.setItems(cart);
        cartView.setStyle("-fx-selection-bar-non-focused: salmon;");
        cartView.getColumns().addAll(nameColumn, quantityColumn, priceColumn, totalColumn);
        cartView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
}
