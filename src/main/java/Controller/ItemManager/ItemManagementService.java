package Controller.ItemManager;

import javafx.collections.ObservableList;
import model.Item;

public interface ItemManagementService {
    void AddItem(String ItemCode, String Description, String PackSize, Double UnitPrice, int Quantity);
    void DeleteItem(String ItemCode);
    void UpdateItem(String ItemCode, String Description, String PackSize, Double UnitPrice, int Quantity);
    ObservableList<Item> getAll();
}
