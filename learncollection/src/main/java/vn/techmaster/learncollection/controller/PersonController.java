package vn.techmaster.learncollection.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.techmaster.learncollection.model.Person;
import vn.techmaster.learncollection.repository.PersonRepositoryCSV;
import vn.techmaster.learncollection.repository.PersonRepositoryInterface;

@Controller
@RequestMapping("/getAll")
public class PersonController {
    @Autowired
    private PersonRepositoryCSV personRepo;

    @GetMapping
    public String listAll(Model model) {
      model.addAttribute("gets",personRepo.getAll());
        return "getAll";
    }

    @GetMapping("/sortPeopleByFullName")
    public String sortPeopleByFullName(Model model){
        model.addAttribute("gets",personRepo.sortPeopleByFullNameSorted());
        return "sortPeopleByFullName";
    }

    @GetMapping("/getSortedJobs")
    public String getSortedJobs(Model model){
        model.addAttribute("gets",personRepo.getSortedJobs());
        return "getSortedJobs";
    }

    @GetMapping("/getSortedCities")
    public String getSortedCities(Model model){
        model.addAttribute("gets",personRepo.getSortedCities());
        return "getSortedCities";
    }

    @GetMapping("/groupPeopleByCity")
    public String groupPeopleByCity(Model model){
        model.addAttribute("gets",personRepo.groupPeopleByCity());
        return "groupPeopleByCity";
    }

    @GetMapping("/groupJobByCount")
    public String groupJobByCount(Model model){
        model.addAttribute("gets",personRepo.groupJobByCount());
        return "groupJobByCount";
    }

    @GetMapping("/findTopJobInCity")
    public String findTopJobInCity(Model model){
        model.addAttribute("gets",personRepo.findTopJobInCity());
        return "findTopJobInCity";
    }

    @GetMapping("/findTop5Jobs")
    public String findTop5Jobs(Model model){
        model.addAttribute("gets",personRepo.findTop5Jobs());
        return "findTop5Jobs";
    }

    @GetMapping("/findTop5Citis")
    public String findTop5Citis(Model model){
        model.addAttribute("gets",personRepo.findTop5Citis());
        return "findTop5Citis";
    }

    @GetMapping("/averageJobSalary")
    public String averageJobSalary(Model model){
        model.addAttribute("gets",personRepo.averageJobSalary());
        return "averageJobSalary";
    }

    @GetMapping("/averageJobAge")
    public String averageJobAge(Model model){
        model.addAttribute("gets",personRepo.averageJobAge());
        return "averageJobAge";
    }

    @GetMapping("/averageCityAge")
    public String averageCityAge(Model model){
        model.addAttribute("gets",personRepo.averageCityAge());
        return "averageCityAge";
    }

    @GetMapping("/groupCityByCount")
    public String groupCityByCount(Model model){
        model.addAttribute("gets",personRepo.groupCityByCount());
        return "groupCityByCount";
    }
}
