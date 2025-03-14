package study.data_jpa;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import study.data_jpa.entity.Member;
import study.data_jpa.repository.MemberRepository;

import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
@EnableJpaAuditing
public class DataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataJpaApplication.class, args);
	}

//	@Bean
//	public AuditorAware<String> auditorProvider() {
//		return () -> Optional.of(UUID.randomUUID().toString()); // 임의의 UUID를 생성하여 반환
//	}
}
