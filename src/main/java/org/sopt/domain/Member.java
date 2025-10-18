package org.sopt.domain;

import java.time.LocalDate;

public class Member {

    private Long id;
    private final String name;
    private final String email;
    private final LocalDate birth;
    private final Gender gender;

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
