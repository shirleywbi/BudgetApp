package ui.chart;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;

import javax.swing.*;

import static model.ExpenseType.*;
import static ui.BudgetTrackerUI.expense;

public class ExpenseBarChart extends JPanel {

    //EFFECTS: returns a Scene with the Expense Type Bar Chart
    public Scene createExpenseTypeBarChart() {
        Group root = new Group();
        Scene scene = new Scene(root, Color.WHITE);
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> bc = new BarChart(xAxis, yAxis);

        bc.setTitle("Expense Breakdown");
        xAxis.setLabel("Expense Category");
        yAxis.setLabel("Amount Spent ($)");
        bc.setLegendVisible(false);
        bc.getData().addAll(updateData());
        root.getChildren().add(bc);
        return (scene);
    }

    //EFFECTS: creates expense type data series for bar chart
    private XYChart.Series updateData() {
        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data(FOOD.getExpenseType(), expense.getFood().getExpenseTotal()));
        series.getData().add(new XYChart.Data(ENTERTAINMENT.getExpenseType(), expense.getEntertainment().getExpenseTotal()));
        series.getData().add(new XYChart.Data(HEALTH.getExpenseType(), expense.getHealth().getExpenseTotal()));
        series.getData().add(new XYChart.Data(TRANSPORTATION.getExpenseType(), expense.getTransportation().getExpenseTotal()));
        series.getData().add(new XYChart.Data(RENT.getExpenseType(), expense.getRent().getExpenseTotal()));
        series.getData().add(new XYChart.Data(OTHER.getExpenseType(), expense.getOther().getExpenseTotal()));
        return series;
    }
}


//Bar chart in JavaFX: https://docs.oracle.com/javafx/2/charts/bar-chart.htm
//Integrating Java Swing and JavaFX: https://docs.oracle.com/javafx/2/swing/swing-fx-interoperability.htm