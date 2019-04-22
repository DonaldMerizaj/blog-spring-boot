package com.donald.blog.Models;

import com.donald.blog.util.Utils;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;

    @Type(type = "text")
    private String content;

    private String image;

    public String getCreatedAt() {
        return Utils.getDate(createdAt, "dd / M / YYYY");
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<PostCategory> getCategories() {
        return categories;
    }

    public boolean hasCategory(int id_category){
        for (PostCategory category : this.getCategories()){
            if (category.getId() == id_category){
                return true;
            }
        };

        return false;
    }

    public void setCategories(List<PostCategory> categories) {
        this.categories = categories;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Column(nullable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

    @ManyToOne
    private User user;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "posts_to_categories",
            joinColumns = {
                    @JoinColumn(name = "id_post", referencedColumnName = "id")
            },
            inverseJoinColumns = {
            @JoinColumn(name = "id_category", referencedColumnName = "id")
    })
    private List<PostCategory> categories = new ArrayList<>();


    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments;

    public User getId_user() {
        return user;
    }

    public void setId_user(User id_user) {
        this.user = id_user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShortDescription(){
        if (content.length() > 255){
            return content.substring(0, 255)+" ...";
        }
        return content;
    }
    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", image='" + image + '\'' +
                ", id_user='" + user + '\'' +
                ", created_at='" + createdAt + '\'' +
                '}';
    }
}
