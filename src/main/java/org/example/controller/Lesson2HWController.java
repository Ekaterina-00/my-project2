package org.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

@RestController
public class Lesson2HWController {

   @GetMapping("/current-datetime")
    public String currentTime(){
       LocalDateTime localDateTime = LocalDateTime.now();
        return "Текущее время: " + localDateTime;
    }

    @GetMapping("/current-season")
    public String currentSeason(){
        int month = LocalDate.now().getMonthValue();
        String season = "Неизвестный сезон";

        switch (month){
            case 12,1,2:
                season = "Зима";
                break;
            case 3,4,5:
                season = "Весна";
                break;
            case 6,7,8:
                season = "Лето";
                break;
            case 9,10,11:
                season = "Осень";
                break;
        }
        return "Текущий сезон: " + season;
    }

    @GetMapping("/future-date")
    public String futureDate(){
        LocalDate localDate = LocalDate.now();
        Random random = new Random();
        int number = random.nextInt(30)+1;
        LocalDate futureDate = localDate.plusDays(number);
        return String.format("Случайная дата в будущем: ") + futureDate;
    }
}
