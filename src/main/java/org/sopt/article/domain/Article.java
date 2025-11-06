package org.sopt.article.domain;

import jakarta.persistence.*;
import lombok.*;
import org.sopt.member.domain.Member;
import java.time.LocalDateTime;

@Entity
@Table(name = "article")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 한 명의 Member가 여러 Article 작성 (N:1 관계)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;


    // 제목
    @Column(nullable = false)
    private String title;

    // 내용
    @Lob
    @Column(nullable = false)
    private String content;

    // 태그(분야)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Tag tag;

    // 작성일
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Builder
    public Article(long id, Tag tag, String title, String content, LocalDateTime createdAt) {
        this.id = id;
        this.tag = tag;
        this.title = title;
        this.content = content;
        this.createdAt = (createdAt != null) ? createdAt : LocalDateTime.now();
    }
}
