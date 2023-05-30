package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.entity.Category;
import com.example.demo.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public String showAllBooks(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categoriesTitle", "Danh sách danh mục");
        model.addAttribute( "categoryAdd", "+ Thêm danh mục");
        model.addAttribute("categories", categories);
        return "categories/list";
    }
    @GetMapping("/add")
    public String addCategoryForm (Model model) {
        model.addAttribute( "category", new Category());

        return "categories/add";
    }

    @PostMapping("/add")
    public String addCategory (@ModelAttribute("category") Category category) {
        categoryService.addCategory(category);
        return "redirect:/categories";
    }
    @GetMapping("/edit/{id}")
    public String editCategoryForm(@PathVariable("id") Long id, Model model) {
        Category category = categoryService.getCategoryById(id);

        if (category != null) {
            model.addAttribute("category", category);
            return "categories/edit";
        }else {
            return "not-found";
        }
    }

    @PostMapping("/edit")
    public String editCategory (@ModelAttribute("category") Category updatedCategory) {
        categoryService.updateCategory(updatedCategory);
        return "redirect:/categories";
    }
    @GetMapping("/delete/{id}")
    public String deleteCategory (@PathVariable("id") Long id){
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }
}
