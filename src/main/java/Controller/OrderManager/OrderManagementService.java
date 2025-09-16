package Controller.OrderManager;

import javafx.collections.ObservableList;
import model.Item;
import model.Orders;

public interface OrderManagementService {
    void placeOrder(String orderId,String orderDate,String customerId);
    void updateOrder(String orderId,String orderDate,String customerId);
    ObservableList<Orders> getAll();
}
