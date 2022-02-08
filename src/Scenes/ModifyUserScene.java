package Scenes;

import Module.DataBase;
import Module.User;
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


public class ModifyUserScene {

    Stage windowModify;
    Label titleL;
    Label errorL;
    Button editB;
    Button cancelB;
    Scene scene;
    VBox layout;

    ObservableList<User> users;
    TableView<User> usersT;

    public void showScene()
    {
        windowModify = new Stage();
        windowModify.initModality(Modality.APPLICATION_MODAL);

        titleL = new Label("Users");
        titleL.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #BB2020;");


        users = FXCollections.observableArrayList();
        users.addAll(DataBase.getCashiers());
        users.addAll(DataBase.getManagers());

        setTable();

        errorL = new Label("");
        errorL.setStyle("-fx-text-fill: #ff5050;");

        editB = new Button("Edit");
        editB.setStyle("-fx-background-color: #2050bb; -fx-text-fill: #eeeeee;");
        editB.setPrefWidth(64);
        editB.setOnAction(actionEvent -> {
            editUsers();
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
        layout.getChildren().addAll(titleHBox, usersT, addHBox);
        layout.setStyle("-fx-background-color: #F1F0EB;");

        scene = new Scene(layout, 380, 520);
        windowModify.setScene(scene);
        windowModify.showAndWait();

    }

    private void editUsers()
    {
        if (!usersT.getSelectionModel().isEmpty()) {
            errorL.setText("");
            User user = usersT.getSelectionModel().getSelectedItem();

            if (user.getRoleName().equals("Cashier"))
            {
                new EditCashierScene().showScene(user);
            }
            if (user.getRoleName().equals("Manager")) {
                new EditManagerScene().showScene(user);
            }
        }
        else
        {
            errorL.setText("Select a User");
        }
    }

    void setTable()
    {
        TableColumn<User, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setMinWidth(100);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));

        TableColumn<User, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setMinWidth(100);
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<User, String> roleColumn = new TableColumn<>("Role");
        roleColumn.setMinWidth(100);
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("roleName"));


        usersT = new TableView<>();
        usersT.setItems(users);
        usersT.getColumns().addAll(idColumn, usernameColumn, roleColumn);
    }
}
