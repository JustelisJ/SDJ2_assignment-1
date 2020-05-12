package ViewModel;

import Model.Model;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ViewModelChatLogin {

    private StringProperty nameProperty;
    private StringProperty errorLabel;

    private Model model;

    public ViewModelChatLogin(Model model)
    {
        this.model = model;
        nameProperty = new SimpleStringProperty();
        errorLabel = new SimpleStringProperty();
    }

    public void enter()
    {
        errorLabel.set("");
        try
        {
            enterChat();
        }
        catch (Exception e)
        {
            errorLabel.set("Cant connect to the server");
        }
    }

    public void enterChat()
    {
        model.setName(nameProperty.get());
    }

    public StringProperty getNameProperty()
    {
        return nameProperty;
    }

    public StringProperty getErrorProperty()
    {
        return errorLabel;
    }

}
