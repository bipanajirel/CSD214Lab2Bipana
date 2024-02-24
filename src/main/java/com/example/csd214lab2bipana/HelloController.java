package com.example.csd214lab2bipana;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    public TextField iD;
    public TextField managers;
    public TextField departments;
    public TextField workers;

    @FXML
    private Label welcomeText;
    @FXML
    private TableView<Restaurant> restaurant;
    @FXML
    private TableColumn<Restaurant, Integer> id;
    @FXML
    private TableColumn<Restaurant, String> Managers;
    @FXML
    private TableColumn<Restaurant, String> Departments;
    @FXML
    private TableColumn<Restaurant, Integer> Workers;
    ObservableList<Restaurant> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new
                PropertyValueFactory<Restaurant, Integer>("id"));
        Managers.setCellValueFactory(new
                PropertyValueFactory<Restaurant, String>("Managers"));
        Departments.setCellValueFactory(new
                PropertyValueFactory<Restaurant, String>("Departments"));
        Workers.setCellValueFactory(new
                PropertyValueFactory<Restaurant, Integer>("Workers"));
        restaurant.setItems(list);
    }

    @FXML
    protected void onHelloButtonClick() {
        populateTable();
    }

    public void populateTable() {
        // Establish a database connection

        list.clear();
        String jdbcUrl = "jdbc:mysql://localhost:3306/csd214lab2bipana";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
            // Execute a SQL query to retrieve data from the database
            String query = "SELECT * FROM restaurant";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            // Populate the table with data from the database
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String Managers = resultSet.getString("Managers");
                String Departments = resultSet.getString("Departments");
                int Workers = resultSet.getInt("Workers");
                restaurant.getItems().add(new Restaurant(id, Managers, Departments, Workers));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void InsertData(ActionEvent actionEvent) {
        String manager = managers.getText();
        String department = departments.getText();
        Integer worker = Integer.valueOf(workers.getText());
        InserTable(manager, department, worker);

    }

    private void InserTable(String manager, String department, Integer worker) {
        // Establish a database connection
        String jdbcUrl = "jdbc:mysql://localhost:3306/csd214lab2bipana";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "INSERT INTO `restaurant`(`Managers`, `Departments`, `Workers`) VALUES ('" + manager + "','" + department + "','" + worker + "')";
            Statement statement = connection.createStatement();
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        populateTable();
    }

    public void DeleteData(ActionEvent actionEvent) {
        Integer id = Integer.valueOf(iD.getText());
        DeleteTable(id);
    }

    private void DeleteTable(Integer id) {
        // Establish a database connection
        String jdbcUrl = "jdbc:mysql://localhost:3306/csd214lab2bipana";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "DELETE FROM `restaurant` WHERE iD='"+id+"'";
            Statement statement = connection.createStatement();
            statement.execute(query);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        populateTable();
    }

    public void UpdateData(ActionEvent actionEvent) {
        Integer id= Integer.valueOf(iD.getText());
        String manager = managers.getText();
        String department = departments.getText();
        Integer worker = Integer.valueOf(workers.getText());
        UpdateTable(id, manager, department, worker);
    }

    private void UpdateTable(Integer id, String manager, String department, Integer worker) {
        // Establish a database connection
        String jdbcUrl = "jdbc:mysql://localhost:3306/csd214lab2bipana";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "UPDATE `restaurant` SET `Managers`='"+manager+"',`Departments`='"+department+"',`Workers`='"+worker+"' WHERE iD='"+id+"'";
            Statement statement = connection.createStatement();
            statement.execute(query);
            populateTable();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        populateTable();
    }

    public void ViewData(ActionEvent actionEvent) {
        Integer id = Integer.valueOf(iD.getText());
        LoadTable(id);
    }

    private void LoadTable(Integer id) {
            String jdbcUrl = "jdbc:mysql://localhost:3306/csd214lab2bipana";
            String dbUser = "root";
            String dbPassword = "";
            try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                    dbPassword)) {
                // Execute a SQL query to retrieve data from the database
                String query = "SELECT * FROM restaurant WHERE iD='"+id+"'";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                // Populate the table with data from the database
                while (resultSet.next()) {

                    String Managers = resultSet.getString("Managers");
                    String Departments = resultSet.getString("Departments");
                    int Workers = resultSet.getInt("Workers");
                     managers.setText(Managers);
                     departments.setText(Departments);
                     workers.setText(String.valueOf(Integer.valueOf(Workers)));


                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        populateTable();
        }
    }


