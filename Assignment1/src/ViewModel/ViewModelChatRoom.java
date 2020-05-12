package ViewModel;

import Model.Model;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import utility.observer.subject.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ViewModelChatRoom implements PropertyChangeListener {

    private StringProperty messageProperty;
    private StringProperty textAreaProperty;

    private Model model;
    private UnnamedPropertyChangeSubject subject;

    public ViewModelChatRoom(Model model, UnnamedPropertyChangeSubject subject)
    {
        this.model = model;
        this.subject = subject;
        subject.addListener(this);
        messageProperty = new SimpleStringProperty();
        textAreaProperty = new SimpleStringProperty();
        textAreaProperty.set("");
    }

    public void sendMessage()
    {
        model.sendText(messageProperty.get());
        messageProperty.set("");
    }

    public StringProperty getMessageProperty()
    {
        return messageProperty;
    }

    public StringProperty getTextAreaProperty()
    {
        return textAreaProperty;
    }



    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("Receive"))
        {
            textAreaProperty.set(textAreaProperty.get() + evt.getNewValue().toString());
        }
    }
}
