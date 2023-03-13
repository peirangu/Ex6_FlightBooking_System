package prog2.exercise6.flight.booking.system;

import static org.junit.Assert.assertEquals;
import java.time.LocalDate;

import org.junit.Test;


public class FlightBookingTest 
{  
    
    @Test
    public void testing_Seat_Booking_Method(){
        int size = 3;
        FlightBooking fb = new FlightBooking(size);

        String [] fullName = {"Matt Jive", "Sally Fields", "Posh Quattro"};
        String [] gender = {"Male", "Female", "Other"};
        int [] age = {43, 32, 21};
        String [] seatsBooked = new String[size];

        for(int i=0; i<size; ++i){
            fb.setPassengerFullName(i, fullName[i]);
            fb.setPassengerGender(i, gender[i]);
            fb.setPassengerAge(i, age[i]);
        }

        fb.setTripSource("1");
        fb.setSourceAirport("1");

        fb.setTripDestination("1", "4");
        fb.setDestinationAirport("1", "4");

        fb.setTripType("2");

        fb.setBookingClass("3");

        for(int i=0; i<size; ++i){
            fb.setFlightSeats(i);
        }

        String sdepart = "2023-04-18";
        LocalDate departingOn = LocalDate.parse(sdepart);

        String returnD = "2023-05-21";
        LocalDate returningOn = LocalDate.parse(returnD);

        fb.setDepartureDate(departingOn);
        fb.setReturnDate(returningOn);

        fb.setDepartingTicketPrice(0, 3);
        
        fb.setReturnTicketPrice();

        fb.setTotalTicketPrice();

        for(int j=0, k=279; j<size; ++j, ++k){
            fb.setTicketNumber(j);
            seatsBooked[j]=fb.getFlightSeats(k);
        }
        
        if(seatsBooked[0].equals("M10EC-Booked") &&
        seatsBooked[1].equals("N1EC-Booked") &&
        seatsBooked[2].equals("N2EC-Booked")){
            assertEquals(1, 1);
        }
        else{
            assertEquals(1, 2);
        }
        
    }
      
}
