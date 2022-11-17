import database.dao.StudentDao;
import database.model.StudentEntity;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        StudentDao studentDao = new StudentDao();
        StudentEntity stMock1           = StudentEntity.builder().surname("Mock1").forename("Mock1").email("mock1@mock.com").build();
        StudentEntity duplicatedStMock1 = StudentEntity.builder().surname("Mock1").forename("Mock1").email("mock1@mock.com").build();
        StudentEntity stMock2           = StudentEntity.builder().surname("Mock2").forename("Mock2").email("mock2@mock.com").build();
        StudentEntity stMock3           = StudentEntity.builder().surname("Mock3").forename("Mock3").email("mock3@mock.com").build();
        StudentEntity stMock4           = StudentEntity.builder().surname("Mock4").forename("Mock4").email("mock4@mock.com").build();

        studentDao.addAll(stMock1, duplicatedStMock1, stMock2, stMock3, stMock4);

        System.out.println("PRINT ALL STUDENTS");
        studentDao.findAll().forEach(System.out::println);

        System.out.println("PRINT ALL STUDENTS with forename Mock1");
        studentDao.findAllByForename("Mock1").forEach(System.out::println);

        System.out.println("PRINT FIRST STUDENT FIND WITH FORENAME Mock1");
        StudentEntity stUpdate = studentDao.findFirstByForename("Mock1");
        System.out.println(stUpdate);

        stUpdate.setSurname("updatedSurname");
        stUpdate.setForename("updatedForename");
        stUpdate.setEmail("update@update.com");
        studentDao.update(stUpdate);
    }
}
