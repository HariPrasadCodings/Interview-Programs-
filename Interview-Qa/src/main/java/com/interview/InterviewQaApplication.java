package com.interview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.Scope;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import com.interview.config.DataSourceConfig;
import com.interview.scope.BeanScopeTestService;
import com.interview.scope.CustomThreadScope;
import com.interview.scope.SingleTonBean;

import jakarta.annotation.PostConstruct;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, AopAutoConfiguration.class })
public class InterviewQaApplication implements CommandLineRunner {

	// approach: 1 To dynamically load/read the application.properties or
	// application.yaml file properties
//	@Value("${discount.offer.price}")
//	private int discountPrice;

	// approach: 2
	@Autowired
	private Environment environment;

	@Autowired
	private DataSourceConfig config;

	@PostConstruct
	public void initLogic() {
		System.out.println("PostConstruct logic executed ...!");
		// connection pool logic
		// kafka producer/consumer instantiate
		// data shedding
		// external API call
	}

	public static void main(String[] args) {
		System.out.println("SpringBootApplication rum method intialized...");
		ConfigurableApplicationContext context = SpringApplication.run(InterviewQaApplication.class, args);

//		OrderService orderService = context.getBean("orderService", OrderService.class);
//		orderService.setOrder();

		BeanScopeTestService bean1 = context.getBean(BeanScopeTestService.class);
		BeanScopeTestService bean2 = context.getBean(BeanScopeTestService.class);
		BeanScopeTestService bean3 = context.getBean(BeanScopeTestService.class);

		Scope scope = new CustomThreadScope();
		context.getBeanFactory().registerScope("threadlocal", scope);

//		Runnable runnable = () -> {
//			Volunteer v1 = context.getBean(Volunteer.class);
//			Volunteer v2 = context.getBean(Volunteer.class);
//			System.out.println(
//					"The HashCode of two objects created by child thread " + v1.hashCode() + " & " + v2.hashCode());
//		};
//		new Thread(runnable).start();
//
//		Volunteer v1 = context.getBean(Volunteer.class);
//		Volunteer v2 = context.getBean(Volunteer.class);
//		System.out
//				.println("The HashCode of two objects created by main method " + v1.hashCode() + " & " + v2.hashCode());

		SingleTonBean s1 = context.getBean(SingleTonBean.class);
		SingleTonBean s2 = context.getBean(SingleTonBean.class);

		System.out.println(s1.getProtoTypeBean().hashCode() + " & " + s2.getProtoTypeBean().hashCode());
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("CommandLineRunner rum method intialized...");
		// discount.offer.price
		System.out.println("DISCOUNT-PRICE: " + environment.getProperty("discount.offer.price"));
		System.out.println("DataSource Config properties: " + config);

	}

}
