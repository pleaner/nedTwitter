package za.co.nedbank.javacoe.nedtweet_frontend;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

  @RestController
  @RequestMapping("/api/tweets")
  public class TweetController {

    private final TweetService service;

    public TweetController(TweetService service) {
      this.service = service;
    }

    @GetMapping()
    public List<Tweet> getAll(){
      return service.get();
    }

    @PostMapping
    public Tweet create(@RequestBody Tweet Tweet){
      return service.create(Tweet);
    }

    @PutMapping
    public ResponseEntity<Tweet> update(@RequestBody Tweet Tweet){
      Optional<Tweet> tweet = service.edit(Tweet);
      if(tweet.isPresent())
        return ResponseEntity.ok(tweet.get());
      else
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
      service.delete(id);
    }

    @GetMapping("/whoami")
    public Principal whoami(Principal principal){
      return principal;
    }
  }
