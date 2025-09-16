package Controller.CustomerManager;

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
import model.Customer;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerManagementFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> Address;

    @FXML
    private TableColumn<?, ?> City;

    @FXML
    private TableColumn<?, ?> DOB;

    @FXML
    private TableColumn<?, ?> Province;

    @FXML
    private TableColumn<?, ?> Salary;

    @FXML
    private TableView<Customer> TblCustomer;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> custId;

    @FXML
    private TableColumn<?, ?> custName;

    @FXML
    private TableColumn<?, ?> custTitle;

    @FXML
    private TableColumn<?, ?> postalCode;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtCity;

    @FXML
    private JFXTextField txtCusId;

    @FXML
    private JFXTextField txtCustName;

    @FXML
    private JFXTextField txtCustTitle;

    @FXML
    private JFXTextField txtDOB;

    @FXML
    private JFXTextField txtPostalCode;

    @FXML
    private JFXTextField txtProvince;

    @FXML
    private JFXTextField txtSalary;
    ObservableList<Customer> customers = FXCollections.observableArrayList();
    CustomerManagementService customerManagementService=new CustomerManagementController();
    @FXML
    void BtnAddOnAction(ActionEvent event) {
        if(txtCusId.getText()==null || txtCusId.getText()==""){
            JOptionPane.showMessageDialog(null,
                    "Please Fill All data: ",
                    "Empty Area Detected",
                    JOptionPane.ERROR_MESSAGE);
        }else {
            String id = txtCusId.getText();
            String title = txtCustTitle.getText();
            String name = txtCustName.getText();
            String dob = txtDOB.getText();
            Double salary = Double.parseDouble(txtSalary.getText());
            String address = txtAddress.getText();
            String city = txtCity.getText();
            String province = txtProvince.getText();
            String postal_code = txtPostalCode.getText();
            customerManagementService.AddItem(id, title, name, dob, salary, address, city, province, postal_code);
            clearTextAreas();
            loadItems();
        }
    }

    @FXML
    void BtnDeleteOnAction(ActionEvent event) {
        if(txtCusId.getText()==null || txtCusId.getText()==""){
            JOptionPane.showMessageDialog(null,
                    "Please Fill All data: ",
                    "Empty Area Detected",
                    JOptionPane.ERROR_MESSAGE);
        }else{
            customerManagementService.DeleteItem(txtCusId.getText());
            clearTextAreas();
            loadItems();
        }

    }

    @FXML
    void BtnUpdateOnAction(ActionEvent event) {
        if(txtCusId.getText()==null || txtCusId.getText()==""){
            JOptionPane.showMessageDialog(null,
                    "Please Fill All data: ",
                    "Empty Area Detected",
                    JOptionPane.ERROR_MESSAGE);
        }else {
            String id = txtCusId.getText();
            String title = txtCustTitle.getText();
            String name = txtCustName.getText();
            String dob = txtDOB.getText();
            Double salary = Double.parseDouble(txtSalary.getText());
            String address = txtAddress.getText();
            String city = txtCity.getText();
            String province = txtProvince.getText();
            String postal_code = txtPostalCode.getText();
            customerManagementService.UpdateItem(id, title, name, dob, salary, address, city, province, postal_code);
            clearTextAreas();
            loadItems();
        }
    }
    private void loadItems(){
        customers.clear();
        TblCustomer.setItems(customerManagementService.getAll());
    }
    public void initialize(URL url, ResourceBundle resourceBundle){
        custId.setCellValueFactory(new PropertyValueFactory<>("id"));
        custTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        custName.setCellValueFactory(new PropertyValueFactory<>("name"));
        DOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        Salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        Address.setCellValueFactory(new PropertyValueFactory<>("address"));
        City.setCellValueFactory(new PropertyValueFactory<>("city"));
        Province.setCellValueFactory(new PropertyValueFactory<>("province"));
        postalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));

        loadItems();

        TblCustomer.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if(newValue != null) {
                txtCusId.setText(newValue.getId());
                txtCustTitle.setText(newValue.getTitle());
                txtCustName.setText(newValue.getName());
                txtDOB.setText(String.valueOf(newValue.getDob()));
                txtSalary.setText(String.valueOf(newValue.getSalary()));
                txtAddress.setText(newValue.getAddress());
                txtCity.setText(newValue.getCity());
                txtProvince.setText(newValue.getProvince());
                txtPostalCode.setText(newValue.getPostalCode());
            }
        }));
    }
    private void clearTextAreas(){
        txtCusId.setText("");
        txtCustTitle.setText("");
        txtCustName.setText("");
        txtDOB.setText("");
        txtSalary.setText("");
        txtAddress.setText("");
        txtPostalCode.setText("");
        txtProvince.setText("");
        txtCity.setText("");
    }


}
