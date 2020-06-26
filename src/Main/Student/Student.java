package Main.Student;

import Main.ModulesList.ModulesList;
import Main.SmartCard.SmartCard;


public abstract class Student {

    private String studentID;

    private SmartCard card;

    ModulesList modulesList; // Follow <<Head First Design Pattern 2nd>> Chapter one, duck example;

    /**
     * package-private constructor cannot be directly instantiated by
     * clients outside this package.
     * Use StudentFactory.getInstance() method instead.
     */
    Student(String studentID) {
        this.studentID = studentID;
    }

    /**
     * To get the whole Student ID.
     *
     * @return the whole Student ID
     */
    public final String getStudentID() {
        return studentID;
    }

    /**
     * To get the letter part of this student id.
     *
     * @return a String represent letter a;
     */
    public final String getIdletter() {
        String[] idCollector =
                getStudentID().split("[0-9]");
        return idCollector[idCollector.length - 1];
    }

    /**
     * To get the digit part of this student id.
     *
     * @return a String represent letter a;
     */
    public final String getDigit() {
        String[] idCollector =
                getStudentID().split("[A-Za-z]");
        return idCollector[idCollector.length - 1];
    }

    /**
     * This method is to get the specific subclass type in the Object Factory.
     *
     * @return the specific student type in a String.
     */
    public abstract String getStudentType();

    public SmartCard getCard() {
        return card;
    }

    /**
     * There is a CardFactory Class existing to grantee the uniqueness of Smart Card.
     *
     * @param card the SmartCard instance.
     */
    public void setCard(SmartCard card) {
        this.card = card;
    }

    /**
     * Accessor method to get register module list when student type is PGT or UG.
     *
     * @return modulesList instance.
     */
    public ModulesList getModulesList() {
        return modulesList;
    }

    /**
     * Students have to be identified via their student Id.
     *
     * @param o Object requiring comparison.
     * @return the comparison results.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return getStudentID().equals(student.getStudentID());
    }

    @Override
    public int hashCode() {
        return getStudentID().hashCode();
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentID='" + studentID + '\'' +
                ", card=" + card +
                ", modulesList=" + modulesList +
                '}';
    }
}