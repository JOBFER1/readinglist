package readinglist;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Book { 
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	private String reader;
	private String isbn;
	private String title;
	private String author;
	private String description;

}