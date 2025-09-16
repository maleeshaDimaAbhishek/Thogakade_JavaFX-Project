package Controller.CustomerManager;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerManagementController implements CustomerManagementService{
    Connection connection;

    {
        try {
            connection = DBConnection.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void AddItem(String id, String title, String name, String dob, double salary, String address, String city, String province, String postal_code) {
        String SQL = "INSERT INTO customer(CustID,CustTitle,CustName,DOB,salary,CustAddress,City,Province,PostalCode) VALUES(?,?,?,?,?,?,?,?,?);";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(SQL);
            preparedStatement.setObject(1,id);
            preparedStatement.setObject(2,title);
            preparedStatement.setObject(3,name);
            preparedStatement.setObject(4,dob);
            preparedStatement.setObject(5,salary);
            preparedStatement.setObject(6,address);
            preparedStatement.setObject(7,city);
            preparedStatement.setObject(8,province);
            preparedStatement.setObject(9,postal_code);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void DeleteItem(String id) {
        try{
            String SQL = "DELETE FROM customer WHERE CustID = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setObject(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void UpdateItem(String id, String title, String name, String dob, double salary, String address, String city, String province, String postal_code) {
        try{
            String SQL = "UPDATE customer SET CustTitle = ?, CustName = ?, DOB = ?, salary =?, CustAddress=?, City=? ,Province=?, PostalCode=?  WHERE CustID = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setObject(1,title);
            preparedStatement.setObject(2,name);
            preparedStatement.setObject(3,dob);
            preparedStatement.setObject(4,salary);
            preparedStatement.setObject(5,address);
            preparedStatement.setObject(6,city);
            preparedStatement.setObject(7,province);
            preparedStatement.setObject(8,postal_code);
            preparedStatement.setObject(9,id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<Customer> getAll() {
        ObservableList<Customer> customerList= FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("Select * FROM customer;");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customerList.add(new Customer(
                                resultSet.getString("CustID"),
                                resultSet.getString("CustTitle"),
                                resultSet.getString("CustName"),
                                resultSet.getDate("DOB") != null ? resultSet.getDate("DOB").toString() : null,
                                resultSet.getDouble("salary"),
                                resultSet.getString("CustAddress"),
                                resultSet.getString("City"),
                                resultSet.getString("Province"),
                                resultSet.getString("PostalCode")
                        )
                );
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving customers: ");
            throw new RuntimeException(e);
        }
        return customerList;
    }
}

