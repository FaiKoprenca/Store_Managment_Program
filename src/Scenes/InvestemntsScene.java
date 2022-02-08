package Scenes;

import Module.*;
import javafx.beans.value.ObservableValue;
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

public class InvestemntsScene extends CDBoughtScene{

    Label CDLabel;
    VBox CDVBox;

    Label totalLabel;
    Label amountLabel;
    HBox investmentsHBox;

    Label usersLabel;
    HBox usersHBox;
    VBox usersVBox;

    Label usersSalaryLabel;
    Label usersAmountLabel;
    HBox usersSalaryHBox;

    ObservableList<Cashier> cashiers;
    TableView<Cashier> cashierTableView;
    ObservableList<Manager> managers;
    TableView<Manager> managerTableView;

    public void showScene()
    {
        windowCBS = new Stage();
        windowCBS.initModality(Modality.APPLICATION_MODAL);

        title = new Label("Investments");
        title.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #bb2020;");

        boughtProductObservableList = FXCollections.observableArrayList();
        boughtProductObservableList.addAll(DataBase.getBoughtProducts());

        resetStatsOverAPeriod();

        cashiers = FXCollections.observableArrayList();
        managers = FXCollections.observableArrayList();

        cashiers.addAll(DataBase.getCashiers());
        managers.addAll(DataBase.getManagers());

        resetUserStatsOverAPeriod();
        boughtCDDateFilters();


        DateChoiceBox.setDateChoiceBox(dateFromLabel, yearLabel, yearChoiceBox, monthLabel, monthChoiceBox, dayLabel,
                dayChoiceBox, dateFromErrLabel, yearVBox, monthVBok, dayVBox, dateFromHBox, 2021, 2022);
        DateChoiceBox.setDateChoiceBox(dateToLabel, yearLabel2, yearChoiceBox2, monthLabel2, monthChoiceBox2, dayLabel2,
                dayChoiceBox2, dateToErrLabel, yearVBox2, monthVBok2, dayVBox2, dateToHBox, 2021, 2022);

        monthChoiceBox.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> observable, String oldValue, String newValue) ->
                        DateChoiceBox.addDaysInDayChBox(dayChoiceBox, monthChoiceBox, yearChoiceBox, 2021, 2022));
        yearChoiceBox.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> observable, String oldValue, String newValue) ->
                        DateChoiceBox.addDaysInDayChBox(dayChoiceBox, monthChoiceBox, yearChoiceBox, 2021, 2022));

        monthChoiceBox2.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> observable, String oldValue, String newValue) ->
                        DateChoiceBox.addDaysInDayChBox(dayChoiceBox2, monthChoiceBox2, yearChoiceBox2, 2021, 2022));
        yearChoiceBox2.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> observable, String oldValue, String newValue) ->
                        DateChoiceBox.addDaysInDayChBox(dayChoiceBox2, monthChoiceBox2, yearChoiceBox2, 2021, 2022));

        dateFromHBox.setMaxWidth(Double.MAX_VALUE);
        dateFromHBox.setAlignment(Pos.CENTER);
        dateToHBox.setMaxWidth(Double.MAX_VALUE);
        dateToHBox.setAlignment(Pos.CENTER);

        filterButton = new Button("Filter");
        filterButton.setPrefWidth(68);
        filterButton.setStyle("-fx-background-color: #2050bb; -fx-text-fill: #eeeeee;");
        filterButton.setOnAction(actionEvent -> {
            filter();
        });

        dateFilterVBox = new VBox(10);
        dateFilterVBox.setMaxWidth(Double.MAX_VALUE);
        dateFilterVBox.setAlignment(Pos.CENTER_RIGHT);
        dateFilterVBox.setPadding(new Insets(25, 0, 10, 0));
        dateFilterVBox.getChildren().addAll(dateFromHBox, dateToHBox, filterButton);

        setTable();


        CDLabel = new Label("CDs");
        CDVBox = new VBox(5);
        CDVBox.getChildren().addAll(CDLabel, productTableView);

        totalLabel = new Label("Investments:");
        //totalLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #eeeeee;");
        amountLabel = new Label("0.0");
        //amountLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #eeeeee;");
        investmentsHBox = new HBox(5);
        investmentsHBox.getChildren().addAll(totalLabel, amountLabel);

        usersLabel = new Label("Users");
        setCashierTable();
        setManagerTableView();

        usersHBox = new HBox(30);
        usersHBox.getChildren().addAll(cashierTableView, managerTableView);

        usersVBox = new VBox(5);
        usersVBox.getChildren().addAll(usersLabel, usersHBox);

        usersSalaryLabel = new Label("Total users salary:");
        usersSalaryLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #bb2020;");
        usersAmountLabel = new Label("0.0");
        usersAmountLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #bb2020;");
        usersSalaryHBox = new HBox(5);
        usersSalaryHBox.setAlignment(Pos.CENTER);
        usersSalaryHBox.getChildren().addAll(usersSalaryLabel, usersAmountLabel);

        closeButton = new Button("Close");
        closeButton.setStyle("-fx-background-color: #bb2020; -fx-text-fill: #eeeeee;");
        closeButton.setOnAction(actionEvent -> windowCBS.close());

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox titleHBox = new HBox();
        titleHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        titleHBox.setAlignment(Pos.CENTER);
        titleHBox.setPadding(new Insets(5, 0, 5, 0));
        titleHBox.getChildren().addAll(title, spacer, closeButton);

        layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(titleHBox, dateFilterVBox, CDVBox, investmentsHBox, usersVBox, usersSalaryHBox);
        layout.setStyle("-fx-background-color: #F1F0EB;");

        scene = new Scene(layout, 680, 720);
        windowCBS.setScene(scene);
        windowCBS.showAndWait();
    }

    void resetUserStatsOverAPeriod()
    {
        for(Cashier c : cashiers)
            c.resetStatsOverAPeriod();

        for(Manager e : managers)
            e.resetSalaryOverAPeriod();
    }

    void filter()
    {
        boolean areFieldsCorrect = true;

        int yearFrom = 0;
        int monthFrom = 0;
        int dayFrom = 0;

        int yearTo = 0;
        int monthTo = 0;
        int dayTo = 0;

        if(!yearChoiceBox.getValue().equals("----------") && !monthChoiceBox.getValue().equals("----------") && !dayChoiceBox.getValue().equals("----------"))
        {
            yearFrom = Integer.parseInt(yearChoiceBox.getValue());
            monthFrom = Integer.parseInt(monthChoiceBox.getValue());
            dayFrom = Integer.parseInt(dayChoiceBox.getValue());
            dateFromErrLabel.setText("");
        }
        else
        {
            areFieldsCorrect = false;
            dateFromErrLabel.setText("Select Date!");
        }

        if(!yearChoiceBox2.getValue().equals("----------") && !monthChoiceBox2.getValue().equals("----------") && !dayChoiceBox2.getValue().equals("----------"))
        {
            yearTo = Integer.parseInt(yearChoiceBox2.getValue());
            monthTo = Integer.parseInt(monthChoiceBox2.getValue());
            dayTo = Integer.parseInt(dayChoiceBox2.getValue());
            dateToErrLabel.setText("");
        }
        else
        {
            areFieldsCorrect = false;
            dateToErrLabel.setText("Select Date!");
        }

        if(areFieldsCorrect)
        {
            double productsTotal = 0;
            double cashiersTotal = 0;
            double managersTotal = 0;

            for(BoughtProduct bp : boughtProductObservableList)
            {
                bp.setBoughtOverAPeriod(dayFrom, monthFrom, yearFrom, dayTo, monthTo, yearTo);
                productsTotal += bp.getPriceOverAPeriod();
            }

            for(Cashier c : cashiers)
            {
                c.setSalaryOverAPeriod(monthFrom, yearFrom, monthTo, yearTo);
                cashiersTotal += c.getSalaryOverAPeriod();
            }

            for(Manager e : managers)
            {
                e.setSalaryOverAPeriod(monthFrom, yearFrom, monthTo, yearTo);
                managersTotal += e.getSalaryOverAPeriod();
            }

            double totalUserSalary = cashiersTotal + managersTotal;

            usersAmountLabel.setText(Double.toString(totalUserSalary));
            amountLabel.setText(Double.toString(productsTotal));

            setTable();
            setCashierTable();
            setManagerTableView();
            layout.getChildren().set(2, productTableView);
            usersHBox.getChildren().set(0, cashierTableView);
            usersHBox.getChildren().set(1, managerTableView);
        }
    }

    void setTable()
    {
        TableColumn<BoughtProduct, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<BoughtProduct, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setMinWidth(80);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<BoughtProduct, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(40);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("boughtOverAPeriod"));

        TableColumn<BoughtProduct, Double> totalPriceColumn = new TableColumn<>("Total price");
        totalPriceColumn.setMinWidth(80);
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("priceOverAPeriod"));

        productTableView = new TableView<>();
        productTableView.setItems(boughtProductObservableList);
        productTableView.getColumns().addAll(nameColumn, priceColumn, quantityColumn, totalPriceColumn);
    }

    void setCashierTable()
    {
        TableColumn<Cashier, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Cashier, Double> salaryPerMonthColumn = new TableColumn<>("Salary/month");
        salaryPerMonthColumn.setMinWidth(100);
        salaryPerMonthColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));

        TableColumn<Cashier, Double> salaryColumn = new TableColumn<>("Total salary");
        salaryColumn.setMinWidth(100);
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salaryOverAPeriod"));

        cashierTableView = new TableView<>();
        cashierTableView.setItems(cashiers);
        cashierTableView.getColumns().addAll(nameColumn, salaryPerMonthColumn, salaryColumn);
    }

    void setManagerTableView()
    {
        TableColumn<Manager, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Manager, Double> salaryPerMonthColumn = new TableColumn<>("Salary/month");
        salaryPerMonthColumn.setMinWidth(100);
        salaryPerMonthColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));

        TableColumn<Manager, Double> salaryColumn = new TableColumn<>("Total salary");
        salaryColumn.setMinWidth(100);
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salaryOverAPeriod"));

        managerTableView = new TableView<>();
        managerTableView.setItems(managers);
        managerTableView.getColumns().addAll(nameColumn, salaryPerMonthColumn, salaryColumn);
    }

}
