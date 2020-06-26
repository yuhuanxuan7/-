package Task2;

public class Event implements Comparable<Event> {
    private String ename;
    private Integer tickets_left;
    private boolean isPaid;

    /**
     * The constructor needs event name and tickets situation
     * @param ename event name;
     * @param tickets_left how many tickets available; when in Client Objects representing ticket numbers a client purchased;
     */
    public Event(String ename, Integer tickets_left) {
        this.ename = ename;
        this.tickets_left = tickets_left;
        isPaid=false;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    /**
     * To know whether this event is paid by a client
     * @return true if is paid, false if not paid
     */
    public boolean isPaid() {
        return isPaid;
    }

    /**
     * Once the event is paid, set isPaid to true;
     * @param paid a boolean variable
     */
    public void setPaid(boolean paid) {
        this.isPaid = paid;
    }

    public Integer getTickets_left() {
        return tickets_left;
    }

    public void setTickets_left(Integer tickets_left) {
        this.tickets_left = tickets_left;
    }

    @Override
    public int compareTo(Event e) {
        return ename.compareTo(e.ename);
    }

    @Override
    public String toString() {
        return
                "event name: " + ename  +
                ", tickets: " + tickets_left ;
    }

    /**
     * To check whether this event has tickets left
     * @return the condition whether tickets left
     */
    public boolean ticket_available(int tickets_buy){
        if (this.tickets_left>=tickets_buy){
            return true;
        } else return false;
    }



}
