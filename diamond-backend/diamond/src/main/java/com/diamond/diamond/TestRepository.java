
// package com.diamond.repository;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;

// import java.time.LocalDate;
// import java.util.List;

// // Spring Data JPA creates CRUD implementation at runtime automatically.
// public interface BookRepository extends JpaRepository<String, Long> {
// 	List<String> findByTitle(String title);

// 	// Custom query
// 	@Query("SELECT b FROM Book b WHERE b.publishDate > :date")
// 	List<Stringk> findByPublishedDateAfter(@Param("date") LocalDate date);

// }
