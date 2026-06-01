package org.example;





import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

    private static final String PERSISTENCE_UNIT = "myPU";
    private static EntityManagerFactory emf;

    // Create factory once (expensive operation)
    static {
        try {
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create EntityManagerFactory");
        }
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }

    // Call this when application shuts down
    public static void shutdown() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
