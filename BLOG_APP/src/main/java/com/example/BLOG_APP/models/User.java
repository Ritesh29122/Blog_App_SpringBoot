//package com.example.BLOG_APP.models;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//
//@NoArgsConstructor
//@Entity
//@Table(name = "users")
//public class User {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    @Column(name="user_name", nullable = false, length=100)
//    private String name;
//
//    private String email;
//
//    private String password;
//
//    private String about;
//
//    @OneToMany(mappedBy = "user")
//    private List<Post> posts=new ArrayList<>();
//
//    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
//    private Set<Comment> comments=new HashSet<>();
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getAbout() {
//        return about;
//    }
//
//    public void setAbout(String about) {
//        this.about = about;
//    }
//
//    public List<Post> getPosts() {
//        return posts;
//    }
//
//    public void setPosts(List<Post> posts) {
//        this.posts = posts;
//    }
//
//    public Set<Comment> getComments() {
//        return comments;
//    }
//
//    public void setComments(HashSet<Comment> comments) {
//        this.comments = comments;
//    }
//}
package com.example.BLOG_APP.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="user_name", nullable = false, length=100)
    private String name;

    private String email;

    private String password;

    private String about;

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();

    @Override
    public String getUsername() {
        return this.email;  // Assuming email is the username
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public Set<GrantedAuthority> getAuthorities() {
        // Assuming a default role for all users
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(() -> "ROLE_USER");  // Replace with actual roles if needed
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  // Modify as needed
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // Modify as needed
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // Modify as needed
    }

    @Override
    public boolean isEnabled() {
        return true;  // Modify as needed
    }

    // Getters and setters for other fields
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(HashSet<Comment> comments) {
        this.comments = comments;
    }

    public void setPassword(String password) {
        this.password=password;
    }
}
