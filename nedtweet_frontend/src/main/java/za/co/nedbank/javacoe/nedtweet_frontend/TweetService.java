package za.co.nedbank.javacoe.nedtweet_frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class TweetService {

  @Value("${nedtweet.backend.url}")
  private String tweetServiceURL;

  @Autowired
  private RestTemplate restTemplate;

  public List<Tweet> get() {
    return restTemplate.getForObject(
        tweetServiceURL, TweetList.class)
        .getTweets();
  }

  public Tweet create(Tweet tweet) {
    HttpEntity<Tweet> request = new HttpEntity<>(tweet);
    return restTemplate.postForObject(
        tweetServiceURL, request, Tweet.class);
  }

  public Optional<Tweet> edit(Tweet tweet) {
    return Optional.empty();
  }

  public void delete(String id) {
    restTemplate.delete(tweetServiceURL +"/" + id);
  }
}
