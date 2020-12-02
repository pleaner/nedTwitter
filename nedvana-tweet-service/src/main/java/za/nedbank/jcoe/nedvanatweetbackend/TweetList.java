package za.nedbank.jcoe.nedvanatweetbackend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TweetList {
  private List<Tweet> tweets;
}
