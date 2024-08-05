/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.Pasciente;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author gilbe
 */
public class PascienteJpaController implements Serializable {

    public PascienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public PascienteJpaController() {
        emf = Persistence.createEntityManagerFactory("consultorioOdontoPU");
    }

    public void create(Pasciente pasciente) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(pasciente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pasciente pasciente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            pasciente = em.merge(pasciente);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = pasciente.getId();
                if (findPasciente(id) == null) {
                    throw new NonexistentEntityException("The pasciente with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pasciente pasciente;
            try {
                pasciente = em.getReference(Pasciente.class, id);
                pasciente.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pasciente with id " + id + " no longer exists.", enfe);
            }
            em.remove(pasciente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pasciente> findPascienteEntities() {
        return findPascienteEntities(true, -1, -1);
    }

    public List<Pasciente> findPascienteEntities(int maxResults, int firstResult) {
        return findPascienteEntities(false, maxResults, firstResult);
    }

    private List<Pasciente> findPascienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pasciente.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Pasciente findPasciente(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pasciente.class, id);
        } finally {
            em.close();
        }
    }

    public int getPascienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pasciente> rt = cq.from(Pasciente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
