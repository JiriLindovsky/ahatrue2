package com.AHAtrading.ahatrue.model;

import java.math.*;
import javax.persistence.*;
import org.openxava.annotations.*;
import com.AHAtrading.ahatrue.annotations.*;

/**
 * based on book
 */
@Entity

@View(name="Simple", members="number, description") 
public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	@ReadOnly
	@Hidden
	int number;
	
	@Column(length=50,name="POPIS")
	@Required
	private String description;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@DescriptionsList
	private Author author;

	@ManyToOne(fetch=FetchType.LAZY)
	@DescriptionsList
	private Supplier supplier;

	@ManyToOne( 
		fetch=FetchType.LAZY,  
		optional=true)
	@DescriptionsList
	private Category category;
	
	@Column(length=10) @ISBN
	@Hidden //JL:not used
	private String isbn;
			
	@Stereotype("MONEY")
	private BigDecimal price;
		
	/*@Stereotype("PHOTO")
	private byte [] photo;*/
	@Files
	@Column(length=32)
	private String photo;

	/*@Stereotype("IMAGES_GALLERY")
	@Column(length=32)
	private String morePhotos;
	*/ //JL

	@Files // A complete image gallery is available
	@Column(length=32) // The 32 length string is for storing the key of the gallery
	String morePhotos; //JL:added


	@Stereotype("MEMO")
	private String remarks;	
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getPhoto() {//JL:fix type
		return photo;
	}

	public void setPhoto(String photo) {//JL:fix type
		this.photo = photo;
	}

	public String getMorePhotos() {
		return morePhotos;
	}

	public void setMorePhotos(String morePhotos) {
		this.morePhotos = morePhotos;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public void setAuthor(Author author) {
		this.author = author;
	}

	public Author getAuthor() {
		return author;
	}
	
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}		
			
}
