package com.example.pidev.Dto;

import com.example.pidev.Dto.DAO.Entities.User;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SlaDto {
        private String providerMail;
}

