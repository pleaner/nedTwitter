package za.co.nedbank.javacoe.nedtweet_frontend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import za.co.nedbank.javacoe.nedtweet_frontend.Tweet;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TweetList {
  private List<Tweet> tweets;
}
