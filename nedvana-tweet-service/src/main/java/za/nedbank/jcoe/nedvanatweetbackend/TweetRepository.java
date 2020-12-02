package za.nedbank.jcoe.nedvanatweetbackend;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TweetRepository extends MongoRepository<Tweet, String> {
}
