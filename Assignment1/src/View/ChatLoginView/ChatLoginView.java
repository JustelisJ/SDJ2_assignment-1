package View.ChatLoginView;

import View.ViewManager;
import ViewModel.ViewModelChatLogin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ChatLoginView {

    @FXML private TextField nameTextField;

    @FXML private Label errorLabel;

    private String title;
    private Scene scene;
    private ViewManager view;
    private ViewModelChatLogin viewModel;

    public ChatLoginView()
    {
    }

    public void init(ViewModelChatLogin viewModel, ViewManager view, Scene scene, String title) {
        this.viewModel = viewModel;
        this.view = view;
        this.scene = scene;
        this.title = title;

        errorLabel.textProperty().bindBidirectional(viewModel.getErrorProperty());
        nameTextField.textProperty().bindBidirectional(viewModel.getNameProperty());
    }

    public Scene getScene() {
        return scene;
    }

    public String getTitle() {
        return title;
    }

    @FXML public void EnterChatRoomButtonPressed(ActionEvent actionEvent) {
        viewModel.enter();
        if (errorLabel.getText() != null && errorLabel.getText().isEmpty())
        {
            nextWindow();
        }
    }

    @FXML public void OnEnterPressed(KeyEvent keyEvent) {
        if(keyEvent.getCode().equals(KeyCode.ENTER))
        {
            viewModel.enter();
            if (errorLabel.getText() != null && errorLabel.getText().isEmpty())
            {
                nextWindow();
            }
        }
    }

    public void nextWindow()
    {
        getScene().getWindow().hide();
        view.setWindow("Room");
    }
}
