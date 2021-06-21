package br.com.fichamento.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.fichamento.modelo.Ficha;
import br.com.fichamento.modelo.Usuario;

public class FichaDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager em;
	
	private DAO<Ficha> dao;
	
	private DAO<Ficha> fichaPorUsuarioLogado;
	
	@PostConstruct
	void init() {
		this.dao = new DAO<Ficha>(this.em, Ficha.class);
	}

	public Ficha buscaPorId(Integer livroId) {
		return this.dao.buscaPorId(livroId);
	}

	public void adiciona(Ficha ficha) {
		this.dao.adiciona(ficha);
	}

	public void atualiza(Ficha ficha) {
		this.dao.atualiza(ficha);
	}

	public void remove(Ficha ficha) {
		this.dao.remove(ficha);
	}

/*	public List<Ficha> listaTodos(Integer idInstanceUser) {
		return this.dao.listaTodos();
	} */
	
	public List<Ficha> listaTodos(Integer idInstanceUser) {
		
		 try {
				EntityManager em = new JPAUtil().getEntityManager();
				Ficha listaTodos =  (Ficha) em.createQuery("select f from ficha f where f.usuarioId = ?1", Ficha.class)
				.setParameter(1, idInstanceUser)
				.getResultList();
				        

				return this.dao.listaTodos();
		  } catch (NoResultException e) {
		        return null;
		  }
	} 


	/*public List<Ficha> fichaPorUsuarioLogado(Integer idInstanceUser) {
	    try {
			EntityManager em = new JPAUtil().getEntityManager();
			Ficha fichaPorUsuarioLogado =  (Ficha) em.createQuery("select u from ficha u where u.usuarioId = :pusuarioId").getSingleResult();
			em.close();         

	        return this.dao.fichaPorUsuarioLogado;
	  } catch (NoResultException e) {
	        return null;
	  }
	}*/
	

}