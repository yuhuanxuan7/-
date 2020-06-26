package Task2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Ticket_PurchaseIO {
    private static SortedArrayList<Client> clients = new SortedArrayList<>();
    private static SortedArrayList<Event> events = new SortedArrayList<>();

    public static void main(String[] args) {
        file_read();
        main_list();
    }

    /**
     * methods to print main menu
     */
    private static void main_list()  {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the ticket purchase systems");
        System.out.println("Input a letter to choose functions");
        System.out.println("e - to display on the screen the information about all the events");
        System.out.println("c - to display on the screen the information about all the clients");
        System.out.println("b - to update the stored data when tickets are bought by one of the registered clients.");
        System.out.println("r - to update the stored data when a registered client cancels/returns tickets.");
        System.out.println("f - to finish running the program.");
        switch (scanner.next()) {
            case "e":
                event_display();
                break;
            case "c":
                client_display();
                break;
            case "b":
                ticket_purchase();
                break;
            case "r":
                cancellation();
                break;
            case "f":
                System.out.println("Thanks for using");
                printMessage();
                System.exit(0);
                break;
            default:
                System.out.println("invalid input please try again");
                main_list();
        }
        main_list();
    }

    /**
     * To read the content from an input file
     * @throws IOException an exception that the file not exists in target route
     */
    private static void file_read() {
        String input_path = "E:\\IO Test\\Input_Content.txt";
        Scanner s = null;
        try {
            s = new Scanner(new FileReader(input_path));
        } catch (FileNotFoundException e) {
            System.out.println("check input route first");
        }
        // Read Event data from the input file
        int time=s.nextInt();
        for (int i = 0; i <time; i++) {
            s.nextLine();
            String temp = s.nextLine();
            String temp2 = s.next();
            Event event = new Event(temp,Integer.parseInt(temp2));
            events.insertion(event);
        }
         // Read Client data from the input file
        int count_client=s.nextInt();
        for (int i = 0; i < count_client; i++) {
            String firstName =s.next();
            String surName =s.next();
            Client client = new Client(firstName,surName);
            clients.insertion(client);
        }
    }

    /**
     * To display the detail of events
     */
    private static void event_display(){
        for (int i = 0; i < events.size(); i++) {
            System.out.println(events.get(i));
        }
    }

    /**
     * To display the detail of clients
     */
    private static void client_display(){
        for (int i = 0; i < clients.size(); i++) {
            System.out.println(clients.get(i));
        }
    }

    /**
     * To get target client via the first name and surname the users input
     * @return if target client exists in SortedArrayList, return that Client Object,
     * else return null;
     */
    private static Client getClients(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("please input a Client's first name");
        String firstName=scanner.next();
        System.out.println("please input a Client's surname");
        String surName = scanner.next();
        Client check_c = new Client(firstName,surName);
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).equals(check_c))
                return clients.get(i);
        }
        return null;
    }

    /**
     * To get target Event by event name;
     * @param ename an input event's name to locate target Event
     * @return if searching event exist, return that Event Object,
     * if not return null;
     */
    private static Event getEvent(String ename){
        /* Ensure the event's name is complete same with the name stored in SortedArrayList,
           trim() method is to avoid effect from space or other irrelevant character
         */
        String compare_name=ename.trim();
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getEname().trim().equalsIgnoreCase(compare_name))
                return events.get(i);
        }
            return null;
    }

    /**
     * To judge the ticket purchasing process;
     * Assumptions: each client can only purchase one ticket each time
     */
    private static void ticket_purchase() {
        try{
        Scanner scanner = new Scanner(System.in);
        String output_path = "E:\\onedrive_files\\OneDrive - Newcastle University\\Data Structure&algorithm\\CourseWork_part2\\out_put.txt";
        PrintWriter f = new PrintWriter(output_path);
        Client check_c = getClients();
        if (check_c == null) {
            System.out.println("Client does not exist");
            main_list();
        } else {
            System.out.println("please input the event to purchase");
            String ename = scanner.nextLine();
            Event check_e = getEvent(ename);
            if (check_e == null) {
                System.out.println("Invalid event");
                main_list();
            } else {
                boolean in = true;
                int buy=0;
                while (in){
                    try {
                        System.out.println("please input how many ticket to buy");
                        buy = scanner.nextInt();
                        in=false;
                    }
                    catch (Exception e){
                        System.out.println("invalid input");
                        scanner.nextLine();
                    }
                }
                if (check_e.ticket_available(buy)) {
                    // variable buy is how many ticket a client buy for each event
                    Integer tickets_left = check_e.getTickets_left() - buy;
                    check_e.setTickets_left(tickets_left);
                    /* update purchasing information in a valid client accordingly
                    Assume: Once the order from one client is available,
                    the client would complete the payment at the same time
                     */
                    check_c.purchase_event(check_e, buy);
                    System.out.println("transaction approved");
                } else {
                    System.out.println("a note has been  printed to informing the client that the tickets are not available");
                    f.println("Error:");
                    f.println("Dear " + check_c.getSurName() + " " + check_c.getFirstName() + " :");
                    f.println("Your transaction orders " + buy + " tickets,");
                    f.println("but only " + check_e.getTickets_left() + " left.");
                    f.println("we are sorry for any inconvenience.");
                    f.println("Ticket Center");
                    f.close();
                }
            }
        }}
        catch (IOException e){
            System.out.println("Error, please check output route first");
        }
        main_list();
    }

    /**
     * To judge the ticket cancellation process
     * Assume a client can cancel tickets for one event only per one transaction
     */
    private static void cancellation() {
            Scanner scanner = new Scanner(System.in);
            Client check_c = getClients();
            if (check_c == null) {
                System.out.println("Client does not exist, please check");
                main_list();
            } else {
                System.out.println("please input the event to return");
                String ename = scanner.nextLine();
                Event check_e = getEvent(ename);
                if (check_e == null) {
                    System.out.println("Event not exists");
                    main_list();
                } else {
                    boolean in = true;
                    int cancel = 0;
                    while (in) {
                        try {
                            System.out.println("please input how many tickets to return");
                            cancel = scanner.nextInt();
                            in = false;
                        } catch (Exception e) {
                            System.out.println("invalid input");
                            scanner.nextLine();
                        }
                    }
                    if (check_c.cancel_check(ename, cancel)) {
                        Integer i = check_e.getTickets_left() + cancel;
                        check_e.setTickets_left(i);
                        check_c.cancel_event(check_e, cancel);
                    } else System.out.println("check order information first");
                }
            }
            main_list();
        }

    /**
     * To output the message to a txt file
     */
    private static void printMessage() {
        try {
            String output_path = "E:\\IO Test\\out_put2.txt";
            PrintWriter f = new PrintWriter(output_path);
            f.println(events.size());
            for (int i = 0; i < events.size(); i++) {
                f.println(events.get(i).getEname());
                f.println(events.get(i).getTickets_left());
            }
            f.println();
            f.println(clients.size());
            for (int i = 0; i < clients.size(); i++) {
                f.print(clients.get(i).getFirstName());
                f.print(" ");
                f.println(clients.get(i).getSurName());
            }
            f.close();
        }
        catch (IOException e){
            System.out.println("file not find, check first");
        }
    }
}
