package org.sopt.member.domain;

import jakarta.persistence.*;
import org.sopt.article.domain.Article;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private LocalDate birth;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    protected Member() {}

    @OneToMany(mappedBy = "member")
    private List<Article> articles = new ArrayList<>();

    public Member(Long id, String name, String email, LocalDate birth, Gender gender) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birth = birth;
        this.gender = gender;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public LocalDate getBirth() { return birth; }
    public Gender getGender() { return gender; }
}
