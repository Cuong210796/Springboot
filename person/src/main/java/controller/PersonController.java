package controller;

import model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import repository.PersonRepositoryInterface;

@Controller
@RequestMapping("/getAll")
public class PersonController {
    @Autowired
    PersonRepositoryInterface personRepo;

    @GetMapping
    public String listAll(Model model){
        model.addAttribute("gets",personRepo.getAll());
        return "getAll";
    }
}
