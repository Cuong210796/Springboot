package repository;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class PersonRepositoryCSV implements PersonRepositoryInterface {

    private ArrayList<Person> people;

    @Autowired
    public PersonRepositoryCSV(@Value("${csvFile}") String csvFile) {
        people = new ArrayList<>();
        loadData(csvFile);
    }

    private void loadData(String csvFile) {
        try {
            File file = ResourceUtils.getFile("classpath:static/" + csvFile);
            CsvMapper mapper = new CsvMapper(); // Dùng để ánh xạ cột trong CSV với từng trường trong POJO
            CsvSchema schema = CsvSchema.emptySchema().withHeader(); // Dòng đầu tiên sử dụng làm Header
            ObjectReader oReader = mapper.readerFor(Person.class).with(schema); // Cấu hình bộ đọc CSV phù hợp với kiểu
            Reader reader = new FileReader(file);
            MappingIterator<Person> mi = oReader.readValues(reader); // Iterator đọc từng dòng trong file
            while (mi.hasNext()) {
                Person person = mi.next();
                people.add(person);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }



    @Override
    public List<Person> getAll() {
        return people;
    }

    @Override
    public List<Person> sortPeopleByFullNameReversed() {
        return people.stream().sorted(Comparator.comparing(Person::getFullname).reversed()).collect(Collectors.toList());
    }

    @Override
    public List<String> getSortedJobs() {
        return people.stream().map(Person::getJob).sorted().collect(Collectors.toList());
    }

    @Override
    public List<String> getSortedCities() {
        return people.stream().map(Person::getCity).sorted().collect(Collectors.toList());
    }

    @Override
    public HashMap<String, List<Person>> groupPeopleByCity() {
        return null;
    }

    @Override
    public HashMap<String, Integer> groupJobByCount() {
        return null;
    }

    @Override
    public HashMap<String, Integer> findTop5Jobs() {
        return null;
    }

    @Override
    public HashMap<String, Integer> findTop5Citis() {
        return null;
    }

    @Override
    public HashMap<String, String> findTopJobInCity() {
        return null;
    }

    @Override
    public HashMap<String, Float> averageJobSalary() {
        return null;
    }

    @Override
    public HashMap<String, Float> top5HighestSalaryCities() {
        return null;
    }

    @Override
    public HashMap<String, Float> averageJobAge() {
        return null;
    }

    @Override
    public HashMap<String, Float> averageCityAge() {
        return null;
    }

    @Override
    public List<String> find5CitiesHaveMostSpecificJob(String job) {
        return null;
    }
}
