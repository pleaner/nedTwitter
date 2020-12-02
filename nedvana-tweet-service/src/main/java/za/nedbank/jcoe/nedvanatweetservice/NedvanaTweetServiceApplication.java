package za.nedbank.jcoe.nedvanatweetservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class NedvanaTweetServiceApplication implements CommandLineRunner {

	private final TweetRepository repository;

	public NedvanaTweetServiceApplication(TweetRepository repository) {
		this.repository = repository;
	}

	public static void main(String[] args) {

		SpringApplication.run(NedvanaTweetServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		repository.deleteAll();
		repository.save(new Tweet(null,"Sean Pleaner", "Is this thing on? Guys?", randomTimeToday(), new ArrayList<>()));
		repository.save(new Tweet(null,"Adriano Iorio", "To the Galaxy and Beyond!", randomTimeToday(), new ArrayList<>()));
		repository.save(new Tweet(null,"Pierre Viljoen", "To the Galaxy and Beyond!", randomTimeToday(), new ArrayList<>()));
		repository.save(new Tweet(null,"Bianca Swartz", "I know the sky is not the limit because there are footprints on the Moon — and I made some of them!", randomTimeToday(), new ArrayList<>()));
		repository.save(new Tweet(null,"Neil Ross", "To some this may look like a sunset. But it’s a new dawn.", randomTimeToday(), new ArrayList<>()));
		repository.save(new Tweet(null,"Brenda May", "Earth is a small town with many neighborhoods in a very big universe.", randomTimeToday(), new ArrayList<>()));
		repository.save(new Tweet(null,"Nardus Voster", "I thought the attractions of being an astronaut were actually, not so much the moon, but flying in a completely new medium.", randomTimeToday(), new ArrayList<>()));
		repository.save(new Tweet(null,"Steve Armstrong", "The scenery was very beautiful. But I did not see The Great Wall.", randomTimeToday(), new ArrayList<>()));
		repository.save(new Tweet(null,"Thapelo Chabangu", "“It’s easy to sleep floating around — it’s very comfortable. But you have to be careful that you don’t float into somebody or something!", randomTimeToday(), new ArrayList<>()));
		repository.save(new Tweet(null,"Sylvia Earle", "Any astronaut can tell you you’ve got to do everything you can to learn about your life support system and then do everything you can to take care of it.", randomTimeToday(), new ArrayList<>()));
		repository.save(new Tweet(null,"Ezra Malebati", "Astronauts are inherently insane. And really noble.", randomTimeToday(), new ArrayList<>()));
		repository.save(new Tweet(null,"John Glenn", "I don’t know what you could say about a day in which you have seen four beautiful sunsets.", randomTimeToday(), new ArrayList<>()));
		repository.save(new Tweet(null,"Lisa Nowak", "Of course risk is part of spaceflight. We accept some of that to achieve greater goals in exploration and find out more about ourselves and the universe.", randomTimeToday(), new ArrayList<>()));
		repository.save(new Tweet(null,"Stephen Hawking", "One of the basic rules of the universe is that nothing is perfect. Perfection simply doesn’t exist…..Without imperfection, neither you nor I would exist.", randomTimeToday(), new ArrayList<>()));
		repository.save(new Tweet(null,"Albert Einstein", "When forced to summarize the general theory of relativity in one sentence: Time and space and gravitation have no separate existence from matter.", randomTimeToday(), new ArrayList<>()));
		repository.save(new Tweet(null,"Carl Sagan", "“For me, it is far better to grasp the Universe as it really is than to persist in delusion, however satisfying and reassuring.", randomTimeToday(), new ArrayList<>()));
		repository.save(new Tweet(null,"Stephen Hawking", "To confine our attention to terrestrial matters would be to limit the human spirit.", randomTimeToday(), new ArrayList<>()));
		repository.save(new Tweet(null,"Konstantin Tsiolkovsky", "The Earth is the cradle of humanity, but mankind cannot stay in the cradle forever.", randomTimeToday(), new ArrayList<>()));
		repository.save(new Tweet(null,"Nadine Venter", "One Small Step for I&O one Griant Leap for GT!", randomTimeToday(), new ArrayList<>()));

		System.out.println("MONGODB: " + repository.count());

	}

	public Date randomTimeToday(){
		return new Date();
	}
}
