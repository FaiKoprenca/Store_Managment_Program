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

public class CDSoldScene {

    Stage windowPCS;
    Scene scene;
    Label title;
    Button closeButton;
    VBox layout;

    Label dateFromLabel;
    Label yearLabel;
    ChoiceBox<String> yearChoiceBox;
    Label monthLabel;
    ChoiceBox<String> monthChoiceBox;
    Label dayLabel;
    ChoiceBox<String> dayChoiceBox;
    Label dateFromErrLabel;

    VBox yearVBox;
    VBox monthVBok;
    VBox dayVBox;
    HBox dateFromHBox;

    Label dateToLabel;
    Label yearLabel2;
    ChoiceBox<String> yearChoiceBox2;
    Label monthLabel2;
    ChoiceBox<String> monthChoiceBox2;
    Label dayLabel2;
    ChoiceBox<String> dayChoiceBox2;
    Label dateToErrLabel;

    VBox yearVBox2;
    VBox monthVBok2;
    VBox dayVBox2;
    HBox dateToHBox;

    Button filterButton;
    VBox dateFilterVBox;

    ObservableList<SoldProduct> soldProductObservableList;
    TableView<SoldProduct> soldProductTableView;

    Button incomes;

    public void showScene()
    {
        windowPCS = new Stage();
        windowPCS.initModality(Modality.APPLICATION_MODAL);

        title = new Label("Products Sold");
        title.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #fd5e53;");

        setSoldProdsList();;
        resetStatsOverAPeriod();
        soldProductDateFilters();

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

        filterButton = new Button("Filter");
        filterButton.setPrefWidth(68);
        filterButton.setStyle("-fx-background-color: #2050bb; -fx-text-fill: #eeeeee;");
        filterButton.setOnAction(actionEvent -> filter());

        dateFromHBox.setMaxWidth(Double.MAX_VALUE);
        dateFromHBox.setAlignment(Pos.CENTER);
        dateToHBox.setMaxWidth(Double.MAX_VALUE);
        dateToHBox.setAlignment(Pos.CENTER);

        dateFilterVBox = new VBox(10);
        dateFilterVBox.setMaxWidth(Double.MAX_VALUE);
        dateFilterVBox.setAlignment(Pos.CENTER_RIGHT);
        dateFilterVBox.setPadding(new Insets(25, 0, 10, 0));
        dateFilterVBox.getChildren().addAll(dateFromHBox, dateToHBox, filterButton);

        setTable();


        closeButton = new Button("Close");
        closeButton.setPrefWidth(68);
        //closeButton.setStyle("-fx-background-color: #bb2020; -fx-text-fill: #eeeeee;");
        closeButton.setOnAction(actionEvent -> windowPCS.close());


        incomes = new Button("Incomes");
        incomes.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        incomes.setAlignment(Pos.CENTER);
        incomes.setStyle("-fx-background-color: #bb2020; -fx-text-fill: #eeeeee;");
        incomes.setOnAction(actionEvent -> {
            new IncomesScene().showScene();
        });


        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox titleHBox = new HBox();
        titleHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        titleHBox.setAlignment(Pos.CENTER);
        titleHBox.setPadding(new Insets(5, 0, 5, 0));
        titleHBox.getChildren().addAll(title, spacer, closeButton);

        layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(titleHBox, dateFilterVBox, soldProductTableView, incomes);
        layout.setStyle("-fx-background-color: #F1F0EB;");

        scene = new Scene(layout, 512, 700);
        windowPCS.setScene(scene);
        windowPCS.showAndWait();

    }

    void setSoldProdsList()
    {
        soldProductObservableList = FXCollections.observableArrayList();

        for(Cashier cashier : DataBase.getCashiers())
            soldProductObservableList.addAll(cashier.getSoldProduct());
    }
    void resetStatsOverAPeriod()
    {
        for(SoldProduct sp : soldProductObservableList)
            sp.resetStatsOverAPeriod();
    }
    void soldProductDateFilters()
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
        TableColumn<SoldProduct, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(150);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<SoldProduct, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setMinWidth(50);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("productID"));

        TableColumn<SoldProduct, Integer> billColumn = new TableColumn<>("Bill no");
        billColumn.setMinWidth(80);
        billColumn.setCellValueFactory(new PropertyValueFactory<>("billNumber"));

        TableColumn<SoldProduct, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(50);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("soldOverAPeriod"));

        TableColumn<SoldProduct, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setMinWidth(70);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("priceOverAPeriod"));

        soldProductTableView = new TableView<>();
        soldProductTableView.setItems(soldProductObservableList);
        soldProductTableView.getColumns().addAll(nameColumn, idColumn, billColumn, quantityColumn, priceColumn);
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
            for(SoldProduct sp : soldProductObservableList)
                sp.setSoldOverAPeriod(dayFrom, monthFrom, yearFrom, dayTo, monthTo, yearTo);

            setTable();
            layout.getChildren().set(2, soldProductTableView);
        }
    }
}
