package ru.netology;
import com.github.javafaker.Faker;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Data
@RequiredArgsConstructor
public class DataGenerator {

    public static class Generate {
        private Generate() {
        }

        @Value
        public static class CityDelivery {
            private final String city;
        }

        public static CityDelivery getCityDelivery() {
            Faker faker = new Faker(new Locale("ru"));
            return new CityDelivery(faker.address().city());
        }

        @Value
        public static class NameDelivery {
            private final String name;
        }

        public static NameDelivery getNameDelivery() {
            Faker faker = new Faker(new Locale("ru"));
            return new NameDelivery(faker.name().fullName());
        }

        @Value
        public static class PhoneDelivery {
            private final String phone;
        }

        public static PhoneDelivery getPhoneDelivery() {
            Faker faker = new Faker(new Locale("ru"));
            return new PhoneDelivery(faker.phoneNumber().cellPhone());
        }

        @Value
        public static class FirstDateDelivery {
            private final String cardFirstDelivery;
        }

        public static FirstDateDelivery getFirstDateDelivery() {
            LocalDate date = LocalDate.now().plusDays(5);
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            String dateInForm = date.format(dateTimeFormatter);
            return new FirstDateDelivery(dateInForm);
        }

        @Value
        public static class SecondDateDelivery {
            private final String cardSecondDelivery;
        }

        public static SecondDateDelivery getSecondDateDelivery() {
            LocalDate date = LocalDate.now().plusDays(6);
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            String dateInForm = date.format(dateTimeFormatter);
            return new SecondDateDelivery(dateInForm);
        }
    }
}
