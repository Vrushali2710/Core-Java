package org.example;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

    public class UserService {

        // ─── CREATE ───────────────────────────────────────────
        public User createUser(String name, String email) {
            EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
            EntityTransaction tx = null;
            try {
                tx = em.getTransaction();
                tx.begin();

                User user = new User(name, email);
                em.persist(user);

                tx.commit();
                System.out.println("Created: " + user);
                return user;

            } catch (Exception e) {
                if (tx != null && tx.isActive()) tx.rollback();
                e.printStackTrace();
                return null;
            } finally {
                em.close(); // always close EntityManager
            }
        }

        // ─── READ BY ID ───────────────────────────────────────
        public User getUserById(Long id) {
            EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
            try {
                User user = em.find(User.class, id);
                if (user == null) {
                    System.out.println("User not found with id: " + id);
                }
                return user;
            } finally {
                em.close();
            }
        }

        // ─── READ ALL ─────────────────────────────────────────
        public List<User> getAllUsers() {
            EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
            try {
                TypedQuery<User> query = em.createQuery(
                        "SELECT u FROM User u", User.class
                );
                return query.getResultList();
            } finally {
                em.close();
            }
        }

        // ─── READ BY EMAIL ────────────────────────────────────
        public User getUserByEmail(String email) {
            EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
            try {
                TypedQuery<User> query = em.createQuery(
                        "SELECT u FROM User u WHERE u.email = :email", User.class
                );
                query.setParameter("email", email);
                return query.getSingleResult();
            } catch (Exception e) {
                System.out.println("User not found with email: " + email);
                return null;
            } finally {
                em.close();
            }
        }

        // ─── UPDATE ───────────────────────────────────────────
        public User updateUser(Long id, String name, String email) {
            EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
            EntityTransaction tx = null;
            try {
                tx = em.getTransaction();
                tx.begin();

                User user = em.find(User.class, id);
                if (user != null) {
                    user.setName(name);
                    user.setEmail(email);
                    em.merge(user);
                    tx.commit();
                    System.out.println("Updated: " + user);
                    return user;
                } else {
                    System.out.println("User not found with id: " + id);
                    tx.rollback();
                    return null;
                }

            } catch (Exception e) {
                if (tx != null && tx.isActive()) tx.rollback();
                e.printStackTrace();
                return null;
            } finally {
                em.close();
            }
        }

        // ─── DELETE ───────────────────────────────────────────
        public void deleteUser(Long id) {
            EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
            EntityTransaction tx = null;
            try {
                tx = em.getTransaction();
                tx.begin();

                User user = em.find(User.class, id);
                if (user != null) {
                    em.remove(user);
                    tx.commit();
                    System.out.println("Deleted user with id: " + id);
                } else {
                    System.out.println("User not found with id: " + id);
                    tx.rollback();
                }

            } catch (Exception e) {
                if (tx != null && tx.isActive()) tx.rollback();
                e.printStackTrace();
            } finally {
                em.close();
            }
        }
    }

