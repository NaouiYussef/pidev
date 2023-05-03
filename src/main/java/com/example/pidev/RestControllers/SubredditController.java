package com.example.pidev.RestControllers;

;
import com.example.pidev.DAO.Entities.Post;
import com.example.pidev.DAO.Entities.Subreddit;
import com.example.pidev.DAO.Repositories.SubredditRepository;
import com.example.pidev.Dto.SubredditDto;
import com.example.pidev.Exceptions.PostNotFoundException;
import com.example.pidev.Exceptions.SubredditNotFoundException;
import com.example.pidev.Service.Classe.SubredditService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subreddit")
@AllArgsConstructor
@Slf4j
public class SubredditController {

    private final SubredditService subredditService;
    SubredditRepository subredditRepository;

    @PostMapping
    public ResponseEntity<SubredditDto> createSubreddit(@RequestBody SubredditDto subredditDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(subredditService.save(subredditDto));
    }

    @GetMapping
    public ResponseEntity<List<SubredditDto>> getAllSubreddits() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(subredditService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubredditDto> getSubreddit(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(subredditService.getSubreddit(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subreddit> updateSubreddit(@PathVariable(value = "id") Long  id,
                                               @RequestBody Subreddit subreddits) throws SubredditNotFoundException {
        Subreddit subreddit = subredditRepository.findById(id)
                .orElseThrow(() -> new SubredditNotFoundException("Subreddit not found for this id :: " + id));

        subreddit.setName(subreddits.getName());
        subreddit.setDescription(subreddits.getDescription());

        final Subreddit updatedSubreddits = subredditRepository.save(subreddit);
        return ResponseEntity.ok(updatedSubreddits);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        subredditService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}