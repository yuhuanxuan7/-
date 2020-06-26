package Main.SmartCard;

import Main.Student.*;

import java.util.HashMap;
import java.util.Map;

import static Main.Student.StudentFactory.*;

/**
 * @Description:
 * @Author: Jacob Ninja
 * @Date:
 */
public final class CardFactory {

    private String studentId;

    CardFactory(String studentId) {
        this.studentId = studentId;
    }

    private static final Map<String, SmartCard> cardMap = new HashMap<>();

    /**
     * Ensure the uniqueness of Smart card for each student.
     *
     * @return the instance.
     */
    public static SmartCard getInstance(String studentId, StudentData data, String type) {
        SmartCard instance = cardMap.get(studentId);
        /*
          In this situation, the cardMap is to judge whether the smart card is unique for each student Id.
          If the input Student Object already has a Smart Card, just return the SmartCard it already has.
         */
        if (data.getAge() > 17 && type.equalsIgnoreCase(Undergraduate)) {
            instance = new SmartCard(studentId, data, type);
        } else if (type.equalsIgnoreCase(Postgraduate_Research) && data.getAge() >= 20) {
            instance = new SmartCard(studentId, data, type);
        } else if (type.equalsIgnoreCase(Postgraduate_Taught) && data.getAge() >= 20) {
            instance = new SmartCard(studentId, data, type);
        } else {
            throw new IllegalArgumentException("Age or Type information is invalid");
        }
        cardMap.put(studentId, instance); //put this student in students map.
        return instance;
    }

}
