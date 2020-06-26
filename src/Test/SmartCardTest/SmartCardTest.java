package Test.SmartCardTest;

import Main.SmartCard.CardFactory;
import Main.SmartCard.SmartCard;
import Main.Student.StudentData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.*;

class SmartCardTest {

    private StudentData data1;
    private Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+00:00"));
    private SmartCard card1;
    private SmartCard card2;
    private SmartCard card3;
    private String expectedCardNumber = "JN-2020-1234";


    @BeforeEach
    void setUp() {
        calendar.set(2020, Calendar.JANUARY, 31);

        String firstName = "Jacob";
        String lastName = "Ninja";
        int age = 25;
//        CardFactory is using a singleton design pattern, so studentId should be different.
        String studentId1 = "A1234";
        String studentId2 = "B1234";
        String studentId3 = "C1234";
        String type1 = "PGR";
        String type2 = "PGT";
        String type3 = "UG";
        data1 = new StudentData(firstName, lastName, age, calendar.getTime());
        card1 = CardFactory.getInstance(studentId1, data1, type1);
        card2 = CardFactory.getInstance(studentId2, data1, type2);
        card3 = CardFactory.getInstance(studentId3, data1, type3);
    }

    /**
     * To test getExpiry Date method for three kind of students.
     */
    @Test
    void getExpiryDatePGR() {
        Calendar t1 = Calendar.getInstance();
        Calendar t2 = Calendar.getInstance();
        Calendar t3 = Calendar.getInstance();
        t1.setTime(card1.getExpiryDate());
        t2.setTime(card2.getExpiryDate());
        t3.setTime(card3.getExpiryDate());

        Assertions.assertEquals(2025, t1.get(Calendar.YEAR));
        Assertions.assertEquals(2022, t2.get(Calendar.YEAR));
        Assertions.assertEquals(2024, t3.get(Calendar.YEAR));
    }

    /**
     * To test whether it could get the correct smart card number;
     */
    @Test
    void getSmartCardnumber() {
        Assertions.assertEquals(expectedCardNumber, card1.getSmartCardnumber());
    }

    /**
     * To test whether getData() method works well. Due to the defensive copy,
     */
    @Test
    void getData() {
        assertEquals(data1, card1.getData());
    }
}