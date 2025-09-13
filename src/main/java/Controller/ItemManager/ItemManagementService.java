package Controller.ItemManager;

import javafx.collections.ObservableList;
import model.Item;

public interface ItemManagementService {
    void AddItem(String ItemCode, String Description, String PackSize, Double UnitPrice, int Quantity);
    void DeleteItem(int RoomNumber);
    void UpdateItem(String roomType,Double pricePerNight,String description,String roomStatus,int roomNumber);
    ObservableList<Item> getAll();
}
