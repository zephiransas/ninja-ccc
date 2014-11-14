package dao;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;
import models.Book;

import javax.persistence.EntityManager;
import java.util.List;

public class BookDao {

	@Inject
	Provider<EntityManager> entitiyManagerProvider;

	public List<Book> findAll() {
		EntityManager manager = entitiyManagerProvider.get();
		String sql = "select x from Book x";
		return manager.createQuery(sql, Book.class).getResultList();
	}

	@Transactional
	public void add(Book book) {
		EntityManager manager = entitiyManagerProvider.get();
		manager.persist(book);
	}

}
