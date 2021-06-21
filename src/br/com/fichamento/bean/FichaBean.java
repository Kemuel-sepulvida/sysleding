package br.com.fichamento.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.com.fichamento.dao.UsuarioDao;
import br.com.fichamento.dao.FichaDao;
import br.com.fichamento.modelo.Livro;
import br.com.fichamento.modelo.Usuario;
import br.com.fichamento.modelo.Ficha;
import br.com.fichamento.tx.Transacional;

@Named
@ViewScoped
public class FichaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private	Integer idInstanceUser;
	
	@Inject
	private Ficha ficha = new Ficha();
	
	private Integer usuarioId;

	private List<Ficha> fichas;

	@Inject
	private FichaDao fichaDao;

	@Inject
	private UsuarioDao usuarioDao;

	@Inject
	private FacesContext context;

	
	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	
	public Integer getUsuarioId() {
		return usuarioId;
	}
	
	public Integer getCurrentUserId() {
		
		FacesContext context = FacesContext.getCurrentInstance() ;
		Usuario usuarioLogado = (Usuario) context.getExternalContext() .getSessionMap().get("usuarioLogado");
		Integer idInstanceUser = usuarioLogado.getId();	
		
		return idInstanceUser;
	}

/*	public List<Ficha> getFichasUsuario(){
		
		
		
	    
		if (this.fichas == null) {
			this.fichas = this.fichaDao.fichaPorUsuarioLogado(idInstanceUser);
		}
	        return fichas;
	  	} */

	public Ficha getFicha() {
		return ficha;
	}
	

	public List<Ficha> getFichas() {
		
	
		if (this.fichas == null) {
			this.fichas = this.fichaDao.listaTodos(idInstanceUser);
		}
		return fichas;
	} 

	/*	public Integer getUsuarioLogado() {
		return this.usuarioDao.usuarioLogado();
	}

	public void getFichaUsuarioLogadoId() {
		 this.ficha.getUsuarioLogado();
	} */

	public void carregarFichaPelaId() {
		this.ficha = this.fichaDao.buscaPorId(this.ficha.getId());
	}


	@Transacional
	public void gravar() {
		System.out.println("Gravando ficha " + this.ficha.getTitulo());

		/*
		 * if (ficha.getAutores().isEmpty()) { context.addMessage("autor", new
		 * FacesMessage("Ficha deve ter pelo menos um Livro.")); return; }
		 */

		if (this.ficha.getId() == null) {
			this.fichaDao.adiciona(this.ficha);
			this.fichas = this.fichaDao.listaTodos(idInstanceUser);
		} else {
			this.fichaDao.atualiza(this.ficha);
		}

		this.ficha = new Ficha();
	}

	public void limpaCampos() {
		System.out.println("Cancelando alteraÁ„o ou inclus„o da ficha");
		this.ficha = new Ficha();
	}

	@Transacional
	public void remover(Ficha ficha) {
		System.out.println("Removendo ficha");
		this.fichaDao.remove(ficha);
		this.fichas = this.fichaDao.listaTodos(idInstanceUser);
	}



	public void carregar(Ficha ficha) {
		System.out.println("Carregando ficha");
		this.ficha = this.fichaDao.buscaPorId(ficha.getId());
	}

	public String formAutor() {
		System.out.println("Chamanda do formul√°rio do Livro.");
		return "autor?faces-redirect=true";
	}

	public void comecaComDigitoUm(FacesContext fc, UIComponent component, Object value) throws ValidatorException {

		String valor = value.toString();
		if (!valor.startsWith("1")) {
			throw new ValidatorException(new FacesMessage("ISBN deveria come√ßar com 1"));
		}

	}
}
