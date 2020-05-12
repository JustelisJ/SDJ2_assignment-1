package Model;

import utility.observer.subject.UnnamedPropertyChangeSubject;

public interface Model extends UnnamedPropertyChangeSubject {

    public void setName(String name);

    public void sendText(String message);

    public void gotAText(Text text);

}
