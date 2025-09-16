package Controller.CustomerManager;

import javafx.collections.ObservableList;
import model.Customer;
import model.Item;

public interface CustomerManagementService {
    void AddItem(String id, String title, String name, String dob, double salary, String address, String city, String province, String postal_code);
    void DeleteItem(String id);
    void UpdateItem(String id, String title, String name, String dob, double salary, String address, String city, String province, String postal_code);
    ObservableList<Customer> getAll();
}
