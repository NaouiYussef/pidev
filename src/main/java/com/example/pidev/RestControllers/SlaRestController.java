package com.example.pidev.RestControllers;

import com.example.pidev.Dto.DAO.Entities.Sla;
import com.example.pidev.Dto.DAO.Repositories.SlaRepository;
import com.example.pidev.Dto.SlaDto;
import com.example.pidev.Service.Interface.ISla;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@AllArgsConstructor

public class SlaRestController {

    private ISla isla;
    private SlaRepository slaRepository;

    @PostMapping("/addSla/{userId}")
    public ResponseEntity<Void> save(@ModelAttribute SlaDto slaDto,
                                              @RequestParam("file") MultipartFile file,
                                              @PathVariable Integer userId,
                                              @RequestParam String providerMail) {
        try {
            isla.save(slaDto, file, userId, providerMail);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

//    @GetMapping("/providers")
//    public ResponseEntity<List<User>> searchProviders(@RequestParam String name) {
//        List<User> providers = isla.searchProviders(name);
//        return ResponseEntity.ok(providers);
//    }


    @GetMapping("/downloadSla/{id}")
    public ResponseEntity<byte[]> download(@PathVariable Integer id) {
        Sla sla = slaRepository.getById(id);
        if (sla == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "sla-" + sla.getUser().getUsername()+ ".pdf" + "\"")
                .contentType(MediaType.APPLICATION_PDF)
                .body(sla.getData());
    }

    @DeleteMapping("/deleteSLA/{id}")
    public void delete(@PathVariable int id)
    {
        isla.deleteById(id);
    }

    @PutMapping("/sla/updateSLA")
    public Sla update(@RequestBody Sla sla)
    {
        return isla.edit(sla);
    }

}
