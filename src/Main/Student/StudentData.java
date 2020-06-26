package Main.Student;

import java.util.Date;

/**
 * @Description: To store common student data for each students
 * @Author: Jacob Ninja
 * @Date: 24/02/2020
 */
public final class StudentData {
    /**
     * String is an immutable class,
     */
    private String firstname;
    private String lastname;

    private final int age;

    private final Date birthday;

    public StudentData(String firstname, String lastname, int age, Date birthday) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.birthday = new Date(birthday.getTime()); // defensive copy
    }

    // defensive copying, copying constructor.
    public StudentData(StudentData studentData) {
        this(studentData.firstname, studentData.lastname, studentData.age, new Date(studentData.birthday.getTime()));
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getAge() {
        return age;
    }

    public Date getBirthday() {
        return new Date(birthday.getTime());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentData)) return false;

        StudentData that = (StudentData) o;

        if (getAge() != that.getAge()) return false;
        if (getFirstname() != null ? !getFirstname().equals(that.getFirstname()) : that.getFirstname() != null)
            return false;
        if (getLastname() != null ? !getLastname().equals(that.getLastname()) : that.getLastname() != null)
            return false;
        getBirthday();
        return getBirthday().equals(that.getBirthday());
    }

    @Override
    public int hashCode() {
        int result = getFirstname() != null ? getFirstname().hashCode() : 0;
        result = 31 * result + (getLastname() != null ? getLastname().hashCode() : 0);
        result = 31 * result + getAge();
        getBirthday();
        result = 31 * result + getBirthday().hashCode();
        return result;
    }
}
