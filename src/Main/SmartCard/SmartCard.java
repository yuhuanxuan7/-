package Main.SmartCard;

import Main.Student.*;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static Main.Student.StudentFactory.*;

/**
 *
 */
public final class SmartCard {

    private String cardnumber;

    // for constructor expiry date.
    private Calendar expiry = Calendar.getInstance(TimeZone.getTimeZone("GMT+00:00"));
    private final Date dateOfIssue = expiry.getTime();

    private Date expiryDate;

    private StudentData data; // student data could be changed.
    private String studentId;
    private String type;

//    private int time = expiry.get(Calendar.YEAR);

    SmartCard(String studentId, StudentData data, String type) {
        this.data = new StudentData(data); // defensive copy;
        this.studentId = studentId;
        this.type = type;
        cardnumber = smartCardNumberGenerator();
        this.expiryDate = setExpiryDate(type);
    }

    SmartCard(SmartCard card) {
        this(card.studentId, card.data, card.type);
    }

    private Date setExpiryDate(String s) {
        Date expiryTime;
        if (s.equalsIgnoreCase(Undergraduate)) {
//            time = expiry.get(Calendar.YEAR) + 4;
            expiry.add(Calendar.YEAR, 4);
        } else if (s.equalsIgnoreCase(Postgraduate_Taught)) {
//            time = expiry.get(Calendar.YEAR) + 2;
            expiry.add(Calendar.YEAR, 2);
        } else if (s.equalsIgnoreCase(Postgraduate_Research)) {
//            time = expiry.get(Calendar.YEAR) + 5;
            expiry.add(Calendar.YEAR, 5);
        }
        expiryTime = expiry.getTime();
        return expiryTime;
    }

    public Date getExpiryDate() {
        return new Date(expiryDate.getTime());
    }

    public String getSmartCardnumber() {
        return cardnumber;
    }

    public void setData(StudentData data) {
        this.data = new StudentData(data);
    }

    /**
     * Method to get student data, defensive copy made.
     *
     * @return a new StudentData instance.
     */
    public StudentData getData() {
        return new StudentData(data);
    }

    private String smartCardNumberGenerator() {
        String first = String.valueOf(data.getFirstname().charAt(0));
        String second = String.valueOf(data.getLastname().charAt(0));
        StringBuffer sb = new StringBuffer(16);
        sb.append(first);
        sb.append(second);
        sb.append("-");
        StringBuffer firstComponent = sb;// first component

        // second component
        Calendar c = Calendar.getInstance();
        c.setTime(dateOfIssue);
        firstComponent.append(c.get(Calendar.YEAR));
        firstComponent.append("-");

        // third component
        String[] third = studentId.split("[A-Za-z]");
        String thirdComponent = third[third.length - 1];

        firstComponent.append(thirdComponent);
        return firstComponent.toString();
    }

    /**
     * Smart Card number is associated with Student Id to grantee its uniqueness;
     *
     * @param o input an Object required comparision;
     * @return the comparison results.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SmartCard)) return false;
        SmartCard smartCard = (SmartCard) o;
        return cardnumber != null ? cardnumber.equals(smartCard.cardnumber) : smartCard.cardnumber == null;
    }

    @Override
    public int hashCode() {
        return cardnumber != null ? cardnumber.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "SmartCard{" +
                "card number='" + cardnumber + '\'' +
                ", dateOfIssue=" + dateOfIssue +
                ", expiryDate=" + expiryDate +
                ", expiry=" + expiry +
                '}';
    }
}
