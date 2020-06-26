package Main.Student;

import Main.ModulesList.Module;
import Main.ModulesList.PGTModulesList;

import java.util.List;


public final class PGT extends Student {

    private double passMark = 50;

    PGT(String studentID, List<Module> c) {
        super(studentID);
        modulesList = new PGTModulesList();
        modulesList.registerModules(c); // default module list
    }

    @Override
    public String getStudentType() {
        return "PGT";
    }


}
