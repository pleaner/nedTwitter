package za.nedbank.jcoe.nedvanatweetservice;

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
