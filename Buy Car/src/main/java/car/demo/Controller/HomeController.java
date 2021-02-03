package car.demo.Controller;


import car.demo.Model.Car;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class HomeController {
    @Value("${app}")
    private String appName;
    static final String APP_NAME = "appName";
    static final String CARS = "cars";

    @GetMapping(value = "/")
    public String getHome(Model model) {
        model.addAttribute(APP_NAME, appName);
        return "home";
    }


    // Trả về books là mảng của String
    @GetMapping(value = "/menuCar")
    public String getCars(Model model) {
        String[] carCollection = {"Mercedes C", "Mercedes E", "Mercedes S", "Mercedes GLC"};
        model.addAttribute(CARS, carCollection);
        model.addAttribute(APP_NAME, appName);
        return "menuCar";
    }

    @GetMapping(value = "/mercedesC")
    public String getCars1(Model model) {
        Car[] carCollection = {
                new Car(1, "Mercedes C180", 2020, 51_000),
                new Car(2, "Mercedes C200", 2020, 96_000),
                new Car(3, "Mercedes C250", 2020, 108_000),
                new Car(4, "Mercedes C300", 2020, 165_000)
        };

        model.addAttribute(CARS, carCollection);
        model.addAttribute(APP_NAME, appName);
        return "mercedesC";
    }

    // Trả về books là mảng của đối tượng kiểu Book
    @GetMapping(value = "/mercedesS")
    public String getCars2(Model model) {
        Car[] carCollection = {
                new Car(5, "Mercedes S450", 2020, 300_000),
                new Car(6, "Mercedes S63 4Matic", 2020, 235_000),
                new Car(7, "Mercedes-Maybach S600", 2020, 625_600),
                new Car(8, "Mercedes-Maybach S560 4Matic", 2020, 510_000)
        };

        model.addAttribute(CARS, carCollection);
        model.addAttribute(APP_NAME, appName);
        return "mercedesS";
    }

    // Trả về books là mảng của đối tượng kiểu Book, thêm ảnh cho từng sách
    @GetMapping(value = "/mercedesGLC")
    public String getCars3(Model model) {
        Car[] carCollection = {
                new Car(9, "Mercedes GLC250", 2020, 135_000),
                new Car(10, "Mercedes GLC200", 2020, 110_000),
                new Car(11, "Mercedes GLC300", 2020, 180_000)
        };

        model.addAttribute(CARS, carCollection);
        model.addAttribute(APP_NAME, appName);
        return "mercedesGLC";
    }

    // Trả về books là mảng của đối tượng kiểu Book, thêm ảnh cho từng sách, CSS đẹp
    @GetMapping(value = "/mercedesE")
    public String getCars4(Model model) {
        Car[] carCollection = {
                new Car(12, "Mercedes E180", 2020, 98_000),
                new Car(13, "Mercedes E200", 2020, 123_000),
                new Car(14, "Mercedes E250", 2020, 155_000),
                new Car(15, "Mercedes E300", 2020, 173_000)
        };

        model.addAttribute(CARS, carCollection);
        model.addAttribute(APP_NAME, appName);
        return "mercedesE";
    }
}
