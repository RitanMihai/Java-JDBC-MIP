package gui.model;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class StudentGuiModel {
    private final SimpleLongProperty id;
    private final SimpleStringProperty surname;
    private final SimpleStringProperty forename;
    private final SimpleStringProperty email;

    public StudentGuiModel(Long id, String surname, String forename, String email) {
        this.id = new SimpleLongProperty(id);
        this.surname = new SimpleStringProperty(surname);
        this.forename =  new SimpleStringProperty(forename);
        this.email =  new SimpleStringProperty(email);
    }

    public Long getId() {
        return id.get();
    }

    public void setId(Long id) {
        this.id.set(id);
    }

    public String getSurname() {
        return surname.get();
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public String getForename() {
        return forename.get();
    }

    public void setForename(String forename) {
        this.forename.set(forename);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }
}
