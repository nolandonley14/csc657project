package com.example.application.data;

import com.example.application.models.user.User;
import com.github.javafaker.Faker;
import com.github.vatbub.randomusers.Generator;
import com.github.vatbub.randomusers.result.Nationality;
import com.github.vatbub.randomusers.result.RandomUser;

import java.net.MalformedURLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;

public class DatabaseAccess {

    private List<Nationality> nationalitiesGroup = new ArrayList<Nationality>();
    private RandomUser.RandomUserSpec spec;

    public DatabaseAccess() {

        nationalitiesGroup.add(Nationality.getFromCurrentDefaultLocale());
        spec = new RandomUser.RandomUserSpec();
        spec.setNationalities(nationalitiesGroup);


    }

    public User getNewUser() throws MalformedURLException {

        RandomUser user = Generator.generateRandomUser(spec);
        Map<String, String> name = new HashMap<String, String>();
        Map<String, String> city = new HashMap<String, String>();
        name.put("firstName", user.getName().getFirstName().substring(0,1).toUpperCase() + user.getName().getFirstName().substring(1));
        name.put("lastName", user.getName().getLastName().substring(0,1).toUpperCase() + user.getName().getLastName().substring(1));
        city.put("city", user.getLocation().getCity().substring(0, 1).toUpperCase() + user.getLocation().getCity().substring(1));
        city.put("state", user.getLocation().getState().substring(0, 1).toUpperCase() + user.getLocation().getState().substring(1));
        LocalDate ldbday = user.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        String bday = String.valueOf(Period.between(ldbday, LocalDate.now()).getYears());
        String gender = user.getGender().toString().substring(0,1).toUpperCase() + user.getGender().toString().substring(1);
        Random num = new Random();
        boolean travel = num.nextInt(2) == 0 ? false : true;
        String bio = getBio();
        User myUser = new User(name, city, bday, bio, user.getNationality().toString(), user.getPicture().getLargePicture().toString(), travel);
        return myUser;

    }

    private String getBio() {
        Faker faker = new Faker(new Locale("en-US"));
        String bio = "";
        Random num = new Random();
        switch (num.nextInt(10)) {
            case 0:
                bio+=faker.backToTheFuture().quote();
                break;
            case 1:
                bio+=faker.harryPotter().quote();
                break;
            case 2:
                bio+=faker.friends().quote();
                break;
            case 3:
                bio+=faker.hobbit().quote();
                break;
            case 4:
                bio+=faker.howIMetYourMother().quote();
                break;
            case 5:
                bio+=faker.lebowski().quote();
                break;
            case 6:
                bio+=faker.rickAndMorty().quote();
                break;
            case 7:
                bio+=faker.shakespeare().romeoAndJulietQuote();
                break;
            case 8:
                bio+=faker.yoda().quote();
                break;
            case 9:
                bio+=faker.hitchhikersGuideToTheGalaxy().quote();
                break;
            default:
                bio+="Hello There!";
                break;
        }

        return bio;
    }

}
