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

		String xmlPath = "classpath*:${user.name}/spring-bean.xml";

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(xmlPath);
		Person person = (Person)context.getBean("personBean");
		person.selfIntroduction();

		// spring profile 的 作用
		//context.getEnvironment().setActiveProfiles("development");
		//context.refresh();
		//Person person1 = (Person) context.getBean("personBean2");
		//person1.selfIntroduction();

	}
}
