package sopt.org.FourthSeminar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@SpringBootApplication
//@SpringBootApplication(
//		exclude = {
//				org.springframework.cloud.aws.autoconfigure.context.ContextInstanceDataAutoConfiguration.class,
//				org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration.class,
//				org.springframework.cloud.aws.autoconfigure.context.ContextRegionProviderAutoConfiguration.class
//		}
//)
public class FourthSeminarApplication {
//	static {
//		System.setProperty("com.amazonaws.sdk.disableEc2Metadata", "true");
//	}
	public static void main(String[] args) {
		SpringApplication.run(FourthSeminarApplication.class, args);
	}

}


