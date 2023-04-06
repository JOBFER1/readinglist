package readinglist;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReaderRepository extends JpaRepository<Reader, String> {

//	Reader findOne(String username);
	
	Reader findByUsername(String username);
	
}