package org.sopt.member.domain;

import jakarta.persistence.*;
import lombok.*;
import org.sopt.article.domain.Article;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private LocalDate birth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "member")
    private List<Article> articles = new ArrayList<>();
}
