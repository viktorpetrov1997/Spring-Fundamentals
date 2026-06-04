package bg.softuni.spring_core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringCoreApplication
{
	public static void main(String[] args)
	{
		ApplicationContext applicationContext = SpringApplication.run(SpringCoreApplication.class, args);

		GreetingService greetingService = applicationContext.getBean(GreetingService.class);

		System.out.println(greetingService.getGreeting());

		MagicBean bean1 = applicationContext.getBean(MagicBean.class);
		MagicBean bean2 = applicationContext.getBean(MagicBean.class);

		System.out.println(bean1);
		System.out.println(bean2);
	}
}
