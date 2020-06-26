package Test.StudentTest;

import Main.ModulesList.Module;
import Main.ModulesList.ModulesList;
import Main.ModulesList.PGTModulesList;
import Main.SmartCard.CardFactory;
import Main.SmartCard.SmartCard;
import Main.Student.Student;
import Main.Student.StudentData;
import Main.Student.StudentFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * It also tests constructor a Undergraduate student and issuing a smart card for
 * a Undergraduate student.
 */
class StudentTest {
    private Student s1;
    private List<Module> c = new ArrayList<>();
    private String studentId;
    private SmartCard card;
    private StudentData data;
    private Date birthday;
    private Calendar d1 = Calendar.getInstance();
    private ModulesList modules = new PGTModulesList();

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        studentId = "A1234";
        s1 = StudentFactory.getInstance("PGT", studentId, c);
        d1.set(2020, 2, 29);
        Date birthday = d1.getTime();
        String firstName = "Jacob";
        String lastName = "Ninja";
        int age = 20; // 20 is a boundary value for postgraduate student.
        data = new StudentData(firstName, lastName, age, d1.getTime());
        card = CardFactory.getInstance(studentId, data, "PGT");
        s1.setCard(card);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    /**
     * To test whether the whole student Id could be accessed to.
     */
    @org.junit.jupiter.api.Test
    void getStudentID() {
        Assertions.assertEquals("A1234", s1.getStudentID());
    }

    @org.junit.jupiter.api.Test
    void getIdletter() {
        Assertions.assertEquals("A", s1.getIdletter());
    }

    @org.junit.jupiter.api.Test
    void getDigit() {
        Assertions.assertEquals("1234", s1.getDigit());
    }

    @org.junit.jupiter.api.Test
    void getStudentType() {
        Assertions.assertEquals("PGT", s1.getStudentType());
    }

    @org.junit.jupiter.api.Test
    void getCard() {
        Assertions.assertEquals(s1.getCard(), card);
    }

    /**
     * This method is to weather getModulesList() method could
     * get a student record registered module List.
     */
    @Test
    void TestGetModulesList() {
        Assertions.assertEquals(modules, s1.getModulesList());
    }

}