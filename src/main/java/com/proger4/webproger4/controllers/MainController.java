package com.proger4.webproger4.controllers;

import com.proger4.webproger4.services.DataServiceBd;
import com.proger4.webproger4.services.DateCalculator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Date;
@Controller
public class MainController {
    private final DateCalculator dateCalculator;
    private final DataServiceBd dataServiceBd;
    public MainController(DateCalculator dateCalculator, DataServiceBd dataServiceBd) {
        this.dateCalculator = dateCalculator;
        this.dataServiceBd = dataServiceBd;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("homepage", "Date Calculator");
        model.addAttribute("addition", "Addition");
        model.addAttribute("subtraction", "Subtraction");
        return "index";
    }

    @GetMapping("/addition")
    public String showAdditionPage(Model model) {
        model.addAttribute("addition", "");
        return "addition";
    }

   @PostMapping("/addition/result")


    public String addDatesResult(@RequestParam("date1") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date1,
                                 @RequestParam("date2") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date2,
                                 Model model) {
        // Сохранение дат в БД
        dataServiceBd.saveDates(date1, date2);

        String sum = dateCalculator.addDates(date1, date2);
        model.addAttribute("result", sum);
        return "date-calculator-result";
    }

    @GetMapping("/subtraction")
    public String showSubtractPage(Model model) {
        model.addAttribute("subtraction", "");
        return "subtraction";
    }

    @PostMapping("/subtraction/result")
    public String subtractDatesResult(@RequestParam("date1") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date1,
                                      @RequestParam("date2") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date2,
                                      Model model) {
        String difference = DateCalculator.subtractDates(date1, date2);
        model.addAttribute("result", difference);
        return "date-calculator-result";
    }
}