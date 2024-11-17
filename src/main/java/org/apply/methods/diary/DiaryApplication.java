package org.apply.methods.diary;

import lombok.NonNull;
import org.apply.methods.diary.model.City;
import org.apply.methods.diary.model.Person;
import org.apply.methods.diary.repository.CityRepository;
import org.apply.methods.diary.repository.PersonRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class DiaryApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiaryApplication.class, args);
    }

    @Bean
    public ApplicationRunner init(@NonNull PersonRepository personRepository) {
        return _ -> savePerson(personRepository, 2L, "Test name", "Test Family");
    }

    @Bean
    public CommandLineRunner commandLineRunner(CityRepository cityRepository) {
        return _ -> {
//          findCities(cityRepository);
//          System.out.println("---------------------");

            addCity(cityRepository, "Detroy");
            addCity(cityRepository, "Lutsk");
            addCity(cityRepository, "Madrid");

//          changeCityName(cityRepository, 4L, "Detroy --> 3");
//          deleteCity(cityRepository);
            findCities(cityRepository);
        };
    }

    private static void savePerson(@NonNull PersonRepository personRepository, @NonNull Long cityId, @NonNull String name, @NonNull String familyName) {
        personRepository.save(new Person(new City(cityId), name, familyName));
    }

    private static void findCities(CityRepository cityRepository) {
        List<City> all = cityRepository.findAll();
        for (City city : all) {
            System.out.println(city.getId() + " " + city.getName());
        }
    }

    private static void addCity(@NonNull CityRepository cityRepository, @NonNull String cityName) {
        cityRepository.save(new City(cityName));
    }

    private static void changeCityName(@NonNull CityRepository cityRepository, @NonNull Long id, @NonNull String name) {
        Optional<City> cityById = cityRepository.findById(id);
        if (cityById.isPresent()) {
            City city = cityById.get();
            city.setName(name);
            cityRepository.save(city);
        }
    }

    private static void deleteCity(CityRepository cityRepository) {
        cityRepository.deleteById(1L);
    }
}
