package org.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

@RestController
public class Lesson4HWController {

    @GetMapping("/day-of-week")
    public String dayOfWeek(@RequestParam String date) {
        //преобр-ие строки в LocalDate
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        //получение дня недели
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        //преобр-ие в русский
        switch (dayOfWeek) {
            case MONDAY:
                return "Понедельник";
            case TUESDAY:
                return "Вторник";
            case WEDNESDAY:
                return "Среда";
            case THURSDAY:
                return "Четверг";
            case FRIDAY:
                return "Пятница";
            case SATURDAY:
                return "Суббота";
            case SUNDAY:
                return "Воскресенье";
            default:
                return "Неизвестный день";
        }
    }

    @GetMapping("/generate-password")
    public String generatePassword(@RequestParam int length){
        String capitalLetters = "QASWEDFFTGFDRGYNYTRBRT";
        String lowercaseLetters = "qaswedewvgrebhtrntrnrstn";
        String numbers = "0123456789";
        String specialCharacters = "!@#$%^&*()-_=+[]{}|;:,.<>?:";

        String compositionOfSymbols = capitalLetters + lowercaseLetters +numbers +specialCharacters;

        SecureRandom random = new SecureRandom(); //дает случайные числа
        StringBuilder randomCharacters = new StringBuilder();//собирает из этих чисел готовый пароль

        //random.nextInt(capitalLetters.length()) - генерирует случайное число от 0 до длины строки - 1
        // capitalLetters.charAt(...) - получает символ по случайному индексу (charAt- метод, который достает символ по номеру позиции из строки.
        //append(...) - добавляет этот символ в StringBuilder
        randomCharacters.append(capitalLetters.charAt(random.nextInt(capitalLetters.length())));
        randomCharacters.append(lowercaseLetters.charAt(random.nextInt(lowercaseLetters.length())));
        randomCharacters.append(numbers.charAt(random.nextInt(numbers.length())));
        randomCharacters.append(specialCharacters.charAt(random.nextInt(specialCharacters.length())));
// Принцип такой: cоздали штуку для рандомных символов + "короб" для этих чисел -> штука выбирает случайные символы и добавляет в "короб"

        for (int i = 4; i < length; i++) { //цикл начинается с 4, так как 4 уже есть randomCharacters.append
            int randomIndex = random.nextInt(compositionOfSymbols.length()); //ген-ия случайного индекса из общей строки символов
            char randomChar = compositionOfSymbols.charAt(randomIndex); //получение символа по сген-му случайному индексу
            randomCharacters.append(randomChar); //доб-ие этого символа в StringBuilder
        }

        return "Случайный пароль: " + randomCharacters.toString(); //randomCharacters.toString() - преобразует StringBuilder в обычную строку
    }

    @GetMapping("/factorial")
    public String factorial(@RequestParam int number) {
        long count = 1;
        for (int i = 1; i <= number; i++) {
            count = count * i;
        }
        return "Факториал числа " + number + ": " + count;
    }

    @GetMapping("/power")
    public String power(@RequestParam int number, @RequestParam int power) {
        int result = 1;
        for (int i = 0; i < power; i++) {
            result *= number;
        }
        return number + " в степени " + power + ": " + result;
    }

    @GetMapping("/random-date")
    public String randomDate(@RequestParam String startDate, @RequestParam String endDate) {
        LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        //разница в днях между датами
        long daysBetween = ChronoUnit.DAYS.between(start, end); //метод для вычисления разницы между двумя датами
        //ген-ия случайного числа дней от 0 до daysBetween
        long randomDays = ThreadLocalRandom.current().nextLong(1, daysBetween); //генератор случайных чисел(диапазон от 1 и до  daysBetween). Синтаксис метода:long nextLong(long origin, long bound) origin - включительно (inclusive), bound - исключительно (exclusive)
        //добавление случайных дней к начальной дате
        LocalDate randomDate = start.plusDays(randomDays);

        return "Случайная дата: " + randomDate;
    }

    @GetMapping("/sort-array")
    public String sortArray(@RequestParam int[] numbers, @RequestParam boolean isAsc) {
        int[] sorted = numbers.clone();
        Arrays.sort(sorted); //сортировка по возрастанию

        if (!isAsc) {
            for (int i = 0; i < sorted.length / 2; i++) {
                int temp = sorted[i];
                sorted[i] = sorted[sorted.length - 1 - i];
                sorted[sorted.length - 1 - i] = temp;
            }
        }
        return "Отсортированный массив: " + Arrays.toString(sorted);
    }

    @GetMapping("/substring")
    public String substring(@RequestParam String str, @RequestParam int position, @RequestParam boolean isFirst) {
        String result;

        if (isFirst) {
            //подстрока от начала и до позиции
            result = str.substring(0, position);
        } else {
            //подстрока от позиции и до конца
            result = str.substring(position);
        }
        return "Часть строки: " + result;
    }

}
