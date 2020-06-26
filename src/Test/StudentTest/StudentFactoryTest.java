package Test.StudentTest;

import Main.Student.StudentFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentFactoryTest {
    private List<String> c = new ArrayList<>();

    @BeforeEach
    void setUp() {
    }

    @Test
    void getInstance() {
        String Id = "A1234";
        String type = "Haha";
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    StudentFactory.getInstance(type, Id, c);
                });
    }
}