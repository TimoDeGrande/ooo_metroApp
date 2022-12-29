package domain.model;

public interface Observer {
    void update(MetroEventsEnum e);

    void close(MetroEventsEnum e);

    void setSubject(Subject subject);
}
