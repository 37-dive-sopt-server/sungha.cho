package org.sopt.article.domain;

import jakarta.persistence.*;
import lombok.*;
import org.sopt.member.domain.Member;
import java.time.LocalDateTime;

@Entity
@Table(name = "article")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA 기본 생성자
@AllArgsConstructor                                 // 모든 필드 포함 생성자
@Builder                                            // 빌더 패턴 적용
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 한 명의 Member가 여러 Article 작성 (N:1 관계)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    // 태그(분야)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private Tag tag;

    // 제목
    @Column(nullable = false, length = 200)
    private String title;

    // 내용
    @Lob
    @Column(nullable = false)
    private String content;

    // 작성일
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;


    @Builder
    public Article(Member member, Tag tag, String title, String content, LocalDateTime createdAt) {
        this.member = member;
        this.tag = tag;
        this.title = title;
        this.content = content;
        this.createdAt = (createdAt != null) ? createdAt : LocalDateTime.now();
    }
}
