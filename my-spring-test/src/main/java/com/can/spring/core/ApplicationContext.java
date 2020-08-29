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

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-bean.xml");

		Person person = (Person)context.getBean("personBean");

		person.selfIntroduction();

	}
}
