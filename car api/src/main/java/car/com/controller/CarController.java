package car.com.controller;

import car.com.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import car.com.repository.CarDao;
import car.com.request.SearchRequest;

import java.util.Optional;

@Controller
@RequestMapping("/allcar")
public class CarController {
    @Autowired
    private CarDao carDao;

    @GetMapping
    public String listAll(Model model) {
        model.addAttribute("cars", carDao.getAll());
        model.addAttribute("searchrequest", new SearchRequest());
        return "allcar";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("car", new Car());
        return "add";
    }

    @GetMapping(value = "/{id}")
    public String getByID(@PathVariable("id") int id, Model model) {
        Optional<Car> car = carDao.get(id);
        car.ifPresent(value -> model.addAttribute("car", value));
        return "car";
    }

    @PostMapping("/save")
    public String save(Car car, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "add";
        }
        if (car.getId() > 0) { //Nếu có trường id có nghĩa là đây là edit form
            carDao.update(car);
        } else { //Nếu id ==0 có nghĩa book lần đầu được add
            carDao.add(car);
        }

        return "redirect:/allcar";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteByID(@PathVariable("id") int id) {
        carDao.deleteByID(id);
        return "redirect:/allcar";
    }

    @GetMapping(value = "/edit/{id}")
    public String editBookId(@PathVariable("id") int id, Model model) {
        Optional<Car> car = carDao.get(id);
        car.ifPresent(value -> model.addAttribute("car", value));
        return "add";
    }

    @GetMapping("/search")
    public String searchForm(Model model) {
        model.addAttribute("searchrequest", new SearchRequest());
        return "search";
    }


    @PostMapping("/search")
    public String searchByKeyword(@ModelAttribute SearchRequest request, BindingResult bindingResult, Model model) {
        System.out.println(request.getKeyword());
        model.addAttribute("cars", carDao.searchByKeyword(request.getKeyword()));
        model.addAttribute("searchrequest", new SearchRequest());
        return "allcar";
    }

}
