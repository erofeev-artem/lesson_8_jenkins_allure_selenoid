import com.github.javafaker.Faker;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class RegistrationFormTest extends TestBase {
    private final String REGISTRATION_URL = "https://demoqa.com/automation-practice-form";
    Faker faker = new Faker();
    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            email = faker.internet().emailAddress(),
            gender = "Other",
            mobile = faker.phoneNumber().subscriberNumber(10),
            birthYear = String.valueOf(faker.number().numberBetween(1950, 2010)),
            birthMonth = "May",
            birthDay = String.valueOf(faker.number().numberBetween(1, 30)),
            fullDate = birthDay.concat(" " + birthMonth).concat("," + birthYear),
            subject = "Hindi",
            subjectTwo = "English",
            subjectTwoShort = "Eng",
            hobbies = "Reading",
            hobbiesTwo = "Music",
            uploadPath = "uploadFiles/bear.jpg",
            fileName = "bear.jpg",
            address = faker.address().fullAddress(),
            state = "Haryana",
            city = "Panipat";


    @Test
    @Tag("positive")
    public void positiveFieldsTest() {
        step("open page", () -> open(REGISTRATION_URL));
        step("fill the form", () -> {
            $(".main-header").shouldHave(text("Practice Form"));
            $("#firstName").setValue(firstName);
            $("#lastName").setValue(lastName);
            $("#userEmail").setValue(email);
            $("#genterWrapper").find(byText("Other")).click();
            $("#userNumber").setValue(mobile);
            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").click();
            $(".react-datepicker__month-select").selectOption(birthMonth);
            $(".react-datepicker__year-select").selectOption(birthYear);
            $(".react-datepicker__month").find(byText(birthDay)).click();
            $("#subjectsContainer #subjectsInput").setValue(subject).pressEnter();
            $("#subjectsContainer #subjectsInput").setValue(subjectTwoShort).pressEnter();
            $("#hobbiesWrapper").find(byText(hobbies)).click();
            $("#hobbiesWrapper").find(byText(hobbiesTwo)).click();
            $("#uploadPicture").uploadFromClasspath(uploadPath);
            $("#currentAddress").setValue(address).pressTab();
            $("#state").click();
            $("#state").find(byText(state)).click();
            $("#city").click();
            $("#city").find(byText(city)).click();
            $("#submit").click();
        });
        step("check result", () -> {
            $(".modal-content").shouldHave(text(firstName),
                    text(email),
                    text(gender),
                    text(mobile),
                    text(fullDate),
                    text(subject),
                    text(subjectTwo),
                    text(hobbies),
                    text(hobbiesTwo),
                    text(fileName),
                    text(address),
                    text(state),
                    text(city));
        });
    }

    @Test
    @Tag("negative")
    public void negativeFieldsTest() {
        step("open page", () -> open(REGISTRATION_URL));
        step("fill the form", () -> {
            $(".main-header").shouldHave(text("Practice Form"));
            $("#firstName").setValue(firstName);
            $("#lastName").setValue(lastName);
            $("#userEmail").setValue(email);
            $("#genterWrapper").find(byText("Other")).click();
            $("#userNumber").setValue(mobile);
            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").click();
            $(".react-datepicker__month-select").selectOption(birthMonth);
            $(".react-datepicker__year-select").selectOption(birthYear);
            $(".react-datepicker__month").find(byText(birthDay)).click();
            $("#subjectsContainer #subjectsInput").setValue(subject).pressEnter();
            $("#subjectsContainer #subjectsInput").setValue(subjectTwoShort).pressEnter();
            $("#hobbiesWrapper").find(byText(hobbies)).click();
            $("#hobbiesWrapper").find(byText(hobbiesTwo)).click();
            $("#uploadPicture").uploadFromClasspath(uploadPath);
            $("#currentAddress").setValue(address).pressTab();
            $("#state").click();
            $("#state").find(byText(state)).click();
            $("#city").click();
            $("#city").find(byText(city)).click();
            $("#submit").click();
        });
        step("check result", () -> {
            $(".modal-content").shouldHave(text(firstName),
                    text(email+"123"),
                    text(gender),
                    text(mobile),
                    text(fullDate),
                    text(subject),
                    text(subjectTwo),
                    text(hobbies),
                    text(hobbiesTwo),
                    text(fileName),
                    text(address),
                    text(state),
                    text(city));
        });
    }
}
