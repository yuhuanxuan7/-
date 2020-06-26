package Main.Student;

import Main.ModulesList.Module;
import Main.ModulesList.UGModulesList;

import java.util.List;


public final class UG extends Student {

    private int credit;
    private double passMark = 40;

    UG(String studentID, List<Module> c) {
        super(studentID);
        modulesList = new UGModulesList();
        modulesList.registerModules(c); // default module list
    }

    /**
     * Indicate which type is this student belonging to.
     *
     * @return a String indicate which type it is.
     */
    @Override
    public String getStudentType() {
        return "UG";
    }


}
