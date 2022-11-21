package gui.controller;

import database.dao.StudentDao;
import database.model.StudentEntity;
import gui.model.StudentGuiModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    public TableView<StudentGuiModel> studentsTable  = new TableView<>();
    @FXML
    public TableColumn surnameCol;
    @FXML
    public TableColumn forenameCol;
    @FXML
    public TableColumn emailCol;
    @FXML
    public TableColumn idCol;
    private StudentDao studentDao = new StudentDao();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<StudentEntity> students = studentDao.findAll();
        /* Columns already added in FXML, this is just a code based model */
        students.forEach(System.out::println);
        /*
        TableColumn idCol = new TableColumn("ID");
        TableColumn surnameCol = new TableColumn("Surname");
        TableColumn forenameCol = new TableColumn("Forename");
        TableColumn emailCol = new TableColumn("Email");
        studentsTable.getColumns().addAll(idCol, surnameCol, forenameCol, emailCol);
        */

        idCol.setCellValueFactory (new PropertyValueFactory<StudentEntity, String>("id"));
        surnameCol.setCellValueFactory (new PropertyValueFactory<StudentEntity, String>("surname"));
        forenameCol.setCellValueFactory(new PropertyValueFactory<StudentEntity, String>("forename"));
        emailCol.setCellValueFactory   (new PropertyValueFactory<StudentEntity, String>("email"));


        /* Maybe map() is a more elegant solution for this conversation */
        List<StudentGuiModel> studentGuiModels = new ArrayList<>();
        students.forEach(student -> studentGuiModels.add(new StudentGuiModel(student.getId(), student.getSurname(), student.getForename(), student.getEmail())));
        
         ObservableList<StudentGuiModel> data = FXCollections
                .observableArrayList(studentGuiModels);


       studentsTable.setItems(data);
    }
}
