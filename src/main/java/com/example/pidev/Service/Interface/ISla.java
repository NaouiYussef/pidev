package com.example.pidev.Service.Interface;

import com.example.pidev.DAO.Entities.Sla;
import com.example.pidev.DAO.Entities.User;
import com.example.pidev.Dto.SlaDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ISla {
    void save(SlaDto slaDto, MultipartFile file,Integer userId, String providerMail ) throws IOException;
//    List<User> searchProviders(String name);
    Sla edit(Sla sla);
    void deleteById(int id);
    List<Sla> selectAll();
}
