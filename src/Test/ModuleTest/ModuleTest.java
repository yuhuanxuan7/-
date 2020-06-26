package Test.ModuleTest;

import Main.ModulesList.Module;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModuleTest {
    private Module module;

    @BeforeEach
    void setUp() {
        module = new Module("CSC8001", "Data Structure", "20");
    }

    @Test
    void getModuleCode() {
        Assertions.assertEquals("CSC8001", module.getModuleCode());
    }

    @Test
    void getModuleName() {
        Assertions.assertEquals("Data Structure", module.getModuleName());
    }

    @Test
    void getModuleCredit() {
        Assertions.assertEquals("20", module.getModuleCredit());
    }
}