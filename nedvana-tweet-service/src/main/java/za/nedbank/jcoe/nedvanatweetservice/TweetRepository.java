package za.nedbank.jcoe.nedvanatweetservice;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.math.BigInteger;

public interface TweetRepository extends MongoRepository<Tweet, String> {
}
