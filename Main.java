import javax.swing.*;

public class Main {
    public static void main(String[] args){
        ReservationSystem system = new ReservationSystem();
        while(true){
            String[] options = {"Register","Login","Exit"};
            int ch = JOptionPane.showOptionDialog(null,
                    "Welcome to Airline Reservation System",
                    "Main Menu",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]);

            if(ch == 0) system.registerMultiple();
            else if(ch == 1) system.login();
            else break;
        }
    }
}


