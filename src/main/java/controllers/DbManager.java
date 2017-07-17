package controllers;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.lang.reflect.Field;
import java.util.List;

/*
* Se agrego el generico I para obligar
* el tipo en los IDs*/
public class DbManager<T,I> {

    private static EntityManagerFactory emf;
    private Class<T> entityClass;


    DbManager(Class<T> entityClass) {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("PersistentUnit");
        }
        this.entityClass = entityClass;

    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    private Object getValorCampo(T entidad) {
        if (entidad == null) {
            return null;
        }
        //aplicando la clase de reflexión.
        for (Field f : entidad.getClass().getDeclaredFields()) {  //tomando todos los campos privados.
            if (f.isAnnotationPresent(Id.class)) {
                try {
                    f.setAccessible(true);
                    Object valorCampo = f.get(entidad);

                    System.out.println("nombre del campo: " + f.getName());
                    System.out.println("Tipo del campo: " + f.getType().getName());
                    System.out.println("Valor del campo: " + valorCampo);

                    return valorCampo;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    public void create(T entidad) {
        EntityManager em = getEntityManager();

        try {
            if (em.find(entityClass, getValorCampo(entidad)) != null) {
                System.out.println("La entidad a guardar existe, no creada.");
                return;
            }
        } catch (IllegalArgumentException ie) {
            //
            System.out.println("Parametro ilegal.");
        }

        em.getTransaction().begin();
        try {
            em.persist(entidad);
            em.getTransaction().commit();

        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        } finally {
            em.close();
        }
    }

    public void edit(T entidad) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            em.merge(entidad);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        } finally {
            em.close();
        }
    }

    public void destroy(I entidadId) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            T entidad = em.find(entityClass, entidadId);
            em.remove(entidad);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        } finally {
            em.close();
        }
    }

    public T find(I id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(entityClass, id);
        } catch (Exception ex) {
            throw ex;
        } finally {
            em.close();
        }
    }

    public List<T> findAll() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<T> criteriaQuery = em.getCriteriaBuilder().createQuery(entityClass);
            criteriaQuery.select(criteriaQuery.from(entityClass));
            return em.createQuery(criteriaQuery).getResultList();
        } catch (Exception ex) {
            throw ex;
        } finally {
            em.close();
        }
    }


    // resultados desde init hasta end (init <= resultados < end)
    // se comienza desde 0
    public List<T> findAll(int init, int end){
        EntityManager em = getEntityManager();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

        CriteriaQuery<T> criteriaQuery = criteriaBuilder
                .createQuery(entityClass);

        CriteriaQuery<T> select = criteriaQuery.select(criteriaQuery.from(entityClass));
        TypedQuery<T> typedQuery = em.createQuery(select);
        typedQuery.setFirstResult(init);
        typedQuery.setMaxResults(end);

        return typedQuery.getResultList();
    }


    public long getCount(){
        EntityManager em = getEntityManager();
        return (long)em.createQuery("SELECT count (t) FROM "+entityClass.getName()+" t").getSingleResult();
    }


    public List<T> findWithPagination(Integer pageNumber, Integer pageSize) {

        EntityManager em = getEntityManager();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

        CriteriaQuery<T> criteriaQuery = criteriaBuilder
                .createQuery(entityClass);

        CriteriaQuery<T> select = criteriaQuery.select(criteriaQuery.from(entityClass));
        TypedQuery<T> typedQuery = em.createQuery(select);
        typedQuery.setFirstResult((pageNumber - 1) * pageSize);
        typedQuery.setMaxResults(pageSize);

        return typedQuery.getResultList();
    }
}
