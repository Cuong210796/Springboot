package vn.techmaster.learncollection.repository;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import vn.techmaster.learncollection.model.Person;

@Repository
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
  public List<Person> sortPeopleByFullNameSorted() {
    return people.stream().sorted(Comparator.comparing(Person::getFullname)).collect(Collectors.toList());
  }

  @Override
  public List<String> getSortedCities() {
    /*
     * return people.stream(). sorted(Comparator.comparing(Person::getCity)).
     * map(Person::getCity).collect(Collectors.toList());
     */
    return people.stream().map(Person::getCity).sorted().collect(Collectors.toList());
  }

  @Override
  public List<String> getSortedJobs() {
    return people.stream().map(Person::getJob).sorted().collect(Collectors.toList());
  }

  @Override
  public List<Map.Entry<String, Integer>> findTop5Citis() {
    HashMap<String, Integer> map =groupCityByCount();
    List<Map.Entry<String, Integer>> newList = new ArrayList<>();
    List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
    Comparator<Map.Entry<String, Integer>> comparator = new Comparator<Map.Entry<String, Integer>>() {
      @Override
      public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
        return -o1.getValue().compareTo(o2.getValue());
      }
    };
    Collections.sort(list,comparator);
    int maxSize = 0;
    if (list.size() < 5){
      maxSize = list.size();
    }else {
      maxSize = 5;
    }
    for (int i = maxSize ; i > 0; i--) {
      newList.add(list.get(i));
      System.out.println(list);
    }

    return newList;
  }

  @Override
  public List<Map.Entry<String, Integer>> findTop5Jobs() {
    HashMap<String,Integer> map = groupJobByCount();
    List<Map.Entry<String, Integer>> newList = new ArrayList<>();
    List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
    Comparator<Map.Entry<String,Integer>> compare = new Comparator<Map.Entry<String, Integer>>() {
      @Override
      public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
        return  -o1.getValue().compareTo(o2.getValue());
      }
    };
    Collections.sort(list,compare);
    int maxSize = 0;
    if(list.size() < 5){
      maxSize = list.size();
    }else {
      maxSize = 5;
    }
      for (int i = 0; i < maxSize; i++) {
        newList.add(list.get(i));
      }
    return newList;
  }

  @Override
  public List<Map.Entry<String, HashMap<String, Integer>>> findTopJobInCity() {
    HashMap<String, List<Person>> mapJobCity = new HashMap<>();
    HashMap<String, Integer> map = new HashMap<>();
    List<Map.Entry<String, HashMap<String, Integer>>> list;

    for (Map.Entry<String, List<Person>> entry : mapJobCity.entrySet()){
      HashMap<String, Integer> mapBJIC = new HashMap<>();
      HashMap<String, Integer> mostBJIC = new HashMap<>();
      List<Person> listPeole = entry.getValue();

      for (int i = 0; i < listPeole.size(); i++){
        String job = listPeole.get(i).getJob();
        if (mapBJIC.containsKey(job)){
          mapBJIC.put(job,mapBJIC.get(job)+1);
        }else
          mapBJIC.put(job,1);
      }
      for (int i = 0; i < listPeole.size(); i++){
        String city = listPeole.get(i).getCity();
        if (mostBJIC.containsKey(city)){
          mostBJIC.put(city,mostBJIC.get(city)+1);
        }else
          mostBJIC.put(city,1);
      }
    }
    return (List<Map.Entry<String, HashMap<String, Integer>>>) map;
  }

  @Override
  public HashMap<String, Integer> groupJobByCount() {
    HashMap<String,Integer> map = new HashMap<>();
    for (Person p : people){
      if (map.containsKey(p.getJob())){
        map.put(p.getJob(),map.get(p.getJob()) + 1);
      }else
        map.put(p.getJob(),1);
    }
    return map;
  }

  @Override
  public HashMap<String, Integer> groupCityByCount() {
    HashMap<String,Integer> map = new HashMap<>();
    for (Person p : people){
      if (map.containsKey(p.getCity())){
        map.put(p.getCity(),map.get(p.getCity()) + 1);
      }else
        map.put(p.getCity(),1);
    }
    return map;
  }

  @Override
  public HashMap<String, List<Person>> groupPeopleByCity() {
    HashMap<String, List<Person>> map = new HashMap<>();
    ArrayList<String> listString = new ArrayList<>();
    for (Person p : people){
      if (map.containsKey(p.getCity())){
       map.get(p.getCity()).add(p);
      }else
        map.put(p.getCity(),new ArrayList<>());
    }
    return map;
  }


  @Override
  public HashMap<String, Float> averageCityAge() {
    HashMap<String, Float> mapC = new HashMap<>();
    HashMap<String, Integer> map = groupCityByCount();
    for (Person p : people){
      if (mapC.containsKey(p.getCity())){
        mapC.put(p.getCity(),mapC.get(p.getCity()) + p.getAge());
      }else {
        mapC.put(p.getCity(), (float) p.getAge());
      }
    }
    for (Map.Entry<String, Float> entry: mapC.entrySet()){
      String key = entry.getKey();
      mapC.put(key, entry.getValue()/map.get(key));
      System.out.println(key);
    }
    return mapC;

  }

  @Override
  public HashMap<String, Float> averageJobAge() {
    HashMap<String, Float> mapJ = new HashMap<>();
    HashMap<String, Integer> map = groupJobByCount();
    for (Person p : people){
      if (mapJ.containsKey(p.getJob())){
        mapJ.put(p.getJob(),mapJ.get(p.getJob()) + p.getAge());
      }else {
        mapJ.put(p.getJob(), (float) p.getAge());
      }
    }
    for (Map.Entry<String, Float> entry: mapJ.entrySet()){
      String key = entry.getKey();
      mapJ.put(key, entry.getValue()/map.get(key));
    }
    return mapJ;
  }

  @Override
  public HashMap<String, Float> averageJobSalary() {
    HashMap<String, Float> mapS = new HashMap<>();
    HashMap<String, Integer> map = groupJobByCount();
    for (Person p : people){
      if (mapS.containsKey(p.getJob())){
        mapS.put(p.getJob(),mapS.get(p.getJob()) + p.getSalary());
      }else {
        mapS.put(p.getJob(), (float) p.getSalary());
      }
    }
    for (Map.Entry<String, Float> entry: mapS.entrySet()){
      String key = entry.getKey();
      mapS.put(key, entry.getValue()/map.get(key));
    }
    return mapS;
  }

  @Override
  public List<String> find5CitiesHaveMostSpecificJob(String job) {

    return null;
  }

  @Override
  public HashMap<String, Float> top5HighestSalaryCities() {

    return null;
  }

 
  
}
