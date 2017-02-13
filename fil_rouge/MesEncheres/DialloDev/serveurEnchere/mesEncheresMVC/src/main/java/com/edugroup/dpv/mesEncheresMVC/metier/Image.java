package com.edugroup.dpv.mesEncheresMVC.metier;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.edugroup.dpv.mesEncheresMVC.utils.JsonPageable;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Image {
	public static class ImageOnly extends JsonPageable.PaginatedResult {}

	@JsonView( { ImageOnly.class } )
	private int id;
	@JsonView( { ImageOnly.class } )
	private String nom;
	@JsonView( { ImageOnly.class } )
	private String filename;
	@JsonView( { ImageOnly.class } )
	private String contentType;
	@JsonView( { ImageOnly.class } )
	private long size;
	private Article article;
	
	@Id @GeneratedValue
	public int getId() {
		return id;
	}
	@ManyToOne
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	
	
	
}
