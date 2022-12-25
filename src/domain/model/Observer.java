package domain.model;

public interface Observer {
    void update();
    void setSubject(Subject subject);
}
