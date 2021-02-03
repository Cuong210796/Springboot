package car.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

public class Car {
    @JsonIgnore
    private int id;
    private String name;
    private int year;
    private int price;
//    private String photo;

    public Car(int id, String name, int year, int price) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    // gõ lệnh equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id &&
                year == car.year &&
                price == car.price &&
                Objects.equals(name, car.name);
//                Objects.equals(photo, car.photo);
    }

    public boolean matchWithKeyword(String keyword) {
        String keywordLowerCase = keyword.toLowerCase();
        return (name.toLowerCase().contains(keywordLowerCase));
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, name, year, price);
    }

    // gõ toString
    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", price=" + price +
                '\'' +
                '}';
    }
}
