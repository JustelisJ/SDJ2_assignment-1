package View;

import View.ChatLoginView.ChatLoginView;
import View.ChatRoomView.ChatRoomView;
import ViewModel.ViewModelManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewManager {

    private Stage primaryStage;

    private ChatLoginView chatLoginView;
    private ChatRoomView ChatRoomView;
    private String currentViewID;

    private ViewModelManager viewModel;

    public ViewManager(ViewModelManager viewModel)
    {
        this.viewModel = viewModel;
    }

    public void start(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
        setWindow("Login");
    }

    public void setWindow(String id)
    {
        currentViewID = id;
        switch (id)
        {
            case "Login":
                loadChatLogin("Welcome", "/View/ChatLoginView/ChatLogin.fxml", 600, 400);
                break;
            case "Room":
                loadChatRoom("Chat room", "/View/ChatRoomView/ChatRoom.fxml", 600, 400);
                break;
        }
    }

    private void loadChatLogin(String title, String fxmlFile, double width,
                               double height)
    {
        if (chatLoginView == null)
        {
            try
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Parent root = loader.load();
                Scene scene = new Scene(root, width, height);
                chatLoginView = loader.getController();
                chatLoginView.init(viewModel.getViewModelChatLogin(), this, scene, title);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        primaryStage.setScene(chatLoginView.getScene());
        primaryStage.setTitle(chatLoginView.getTitle());
        primaryStage.show();
    }

    private void loadChatRoom(String title, String fxmlFile, double width,
                              double height)
    {
        if (ChatRoomView == null)
        {
            try
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Parent root = loader.load();
                Scene scene = new Scene(root, width, height);
                ChatRoomView = loader.getController();
                ChatRoomView.init(viewModel.getViewModelChatRoom(), this, scene, title);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        primaryStage.setScene(ChatRoomView.getScene());
        primaryStage.setTitle(ChatRoomView.getTitle());
        primaryStage.show();
    }


}
