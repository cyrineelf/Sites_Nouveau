package com.asleimi.testapp.model;

import java.io.Serializable;

import android.graphics.Bitmap;

public class Plat implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idPlat;
	private String nomPlat;
	private String photoPlat;
	private String ingredientPlat;
	private String etapePlat;
	private Bitmap bitmap;
	public int getIdPlat() {
		return idPlat;
	}
	public void setIdPlat(int idPlat) {
		this.idPlat = idPlat;
	}
	public String getNomPlat() {
		return nomPlat;
	}
	public void setNomPlat(String nomPlat) {
		this.nomPlat = nomPlat;
	}
	public String getPhotoPlat() {
		return photoPlat;
	}
	public void setPhotoPlat(String photoPlat) {
		this.photoPlat = photoPlat;
	}
	public String getIngredientPlat() {
		return ingredientPlat;
	}
	public void setIngredientPlat(String ingredientPlat) {
		this.ingredientPlat = ingredientPlat;
	}
	public String getEtapePlat() {
		return etapePlat;
	}
	public void setEtapePlat(String etapePlat) {
		this.etapePlat = etapePlat;
	}
	public Bitmap getBitmap() {
		return bitmap;
	}
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	public Plat(int idPlat, String nomPlat, String photoPlat,
			String ingredientPlat, String etapePlat) {
		super();
		this.idPlat = idPlat;
		this.nomPlat = nomPlat;
		this.photoPlat = photoPlat;
		this.ingredientPlat = ingredientPlat;
		this.etapePlat = etapePlat;
	}
	
	
	
}
