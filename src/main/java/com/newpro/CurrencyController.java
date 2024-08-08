package com.newpro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CurrencyController {

        @Autowired
        private CurrencyService currencyService;

        @GetMapping("/")
        public String index(Model model) {
            model.addAttribute("baseCurrency", "USD");
            model.addAttribute("amount", 0.0);
            model.addAttribute("result", 0.0);
            return "index";
        }

        @PostMapping("/convert")
        public String convert(@RequestParam String baseCurrency,
                              @RequestParam double amount,
                              Model model) {
            try {
                double result = currencyService.convertToKZT(baseCurrency, amount);
                model.addAttribute("baseCurrency", baseCurrency);
                model.addAttribute("amount", amount);
                model.addAttribute("result", result);
            } catch (Exception e) {
                model.addAttribute("error", "Ошибка конвертации: " + e.getMessage());
            }
            return "index";
        }
    }