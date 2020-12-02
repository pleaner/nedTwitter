package za.nedbank.jcoe.nedvanatweetbackend;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TweetController {

  private final TweetRepository repository;

  public TweetController(TweetRepository repository) {
    this.repository = repository;
}

  @GetMapping()
  public TweetList get(){
    return new TweetList(repository.findAll()
        .stream()
        .sorted(Comparator.comparing(Tweet::getDate).reversed())
        .collect(Collectors.toList()));
  }

  @GetMapping("/{id}")
  public Optional<Tweet> find(@PathVariable String id){
    return repository.findById(id);
  }

  @PostMapping()
  public Tweet create(@RequestBody Tweet tweet){
    return repository.save(tweet);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Tweet> edit(@PathVariable String id, @RequestBody Tweet tweet){
    Optional<Tweet> oldTweet = repository.findById(id);
    if(oldTweet.isEmpty())
      return ResponseEntity.notFound().build();

    tweet.setId(oldTweet.get().getId());
    return ResponseEntity.ok(repository.save(tweet));
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable String id){
    System.out.println("Delete: " + id);
    System.out.println("Before Delete: " + repository.findById(id));
    repository.deleteById(id);
    System.out.println("Delete: " + repository.findById(id));
  }

  @GetMapping("/like/{id}/{username}")
  public ResponseEntity<Tweet> like(@PathVariable String id, @PathVariable String username){
    Optional<Tweet> tweet = repository.findById(id);
    if(tweet.isEmpty())
      return ResponseEntity.notFound().build();
    Tweet tw = tweet.get();
    if(tw.getLikedBy().contains(username))
      tw.getLikedBy().remove(username);
    else
      tw.getLikedBy().add(username);
    repository.save(tw);
    return ResponseEntity.ok().body(tw);
  }
}
