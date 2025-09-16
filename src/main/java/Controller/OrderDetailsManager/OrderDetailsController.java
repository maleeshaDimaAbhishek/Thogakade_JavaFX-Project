package Controller.OrderDetailsManager;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.OrderDetails;
import model.Orders;

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
    public void updateOrderDetails(String itemCode, String orderQuantity, Double discount, Double total) {

    }

    @Override
    public void deleteOrderDetails(String orderId, String orderDate, String customerId) {

    }

    @Override
    public ObservableList<OrderDetails> getAll() {
        ObservableList<OrderDetails> ordersDetailsList= FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("Select * FROM OrderDetail;");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ordersDetailsList.add(new OrderDetails(
                                resultSet.getString("OrderID"),
                                resultSet.getString("ItemCode"),
                                resultSet.getInt("OrderQTY"),
                                resultSet.getInt("Discount")
                        )
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ordersDetailsList;
    }
}
