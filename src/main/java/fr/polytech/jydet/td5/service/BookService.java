package fr.polytech.jydet.td5.service;

import fr.polytech.jydet.td5.beans.Book;
import fr.polytech.jydet.td5.beans.BookGenre;
import fr.polytech.jydet.td5.dao.book.BookDao;
import fr.polytech.jydet.td5.exception.ServiceException;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
public class BookService {

    private BookDao dao;

    public Collection<Book> getPublicBooks(String authorQuery, String titleQuery, Set<BookGenre> selectedGenre, boolean onlyAvailable) throws ServiceException {
        Collection<Book> books = dao.getAll();
        Stream<Book> stream = books.stream();
        try {
            if (authorQuery != null && ! (authorQuery.isEmpty())) {
                Pattern authorPattern = Pattern.compile(authorQuery);
                stream = stream.filter(b -> authorPattern.matcher(b.getAuthor()).find());
            }
            if (titleQuery != null && ! (titleQuery.isEmpty())) {
                Pattern authorPattern = Pattern.compile(titleQuery);
                stream = stream.filter(b -> authorPattern.matcher(b.getTitle()).find());
            }
            if (selectedGenre != null) {
                stream = stream.filter(b -> selectedGenre.contains(b.getGenre()));
            }
            if (onlyAvailable) {
                stream = stream.filter(b -> b.getInventoryItemCount() > 0);
            }
        } catch (PatternSyntaxException e) {
            throw new ServiceException("Invalid pattern !");
        }
        return stream.collect(Collectors.toList());
    }
}
