package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Contato;

public class ContatoDAO extends GenericDAO<Contato> {

	@Override
	public Class<Contato> getClassType() {
		return Contato.class;
	}
	
	public List<Contato> selectFilter(String filter) {
		EntityManager em = getEm();
		TypedQuery<Contato> query = em.createQuery("SELECT c FROM Contato c WHERE c.nome LIKE :filter OR c.fone LIKE :filter", Contato.class);
		query.setParameter("filter", "%" + filter.toUpperCase() + "%");
		return query.getResultList();
	}
	
}
