package CourseWork;

/**
 * @author YuLun
 * @project_name MutilThread
 * @createTime 2020-04-24 11:03
 */
public class BinarySemaphore extends Semaphore {

    // constructor
    public BinarySemaphore(int initial) {
        // ensure the value of BinarySemaphore be 0 or 1.
        super((initial != 0) ? 1 : 0);
    }

    public final synchronized void V() {
        super.V();
        if (value > 1) value = 1; // cap the value;
    }
}
