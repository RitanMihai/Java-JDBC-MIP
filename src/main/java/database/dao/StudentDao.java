package database.dao;

import database.DatabaseConnection;
import database.model.StudentEntity;

import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/***
 * A DataAccessObject for StudentEntity with CRUD operations
 */
public class StudentDao {
    DatabaseConnection connection = new DatabaseConnection();
    /* CREATE */
    public void add(StudentEntity student) {
        connection.executeTransaction(entityManager -> entityManager.persist(student));
    }

    public void addAll(StudentEntity ... students) {
        for (StudentEntity student : students) {
            connection.executeTransaction(entityManager -> entityManager.persist(student));
        }
    }

    /* READ */
    /* *
     * I encapsulate the repetitive code (begin, commit, rollback in case of error)
     * in a separate method called executeQueryTransaction
     * */
    public List<StudentEntity> getAll(){
        List<StudentEntity> students = new ArrayList<>();

        /* Current transaction */
        EntityTransaction transaction = connection.getEntityManager().getTransaction();

        try {
            /* Start the transaction */
            transaction.begin();

            /* createQuery() uses Java Persistence Query Language (JPQL) syntax as first param. */
            TypedQuery<StudentEntity> query = connection.getEntityManager()
                    /* translates in native as:  SELECT * FROM student; */
                    .createQuery("SELECT student FROM StudentEntity student", StudentEntity.class);

                    students = query.getResultList();

            /* End the transaction */
            transaction.commit();

        } catch (RuntimeException e) {
            System.err.println("Transaction error: " + e.getLocalizedMessage());
            /* revert the transaction */
            transaction.rollback();
        }

        return students;
    }

    /* The short variant using functional programming */
    /* *
     * We can not select class from TypedQuery<T>, or any template in general. The base class, TypedQuery, is a good enough alternative,
     * but the compiler warns us that the return type can not be checked. If this approach bothers you, I have a longer version without
     * warnings above in getAll()
     * */
    @SuppressWarnings("unchecked")
    public List<StudentEntity> findAll(){
        TypedQuery<StudentEntity> query  =
                connection.executeQueryTransaction(entityManager -> entityManager
                .createQuery("SELECT student FROM StudentEntity student", StudentEntity.class), TypedQuery.class);
        return query.getResultList();
    }

    public StudentEntity findById(Long id){
       return connection.executeQueryTransaction(entityManager -> entityManager.find(StudentEntity.class, id), StudentEntity.class);
    }

    /* Example with createNativeQuery */
    @SuppressWarnings("unchecked")
    public StudentEntity findFirstByForename(String forename) {
        /* createQuery does not have LIMIT */
        return connection.executeQueryTransaction(entityManager -> entityManager
                    .createNativeQuery("SELECT * FROM student WHERE forename=?1 LIMIT 1", StudentEntity.class)
                    .setParameter(1, forename).getSingleResult(), StudentEntity.class
                );
    }

    @SuppressWarnings("unchecked")
    public List<StudentEntity> findAllByForename(String forename){
        return connection.executeQueryTransaction(entityManager -> entityManager
                .createQuery("SELECT s FROM StudentEntity s WHERE s.forename=:forename")
                /* named parameter */
                .setParameter("forename", forename).getResultList(), List.class);
    }

    /* UPDATE */
    /***
     * @param student to be updated. The student provided must have an id in order to find the wanted student.
     */
    public StudentEntity update(StudentEntity student){
        return connection.executeQueryTransaction(entityManager -> entityManager.merge(student), StudentEntity.class);
    }


    /* DELETE */
    public void remove(StudentEntity student){
        connection.executeTransaction(entityManager -> entityManager.remove(student));
    }

    public void removeById(Long id) {
        connection.executeQueryTransaction(entityManager -> entityManager
                .createQuery("DELETE FROM StudentEntity s WHERE s.id = :id")
                .setParameter("id", id)
                /* Return nr. of rows deleted by default, but in this instance I choose to not */
                .executeUpdate(), /* Integer.class */ Void.class);
    }
}
