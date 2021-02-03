package vn.techmaster.bmi.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import vn.techmaster.bmi.model.CurrencyRate;

@Service
public class MoneyConverter<CurrencyCode> {

    /**
     * Đọc dữ liệu từ file JSON vào JsonNode masterNode
     */
  /*private void loadExchangeRateFromJSON() {

  }*/

    /**
     * Lấy tỷ giá chuyển đổi 1 USD sang currencyCode
     */
  /*public float getExchangeRate(String currencyCode) {

  }*/
    public Map<String, Double> parseExchangeRate() {
        Map<String, Double> map = new HashMap<>();
        try {
            File file = ResourceUtils.getFile("classpath:static/exchange_rate.json");
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode masterNode = objectMapper.readTree(bufferedReader);
            Iterator<Map.Entry<String, JsonNode>> iter = masterNode.fields();
            while (iter.hasNext()) {
                var node = iter.next();
                map.put(node.getKey(), node.getValue().doubleValue());
            }

        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
        return map;
    }

    public List<CurrencyRate> parseCurrencyCode() throws IOException {
        Map<String, String> mapping = new
                HashMap<String, String>();
        mapping.put("Country", "Country");
        mapping.put("CountryCode", "CountryCode");
        mapping.put("Currency", "Currency");
        mapping.put("Code", "Code");
        HeaderColumnNameTranslateMappingStrategy<CurrencyRate> strategy =
                new HeaderColumnNameTranslateMappingStrategy<>();
        strategy.setType(CurrencyRate.class);
        strategy.setColumnMapping(mapping);

        File file = ResourceUtils.getFile("classpath:static/currency.csv");
        FileReader reader = new FileReader(file);
        CSVReader csvReader = new CSVReader(reader);
        CsvToBean csvToBean = new CsvToBean();

        // call the parse method of CsvToBean
        // pass strategy, csvReader to parse method
        List<CurrencyRate> list = csvToBean.parse(strategy, csvReader);

        return list;

    }
}
