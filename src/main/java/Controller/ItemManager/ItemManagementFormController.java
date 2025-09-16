package Controller.ItemManager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Item;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemManagementFormController implements Initializable {
    ObservableList<Item> itemDetails = FXCollections.observableArrayList();

    @FXML
    private TableColumn<?, ?> QuantityOnHand;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> description;

    @FXML
    private TableColumn<?, ?> idem_Code;

    @FXML
    private TableColumn<?, ?> packSize;

    @FXML
    private TableColumn<?, ?> unitPrice;
    @FXML
    private TableView<Item> tblItem;
    @FXML
    private JFXTextArea txtDescription;

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private JFXTextField txtPackSize;

    @FXML
    private JFXTextField txtQuantity;

    @FXML
    private JFXTextField txtUnitPrice;
    @FXML
    ItemManagementService itemManagementService=new ItemManagmentController();
    @FXML
    void DeleteButtonOnAction(ActionEvent event) {
        String  ItemCode = txtItemCode.getText();
        itemManagementService.DeleteItem(ItemCode);
        clearTextAreas();
        loadItems();
    }

    @FXML
    void UpdateButtonOnAction(ActionEvent event) {
        String  ItemCode = txtItemCode.getText();
        String Description = txtDescription.getText();
        String PackSize = txtPackSize.getText();
        Double UnitPrice = Double.parseDouble(txtUnitPrice.getText());
        int Quantity = Integer.parseInt(txtQuantity.getText());
        itemManagementService.UpdateItem(ItemCode,Description,PackSize,UnitPrice,Quantity);
        clearTextAreas();
        loadItems();
    }

    @FXML
    void addButtonOnAction(ActionEvent event) {
        String  ItemCode = txtItemCode.getText();
        String Description = txtDescription.getText();
        String PackSize = txtPackSize.getText();
        Double UnitPrice = Double.parseDouble(txtUnitPrice.getText());
        int Quantity = Integer.parseInt(txtQuantity.getText());
        itemManagementService.AddItem(ItemCode,Description,PackSize,UnitPrice,Quantity);
        clearTextAreas();
        loadItems();
    }
    private void loadItems(){
        itemDetails.clear();
        tblItem.setItems(itemManagementService.getAll());
    }
    private void clearTextAreas(){
        txtItemCode.setText("");
        txtDescription.setText("");
        txtPackSize.setText("");
        txtUnitPrice.setText("");
        txtQuantity.setText("");
    }
    public void initialize(URL url, ResourceBundle resourceBundle) {

//        ------set table details----------
        idem_Code.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        packSize.setCellValueFactory(new PropertyValueFactory<>("packSize"));
        unitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        QuantityOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));

        loadItems();

        tblItem.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if(newValue != null) {
                txtItemCode.setText(String.valueOf(newValue.getItemCode()));
                txtDescription.setText(newValue.getDescription());
                txtPackSize.setText(String.valueOf(newValue.getPackSize()));
                txtUnitPrice.setText(String.valueOf(newValue.getUnitPrice()));
                txtQuantity.setText(String.valueOf(newValue.getQtyOnHand()));
            }
        }));

//        ------------------------------------

    }

}
