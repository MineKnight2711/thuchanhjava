package com.example.demo.controller;
import com.example.demo.entity. Book;
import com.example.demo.entity.Category;
import com.example.demo.services. BookService;
import com.example.demo.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation. Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui. Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String showAllBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        model.addAttribute("booksTitle", "Danh sách các cuốn sách");
        return "book/list";
    }

    @GetMapping("/add")
    public String addBookForm (Model model) {
        model.addAttribute( "book", new Book());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "book/add";
    }

    @PostMapping("/add")
    public String addBook (@ModelAttribute("book") @Valid Book book, BindingResult result,Model model) {
        if(result.hasErrors()){
            model.addAttribute("categories", categoryService.getAllCategories());
            return "book/add";
        }
        else{
            bookService.addBook(book);
            return "redirect:/books";
        }
    }
    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable("id") Long id, Model model) {
        Book book = bookService.getBookById(id);

        if (book != null) {
            model.addAttribute("book", book);
            model.addAttribute("currentCategory", book.getCategory().getId());
            model.addAttribute("categories", categoryService.getAllCategories());
            return "book/edit";
        }else {
            return "not-found";
        }
    }

    @PostMapping("/edit")
    public String editBook (@ModelAttribute("book") Book updatedBook) {
        bookService.updateBook(updatedBook);
        return "redirect:/books";
    }
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id){
        bookService.deleteBook(id);
        return "redirect:/books";
    }
}