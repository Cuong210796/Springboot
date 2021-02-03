package car.com.repository;

import car.com.model.Car;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;


public class CarDao extends Dao<Car> {

    public CarDao(String csvFile) {
        this.readCSV(csvFile);
    }

    @Override
    public void readCSV(String csvFile) {
        try {
            File file = ResourceUtils.getFile("classpath:static/" + csvFile);
            CsvMapper mapper = new CsvMapper(); // Dùng để ánh xạ cột trong CSV với từng trường trong POJO
            CsvSchema schema = CsvSchema.emptySchema().withHeader().withColumnSeparator('|'); // Dòng đầu tiên sử dụng làm Header
            ObjectReader oReader = mapper.readerFor(Car.class).with(schema); // Cấu hình bộ đọc CSV phù hợp với kiểu
            Reader reader = new FileReader(file);
            MappingIterator<Car> mi = oReader.readValues(reader); // Iterator đọc từng dòng trong file
            while (mi.hasNext()) {
                Car car = mi.next();
                this.add(car);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public List<Car> getAll() {
        return collections;
    }

    @Override
    public Optional<Car> get(int id) {
        return collections.stream().filter(u -> u.getId() == id).findFirst();
    }

    @Override
    public void add(Car car) {
        int id;
        if (collections.isEmpty()) {
            id = 1;
        } else {
            Car lastCar = collections.get(collections.size() - 1);
            id = lastCar.getId() + 1;
        }
        car.setId(id);
        collections.add(car);
    }

    @Override
    public void update(Car car) {
        get(car.getId()).ifPresent(existcar -> {
            existcar.setName(car.getName());
            existcar.setYear(car.getYear());
            existcar.setPrice(car.getPrice());
            existcar.setPhoto(car.getPhoto());
        });
    }

    @Override
    public void delete(Car car) {
        deleteByID(car.getId());
    }

    @Override
    public void deleteByID(int id) {
        get(id).ifPresent(existcar -> collections.remove(existcar));
    }

    @Override
    public List<Car> searchByKeyword(String keyword) {
        return collections
                .stream()
                .filter(car -> car.matchWithKeyword(keyword))
                .collect(Collectors.toList());
    }
}
