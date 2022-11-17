package database;

import java.lang.reflect.Type;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.persistence.*;

public class DatabaseConnection {
    private EntityManager entityManager;

    public DatabaseConnection() {
        this.initTransaction();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    /***
     * @param action that returns data. Compatible with createQuery and createNativeQuery
     * @return data resulted from interrogation
     */
    public <T,R> R executeQueryTransaction(Function<EntityManager, T> action, Class<R> resultClass) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        Object queryResult = null;

        try {
            entityTransaction.begin();
            queryResult = action.apply(entityManager);
            entityTransaction.commit();
        } catch (RuntimeException e) {
            System.err.println("Transaction error: " + e.getLocalizedMessage());
            entityTransaction.rollback();
        }

        return (R)queryResult;
    }

    /***
     * @param action that does not return data. Compatible with persist, remove
     */
    public void executeTransaction(Consumer<EntityManager> action) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            action.accept(entityManager);
            entityTransaction.commit();
        } catch (RuntimeException e) {
            System.err.println("Transaction error: " + e.getLocalizedMessage());
            entityTransaction.rollback();
        }
    }

    private void initTransaction() {
        try {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("collegePersistenceUnit");
            entityManager = entityManagerFactory.createEntityManager();
        } catch (Exception e) {
            System.err.println("Error at initialing DatabaseManager: " + e.getMessage());
        }
    }

}