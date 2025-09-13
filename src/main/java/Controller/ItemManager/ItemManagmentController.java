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
    @Override
    public void AddItem(String ItemCode, String Description, String PackSize, Double UnitPrice, int Quantity) {

    }

    @Override
    public void DeleteItem(int RoomNumber) {

    }

    @Override
    public void UpdateItem(String roomType, Double pricePerNight, String description, String roomStatus, int roomNumber) {

    }

    @Override
    public ObservableList<Item> getAll() {
        ObservableList<Item> itermList= FXCollections.observableArrayList();
        try {
            Connection connection = DBConnection.getInstance().getConnection();
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

