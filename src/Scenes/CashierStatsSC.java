package Scenes;

import Module.DataBase;
import Module.Cashier;
import Module.DateChoiceBox;
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

public class CashierStatsSC {

    Stage windowCSS;
    Scene scene;
    Label title;
    Button closeB;
    VBox layout;



    Label dateFromLabel;
    Label yearLabel;
    ChoiceBox<String> yearChoiceBox;
    Label monthLabel;
    ChoiceBox<String> monthChoiceBox;
    Label dayLabel;
    ChoiceBox<String> dayChoiceBox;
    Label dateFromErrLabel;

    Label dateToLabel;
    Label yearLabel2;
    ChoiceBox<String> yearChoiceBox2;
    Label monthLabel2;
    ChoiceBox<String> monthChoiceBox2;
    Label dayLabel2;
    ChoiceBox<String> dayChoiceBox2;
    Label dateToErrLabel;

    VBox yearVBox;
    VBox monthVBok;
    VBox dayVBox;
    HBox dateFromHBox;
    VBox yearVBox2;
    VBox monthVBok2;
    VBox dayVBox2;
    HBox dateToHBox;

    HBox dateChHBox;

    Button filterButton;
    VBox dateFilterVBox;

    Button incomes;

    TableView<Cashier> cashierTableView;
    ObservableList<Cashier> cashiers;

    public void showScene()
    {
        windowCSS = new Stage();
        windowCSS.initModality(Modality.APPLICATION_MODAL);

        title = new Label("All cashiers");
        //tile.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #eeeeee;");

        cashiers = FXCollections.observableArrayList();
        cashiers.addAll(DataBase.getCashiers());

        resetStatsOverAPeriod();
        cashierDateFilter();

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

        /*dateChHBox = new HBox();
        dateChHBox.setAlignment(Pos.CENTER);
        dateChHBox.getChildren().addAll(dateToHBox, dateFromHBox);*/

        filterButton = new Button("Filter");
        filterButton.setPrefWidth(68);
        //filterButton.setStyle("-fx-background-color: #2050bb; -fx-text-fill: #eeeeee;");
        filterButton.setOnAction(actionEvent -> {
            filter();
        });

        dateFilterVBox = new VBox(10);
        dateFilterVBox.setMaxWidth(Double.MAX_VALUE);
        dateFilterVBox.setAlignment(Pos.CENTER_RIGHT);
        dateFilterVBox.setPadding(new Insets(25, 0, 10, 0));
        dateFilterVBox.getChildren().addAll(dateFromHBox, dateToHBox, filterButton);

        setTable();

        incomes = new Button("Incomes");
        incomes.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        incomes.setAlignment(Pos.CENTER);
        incomes.setStyle("-fx-background-color: #bb2020; -fx-text-fill: #eeeeee;");
        incomes.setOnAction(actionEvent -> {
            new IncomesScene().showScene();
        });

        closeB = new Button("Close");
        closeB.setPrefWidth(68);
        closeB.setStyle("-fx-background-color: #bb2020; -fx-text-fill: #eeeeee;");
        closeB.setOnAction(actionEvent -> windowCSS.close());

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox titleHBox = new HBox();
        titleHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        titleHBox.setAlignment(Pos.CENTER);
        titleHBox.setPadding(new Insets(5, 0, 5, 0));
        titleHBox.getChildren().addAll(title, spacer, closeB);

        layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(titleHBox, dateFilterVBox, cashierTableView, incomes);
        layout.setStyle("-fx-background-color: #F1F0EB;");

        scene = new Scene(layout, 642, 730);
        windowCSS.setScene(scene);
        windowCSS.showAndWait();
    }

    void resetStatsOverAPeriod()
    {
        for(Cashier c : cashiers)
            c.resetStatsOverAPeriod();
    }

    void cashierDateFilter()
    {
        dateFromLabel = new Label("From");
        dateFromLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #fd5e53;");
        dateFromErrLabel = new Label("");

        yearLabel = new Label("Year");
        yearLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #fd5e53;");
        yearChoiceBox = new ChoiceBox<>();

        monthLabel = new Label("Month");
        monthLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #fd5e53;");
        monthChoiceBox = new ChoiceBox<>();

        dayLabel = new Label("Day");
        dayLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #fd5e53;");
        dayChoiceBox = new ChoiceBox<>();

        yearVBox = new VBox(5);
        monthVBok = new VBox(5);
        dayVBox = new VBox(5);

        dateFromHBox = new HBox(10);



        dateToLabel = new Label("To");
        dateToLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #fd5e53;");
        dateToErrLabel = new Label("");

        yearLabel2 = new Label("Year");
        yearLabel2.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #fd5e53;");
        yearChoiceBox2 = new ChoiceBox<>();

        monthLabel2 = new Label("Month");
        monthLabel2.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #fd5e53;");
        monthChoiceBox2 = new ChoiceBox<>();

        dayLabel2 = new Label("Day");
        dayLabel2.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #fd5e53;");
        dayChoiceBox2 = new ChoiceBox<>();

        yearVBox2 = new VBox(5);
        monthVBok2 = new VBox(5);
        dayVBox2 = new VBox(5);

        dateToHBox = new HBox(10);
    }

    void setTable()
    {
        TableColumn<Cashier, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(150);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Cashier, Integer> nrOfBillsColumn = new TableColumn<>("Bills");
        nrOfBillsColumn.setMinWidth(150);
        nrOfBillsColumn.setCellValueFactory(new PropertyValueFactory<>("billOverAPeriod"));

        TableColumn<Cashier, Integer> prodSoldColumn = new TableColumn<>("Products Sold");
        prodSoldColumn.setMinWidth(150);
        prodSoldColumn.setCellValueFactory(new PropertyValueFactory<>("soldProdsOverAPeriod"));

        TableColumn<Cashier, Double> moneyGenColumn = new TableColumn<>("Money Generated");
        moneyGenColumn.setMinWidth(150);
        moneyGenColumn.setCellValueFactory(new PropertyValueFactory<>("moneyGenOverAPeriod"));

        cashierTableView = new TableView<>();
        cashierTableView.setItems(cashiers);
        cashierTableView.getColumns().addAll(nameColumn, nrOfBillsColumn, prodSoldColumn, moneyGenColumn);
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

        if(!yearChoiceBox.getValue().equals("**********") && !monthChoiceBox.getValue().equals("**********") && !dayChoiceBox.getValue().equals("**********"))
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

        if(!yearChoiceBox2.getValue().equals("**********") && !monthChoiceBox2.getValue().equals("**********") && !dayChoiceBox2.getValue().equals("**********"))
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
            for(Cashier c : cashiers)
            {
                c.setBillAndMoneyGenOverAPeriod(dayFrom, monthFrom, yearFrom, dayTo, monthTo, yearTo);
                c.setSoldProdsOverAPeriod(dayFrom, monthFrom, yearFrom, dayTo, monthTo, yearTo);
            }

            setTable();
            layout.getChildren().set(2, cashierTableView);
        }
    }
}
