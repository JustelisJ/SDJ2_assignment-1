package View.ChatRoomView;

import View.ViewManager;
import ViewModel.ViewModelChatRoom;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ChatRoomView{

    @FXML private TextField messageTextField;

    @FXML private TextArea displayTextArea;

    private String title;
    private Scene scene;
    private ViewManager view;
    private ViewModelChatRoom viewModel;

    public ChatRoomView()
    {

    }

    public void init(ViewModelChatRoom viewModel, ViewManager view, Scene scene, String title) {
        this.viewModel = viewModel;
        this.view = view;
        this.scene = scene;
        this.title = title;

        messageTextField.textProperty().bindBidirectional(viewModel.getMessageProperty());
        displayTextArea.textProperty().bindBidirectional(viewModel.getTextAreaProperty());
    }

    public Scene getScene() {
        return scene;
    }

    public String getTitle() {
        return title;
    }

    public void SendButtonPressed(ActionEvent actionEvent) {
        viewModel.sendMessage();
    }

    public void EnterPressed(KeyEvent keyEvent) {
        if(keyEvent.getCode().equals(KeyCode.ENTER))
        {
            viewModel.sendMessage();
        }
    }
}
