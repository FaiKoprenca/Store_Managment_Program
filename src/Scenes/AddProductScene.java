package Scenes;

import Module.*;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddProductScene {

    Stage windowAP;
    Scene sceneAP;
    VBox layout;
    Label title;
    Button exitB;

    TextField nameTF;
    Label fillNameError;

    TextField singerTF;
    Label fillSingerError;

    ChoiceBox<String> genreChBox;
    Label fillCategoryError;

    TextField supplierIDTF;
    Label fillSupplIDError;

    TextField priceTF;
    Label fillPriceError;

    TextField barcodeTF;
    Label fillBarcodeError;

    TextField quantityTF;
    Label fillQuantityError;



    Label yearL;
    ChoiceBox<String> yearChoiceBox;

    Label monthL;
    ChoiceBox<String> monthChoiceBox;

    Label dayL;
    ChoiceBox<String> dayChoiceBox;

    Label releaseDateErrLabel;

    VBox yearVBox;
    VBox monthVBok;
    VBox dayVBox;

    VBox nameVBox;
    VBox singerVBox;
    VBox genreVBox;
    VBox supplierIDVBox;
    VBox priceVBox;
    VBox barcodeVBox;
    VBox quantityVBox;
    HBox releaseDateHBox;

    Button addButton;

    Label releaseDL;

    public void showScene()
    {
        windowAP = new Stage();
        windowAP.initModality(Modality.APPLICATION_MODAL);

        setFields();

        monthChoiceBox.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> observable, String oldValue, String newValue) -> addDaysInDayChBox());
        yearChoiceBox.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> observable, String oldValue, String newValue) -> addDaysInDayChBox());

        addButton = new Button("Add product");
        addButton.setStyle("-fx-background-color: #2050bb; -fx-text-fill: #eeeeee;");
        addButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        addButton = new Button("Add product");
        addButton.setStyle("-fx-background-color: #2050bb; -fx-text-fill: #eeeeee;");
        addButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        addButton.setOnAction(actionEvent -> addNewCD());

        VBox addButtonHBox = new VBox();
        addButtonHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        addButtonHBox.setPadding(new Insets(0, 150, 0, 150));
        addButtonHBox.setAlignment(Pos.CENTER);
        addButtonHBox.getChildren().add(addButton);

        exitB = new Button("Cancel");
        exitB.setStyle("-fx-background-color: #bb2020; -fx-text-fill: #eeeeee;");
        exitB.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        exitB.setOnAction(actionEvent -> windowAP.close());

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox titleHBox = new HBox();
        titleHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        titleHBox.setPadding(new Insets(0, 0, 15, 0));
        titleHBox.setAlignment(Pos.CENTER);
        titleHBox.getChildren().addAll(title, spacer, exitB);

        layout = new VBox(16);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #F1F0EB;");
        layout.getChildren().addAll(titleHBox, nameVBox, singerVBox, genreVBox, priceVBox, quantityVBox, releaseDateHBox, barcodeVBox, supplierIDVBox, addButtonHBox);

        sceneAP = new Scene(layout, 480, 720);

        windowAP.setScene(sceneAP);
        windowAP.showAndWait();

    }

    void addNewCD()
    {


        String name = "";
        String genre = "";
        String singer = "";
        int supplierID = 0;
        double price = 0.0;
        String barcode = "";
        int quantity = 0;

        int day = 0;
        int month = 0;
        int year = 0;

        boolean isFieldCorrect = true;

        if(nameTF.getText().isEmpty())
        {
            fillNameError.setText("Enter CD title");
            isFieldCorrect = false;
        }
        else
        {
            /*if(isOnList(nameTF.getText()))
            {
                fillNameError.setText("This title already exists");
                isFieldCorrect = false;
            }
            else
            {
                name = nameTF.getText();
                fillNameError.setText("");
            }*/

            name = nameTF.getText();
            fillNameError.setText("");
        }

        if (singerTF.getText().isEmpty())
        {
            fillSingerError.setText("Enter a Singer");
            isFieldCorrect = false;
        }
        else
        {
            singer = singerTF.getText();
            fillSingerError.setText("");
        }

        if(genreChBox.getValue().equals("**********"))
        {
            fillCategoryError.setText("Select product category!");
            isFieldCorrect = false;
        }
        else
        {
            genre = genreChBox.getValue();
            fillCategoryError.setText("");
        }

        if(supplierIDTF.getText().isEmpty() || !InputCheck.isInt(supplierIDTF.getText()))
        {
            fillSupplIDError.setText("Enter supplier ID!");
            isFieldCorrect = false;
        }
        else
        {
            supplierID = Integer.parseInt(supplierIDTF.getText());
            fillSupplIDError.setText("");
        }

        if(priceTF.getText().isEmpty() || !InputCheck.isDouble(priceTF.getText()))
        {
            fillPriceError.setText("Enter CD price!");
            isFieldCorrect = false;
        }
        else
        {
            price = Double.parseDouble(priceTF.getText());
            fillPriceError.setText("");
        }

        if(barcodeTF.getText().isEmpty())
        {
            fillBarcodeError.setText("Enter CD barcode!");
            isFieldCorrect = false;
        }
        else
        {
            barcode = barcodeTF.getText();
            fillBarcodeError.setText("");
        }

        if(quantityTF.getText().isEmpty() || !InputCheck.isInt(quantityTF.getText()))
        {
            fillQuantityError.setText("Enter CD quantity!");
            isFieldCorrect = false;
        }
        else
        {
            quantity = Integer.parseInt(quantityTF.getText());
            fillQuantityError.setText("");
        }

        if(!yearChoiceBox.getValue().equals("**********") && !monthChoiceBox.getValue().equals("**********") && !dayChoiceBox.getValue().equals("**********"))
        {
            year = Integer.parseInt(yearChoiceBox.getValue());
            month = Integer.parseInt(monthChoiceBox.getValue());
            day = Integer.parseInt(dayChoiceBox.getValue());
            releaseDateErrLabel.setText("");
        }
        else
        {
            isFieldCorrect = false;
            releaseDateErrLabel.setText("Enter release date!");
        }


        if(isFieldCorrect)
        {
            DataBase.getProducts().add(new Product());
            int addedProdIndex = DataBase.getProducts().size() - 1;
            Product addedProduct = DataBase.getProducts().get(addedProdIndex);

            addedProduct.setName(name);
            addedProduct.setSinger(singer);
            addedProduct.setGenre(genre);
            addedProduct.setSupplierID(supplierID);
            addedProduct.setPrice(price);
            addedProduct.setBarcode(barcode);
            addedProduct.setQuantity(quantity);
            addedProduct.setReleaseDate(new Date(day, month, year));


            // Add product to products bought list on database
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


            windowAP.close();
        }

    }

    void setFields()
    {
        title = new Label("Add a new CD");
        title.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #fd5e53;");

        nameTF = new TextField();
        nameTF.setPromptText("Title:");
        //nameTF.setStyle("-fx-background-color: #505050; -fx-text-fill: #eeeeee; -fx-prompt-text-fill: #888888;");
        fillNameError = new Label();
        //fillNameError.setStyle("-fx-text-fill: #ff5050;");
        nameVBox = new VBox();
        nameVBox.getChildren().addAll(nameTF, fillNameError);

        singerTF = new TextField();
        singerTF.setPromptText("Singer:");
        fillSingerError = new Label();
        singerVBox = new VBox();
        singerVBox.getChildren().addAll(singerTF, fillSingerError);

        genreChBox = new ChoiceBox<>();
        genreChBox.getItems().add("Choose a genre");
        genreChBox.getItems().addAll(DataBase.getGenre());
        genreChBox.setValue("Choose a genre");
        genreChBox.setMaxWidth(Double.MAX_VALUE);
        fillCategoryError = new Label();
        //fillCategoryError.setStyle("-fx-text-fill: #ff5050;");
        genreVBox = new VBox();
        genreVBox.getChildren().addAll(genreChBox, fillCategoryError);

        supplierIDTF = new TextField();
        supplierIDTF.setPromptText("Supplier ID");
        //supplierIDTF.setStyle("-fx-background-color: #505050; -fx-text-fill: #eeeeee; -fx-prompt-text-fill: #888888;");
        fillSupplIDError = new Label();
        //fillSupplIDError.setStyle("-fx-text-fill: #ff5050;");
        supplierIDVBox = new VBox();
        supplierIDVBox.getChildren().addAll(supplierIDTF, fillSupplIDError);

        priceTF = new TextField();
        priceTF.setPromptText("Price");
        //priceTF.setStyle("-fx-background-color: #505050; -fx-text-fill: #eeeeee; -fx-prompt-text-fill: #888888;");
        fillPriceError = new Label();
        //fillPriceError.setStyle("-fx-text-fill: #ff5050;");
        priceVBox = new VBox();
        priceVBox.getChildren().addAll(priceTF, fillPriceError);

        barcodeTF = new TextField();
        barcodeTF.setPromptText("Barcode");
        //barcodeTF.setStyle("-fx-background-color: #505050; -fx-text-fill: #eeeeee; -fx-prompt-text-fill: #888888;");
        fillBarcodeError = new Label();
        //fillBarcodeError.setStyle("-fx-text-fill: #ff5050;");
        barcodeVBox = new VBox();
        barcodeVBox.getChildren().addAll(barcodeTF, fillBarcodeError);

        quantityTF = new TextField();
        quantityTF.setPromptText("Quantity");
        //quantityTF.setStyle("-fx-background-color: #505050; -fx-text-fill: #eeeeee; -fx-prompt-text-fill: #888888;");
        fillQuantityError = new Label();
        //fillQuantityError.setStyle("-fx-text-fill: #ff5050;");
        quantityVBox = new VBox();
        quantityVBox.getChildren().addAll(quantityTF, fillQuantityError);

        yearL = new Label("Year");
        yearChoiceBox = new ChoiceBox<>();
        yearChoiceBox.getItems().add("**********");
        addYearsInYearChBox();
        yearChoiceBox.setValue("**********");
        monthL = new Label("Month");
        monthChoiceBox = new ChoiceBox<>();
        monthChoiceBox.getItems().add("**********");
        addMonthsInMonthChBox();
        monthChoiceBox.setValue("**********");
        dayL = new Label("Day");
        dayChoiceBox = new ChoiceBox<>();
        dayChoiceBox.getItems().add("**********");
        dayChoiceBox.setValue("**********");
        releaseDateErrLabel = new Label("");
        //releaseDateErrLabel.setStyle("-fx-text-fill: #ff5050;");

        yearVBox = new VBox(5);
        yearVBox.getChildren().addAll(yearChoiceBox, yearL);
        monthVBok = new VBox(5);
        monthVBok.getChildren().addAll(monthChoiceBox, monthL);
        dayVBox = new VBox(5);
        dayVBox.getChildren().addAll(dayChoiceBox, dayL);
        releaseDateHBox = new HBox(10);
        releaseDL = new Label();
        releaseDL.setText("Release Date");
        releaseDL.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #fd5e53;");
        releaseDateHBox.getChildren().addAll(releaseDL, yearVBox, monthVBok, dayVBox, releaseDateErrLabel);
    }

    void addMonthsInMonthChBox()
    {
        for(int i = 1; i <= 12; i++)
            monthChoiceBox.getItems().add(Integer.toString(i));
    }

    void addYearsInYearChBox()
    {
        for(int i = 1950; i <= 2022; i++)
            yearChoiceBox.getItems().add(Integer.toString(i));
    }

    void addDaysInDayChBox()
    {
        dayChoiceBox.getItems().clear();
        dayChoiceBox.getItems().add("**********");
        dayChoiceBox.setValue("**********");

        int month = 0;
        int year = 0;
        int max = 0;

        if(!monthChoiceBox.getValue().equals("**********")) month = Integer.parseInt(monthChoiceBox.getValue());
        if(!yearChoiceBox.getValue().equals("**********")) year = Integer.parseInt(yearChoiceBox.getValue());

        if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) max = 31;
        if(month == 4 || month == 6 || month == 9 || month == 11) max = 30;


                                //todo check for errors
        if(month == 2)
        {
            max = 28;

            for(int i = 2020; i <= 2030; i += 4)
            {
                if(year == i)
                {
                    max = 29;
                    break;
                }

                if(i > year) break;
            }
        }

        if(year == 0) max = 0;

        for(int i = 1; i <= max; i++)
            dayChoiceBox.getItems().add(Integer.toString(i));
    }


    /*private boolean isOnList(String text)
    {
        String name = text.toLowerCase();

        for(Product p : DataBase.getProducts())
        {
            String CDInListName = p.getName().toLowerCase();

            if(name.equals(CDInListName))
                return true;
        }

        return false;
    }*/
}
