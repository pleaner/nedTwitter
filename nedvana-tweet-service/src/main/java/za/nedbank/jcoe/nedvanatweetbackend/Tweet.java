package za.nedbank.jcoe.nedvanatweetbackend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tweet {
  @Id
  private String id;
  private String author;
  private String tweetContent;
  private Date date;
  private List<String> likedBy;

  public Tweet(String author, String tweetContent, Date date, List<String> likedBy) {
    this.author = author;
    this.tweetContent = tweetContent;
    this.date = date;
    this.likedBy = likedBy;
  }
}
