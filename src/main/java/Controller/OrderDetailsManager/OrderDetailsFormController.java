package Controller.OrderDetailsManager;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.OrderDetails;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderDetailsFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> Discount;

    @FXML
    private TableColumn<?, ?> Item_Code;

    @FXML
    private TableColumn<?, ?> Order_Id;

    @FXML
    private TableColumn<?, ?> Order_Quantity;

    @FXML
    private TableView<OrderDetails> TblOrderDetails;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private AnchorPane btnUpdate;

    @FXML
    private TableColumn<?, ?> total;

    @FXML
    private JFXTextField txtDiscount;

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private JFXTextField txtOrderQuantity;

    @FXML
    private JFXTextField txtTotal;

    ObservableList<OrderDetails> orders = FXCollections.observableArrayList();
    OrderDetailsService orderDetailsService=new OrderDetailsController();
    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(MouseEvent event) {

    }
    private void loadItems(){
        orders.clear();
        TblOrderDetails.setItems(orderDetailsService.getAll());
    }
    private void clearTextAreas(){
        txtItemCode.setText("");
        txtOrderQuantity.setText("");
        txtDiscount.setText("");
        txtTotal.setText("");

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Order_Id.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        Item_Code.setCellValueFactory(new PropertyValueFactory<>("item_Code"));
        Order_Quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        Discount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        //total.setCellValueFactory(new PropertyValueFactory<>("total"));


        loadItems();

        TblOrderDetails.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if(newValue != null) {
                txtItemCode.setText(newValue.getItem_Code());
                txtOrderQuantity.setText(String.valueOf(newValue.getQuantity()));
                txtDiscount.setText(String.valueOf(newValue.getDiscount()));
                //txtTotal.setText(String.valueOf(newValue.getTotal()));
            }
        }));
    }
}

