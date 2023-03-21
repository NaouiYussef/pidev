package com.example.pidev.DAO.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idUser;

    String username;
    String mail;
    String password;
    @Transient
    String verifpassword;
    @Temporal(TemporalType.DATE)
    Date dateNaissance;
    @ManyToOne
    Role roles;

}
