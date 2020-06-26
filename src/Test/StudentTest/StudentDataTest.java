package Test.StudentTest;

import Main.Student.StudentData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class StudentDataTest {

    private Date birthday;
    private Calendar d1 = Calendar.getInstance();
    private Calendar expected = Calendar.getInstance();
    private StudentData data;

    @BeforeEach
    void setUp() {
        d1.set(2020, Calendar.FEBRUARY, 29);
        Date birthday = d1.getTime();
        expected.setTime(birthday);
        String firstName = "Jacob";
        String lastName = "Ninja";
        int age = 21; // normal case for age
        data = new StudentData(firstName, lastName, age, d1.getTime());
    }

    @Test
    void getFirstname() {
        Assertions.assertEquals(data.getFirstname(), "Jacob");
    }

    @Test
    void getLastname() {
        Assertions.assertEquals(data.getLastname(), "Ninja");
    }

    @Test
    void getAge() {
        Assertions.assertEquals(21, data.getAge());
    }

    @Test
    void getBirthday() {
        Assertions.assertEquals(d1.getTime(), data.getBirthday());
    }
}