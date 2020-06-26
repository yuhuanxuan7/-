package Main;

import Main.ModulesList.Module;
import Main.SmartCard.CardFactory;
import Main.SmartCard.SmartCard;
import Main.Student.Student;
import Main.Student.StudentData;
import Main.Student.StudentFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import static Main.Student.StudentFactory.Postgraduate_Research;

/**
 * @Description: Main Class to integrate the whole system;
 * @Author: Jacob Ninja
 * @Date:
 */
public final class StudentSystem {

    private final List<Module> list = readModuleData();// Module List
    private final List<String> tutor = readTutorData();// Supervisor List
    private static final HashMap<String, Student> students = new HashMap<>();// student system to store data.

    /**
     * This method returns the number of students of the specified type that are currently
     * enrolled.
     *
     * @param typeOfStudent a String to declare which type of the students to be counted
     * @return a count number
     */
    public final long noOfStudents(String typeOfStudent) {
        long count =
                // using stream and lambada expression;
                students.values().stream().filter(i -> i.getStudentType().equalsIgnoreCase(typeOfStudent))
                        .count();
        System.out.println(typeOfStudent + " has " + count + " students");
        return count;
    }

    /**
     * This method registers a new student onto the system and allocates a student ID.
     * A student ID has two components - a single letter followed by a four digit number.
     * For example:
     * • a1234
     * You must provide access to each component and an appropriate string representation of the ID.
     * Student ID’s are unique. You must guarantee that no two students have the same ID.
     *
     * @param s the student object need to be registered.
     */
    public final void registerStudent(Student s, StudentData data) {
        String id = iDGenerator();
        Student newS;
        /**
         * Defensive copy, an Object is always mutable.
         */
        if (s.getStudentType().equalsIgnoreCase(Postgraduate_Research)) {
            newS = StudentFactory.getInstance(s.getStudentType(), id, tutor);
        } else {
            newS = StudentFactory.getInstance(s.getStudentType(), id, list);
        }
        SmartCard card = CardFactory.getInstance(
                s.getStudentID(), data, s.getStudentType());
        newS.setCard(card);
        students.put(id, newS); // register successfully.
    }

    /**
     * This method changes a student record.
     *
     * @param studentID
     * @param data
     */
    public final void amendStudentData(String studentID, StudentData data) {
        Collection<Student> studentSet = StudentSystem.students.values();
//        Common approach to search a Student via student ID
//        for (Student s:studentSet) {
//            if (s.getStudentID().equalsIgnoreCase(studentID))
//            s.getCard().setData(data);
//        }
        Consumer<Student> action = i -> {
            if (i.getStudentID().equalsIgnoreCase(studentID)) {
                i.getCard().setData(data);
            } else {
                throw new NoSuchElementException("No Student find, please check student ID first");
            }
        };
        studentSet.forEach(action);
    }

    /**
     * This method removes the student record associated with the given student number.
     * In effect, the student is leaving the University.
     *
     * @param studentID
     */
    public final void terminateStudent(String studentID) {
        if (students.containsKey(studentID)) {
            students.remove(studentID);
        } else {
            throw new NoSuchElementException("No student find, please check");
        }
    }

    /**
     * This method is to grantee the uniqueness of student ID.
     */
    private String iDGenerator() {
        // count the students number already existing in the system.
        int map_size =
                students.keySet().size();
        String uniqueID = null;

        try {
            // Letter part
            String az = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            int char_index = map_size / 8999;
            char letter = az.charAt(char_index);

            // number part
            int[] nums = IntStream.rangeClosed(1000, 9999).toArray();
            int num_index = 0;
            if (map_size < 8999) {
                num_index = map_size;
            } else {
                num_index = map_size - 8999 * char_index;
            }
            String num = String.valueOf(nums[num_index]);

            // append to a unique ID;
            StringBuffer sb = new StringBuffer(6);
            sb.append(letter);
            sb.append(num);
            uniqueID = sb.toString();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("System overload, please check");
        }
        return uniqueID;
    }

    /**
     * This method is for read module data from files.
     */
    public List readModuleData() {
        String path = "./src/Main/ModuleList";
        String path2 = "./src/Main/Supervisor";
        File file = new File(path);
        File file2 = new File(path2);
        List<Module> modules = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            String moduleCode;
            String moduleName;
            String moduleCredit;
            Module module;
            while (scanner.hasNext()) {
                String[] information = scanner.nextLine().split(",");
                moduleCode = information[0];
                moduleName = information[1];
                moduleCredit = information[2];
                module = new Module(moduleCode, moduleName, moduleCredit);
                list.add(module);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("invalid file or file route, please check");
        }
        return modules;
    }

    public List readTutorData() {
        String path2 = "./src/Main/Supervisor";
        File file2 = new File(path2);
        List<Module> modules = new ArrayList<>();
        try {
            Scanner scanner1 = new Scanner(file2);
            while (scanner1.hasNext()) {
                String tutorName = scanner1.nextLine();
                tutor.add(tutorName);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("invalid file or file route, please check");
        }
        return modules;
    }

}
