package Scenes;

import Module.DateChoiceBox;
import Module.SoldProduct;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class IncomesScene extends CDSoldScene {

    Label totalIncLabel;
    Label amountLabel;
    HBox investmentsHBox;

    @Override
    public void showScene()
    {
        windowPCS = new Stage();
        windowPCS.initModality(Modality.APPLICATION_MODAL);

        title = new Label("Incomes");
        title.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #bb2020 ;");

        setSoldProdsList();
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

        dateFromHBox.setMaxWidth(Double.MAX_VALUE);
        dateFromHBox.setAlignment(Pos.CENTER);
        dateToHBox.setMaxWidth(Double.MAX_VALUE);
        dateToHBox.setAlignment(Pos.CENTER);

        filterButton = new Button("Filter");
        filterButton.setPrefWidth(68);
        //filterButton.setStyle("-fx-background-color: #2050bb; -fx-text-fill: #eeeeee;");
        filterButton.setOnAction(actionEvent -> filter());

        dateFilterVBox = new VBox(10);
        dateFilterVBox.setMaxWidth(Double.MAX_VALUE);
        dateFilterVBox.setAlignment(Pos.CENTER_RIGHT);
        dateFilterVBox.setPadding(new Insets(25, 0, 10, 0));
        dateFilterVBox.getChildren().addAll(dateFromHBox, dateToHBox, filterButton);

        //setTable();

        totalIncLabel = new Label("Total income:");
        //totalIncLabel.setAlignment(Pos.CENTER);
        totalIncLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #bb2020 ;");
        amountLabel = new Label("0.0");
        //amountLabel.setAlignment(Pos.CENTER);
        amountLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #bb2020 ;");
        investmentsHBox = new HBox(5);
        investmentsHBox.setAlignment(Pos.CENTER);
        investmentsHBox.getChildren().addAll(totalIncLabel, amountLabel);

        closeButton = new Button("Close");
        closeButton.setStyle("-fx-background-color: #bb2020; -fx-text-fill: #eeeeee;");
        closeButton.setOnAction(actionEvent -> windowPCS.close());

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox titleHBox = new HBox();
        titleHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        titleHBox.setAlignment(Pos.CENTER);
        titleHBox.setPadding(new Insets(5, 0, 5, 0));
        titleHBox.getChildren().addAll(title, spacer, closeButton);

        layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(titleHBox, dateFilterVBox, investmentsHBox);
        layout.setStyle("-fx-background-color: #F1F0EB;");

        scene = new Scene(layout, 360, 360);
        windowPCS.setScene(scene);
        windowPCS.showAndWait();
    }


    @Override
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
            double totalIncome = 0;

            for(SoldProduct sp : soldProductObservableList)
            {
                sp.setSoldOverAPeriod(dayFrom, monthFrom, yearFrom, dayTo, monthTo, yearTo);
                totalIncome += sp.getPriceOverAPeriod();
            }

            amountLabel.setText(Double.toString(totalIncome));

            //setTable();
            //layout.getChildren().set(2, soldProductTableView);
        }
    }
}
