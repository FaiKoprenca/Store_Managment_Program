package Scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AdminScene extends ManagerScene
{

    Button AddUsersButton;
    Button allUsersB;
    Button modifyUsersB;
    Button deleteUserB;
    Button investmentsB;
    HBox adminSceneHBox;
    VBox lastVbox;

    public void showScene(Stage window) throws FileNotFoundException {
        title = new Label("Admin Test");

        setManagerLayout(window);
        setAdminLayout();

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);


        /*lay = new VBox(10);
        lay.getChildren().addAll(titleHBox, allButtons, spacer, adminSceneHBox);

        Image image = new Image(new FileInputStream("resources/Images/admF.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(480);
        imageView.setPreserveRatio(true);

        ImgAll = new HBox(10);
        ImgAll.setPadding(new Insets(100, 0, 100, 0));
        ImgAll.getChildren().addAll(imageView, lay);*/

        layout = new VBox(35);
        layout.setPadding(new Insets(10, 30, 30, 30));
        layout.setStyle("-fx-background-color: #F1F0EB;");
        layout.getChildren().addAll(titleHBox, allButtons, spacer, adminSceneHBox);
        //layout.getChildren().add(ImgAll);

        scene = new Scene(layout, 580, 350);
        window.setScene(scene);


    }

    void setAdminLayout()
    {
        AddUsersButton = new Button("Add User");
        AddUsersButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        AddUsersButton.setPrefWidth(128);
        AddUsersButton.setAlignment(Pos.CENTER);
        AddUsersButton.setStyle("-fx-background-color: #F13C20; -fx-text-fill: #eeeeee;");
        AddUsersButton.setOnAction(actionEvent -> {
            new AddUserScene().showScenesADD();
        });

        allUsersB = new Button("All Users");
        allUsersB.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        allUsersB.setAlignment(Pos.CENTER);
        allUsersB.setStyle("-fx-background-color: #F13C20; -fx-text-fill: #eeeeee;");
        allUsersB.setOnAction(actionEvent -> {
            new AllEmpoyeesScene().showScene();
        });

        modifyUsersB = new Button("Modify Users");
        modifyUsersB.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        modifyUsersB.setPrefWidth(128);
        modifyUsersB.setStyle("-fx-background-color: #F13C20; -fx-text-fill: #eeeeee;");
        modifyUsersB.setAlignment(Pos.CENTER);
        modifyUsersB.setOnAction(actionEvent -> {
            new ModifyUserScene().showScene();
        });

        deleteUserB = new Button("Delete Users");
        deleteUserB.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        deleteUserB.setPrefWidth(128);
        deleteUserB.setAlignment(Pos.CENTER);
        deleteUserB.setStyle("-fx-background-color: #F13C20; -fx-text-fill: #eeeeee;");
        deleteUserB.setOnAction(actionEvent -> {
            new DeleteUserScene().showSceneD();
        });


        investmentsB = new Button("Investments");
        investmentsB.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        investmentsB.setPrefWidth(128);
        investmentsB.setStyle("-fx-background-color: #F13C20; -fx-text-fill: #eeeeee;");
        investmentsB.setAlignment(Pos.CENTER);
        investmentsB.setOnAction(actionEvent -> {
            new InvestemntsScene().showScene();
        });


        /*allProducts = new Button("All Products");
        allProducts.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        allProducts.setAlignment(Pos.CENTER);
        allProducts.setStyle("-fx-background-color: #F13C20; -fx-text-fill: #eeeeee;");
        allProducts.setOnAction(actionEvent -> {
            new AllProductsScene().showScene();
        });*/

        adminSceneHBox = new HBox(10);
        adminSceneHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        adminSceneHBox.setAlignment(Pos.CENTER);
        adminSceneHBox.getChildren().add( investmentsB);

        //addVBox.getChildren().addAll(allProducts, addProductButton, modifyProductsB, deleteProductB);

        lastVbox = new VBox(10);
        lastVbox.setStyle("-fx-background-color: #F1F0EB;");
        lastVbox.getChildren().addAll(allProducts, addProductButton, modifyProductsB, deleteProductB);



        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        titleHBox = new HBox();
        titleHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        titleHBox.setAlignment(Pos.CENTER);
        titleHBox.setPadding(new Insets(5, 0, 5, 0));
        titleHBox.getChildren().addAll(title, spacer, loggOutB);

        VBox usersVBox = new VBox(10);
        usersVBox.getChildren().addAll(allUsersB, AddUsersButton, modifyUsersB, deleteUserB);

        allButtons = new HBox(30);
        allButtons.getChildren().addAll(usersVBox, lastVbox, addVBox, statsVbox);

    }

}
