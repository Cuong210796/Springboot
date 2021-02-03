package vn.techmaster.learncollection.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static java.time.Period.*;

public class Person {

  private int id;
  private String fullname;
  private String job;
  private String gender;
  private String city;
  private int salary;
  private Date birthday;
  private final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd");
  private int age;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFullname() {
    return fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

  public String getJob() {
    return job;
  }

  public void setJob(String job) {
    this.job = job;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public int getSalary() {
    return salary;
  }

  public void setSalary(int salary) {
    this.salary = salary;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(String birthday) {
    try {
      this.birthday = dateFormatter.parse(birthday);
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  public int getAge() {
    setAge();
    return age;
  }

  public void setAge() {
    LocalDate birthdayDate;
    LocalDate currentDate;
    birthdayDate = birthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    currentDate = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    age = between(birthdayDate, currentDate).getYears();
  }

  @Override
  public String toString(){
    return  "Tên: " + fullname
            + "\t" + "Nghề nghiệp: " + job
            + "\t" + "Giới tính: " + gender
            + "\t" + "Thành phố: " + city
            + "\t" + "Lương: " + salary + "$"
            + "\t" + "Ngày sinh: " + birthday;
  }
}
