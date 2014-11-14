package models;


import org.hibernate.validator.constraints.NotBlank;

public class BookDto {

	@NotBlank
	public String title;

	@NotBlank
	public String author;

	public String price;

}
