import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class ReservationSystem {
    public ArrayList<User> users = new ArrayList<>();
    public ArrayList<Flight> flights = new ArrayList<>();
    public ArrayList<Booking> bookings = new ArrayList<>();
    public User currentUser = null;
    public boolean isAdmin = false;

    public ReservationSystem() {
        // Sample flights
        flights.add(new Flight("F101","Dhaka","Chittagong",5,2,1,3000,6000,10000));
        flights.add(new Flight("F102","Dhaka","Sylhet",5,2,1,3500,6500,11000));
        flights.add(new Flight("F103","Ctg","CoxBazar",3,2,1,2500,5500,9000));
        flights.add(new Flight("F104","Sylhet","Dhaka",4,2,1,3200,6200,10200));
        flights.add(new Flight("F105","CoxBazar","Dhaka",3,1,1,2800,5800,9500));
        flights.add(new Flight("F106","Khulna","Dhaka",5,2,1,3100,6100,10000));
        flights.add(new Flight("F107","Barishal","Dhaka",4,2,1,3000,6000,9900));
        flights.add(new Flight("F108","Jessore","Ctg",5,2,1,3300,6300,10200));
        flights.add(new Flight("F109","Dhaka","Khulna",5,2,1,3400,6400,10300));
        flights.add(new Flight("F110","Ctg","Dhaka",4,2,1,3200,6200,10100));
        flights.add(new Flight("F111","Sylhet","Ctg",3,2,1,2800,5800,9400));
        flights.add(new Flight("F112","Dhaka","Barishal",5,2,1,3500,6500,10800));
        flights.add(new Flight("F113","Barishal","Ctg",4,2,1,3100,6100,9700));
        flights.add(new Flight("F114","Dhaka","Jessore",5,2,1,3300,6300,10250));
        flights.add(new Flight("F115","Khulna","Ctg",3,1,1,2900,5900,9500));
    }

    public void registerMultiple() {
        int n = Integer.parseInt(JOptionPane.showInputDialog("How many users to register?"));
        for (int i = 0; i < n; i++) {
            String username = JOptionPane.showInputDialog("Enter username for user " + (i + 1) + ":");
            String password = JOptionPane.showInputDialog("Enter password:");
            String nid = JOptionPane.showInputDialog("Enter NID:");
            users.add(new User(username, password, nid, false));
            JOptionPane.showMessageDialog(null, "User " + username + " registered successfully!");
        }
        JOptionPane.showMessageDialog(null,"All users registered successfully!");
    }

    public void login() {
        String username = JOptionPane.showInputDialog("Enter username:");
        String password = JOptionPane.showInputDialog("Enter password:");
        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                currentUser = u;
                JOptionPane.showMessageDialog(null, "Login successful! Welcome " + username);
                showReservationMenu();
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Invalid credentials.");
    }

    public void showReservationMenu() {
        JFrame frame = new JFrame("Reservation Menu - User: " + currentUser.getUsername());
        frame.setSize(700, 700);
        frame.setLayout(new GridLayout(10, 2, 10, 10));

        String[] buttons = {
                 "View Flights","Search Flights","Book Flight","Emergency Book","Cancel Booking","View My Bookings",
                "Show Flight Passengers","View Profile","Change Password","Update NID",
                "Add Favorite Flight","Remove Favorite Flight","Show Favorite Flights","Rate Flight",
                "Admin Login","Add Flight","Remove Flight","View All Users","Logout","Exit System"
        };

        for (String bname : buttons) {
            JButton btn = new JButton(bname);
            btn.addActionListener(e -> handleOption(bname));
            frame.add(btn);
        }

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    public void showAdminMenu(){
        JFrame frame = new JFrame("Admin Menu");
        frame.setSize(500,500);
        frame.setLayout(new GridLayout(5,1,10,10));

        String[] buttons = {"Add Flight","Remove Flight","View All Users","Logout","Exit System"};
        for(String bname: buttons){
            JButton btn = new JButton(bname);
            btn.addActionListener(e->handleAdminOption(bname));
            frame.add(btn);
        }

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    public void handleAdminOption(String option){
    switch(option){
        case "Add Flight":
            if(isAdmin) addFlight();
            else JOptionPane.showMessageDialog(null,"Only admin can add flights.");
            break;
        case "Remove Flight":
            if(isAdmin) removeFlight();
            else JOptionPane.showMessageDialog(null,"Only admin can remove flights.");
            break;
        case "View All Users":
            if(isAdmin) viewAllUsers();
            else JOptionPane.showMessageDialog(null,"Only admin can view users.");
            break;
        case "Logout":
            isAdmin=false;
            JOptionPane.showMessageDialog(null,"Admin logged out.");
            break;
        case "Exit System":
            System.exit(0);
            break;
    }
}


    public void handleOption(String option){
        switch(option){
            case "View Flights": viewFlights(); break;
            case "Search Flights": searchFlights(); break;
            case "Book Flight": bookMultiplePassengers(false); break;
            case "Emergency Book": bookMultiplePassengers(true); break;
            case "Cancel Booking": cancelBooking(); break;
            case "View My Bookings": viewMyBookings(); break;
            case "Show Flight Passengers": showFlightPassengers(); break;
            case "View Profile": viewProfile(); break;
            case "Change Password": changePassword(); break;
            case "Update NID": updateNid(); break;
            case "Add Favorite Flight": addFavoriteFlight(); break;
            case "Remove Favorite Flight": removeFavoriteFlight(); break;
            case "Show Favorite Flights": showFavoriteFlights(); break;
            case "Rate Flight": rateFlight(); break;
            case "Add Flight": addFlight(); break;
            case "Remove Flight": removeFlight(); break;
            case "View All Users": viewAllUsers(); break;
            case "Logout": logout(); break;
            case "Exit System": System.exit(0); break;
            case "Admin Login":
    String adminUser = JOptionPane.showInputDialog("Enter admin username:");
    String adminPass = JOptionPane.showInputDialog("Enter admin password:");
    if(adminUser.equals("admin") && adminPass.equals("admin123")) {
        // Create admin user object
        User admin = new User("admin", "admin123", "0000", true); // NID 0000, isAdmin = true
        currentUser = admin;    // Set currentUser as admin
        isAdmin = true;         // Flag
        JOptionPane.showMessageDialog(null,"Admin login successful!");
        showAdminMenu();
    } else {
        JOptionPane.showMessageDialog(null,"Invalid admin credentials.");
    }
    break;



        }
    }

    // ---- METHODS ----

    public void viewFlights() {
        StringBuilder sb = new StringBuilder();
        for (Flight f : flights) {
            sb.append(f.getFlightNo() + " | " + f.getSource() + " -> " + f.getDestination()
                    + " | Economy: " + f.getAvailableSeats("Economy") + " (Tk " + f.getPrice("Economy") + ")"
                    + ", Business: " + f.getAvailableSeats("Business") + " (Tk " + f.getPrice("Business") + ")"
                    + ", First: " + f.getAvailableSeats("First") + " (Tk " + f.getPrice("First") + ")"
                    + " | Rating: " + String.format("%.1f", f.getAverageRating()) + "\n");
        }
        JTextArea ta = new JTextArea(sb.toString());
        ta.setEditable(false);
        JOptionPane.showMessageDialog(null, new JScrollPane(ta), "All Flights", JOptionPane.INFORMATION_MESSAGE);
    }

    public void searchFlights() {
        String src = JOptionPane.showInputDialog("Enter source:");
        String dst = JOptionPane.showInputDialog("Enter destination:");
        StringBuilder sb = new StringBuilder();
        for (Flight f : flights) {
            if (f.getSource().equalsIgnoreCase(src) && f.getDestination().equalsIgnoreCase(dst)) {
                sb.append(f.getFlightNo() + " | " + f.getSource() + " -> " + f.getDestination()
                        + " | Economy: " + f.getAvailableSeats("Economy") + " (Tk " + f.getPrice("Economy") + ")"
                        + ", Business: " + f.getAvailableSeats("Business") + " (Tk " + f.getPrice("Business") + ")"
                        + ", First: " + f.getAvailableSeats("First") + " (Tk " + f.getPrice("First") + ")\n");
            }
        }
        if (sb.length() == 0) JOptionPane.showMessageDialog(null, "No flights found.");
        else {
            JTextArea ta = new JTextArea(sb.toString());
            ta.setEditable(false);
            JOptionPane.showMessageDialog(null, new JScrollPane(ta), "Search Results", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void bookMultiplePassengers(boolean emergency) {
        String fno = JOptionPane.showInputDialog("Enter Flight Number to book:");
        Flight chosen = null;
        for (Flight f : flights) { if (f.getFlightNo().equalsIgnoreCase(fno)) { chosen = f; break; } }
        if (chosen == null) { JOptionPane.showMessageDialog(null, "Flight not found."); return; }

        int n = Integer.parseInt(JOptionPane.showInputDialog("How many passengers to book?"));
        for (int i = 0; i < n; i++) {
            String pname = JOptionPane.showInputDialog("Enter passenger name " + (i + 1) + ":");
            String cls = JOptionPane.showInputDialog("Enter class (Economy/Business/First):");
            double price = chosen.getPrice(cls);
            if (emergency) price *= 1.5;
            if (chosen.bookSeat(cls, pname)) {
                bookings.add(new Booking(pname, chosen, cls, price, currentUser.getUsername()));
                JOptionPane.showMessageDialog(null, "Booking successful for " + pname + "! Price: " + price + " Tk");
            } else JOptionPane.showMessageDialog(null, "No seats available in " + cls + " class for " + pname);
        }
    }

    public void cancelBooking() {
        String fno = JOptionPane.showInputDialog("Enter Flight Number to cancel:");
        Flight f = null;
        for (Flight flight : flights) if (flight.getFlightNo().equalsIgnoreCase(fno)) { f = flight; break; }
        if (f == null) { JOptionPane.showMessageDialog(null, "Flight not found."); return; }

        ArrayList<Booking> toCancel = new ArrayList<>();
        for (Booking b : bookings)
            if (b.getFlight() == f && b.getBookedBy().equals(currentUser.getUsername()))
                toCancel.add(b);

        if (toCancel.isEmpty()) { JOptionPane.showMessageDialog(null, "No bookings found for this flight."); return; }

        for (Booking b : toCancel) { f.cancelBooking(b.getPassengerName()); bookings.remove(b); }
        JOptionPane.showMessageDialog(null, "Bookings cancelled successfully.");
    }

    public void viewMyBookings() {
        StringBuilder sb = new StringBuilder();
        for (Booking b : bookings) if (b.getBookedBy().equals(currentUser.getUsername())) sb.append(b.getInfo() + "\n");
        if (sb.length() == 0) sb.append("No bookings found.");
        JTextArea ta = new JTextArea(sb.toString());
        ta.setEditable(false);
        JOptionPane.showMessageDialog(null, new JScrollPane(ta), "My Bookings", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showFlightPassengers() {
        String fno = JOptionPane.showInputDialog("Enter Flight Number:");
        Flight f = null;
        for (Flight flight : flights) if (flight.getFlightNo().equalsIgnoreCase(fno)) { f = flight; break; }
        if (f == null) { JOptionPane.showMessageDialog(null, "Flight not found."); return; }
        ArrayList<String> p = f.getPassengers();
        if (p.isEmpty()) JOptionPane.showMessageDialog(null, "No passengers yet.");
        else {
            JTextArea ta = new JTextArea(String.join("\n", p));
            ta.setEditable(false);
            JOptionPane.showMessageDialog(null, new JScrollPane(ta), "Passengers of " + fno, JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void viewProfile() {
        JOptionPane.showMessageDialog(null, "Username: " + currentUser.getUsername()
                + "\nNID: " + currentUser.getNid()
                + "\nFavorites: " + currentUser.getFavorites());
    }

    public void changePassword() {
        String p = JOptionPane.showInputDialog("Enter new password:");
        currentUser.setPassword(p);
        JOptionPane.showMessageDialog(null, "Password updated.");
    }

    public void updateNid() {
        String n = JOptionPane.showInputDialog("Enter new NID:");
        currentUser.setNid(n);
        JOptionPane.showMessageDialog(null, "NID updated.");
    }

    public void addFavoriteFlight() {
        String fno = JOptionPane.showInputDialog("Enter Flight Number to add as favorite:");
        currentUser.addFavorite(fno);
        JOptionPane.showMessageDialog(null, "Favorite added.");
    }

    public void removeFavoriteFlight() {
        String fno = JOptionPane.showInputDialog("Enter Flight Number to remove from favorites:");
        currentUser.removeFavorite(fno);
        JOptionPane.showMessageDialog(null, "Favorite removed.");
    }

    public void showFavoriteFlights() {
        ArrayList<String> fav = currentUser.getFavorites();
        if (fav.isEmpty()) JOptionPane.showMessageDialog(null, "No favorites.");
        else JOptionPane.showMessageDialog(null, String.join("\n", fav));
    }

    public void rateFlight() {
        String fno = JOptionPane.showInputDialog("Enter Flight Number to rate:");
        Flight f = null;
        for (Flight flight : flights) if (flight.getFlightNo().equalsIgnoreCase(fno)) { f = flight; break; }
        if (f == null) { JOptionPane.showMessageDialog(null, "Flight not found."); return; }
        int r = Integer.parseInt(JOptionPane.showInputDialog("Enter rating (1-5):"));
        f.addRating(r);
        JOptionPane.showMessageDialog(null, "Rating added.");
    }

    public void adminLogin() {
        String uname = JOptionPane.showInputDialog("Enter admin username:");
        String pass = JOptionPane.showInputDialog("Enter admin password:");
        for (User u : users) {
            if (u.getUsername().equals(uname) && u.getPassword().equals(pass) && u.isAdmin()) {
                currentUser = u;
                JOptionPane.showMessageDialog(null, "Admin login successful!");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Invalid admin credentials.");
    }

    public void addFlight() {
        if (!currentUser.isAdmin()) { JOptionPane.showMessageDialog(null, "Only admin can add flights."); return; }
        String fno = JOptionPane.showInputDialog("Flight Number:");
        String src = JOptionPane.showInputDialog("Source:");
        String dst = JOptionPane.showInputDialog("Destination:");
        int eco = Integer.parseInt(JOptionPane.showInputDialog("Economy Seats:"));
        int bus = Integer.parseInt(JOptionPane.showInputDialog("Business Seats:"));
        int first = Integer.parseInt(JOptionPane.showInputDialog("First Seats:"));
        double ep = Double.parseDouble(JOptionPane.showInputDialog("Economy Price:"));
        double bp = Double.parseDouble(JOptionPane.showInputDialog("Business Price:"));
        double fp = Double.parseDouble(JOptionPane.showInputDialog("First Price:"));
        flights.add(new Flight(fno, src, dst, eco, bus, first, ep, bp, fp));
        JOptionPane.showMessageDialog(null, "Flight added.");
    }

    public void removeFlight() {
        if (!currentUser.isAdmin()) { JOptionPane.showMessageDialog(null, "Only admin can remove flights."); return; }
        String fno = JOptionPane.showInputDialog("Flight Number to remove:");
        Flight toRemove = null;
        for (Flight f : flights) if (f.getFlightNo().equalsIgnoreCase(fno)) { toRemove = f; break; }
        if (toRemove != null) { flights.remove(toRemove); JOptionPane.showMessageDialog(null, "Flight removed."); }
        else JOptionPane.showMessageDialog(null, "Flight not found.");
    }

    public void viewAllUsers() {
        if (!currentUser.isAdmin()) { JOptionPane.showMessageDialog(null, "Only admin can view users."); return; }
        StringBuilder sb = new StringBuilder();
        for (User u : users) sb.append(u.getUsername() + " | NID: " + u.getNid() + " | Admin: " + u.isAdmin() + "\n");
        JTextArea ta = new JTextArea(sb.toString());
        ta.setEditable(false);
        JOptionPane.showMessageDialog(null, new JScrollPane(ta), "All Users", JOptionPane.INFORMATION_MESSAGE);
    }

    public void logout() {
        currentUser = null;
        JOptionPane.showMessageDialog(null, "Logged out.");
    }

    public static void main(String[] args) {
        ReservationSystem system = new ReservationSystem();
        system.registerMultiple();
        system.login();
    }
}


