package br.com.fichamento.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.fichamento.dao.LivroDao;
import br.com.fichamento.modelo.Livro;
import br.com.fichamento.tx.Transacional;

@Named
@ViewScoped
public class LivroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Livro livro = new Livro();

	private Integer autorId;

	@Inject
	private LivroDao dao;

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public void carregarAutorPelaId() {
		this.livro = this.dao.buscaPorId(autorId);
	}

	@Transacional
	public String gravar() {
		System.out.println("Gravando livro " + this.livro.getTituloLivro());

		if (this.livro.getId() == null) {
			this.dao.adiciona(this.livro);
		} else {
			this.dao.atualiza(this.livro);
		}

		this.livro = new Livro();

		return "livro?faces-redirect=true";
	}

	@Transacional
	public void remover(Livro livro) {
		System.out.println("Removendo livro " + livro.getTituloLivro());
		this.dao.remove(livro);
	}

	public List<Livro> getAutores() {
		return this.dao.listaTodos();
	}

	public Livro getAutor() {
		return livro;
	}

	public void setAutor(Livro livro) {
		this.livro = livro;
	}
}
