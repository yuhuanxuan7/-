package Task2;

public class Client implements Comparable<Client> {
    private String firstName;
    private String surName;
    private SortedArrayList<Event> choose_event;

    public Client(String firstName, String surName) {
        this.firstName = firstName;
        this.surName = surName;
        this.choose_event = new SortedArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public Event getChoose_event(String ename) {
        /** if the SortedArrayList contains elements,
         *  loop through the SortArrayList:
         *  in the loop, if a event stored shares the same name with the input ename,
         *  return the Event Object in this SortedArrayList;
         *  if not go to else, create a new Event Object and return this new Event Object;
         */
        if (choose_event.size() != 0) {
            for (int i = 0; i < choose_event.size(); i++) {
                if (choose_event.get(i).getEname().equalsIgnoreCase(ename)) {
                    return choose_event.get(i);
                }
            }
        }
        return null;
    }

    /**
     * This method is to judge conditions when a ticket order request is submitted
     * @param e the event used for searching target inside Client's SortedArrayList
     */
    public void purchase_event(Event e, int buy) {
        /* In this situation, a client has already paid for an existing event,
           so the payment status won't be changed
        */
        if (getChoose_event(e.getEname())!=null) {
            Integer new_T = getChoose_event(e.getEname()).getTickets_left() + buy;
            getChoose_event(e.getEname()).setTickets_left(new_T);
        } else {
            /* In this situation, a client never purchased before,
               so a new Event Object with the same name with that input one is created;
               then the information is stored.
             */
            Event newE = new Event(e.getEname(), buy);
            newE.setPaid(true);
            choose_event.insertion(newE);
        }
    }

    /**
     * A method to check whether the client purchased information is correct;
     * @param ename the event name that a client want to cancel;
     * @param cancel the tickets number to cancel/return;
     * @return false if a client did not purchase event before;
     * or the ticket numbers are smaller than it does;
     * else return true;
     */
    public boolean cancel_check(String ename, int cancel) {
        /* The conditional statement should follow this order,
            or a NullPointerException would occur.
         */
        if (
                this.getChoose_event(ename) == null ||
                this.getChoose_event(ename).getTickets_left() < cancel)
            return false;
        else return true;
    }

    /**
     * This method is to judge conditions when a cancellation request is submitted
     * @param e the event used for searching target inside Client's SortedArrayList
     */
    public void cancel_event(Event e, int cancel) {
        Event temp_e = getChoose_event(e.getEname());
        if (cancel_check(temp_e.getEname(), cancel) && temp_e.isPaid()) {
            int integer = temp_e.getTickets_left() - cancel;
              /* There is a condition, a event was ordered by a client before,
                 but this client cancels or return that transaction;
                 personally, I think that event should remain as a record.
               */
                temp_e.setTickets_left(integer);
                System.out.println("cancellation approved");
        } else {
            System.out.println("please check if this client complete payment before");
        }
    }

    @Override
    public String toString() {
        return
                firstName +
                        " " + surName + " "
                        + " ticket ordered: "
                        + choose_event;
    }

    @Override
    public int compareTo(Client c) {
        int result_sur=surName.compareTo(c.surName);
        if (result_sur !=0)
        {return result_sur;}
        else
        {return firstName.compareTo(c.firstName);}
    }

    /** compare two client logically;
     * make it not case sensitive;
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        if (!getFirstName().equalsIgnoreCase(client.getFirstName())) return false;
        return getSurName().equalsIgnoreCase(client.getSurName());
    }

    /**
     * follow a reminder from book <<effective java>>;
     * In case the use of
     * @return a hashcode value;
     */
    @Override
    public int hashCode() {
        int result = getFirstName().hashCode();
        result = 31 * result + getSurName().hashCode();
        result = 31 * result + choose_event.hashCode();
        return result;
    }
}
