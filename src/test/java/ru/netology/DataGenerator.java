package ru.netology;
import com.github.javafaker.Faker;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Data
@RequiredArgsConstructor
public class DataGenerator {

    public static class Generate {
        private Generate() {}

        public static CardDelivery generateUserData(String locale) {
            Faker faker = new Faker(new Locale("ru"));
            return new CardDelivery(faker.address().city(), faker.name().fullName(), faker.phoneNumber().cellPhone());
        }

        public static String generateFirstDeliveryDate() {
            LocalDate date1 = LocalDate.now().plusDays(4);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            return date1.format(formatter);
        }

        public static String generateSecondDeliveryDate() {
            LocalDate date2 = LocalDate.now().plusDays(5);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            return date2.format(formatter);
        }

    }
}

