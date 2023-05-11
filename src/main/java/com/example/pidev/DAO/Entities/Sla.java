package com.example.pidev.DAO.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table( name = "SLA")
public class Sla implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idSla")
    private Integer id;
    @Temporal(TemporalType.DATE)

    private Date date;

    @OneToOne
    @JoinColumn(name = "provider_id")
    private User provider;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Lob
    private byte[] data;

//    @OneToMany(mappedBy = "SLA", cascade = CascadeType.PERSIST)
//    @JsonIgnore
//    private List<SLAContent> content;
}
