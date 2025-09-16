package Controller.OrderDetailsManager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class OrderDetailsFormController {

    @FXML
    private TableColumn<?, ?> Discount;

    @FXML
    private TableColumn<?, ?> Item_Code;

    @FXML
    private TableColumn<?, ?> Order_Id;

    @FXML
    private TableColumn<?, ?> Order_Quantity;

    @FXML
    private TableView<?> TblOrderDetails;

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

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(MouseEvent event) {

    }

}
