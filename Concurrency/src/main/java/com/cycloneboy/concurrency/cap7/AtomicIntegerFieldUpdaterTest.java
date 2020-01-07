package com.cycloneboy.concurrency.cap7;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/** Create by sl on 2020-01-07 14:50 */
public class AtomicIntegerFieldUpdaterTest {

  public static void main(String[] args) {
    AtomicIntegerFieldUpdater<User> a = AtomicIntegerFieldUpdater.newUpdater(User.class, "age");

    User user = new User("Java", 22);
    System.out.println(a.getAndIncrement(user)); // 22
    System.out.println(a.get(user)); // 23
  }
}

class User {
  public volatile int age;
  private String name;

  public User(String name, int age) {
    super();
    this.name = name;
    this.age = age;
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
