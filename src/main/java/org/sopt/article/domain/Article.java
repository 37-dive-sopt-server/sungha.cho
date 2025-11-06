package org.sopt.article.domain;

import jakarta.persistence.*;
import org.sopt.member.domain.Member;

import java.time.LocalDateTime;

@Entity
@Table(name = "article")
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

    // 작성일 (자동 저장)
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // 기본 생성자 (JPA용)
    protected Article() {}

    // 생성자 (모든 필드 초기화)
    public Article(Member member, Tag tag, String title, String content) {
        this.member = member;
        this.tag = tag;
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now(); // 생성 시점 자동 저장
    }

    // getter 메서드들
    public Long getId() {
        return id;
    }

    public Member getMember() {
        return member;
    }

    public Tag getTag() {
        return tag;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // 작성자(Member) 지정용 편의 메서드
    public void assignMember(Member member) {
        this.member = member;
        if (member != null && !member.getArticles().contains(this)) {
            member.getArticles().add(this);
        }
    }
}
