package br.com.fichamento.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SequenceGenerator(name="ficha_seq", sequenceName = "ficha_seq", allocationSize = 1)
@javax.persistence.Table(name = "FICHA")
public class Ficha implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ficha_seq")
	private Integer id;
	private String titulo;
	private String assunto;
	private String tipoDeFichamento;
	private String trecho;
	private String autor;
	private String link;
	private String comentario;
	private Integer usuarioId;
	@Temporal(TemporalType.DATE)
	private Calendar dataFichamento = Calendar.getInstance();
	@ManyToOne
	private Usuario usuario;
	
	@ManyToMany
	private List<Livro> autores = new ArrayList<Livro>();

	

	public Ficha() {
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Calendar getDataFichamento() {
		return dataFichamento;
	}

	public void setDataFichamento(Calendar dataFichamento) {
		this.dataFichamento = dataFichamento;
	}

	public List<Livro> getLivros() {
		return autores;
	}

	public void adicionaLivro(Livro livro) {
		this.autores.add(livro);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTipoDeFichamento() {
		return tipoDeFichamento;
	}

	public void setTipoDeFichamento(String isbn) {
		this.tipoDeFichamento = isbn;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getTrecho() {
		return trecho;
	}

	public void setTrecho(String trecho) {
		this.trecho = trecho;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

}