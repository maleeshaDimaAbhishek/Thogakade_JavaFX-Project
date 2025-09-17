package Controller.OrderDetailsManager;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.OrderDetails;
import model.Orders;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetailsController implements OrderDetailsService{
    Connection connection;

    {
        try {
            connection = DBConnection.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void updateOrderDetails(String orderId, String itemCode, int orderQuantity,int discount) {
        try{
            String SQL = "UPDATE OrderDetail SET OrderQTY = ?, Discount = ? WHERE OrderID = ? AND ItemCode = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setInt(1, orderQuantity);
            preparedStatement.setInt(2, discount);
            preparedStatement.setString(3, orderId);
            preparedStatement.setString(4, itemCode);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteOrderDetails(String orderId) {
        try{
            String SQL = "DELETE FROM OrderDetail WHERE OrderID = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setObject(1,orderId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<OrderDetails> getAll() {
        ObservableList<OrderDetails> ordersDetailsList = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT od.OrderID, od.ItemCode, od.OrderQTY, od.Discount, " +
                            "(i.UnitPrice * od.OrderQTY) AS Total " +
                            "FROM OrderDetail od " +
                            "INNER JOIN Item i ON od.ItemCode = i.ItemCode"
            );

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ordersDetailsList.add(new OrderDetails(
                        resultSet.getString("OrderID"),
                        resultSet.getString("ItemCode"),
                        resultSet.getInt("OrderQTY"),
                        resultSet.getInt("Discount"),
                        resultSet.getDouble("Total")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ordersDetailsList;
    }
}
