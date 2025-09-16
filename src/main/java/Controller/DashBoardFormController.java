package Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DashBoardFormController {
    Stage ItemManagement = new Stage();
    Stage customerManagement = new Stage();
    Stage orderManagement=new Stage();
    @FXML
    private JFXButton btnCustomermgt;

    @FXML
    private JFXButton btnManageItem;
    @FXML
    private JFXButton btnManageOrder;

    @FXML
    private JFXButton btnManageOrderDetails;

    @FXML
    void ManageOrderButtonClickAction(ActionEvent event) {
        try {
            orderManagement.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/OrderManagement.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        orderManagement.setResizable(false);
        orderManagement.show();
    }

    @FXML
    void ManageOrderDetailsButtonClickAction(ActionEvent event) {

    }

    @FXML
    void customerButtonClickAction(ActionEvent event) {
        try {
            customerManagement.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/CustomerManagement.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        customerManagement.setResizable(false);
        customerManagement.show();
    }

    @FXML
    void itemButtonClickAction(ActionEvent event) {
        try {
            ItemManagement.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/ItemManagement.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ItemManagement.setResizable(false);
        ItemManagement.show();
    }
}

