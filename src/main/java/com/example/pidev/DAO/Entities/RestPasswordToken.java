package com.example.pidev.DAO.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class RestPasswordToken {
    @SequenceGenerator(
            name = "password_token_sequence",
            sequenceName = "password_token_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "password_token_sequence"
    )
    private Long id;
    @Column(nullable=false)
    private String token;
    @Column(nullable=false)
    private LocalDateTime createdAt;
    @Column(nullable=false)
    private LocalDateTime expiresAt;
    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(nullable=false,name="user_id")
    private User users;
    public RestPasswordToken(String token, LocalDateTime createdAt, LocalDateTime expiresAt, User user) {
        super();
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.users= user;
    }


}