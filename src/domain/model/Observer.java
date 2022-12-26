package domain.model;

public interface Observer {
    void update(MetroEventsEnum e);

    void setSubject(Subject subject);
}
