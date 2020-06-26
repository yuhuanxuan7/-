package Main.Student;


import java.util.List;

public final class PGR extends Student {

    private String supervisorName;

    PGR(String studentID, List<String> tutor) {
        super(studentID);
        this.supervisorName = setSupervisorName(tutor);
    }

    public String setSupervisorName(List<String> tutor) {
        this.supervisorName = tutor.get(0);
        return this.supervisorName;
    }

    public String getSupervisorName() {
        return this.supervisorName;
    }

    @Override
    public String getStudentType() {
        return "PGR";
    }

}
