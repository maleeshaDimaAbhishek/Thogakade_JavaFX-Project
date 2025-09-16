package Controller.ItemManager;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemManagmentController implements ItemManagementService{
    Connection connection;

    {
        try {
            connection = DBConnection.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void AddItem(String ItemCode, String Description, String PackSize, Double UnitPrice, int Quantity) {
        String SQL = "INSERT INTO Item(ItemCode,Description,PackSize,UnitPrice,QtyOnHand) VALUES(?,?,?,?,?);";
        try {

            PreparedStatement preparedStatement=connection.prepareStatement(SQL);
            preparedStatement.setObject(1,ItemCode);
            preparedStatement.setObject(2,Description);
            preparedStatement.setObject(3,PackSize);
            preparedStatement.setObject(4,UnitPrice);
            preparedStatement.setObject(5,Quantity);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void DeleteItem(String ItemCode) {
        try{
            String SQL = "DELETE FROM Item WHERE ItemCode = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setObject(1,ItemCode);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void UpdateItem(String ItemCode, String Description, String PackSize, Double UnitPrice, int Quantity) {
        try{
            String SQL = "UPDATE Item SET QtyOnHand = ?, Description = ?, PackSize = ?, UnitPrice =? WHERE ItemCode = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setObject(1,Quantity);
            preparedStatement.setObject(2,Description);
            preparedStatement.setObject(3,PackSize);
            preparedStatement.setObject(4,UnitPrice);
            preparedStatement.setObject(5,ItemCode);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<Item> getAll() {
        ObservableList<Item> itermList= FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("Select * FROM Item;");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                itermList.add(new Item(
                                resultSet.getString("itemCode"),
                                resultSet.getString("description"),
                                resultSet.getString("packSize"),
                                resultSet.getDouble("unitPrice"),
                                resultSet.getInt("qtyOnHand")
                        )
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return itermList;
    }
}

