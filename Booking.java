public class Booking {
    private String passengerName;
    private Flight flight;
    private String flightClass;
    private double price;
    private String bookedBy;

    public Booking(String pname, Flight f, String cls, double p, String user){
        passengerName=pname; flight=f; flightClass=cls; price=p; bookedBy=user;
    }

    public Flight getFlight(){ return flight; }
    public String getPassengerName(){ return passengerName; }
    public String getBookedBy(){ return bookedBy; }

    public String getInfo(){
        return passengerName+" | "+flight.getFlightNo()+" | "+flight.getSource()+"->"+flight.getDestination()
                +" | Class: "+flightClass+" | Price: "+price;
    }
}
