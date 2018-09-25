package br.senac.rn.agenda.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public abstract class GenericDAO <T extends PersistDB> {
	
    protected static EntityManager manager;
    
    public abstract Class<T> getClassType();

    protected static EntityManager getEm() {
        if (manager == null || !manager.isOpen()) {
            manager = DataBase.getInstance().getEntityManager();
        }
        return manager;
    }
    
    private boolean change(T c, OperacaoDatabase op) {
        EntityManager em = getEm();
        em.getTransaction().begin();
        try {
        	switch(op) {
        	case INSERIR:
        		em.persist(c);
        		break;
        	case ALTERAR:
        		em.merge(c);
        		break;
        	case REMOVER:
        		em.remove(c);
        		break;
        	}
        	em.getTransaction().commit();
        	return true;
        } catch (Exception e) {
        	em.getTransaction().rollback();
        }
        return false;
    }

    public boolean insert(T c) {
        return change(c, OperacaoDatabase.INSERIR);
    }
    
    public boolean update(T c) {
        return change(c, OperacaoDatabase.ALTERAR);
    }
    
    public boolean delete(T c) {
        return change(c, OperacaoDatabase.REMOVER);
    }

    public T select(Integer id) {
        EntityManager em = getEm();
        T c = em.find(getClassType(), id);
        return c;
    }

    public List<T> selectAll() {
        EntityManager em = getEm();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(getClassType());
        TypedQuery<T> typedQuery = em.createQuery(query.select(query.from(getClassType())));
        List<T> c = typedQuery.getResultList();
        return c;
    }

    @SuppressWarnings("unchecked")
    public List<T> findAllLike(String coluna, String valor) {
        String tabela = getClassType().getSimpleName();
        String jpql = "from "+tabela+ " where "+coluna+" like :valor";
        EntityManager em = getEm();
        Query q = em.createQuery(jpql);
        q.setParameter("valor", "%"+valor+"%");
        List<T> retorno = q.getResultList();
        return retorno;
    }
    
}
