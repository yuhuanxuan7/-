package Main.Student;

import Main.ModulesList.Module;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class StudentFactory {

    public static final String Postgraduate_Taught = "PGT";
    public static final String Postgraduate_Research = "PGR";
    public static final String Undergraduate = "UG";

    private String Id;

    private StudentFactory(String studentId) {
        this.Id = studentId;
    }

    // map of account number to instantiated account.
    private static final Map<String, Student> students = new HashMap<>();

    public static Student getInstance
            (String type, String studentID, List c) {
        Student student = students.get(studentID);
        if (student != null) {
            return student;
        }
        if (type.equalsIgnoreCase(Postgraduate_Research)) {
            student = new PGR(studentID, c);
        } else if (type.equalsIgnoreCase(Postgraduate_Taught)) {
            student = new PGT(studentID, c);
        } else if (type.equalsIgnoreCase(Undergraduate)) {
            student = new UG(studentID, c);
        } else {
            throw new IllegalArgumentException("Invalid student type " + type);
        }
        students.put(studentID, student); // put this student in students map.
        return student;
    }

    @Override
    public String toString() {
        return "StudentFactory{" +
                "Id='" + Id + '\'' +
                '}';
    }
}
