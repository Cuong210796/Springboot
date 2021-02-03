package car.com.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Car {
    @JsonIgnore
    private int id;
    private String name;
    private String year;
    private String price;
    private String photo;

    public Car() {
    }

//    public Car(int id, String name, String year, String price, String photo) {
//        this.id = id;
//        this.name = name;
//        this.year = year;
//        this.price = price;
//        this.photo = photo;
//    }

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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public boolean matchWithKeyword(String keyword) {
        String keywordLowerCase = keyword.toLowerCase();
        return (name.toLowerCase().contains(keywordLowerCase) ||
                year.toLowerCase().contains(keywordLowerCase)) ||
                price.toLowerCase().contains(keywordLowerCase) ||
                photo.toLowerCase().contains(keywordLowerCase);
    }
}
