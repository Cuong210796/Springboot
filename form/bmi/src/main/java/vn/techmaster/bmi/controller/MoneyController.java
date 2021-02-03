package vn.techmaster.bmi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.techmaster.bmi.model.CurrencyRate;
import vn.techmaster.bmi.model.ExchangeRate;
import vn.techmaster.bmi.service.MoneyConverter;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;


@Controller
public class MoneyController {

    @Autowired
    private MoneyConverter moneyConverter;

    @GetMapping("/money")
    public String moneyForm(Model model, @RequestParam Map<String, String> params) {
        ExchangeRate m = new ExchangeRate();
        if (params.get("from") != null) {
            m.setFromCode(params.get("from"));
        }
        if (params.get("to") != null) {
            m.setToCode(params.get("to"));
        }
        if (params.get("result") != null) {
            m.setResult(Double.parseDouble(params.get("result")));
        }
        if (params.get("rate") != null) {
            m.setRate(Double.parseDouble(params.get("rate")));
        }
        if (m.getFromCode() == null && m.getToCode() == null) {
            m.setFromCode("USD");
            m.setToCode("VND");
        }
        List<CurrencyRate> result;
        try {
            result = moneyConverter.parseCurrencyCode();
            Collections.sort(result, new Comparator<CurrencyRate>() {
                @Override
                public int compare(CurrencyRate o1, CurrencyRate o2) {
                    return o1.getCountry().compareTo(o2.getCountry());
                }
            });
            model.addAttribute("currencies", result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("money", m);
        return "money";
    }

    @PostMapping("/money")
    public String postData(Model model, ExchangeRate exchangeRate) {
        Map<String, Double> map = moneyConverter.parseExchangeRate();
        double result = exchangeRate.getRate() * map.get(exchangeRate.getToCode()) / map.get(exchangeRate.getFromCode());
        return "redirect:/money?from="
                + exchangeRate.getFromCode()
                + "&to=" + exchangeRate.getToCode()
                + "&result=" + result
                + "&rate=" + exchangeRate.getRate();
    }

}
