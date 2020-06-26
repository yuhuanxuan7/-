package Test.SmartCardTest;

import Main.SmartCard.CardFactory;
import Main.Student.StudentData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class CardFactoryTest {

    private StudentData data1;
    private StudentData data2;
    private String type1 = "UG";
    private String type2 = "PGT";
    private String id1 = "P1234";
    private String id2 = "P5678";

    @BeforeEach
    void setUp() {
        String firstName = "Jacob";
        String lastName = "Ninja";
        int age1 = 15;
        int age2 = 17;
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2020, Calendar.FEBRUARY, 29);
        data1 = new StudentData(firstName, lastName, age1, calendar1.getTime());
        data2 = new StudentData(firstName, lastName, age2, calendar1.getTime());
    }

    /**
     * To test whether undergraduate student card issue function works well.
     */
    @Test
    void TestgetInstanceUG() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CardFactory.getInstance(id1, data1, type1);
        });
    }

    /**
     * To test whether postgraduate student card issue function works well.
     */
    @Test
    void TestgetInstancePG() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CardFactory.getInstance(id2, data2, type2);
        });
    }
}