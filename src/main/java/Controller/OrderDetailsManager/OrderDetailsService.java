package Controller.OrderDetailsManager;

import javafx.collections.ObservableList;
import model.OrderDetails;
import model.Orders;

public interface OrderDetailsService {
    void updateOrderDetails(String orderId, String itemCode, int orderQunatity,int discount);
    void deleteOrderDetails(String orderId);
    ObservableList<OrderDetails> getAll();
}
