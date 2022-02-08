package Scenes;

import Module.*;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddUserScene {

    Stage windowB;
    Label titleLabel;
    Button cancelButton;
    Button addButton;
    Scene sceneB;
    VBox layoutB;

    TextField usernameTxtField;
    Label usernameErrLabel;

    PasswordField passPassField;
    Label passErrLabel;

    TextField nameTxtField;
    Label nameErrLabel;

    TextField phoneNrTxtField;
    Label phoneNrErrLabel;

    TextField emailTxtField;
    Label emailErrLabel;

    TextField salaryTxtField;
    Label salaryErrLabel;

    TextField idCardNrTxtField;
    Label idCardNrErrLabel;

    ChoiceBox<String> roleChoiceBox;
    Label roleErrLabel;

    Label yearLabel;
    ChoiceBox<String> yearChoiceBox;

    Label monthLabel;
    ChoiceBox<String> monthChoiceBox;

    Label dayLabel;
    ChoiceBox<String> dayChoiceBox;

    Label bDayErrLabel;
    VBox yearVBox;
    VBox monthVBok;
    VBox dayVBox;

    VBox usernameHBox;
    VBox passwordHBox;
    VBox nameHBox;
    VBox roleHBox;
    VBox phoneNumberHBox;
    HBox birthdayHBox;
    VBox emailHBox;
    VBox salaryHBox;
    VBox idCardNrHBox;

    public void showScenesADD()
    {
        windowB = new Stage();
        windowB.initModality(Modality.APPLICATION_MODAL);

        titleLabel = new Label("Add User");
        //titleLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #eeeeee;");

        setFields();

        monthChoiceBox.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> observable, String oldValue, String newValue) ->
                        DateChoiceBox.addDaysInDayChBox(dayChoiceBox, monthChoiceBox, yearChoiceBox, 1950, 2000));
        yearChoiceBox.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> observable, String oldValue, String newValue) ->
                        DateChoiceBox.addDaysInDayChBox(dayChoiceBox, monthChoiceBox, yearChoiceBox, 1950, 2000));

        addButton = new Button("Add");
        addButton.setStyle("-fx-background-color: #2050bb; -fx-text-fill: #eeeeee;");
        addButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        addButton.setOnAction(actionEvent -> {
            addUser();
        });

        cancelButton = new Button("Cancel");
        cancelButton.setStyle("-fx-background-color: #bb2020; -fx-text-fill: #eeeeee;");
        cancelButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        cancelButton.setOnAction(actionEvent -> windowB.close());

        VBox addButtonHBox = new VBox();
        addButtonHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        addButtonHBox.setPadding(new Insets(0, 150, 0, 150));
        addButtonHBox.setAlignment(Pos.CENTER);
        addButtonHBox.getChildren().add(addButton);

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox titleHBox = new HBox();
        titleHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        titleHBox.setPadding(new Insets(0, 0, 15, 0));
        titleHBox.setAlignment(Pos.CENTER);
        titleHBox.getChildren().addAll( spacer, cancelButton);

        layoutB = new VBox(16);
        layoutB.setPadding(new Insets(20));
        layoutB.getChildren().addAll(titleHBox, usernameHBox, passwordHBox, nameHBox, phoneNumberHBox, emailHBox , salaryHBox, idCardNrHBox, roleHBox, birthdayHBox, addButtonHBox);
        layoutB.setStyle("-fx-background-color: #F1F0EB;");

        sceneB = new Scene(layoutB, 480, 720);
        windowB.setScene(sceneB);
        windowB.showAndWait();
    }

    void addUser()
    {
        String username = "";
        String password = "";
        String name = "";

        String phoneNumber = "";

        String email = "";
        double salary = 0.0;
        String idCardNr = "";
        String role = "";

        int year = 0;
        int month = 0;
        int day = 0;

        boolean areFieldsCorrect = true;

        if(!usernameTxtField.getText().isEmpty() && InputCheck.isUsernameValid(usernameTxtField.getText()))
        {
            if(InputCheck.doesUsernameExists(usernameTxtField.getText()))
            {
                areFieldsCorrect = false;
                usernameErrLabel.setText("This username already exists.");
            }
            else
            {
                username = usernameTxtField.getText();
                usernameErrLabel.setText("");
            }
        }
        else
        {
            areFieldsCorrect = false;
            usernameErrLabel.setText("The only values that are allowed are: letters, digits and - _ .");
        }

        if(!passPassField.getText().isEmpty() && passPassField.getText().length() >= 8)
        {
            password = passPassField.getText();
            passErrLabel.setText("");
        }
        else
        {
            areFieldsCorrect = false;
            passErrLabel.setText("Enter a password!");
        }

        if(!nameTxtField.getText().isEmpty() && InputCheck.isNameValid(nameTxtField.getText()))
        {
            name = nameTxtField.getText();
            nameErrLabel.setText("");
        }
        else
        {
            areFieldsCorrect = false;
            nameErrLabel.setText("Enter name!");
        }

        if(!phoneNrTxtField.getText().isEmpty() && InputCheck.isPhoneNrValid(phoneNrTxtField.getText()))
        {
            phoneNumber = phoneNrTxtField.getText();
            phoneNrErrLabel.setText("");
        }
        else
        {
            areFieldsCorrect = false;
            phoneNrErrLabel.setText("Enter 10 digits only!");
        }

        if(!emailTxtField.getText().isEmpty())
        {
            email = emailTxtField.getText();
            emailErrLabel.setText("");
        }
        else
        {
            areFieldsCorrect = false;
            emailErrLabel.setText("Enter email!");
        }


        if(!salaryTxtField.getText().isEmpty() && InputCheck.isDouble(salaryTxtField.getText()))
        {
            salary = Double.parseDouble(salaryTxtField.getText());
            salaryErrLabel.setText("");
        }
        else
        {
            areFieldsCorrect = false;
            salaryErrLabel.setText("Enter salary!");
        }


        if(!idCardNrTxtField.getText().isEmpty() && idCardNrTxtField.getText().length() == 10)
        {
            idCardNr = idCardNrTxtField.getText();
            idCardNrErrLabel.setText("");
        }
        else
        {
            areFieldsCorrect = false;
            idCardNrErrLabel.setText("Enter ID Card Number!");
        }

        if(!roleChoiceBox.getValue().equals("**********"))
        {
            role = roleChoiceBox.getValue();
            roleErrLabel.setText("");
        }
        else
        {
            areFieldsCorrect = false;
            roleErrLabel.setText("Select a role!");
        }

        if(!yearChoiceBox.getValue().equals("**********") && !monthChoiceBox.getValue().equals("**********") && !dayChoiceBox.getValue().equals("----------"))
        {
            year = Integer.parseInt(yearChoiceBox.getValue());
            month = Integer.parseInt(monthChoiceBox.getValue());
            day = Integer.parseInt(dayChoiceBox.getValue());
            bDayErrLabel.setText("");
        }
        else
        {
            areFieldsCorrect = false;
            bDayErrLabel.setText("Select Date of Birth!");
        }

        if(areFieldsCorrect)
        {
            if(role.equals("Cashier"))
            {
                DataBase.getCashiers().add(new Cashier());
                int addedCashierIndex = DataBase.getCashiers().size() - 1;
                Cashier addedUser = DataBase.getCashiers().get(addedCashierIndex);

                addedUser.setUsername(username);
                addedUser.setPassword(password);
                addedUser.setName(name);
                addedUser.setRole(role);
                addedUser.setPhoneNumber(phoneNumber);
                addedUser.setEmail(email);
                addedUser.setSalary(salary);
                addedUser.setIdCardNumber(idCardNr);
                addedUser.setBirthday(new Date(day, month, year));
            }

            if(role.equals("Manager"))
            {
                DataBase.getManagers().add(new Manager());
                int addedManagerIndex = DataBase.getManagers().size()-1;
                Manager addedUser = DataBase.getManagers().get(addedManagerIndex);

                addedUser.setUsername(username);
                addedUser.setPassword(password);
                addedUser.setName(name);
                addedUser.setRole(role);
                addedUser.setPhoneNumber(phoneNumber);
                addedUser.setEmail(email);
                addedUser.setSalary(salary);
                addedUser.setIdCardNumber(idCardNr);
                addedUser.setBirthday(new Date(day, month, year));
            }

            windowB.close();
        }
    }

    void setFields()
    {
        usernameTxtField = new TextField();
        usernameTxtField.setPromptText("Username");
        usernameErrLabel = new Label("");
        usernameErrLabel.setStyle("-fx-text-fill: #ff5050;");
        usernameHBox = new VBox();
        usernameHBox.getChildren().addAll(usernameTxtField, usernameErrLabel);

        passPassField = new PasswordField();
        passPassField.setPromptText("Password");
        passErrLabel = new Label("");
        //passErrLabel.setStyle("-fx-text-fill: #ff5050;");
        passwordHBox = new VBox();
        passwordHBox.getChildren().addAll(passPassField, passErrLabel);

        nameTxtField = new TextField();
        nameTxtField.setPromptText("Name");
        nameErrLabel = new Label("");
        //nameErrLabel.setStyle("-fx-text-fill: #ff5050;");
        nameHBox = new VBox();
        nameHBox.getChildren().addAll(nameTxtField, nameErrLabel);

        phoneNrTxtField = new TextField();
        phoneNrTxtField.setPromptText("Phone number");
        phoneNrErrLabel = new Label("");
        phoneNrErrLabel.setStyle("-fx-text-fill: #ff5050;");
        phoneNumberHBox = new VBox();
        phoneNumberHBox.getChildren().addAll(phoneNrTxtField, phoneNrErrLabel);

        emailTxtField = new TextField();
        emailTxtField.setPromptText("E-mail");
        emailErrLabel = new Label("");
        emailErrLabel.setStyle("-fx-text-fill: #ff5050;");
        emailHBox = new VBox();
        emailHBox.getChildren().addAll(emailTxtField, emailErrLabel);

        salaryTxtField = new TextField();
        salaryTxtField.setPromptText("Salary");
        salaryErrLabel = new Label("");
        salaryErrLabel.setStyle("-fx-text-fill: #ff5050;");
        salaryHBox = new VBox();
        salaryHBox.getChildren().addAll(salaryTxtField, salaryErrLabel);

        idCardNrTxtField = new TextField();
        idCardNrTxtField.setPromptText("ID card number");
        idCardNrErrLabel = new Label("");
        idCardNrErrLabel.setStyle("-fx-text-fill: #ff5050;");
        idCardNrHBox = new VBox();
        idCardNrHBox.getChildren().addAll(idCardNrTxtField, idCardNrErrLabel);

        roleChoiceBox = new ChoiceBox<>();
        roleChoiceBox.getItems().addAll( "Cashier", "Manager");
        roleChoiceBox.setValue("ROLE");
        roleChoiceBox.setMaxWidth(Double.MAX_VALUE);
        roleErrLabel = new Label("");
        roleHBox = new VBox();
        roleHBox.getChildren().addAll(roleChoiceBox, roleErrLabel);

        yearLabel = new Label("Year");
        yearLabel.setMaxWidth(Double.MAX_VALUE);
        yearLabel.setAlignment(Pos.CENTER);

        yearChoiceBox = new ChoiceBox<>();
        yearChoiceBox.getItems().add("**********");
        DateChoiceBox.addYearsInYearChBox(yearChoiceBox, 1950, 2002);
        yearChoiceBox.setValue("**********");
        yearChoiceBox.setMaxWidth(Double.MAX_VALUE);

        monthLabel = new Label("Month");
        monthLabel.setMaxWidth(Double.MAX_VALUE);
        monthLabel.setAlignment(Pos.CENTER);

        monthChoiceBox = new ChoiceBox<>();
        monthChoiceBox.getItems().add("**********");
        DateChoiceBox.addMonthsInMonthChBox(monthChoiceBox);
        monthChoiceBox.setValue("**********");
        monthChoiceBox.setMaxWidth(Double.MAX_VALUE);

        dayLabel = new Label("Day");
        dayLabel.setMaxWidth(Double.MAX_VALUE);
        dayLabel.setAlignment(Pos.CENTER);

        dayChoiceBox = new ChoiceBox<>();
        dayChoiceBox.getItems().add("**********");
        dayChoiceBox.setValue("**********");
        dayChoiceBox.setMaxWidth(Double.MAX_VALUE);

        bDayErrLabel = new Label("");

        yearVBox = new VBox(5);
        yearVBox.getChildren().addAll(yearChoiceBox, yearLabel);
        monthVBok = new VBox(5);
        monthVBok.getChildren().addAll(monthChoiceBox, monthLabel);
        dayVBox = new VBox(5);
        dayVBox.getChildren().addAll(dayChoiceBox, dayLabel);

        birthdayHBox = new HBox(5);
        birthdayHBox.setMaxWidth(Double.MAX_VALUE);
        birthdayHBox.getChildren().addAll(yearVBox, monthVBok, dayVBox, bDayErrLabel);

    }
}
