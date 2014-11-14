package controllers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dao.BookDao;
import models.Book;
import models.BookDto;
import ninja.Context;
import ninja.Result;
import ninja.Results;
import ninja.validation.FieldViolation;
import ninja.validation.JSR303Validation;
import ninja.validation.Validation;

import java.util.List;

@Singleton
public class BookController {

	@Inject
	BookDao bookDao;

	public Result index() {
		List<Book> books = bookDao.findAll();
		return Results.html().render("books", books);
	}

	public Result newBook() {
		BookDto dto = new BookDto();
		return Results.html()
				          .template("views/BookController/form.ftl.html")
				          .render("dto", dto);
	}

	public Result create(@JSR303Validation BookDto dto,
											 Validation validation) {

		if(validation.hasViolations()) {
			List<FieldViolation> errors = validation.getBeanViolations();
			return Results.html()
					          .template("views/BookController/form.ftl.html")
					          .render("dto", dto)
					          .render("errors", errors);
		}

		Book book = new Book();
		book.title = dto.title;
		book.author = dto.author;
		if(dto.price.equals("")) {
			book.price = null;
		} else {
			book.price = Integer.parseInt(dto.price);
		}

		bookDao.add(book);

		return Results.redirect("/book");
	}

}
