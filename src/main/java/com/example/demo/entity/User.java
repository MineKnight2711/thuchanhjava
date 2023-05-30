package com.example.demo.entity;
import com.example.demo.validator.annotation. ValidUsername;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Data
@Entity
@Table (name = "user")
public class User{


        @Id
        @GeneratedValue (strategy = GenerationType.IDENTITY)
        private Long id;
        @Column (name = "username", length = 50, nullable = false, unique = true)
        @NotBlank (message = "Username is required")
        @Size (max = 50, message = "Username must be less than 50 characters")
        @ValidUsername
        private String username;
        @Column(name = "password", length =250, nullable = false)
        @NotBlank (message = "Password is required")
        private String password;
        @Column(name = "email", length = 50)
        @Size (max=50, message = "Email must be less than 50 characters")
        private String email;
        @Column(name = "name", length = 50, nullable = false)
        @Size (max = 50, message = "Your name must be less than 50 characters")
        @NotBlank (message = "Your name is required")
        private String name;
        @OneToMany (mappedBy ="users", cascade=CascadeType.ALL)
        private List<Book> books = new ArrayList<>();
        public Long getId() {
                return id;
        }


        @ManyToMany
        @JoinTable (name = "user_role",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id"))
        private Set<Role> roles = new HashSet<>();

        public void setId(Long id) {
                this.id = id;
        }

        public String getUsername() {
                return username;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public List<Book> getBooks() {
                return books;
        }

        public void setBooks(List<Book> books) {
                this.books = books;
        }


}
