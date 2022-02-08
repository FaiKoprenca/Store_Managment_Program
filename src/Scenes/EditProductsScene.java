package Scenes;

import Module.DataBase;
import Module.Product;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EditProductsScene {

    Stage windowEC;
    Label titleL;
    Button cancelB;
    Scene scene;
    VBox layout;

    Button changePriceB;
    Button changeSingerB;
    Button changerGenreB;

    VBox buttonsV;

    Product product;
    String username;

    public void showScene(Product product)
    {
        username = product.getName();

        getProducts();

        windowEC = new Stage();
        windowEC.initModality(Modality.APPLICATION_MODAL);

        titleL = new Label("Edit Products" + product.getName()) ;
        titleL.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #bb2020;");

        changePriceB = new Button("Change Price");
        changePriceB.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        changePriceB.setOnAction(actionEvent -> {
            //todo
        });

        changerGenreB = new Button("Chnge Genre");
        changerGenreB.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        changerGenreB.setOnAction(actionEvent -> {
            String newGenre = new GetInputProductsScene().showScene("genre");
            System.out.println(newGenre);
            changerGenre(newGenre);
            DataBase.save();

        });

        changeSingerB = new Button("Changer Singer");
        changeSingerB.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        changeSingerB.setOnAction(actionEvent -> {

        });

        layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(changerGenreB, changePriceB);
        layout.setStyle("-fx-background-color: #F1F0EB;");

        scene = new Scene(layout, 380, 280);
        windowEC.setScene(scene);
        windowEC.showAndWait();
    }


    void changerGenre(String newGenre)
    {
        if (!newGenre.equals(""))
        {
            product.setGenre(newGenre);
        }
    }

    void getProducts()
    {
        product = new Product();

        for (Product p : DataBase.getProducts())
        {
            if (username.equals(p.getName()))
            {
                product = p;
                return;
            }
        }
    }

}
