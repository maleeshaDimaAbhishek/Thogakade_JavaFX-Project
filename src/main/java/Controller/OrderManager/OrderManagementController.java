package Controller.OrderManager;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Item;
import model.Orders;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderManagementController implements OrderManagementService{
    Connection connection;

    {
        try {
            connection = DBConnection.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void placeOrder(String orderId,String orderDate,String customerId) {
        String SQL = "INSERT INTO Orders(OrderID ,OrderDate ,CustID ) VALUES(?,?,?);";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(SQL);
            preparedStatement.setObject(1,orderId);
            preparedStatement.setObject(2,orderDate);
            preparedStatement.setObject(3,customerId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateOrder(String orderId,String orderDate,String customerId) {
        try{
            String SQL = "UPDATE Orders SET CustID  = ?, OrderDate  = ? WHERE OrderID  = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setObject(1,customerId);
            preparedStatement.setObject(2,orderDate);
            preparedStatement.setObject(3,orderId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Customer ID Can't Update ",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public ObservableList<Orders> getAll() {
        ObservableList<Orders> ordersList= FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("Select * FROM Orders;");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ordersList.add(new Orders(
                                resultSet.getString("OrderID"),
                                resultSet.getString("OrderDate"),
                                resultSet.getString("CustID")
                        )
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ordersList;
    }
}
