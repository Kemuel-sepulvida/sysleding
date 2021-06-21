package br.com.fichamento.dao;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.fichamento.modelo.Ficha;
import br.com.fichamento.modelo.Usuario;

public class UsuarioDao implements Serializable {
	
	@Inject
	EntityManager em;
	

	public boolean existe(Usuario usuario) {
		
		TypedQuery<Usuario> query = em.createQuery(
				  " select u from Usuario u "
				+ " where u.email = :pEmail and u.senha = :pSenha", Usuario.class);
		
		query.setParameter("pEmail", usuario.getEmail());
		query.setParameter("pSenha", usuario.getSenha());
		try {
			Usuario resultado =  query.getSingleResult();
			
		} catch (NoResultException ex) {
			return false;
		}
		
		return true;
	}

	public Integer idUsuarioLogado() {	
		FacesContext context = FacesContext.getCurrentInstance() ;
		Usuario usuarioLogado = (Usuario) context.getExternalContext() .getSessionMap().get("usuarioLogado");
		Integer idUsuarioLogado = usuarioLogado.getId();
		
		return idUsuarioLogado;
	}

}
