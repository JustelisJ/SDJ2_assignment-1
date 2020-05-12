package ViewModel;

import Model.Model;

public class ViewModelManager {
    private ViewModelChatRoom viewModelChatRoom;
    private ViewModelChatLogin viewModelChatLogin;
    private Model model;

    public ViewModelManager(Model model)
    {
        this.model = model;
        this.viewModelChatRoom = new ViewModelChatRoom(model, model);
        this.viewModelChatLogin = new ViewModelChatLogin(model);
    }

    public ViewModelChatRoom getViewModelChatRoom()
    {
        return viewModelChatRoom;
    }

    public ViewModelChatLogin getViewModelChatLogin()
    {
        return viewModelChatLogin;
    }
}