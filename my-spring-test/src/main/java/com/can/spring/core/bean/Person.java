package com.can.spring.core.bean;

/**
 * <pre>
 *
 * </pre>
 *
 * @author LCN
 * @date 2020-08-29 15:23
 */
public class Person {

	private String name;

	private int age;

	public void selfIntroduction() {
		System.out.println("Hi, my name is " + name + " and age is " + age);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
