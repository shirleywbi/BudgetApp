package ui.chart;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;
import model.Expense;
import ui.panel.ExpensePanel;

import java.util.ArrayList;
import java.util.List;

import static model.ExpenseType.*;

public class ExpenseBarChart {
    Expense expense = ExpensePanel.expense;
    Group root;
    Scene scene;
    XYChart.Series series = new XYChart.Series();
    List data = new ArrayList<XYChart.Series>();

    //EFFECTS: returns a Scene with the Expense Type Bar Chart
    public Scene createExpenseTypeBarChart() {
        root = new Group();
        scene = new Scene(root, Color.WHITE);

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> bc = new BarChart(xAxis, yAxis);
        bc.setTitle("Expense Breakdown");
        xAxis.setLabel("Expense Category");
        yAxis.setLabel("Amount Spent ($)");

        setData();
        bc.setLegendVisible(false);
        bc.getData().addAll(series);
        root.getChildren().add(bc);
        return (scene);
    }

    //EFFECTS: creates expense type data series for bar chart
    private void setData() {
        series.getData().add(new XYChart.Data(FOOD.getExpenseType(), expense.getFood().getExpenseTotal()));
        series.getData().add(new XYChart.Data(ENTERTAINMENT.getExpenseType(), expense.getEntertainment().getExpenseTotal()));
        series.getData().add(new XYChart.Data(HEALTH.getExpenseType(), expense.getHealth().getExpenseTotal()));
        series.getData().add(new XYChart.Data(TRANSPORTATION.getExpenseType(), expense.getTransportation().getExpenseTotal()));
        series.getData().add(new XYChart.Data(RENT.getExpenseType(), expense.getRent().getExpenseTotal()));
        series.getData().add(new XYChart.Data(OTHER.getExpenseType(), expense.getOther().getExpenseTotal()));
    }

}


//Bar chart in JavaFX: https://docs.oracle.com/javafx/2/charts/bar-chart.htm
//Integrating Java Swing and JavaFX: https://docs.oracle.com/javafx/2/swing/swing-fx-interoperability.htm