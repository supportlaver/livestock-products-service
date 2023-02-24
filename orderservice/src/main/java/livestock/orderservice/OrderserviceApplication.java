package livestock.orderservice;

import livestock.orderservice.test.Test;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrderserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderserviceApplication.class, args);

		Test test = new Test();
		test.setId(0L);
		test.setName("test1");
		test.setNumber("123123");

		System.out.println(test);

	}

}
