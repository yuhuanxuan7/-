package Test.ModuleTest;

import Main.ModulesList.Module;
import Main.ModulesList.UGModulesList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class could test both PGTModulesList and UGModulesList Class.
 * Cause most codes in this two classes are exactly same except credit check.
 */
class ModulesListTest {
    private UGModulesList ugList = new UGModulesList();
    private List<Module> modules = new ArrayList<>();

    @BeforeEach
    void setUp() {
        Module m1 = new Module("CSC8099", "Dissertation", "120");
        Module m2 = new Module("CSC8001", "Programming", "60");
        modules.add(m1);
        modules.add(m2);
        ugList.registerModules(modules);
    }

    @Test
    void TestRegisterModules() {
        Assertions.assertEquals(ugList.getRegisterSet(), modules);
    }

    @Test
    void TestGetEnoughCredits() {
        Assertions.assertFalse(ugList.getEnoughCredits());
    }
}