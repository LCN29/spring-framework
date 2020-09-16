package com.can.spring.core;

import com.can.spring.core.bean.Person;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <pre>
 *
 * </pre>
 *
 * @author LCN
 * @date 2020-08-29 15:23
 */
public class ApplicationContext {

	public static void main(String[] args) {

		String xmlPath = "classpath:${user.name}/spring-bean.xml";

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(xmlPath);

		context.getEnvironment().setActiveProfiles("development");
		context.refresh();

		Person person = (Person)context.getBean("personBean");
		Person person1 = (Person) context.getBean("personBean2");

		person.selfIntroduction();
		person1.selfIntroduction();

	}
}
