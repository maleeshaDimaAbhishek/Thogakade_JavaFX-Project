package Controller.OrderManager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Orders;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class OrderFormManagementController implements Initializable {
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXButton btnPlaceOrder;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> tblCustId;

    @FXML
    private TableColumn<?, ?> tblOrderDate;

    @FXML
    private TableColumn<?, ?> tblOrderId;

    @FXML
    private TableView<Orders> tblOrders;

    @FXML
    private JFXTextField txtCustomerId;

    @FXML
    private JFXTextField txtOrderDate;

    @FXML
    private JFXTextField txtOrderId;
    ObservableList<Orders> orders = FXCollections.observableArrayList();
    OrderManagementService orderManagementService=new OrderManagementController();
    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        if(txtOrderId.getText()==""||txtOrderDate.getText()==""||txtCustomerId.getText()==""){
            JOptionPane.showMessageDialog(null,
                    "Please Fill All data: ",
                    "Empty Area Detected",
                    JOptionPane.ERROR_MESSAGE);
        }else{
            String orderId = txtOrderId.getText();
            String orderDate= txtOrderDate.getText();
            String custId = txtCustomerId.getText();
            orderManagementService.placeOrder(orderId,orderDate,custId);
            clearTextAreas();
            loadItems();
        }

    }

    @FXML
    void btnUpdateOrderOnAction(ActionEvent event) {
        if(txtOrderId.getText()==""||txtOrderDate.getText()==""||txtCustomerId.getText()==""){
            JOptionPane.showMessageDialog(null,
                    "Please Fill All data: ",
                    "Empty Area Detected",
                    JOptionPane.ERROR_MESSAGE);
        }else{
            String orderId = txtOrderId.getText();
            String orderDate= txtOrderDate.getText();
            String custId = txtCustomerId.getText();
            orderManagementService.updateOrder(orderId,orderDate,custId);
            clearTextAreas();
            loadItems();
        }
    }
    @FXML
    void btnDeleteOrderOnAction(ActionEvent event) {
        orderManagementService.deleteOrder(txtOrderId.getText());
        clearTextAreas();
        loadItems();
    }
    private void loadItems(){
        orders.clear();
        tblOrders.setItems(orderManagementService.getAll());
    }
    private void clearTextAreas(){
        txtOrderId.setText("");
        txtOrderDate.setText("");
        txtCustomerId.setText("");

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        tblOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        tblCustId.setCellValueFactory(new PropertyValueFactory<>("customerId"));


        loadItems();

        tblOrders.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if(newValue != null) {
                txtOrderId.setText(String.valueOf(newValue.getOrderId()));
                txtOrderDate.setText(newValue.getOrderDate());
                txtCustomerId.setText(String.valueOf(newValue.getCustomerId()));
            }
        }));
    }
}
