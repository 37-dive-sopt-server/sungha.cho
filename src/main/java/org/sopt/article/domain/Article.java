package org.sopt.article.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
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
    @Column(nullable = false, unique = true)
    private String title;

    // 내용
    @Lob
    @Column(nullable = false)
    private String content;

    // 태그(분야)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Tag tag;

    // 작성일 (엔티티 최초 생성 시 자동 저장)
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
