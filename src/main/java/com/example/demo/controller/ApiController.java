package com.example.demo.controller;

import com.example.demo.dto.BookDTO;
import com.example.demo.entity.Book;
import com.example.demo.services.BookService;
import com.example.demo.services.CategoryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1/books")
public class ApiController {
    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;
    private BookDTO convertToBooksDTO(Book book){
        BookDTO bookDTO=new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setPrice(book.getPrice());
        bookDTO.setCategoryId(categoryService.getCategoryById(book.getCategory().getId()).getId());
        return bookDTO;
    }
    @GetMapping
    @ResponseBody
    public List<BookDTO> getAllBooks() {
        List<Book> books=bookService.getAllBooks();
        List<BookDTO> bookDTOs = new ArrayList<>();
        for (Book book : books) {
            bookDTOs.add(convertToBooksDTO (book));
        }
        return bookDTOs;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public BookDTO getBookById(@PathVariable Long id) {
        Book book= bookService.getBookById(id);
        return convertToBooksDTO(book);
    }
    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable("id") Long id, Model model) {
        BookDTO book = convertToBooksDTO(bookService.getBookById(id));

        if (book != null) {
            model.addAttribute("book", book);
            model.addAttribute("currentCategory", categoryService.getCategoryById(book.getCategoryId()));
            model.addAttribute("categories", categoryService.getAllCategories());
            return "book/edit";
        }else {
            return "not-found";
        }
    }

    @PostMapping("/edit")
    public String editBook (@ModelAttribute("book") Book updatedBook) {
        bookService.updateBook(updatedBook);
        return "redirect:/api/v1/books";
    }
    @DeleteMapping("/{id}")
    @Transactional
    public void deleteBook (@PathVariable Long id) {
        if (bookService.getBookById(id) != null)
            bookService.deleteBook(id);
    }
}
