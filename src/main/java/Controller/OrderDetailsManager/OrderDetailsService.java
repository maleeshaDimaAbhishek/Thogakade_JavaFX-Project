package Controller.OrderDetailsManager;

import javafx.collections.ObservableList;
import model.OrderDetails;
import model.Orders;

public interface OrderDetailsService {
    void updateOrderDetails(String itemCode,String orderQuantity,Double discount,Double total);
    void deleteOrderDetails(String orderId,String orderDate,String customerId);
    ObservableList<OrderDetails> getAll();
}
