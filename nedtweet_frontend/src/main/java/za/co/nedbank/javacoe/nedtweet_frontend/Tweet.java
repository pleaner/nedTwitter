package za.co.nedbank.javacoe.nedtweet_frontend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tweet {

  private String id;
  private String author;
  private String tweetContent;
  private Date date;
  private List<String> likedBy;
}
