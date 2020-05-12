import Model.Model;
import Model.ChatModel;
import View.ViewManager;
import ViewModel.ViewModelManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class RunApplication extends Application{

   @Override
   public void start(Stage primaryStage) throws Exception
   {
      Model model = new ChatModel();
      ViewModelManager viewModel = new ViewModelManager(model);
      ViewManager view = new ViewManager(viewModel);
      view.start(primaryStage);
   }

}