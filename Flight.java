import java.util.ArrayList;

public class Flight {
    private String flightNo, source, destination;
    private int ecoSeats, busSeats, firstSeats;
    private double ecoPrice, busPrice, firstPrice;
    private ArrayList<String> passengers = new ArrayList<>();
    private ArrayList<Integer> ratings = new ArrayList<>();

    public Flight(String fno,String src,String dst,int eco,int bus,int first,double ep,double bp,double fp){
        flightNo=fno; source=src; destination=dst;
        ecoSeats=eco; busSeats=bus; firstSeats=first;
        ecoPrice=ep; busPrice=bp; firstPrice=fp;
    }

    public String getFlightNo(){ return flightNo; }
    public String getSource(){ return source; }
    public String getDestination(){ return destination; }

    public int getAvailableSeats(String cls){
        switch(cls.toLowerCase()){
            case "economy": return ecoSeats;
            case "business": return busSeats;
            case "first": return firstSeats;
        }
        return 0;
    }

    public double getPrice(String cls){
        switch(cls.toLowerCase()){
            case "economy": return ecoPrice;
            case "business": return busPrice;
            case "first": return firstPrice;
        }
        return 0;
    }

    public boolean bookSeat(String cls, String pname){
        switch(cls.toLowerCase()){
            case "economy":
                if(ecoSeats>0){ ecoSeats--; passengers.add(pname); return true; } break;
            case "business":
                if(busSeats>0){ busSeats--; passengers.add(pname); return true; } break;
            case "first":
                if(firstSeats>0){ firstSeats--; passengers.add(pname); return true; } break;
        }
        return false;
    }

    public void cancelBooking(String pname){
        if(passengers.contains(pname)){
            passengers.remove(pname);
            if(ecoSeats<5) ecoSeats++;
            else if(busSeats<5) busSeats++;
            else if(firstSeats<5) firstSeats++;
        }
    }

    public ArrayList<String> getPassengers(){ return passengers; }

    public void addRating(int r){ if(r>=1 && r<=5) ratings.add(r); }
    public double getAverageRating(){
        if(ratings.isEmpty()) return 0;
        double sum=0; for(int r: ratings) sum+=r;
        return sum/ratings.size();
    }
}
