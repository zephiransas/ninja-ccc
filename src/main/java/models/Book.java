package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long id;

	public String title;

	public String author;

	public Integer price;

}
