package prog2.exercise6.flight.booking.system;


import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class FlightBooking {
    enum BookingClass{
        FIRST,BUSINESS,ECONOMY
    }
    enum TripSource{
        NANJING, BEIJING, SHANGHAI, OULU, HELSINKI, PARIS
    }
    enum TripDestination{
        NANJING, BEIJING, SHANGHAI, OULU, HELSINKI, PARIS
    }
    enum TripType{
        ONE_WAY,RETURN
    }

    enum SourceAirport{
        NANJING_LUKOU_INTERNATIONAL_AIRPORT, BEIJING_CAPITAL_INTERNATIONAL_AIRPORT,
        SHANGHAI_PUDONG_INTERNATIONAL_AIRPORT, OULU_AIRPORT,HELSINKI_AIRPORT,
        PARIS_CHARLES_DE_GAULLE_AIRPORT
    }
    enum DestinationAirport{
        NANJING_LUKOU_INTERNATIONAL_AIRPORT, BEIJING_CAPITAL_INTERNATIONAL_AIRPORT,
        SHANGHAI_PUDONG_INTERNATIONAL_AIRPORT, OULU_AIRPORT,HELSINKI_AIRPORT,
        PARIS_CHARLES_DE_GAULLE_AIRPORT
    }
    private String doi;
    private BookingClass bookingClass;
    private TripType tripType;
    private  final String flightCompany = "Flights-of-Fancy";
    private String flightID;
    private String [] passengerFullName;
    private String  [] passengerGender ;
    private int [] passengerAge ;

    private  TripSource tripSource;
    private  SourceAirport sourceAirport;
    private  TripDestination tripDestination;
    private  DestinationAirport destinationAirport;
    private double departingTicketPrice;
    private double returnTicketPrice;
    private double totalTicketPrice;
    private String [] ticketNumber;
    private LocalDate departureDate;
    private LocalDate returnDate;
    private LocalDate oldDate=null;
    private int childPassengers;
    private int adultPassengers;
    private int totalPassengers;
    private boolean testChange=false ;
    private int number;
    private ArrayList<String> flightSeats = new ArrayList<>(410);
    private String [] initialBook;



    public FlightBooking(String passengerFullName,LocalDate depart,LocalDate returnDate,int childPassengers, int adultPassengers){
        this.passengerFullName = new String[1];
        this.passengerFullName[0] = passengerFullName;
        setDepartureDate(depart);
        setReturnDate(returnDate);
        this.childPassengers = childPassengers;
        this.adultPassengers = adultPassengers;
        setTotalPassengers(childPassengers,adultPassengers);
        setFlightID();
    }
    public FlightBooking(int number){
        this.passengerFullName = new String[number];
        this.passengerGender = new String[number];
        this.passengerAge = new int[number];
        this.ticketNumber = new String[number];
        this.initialBook = new String[number];
        this.number = number;
        setInitialBook();
    }




    private void bookSeats(int index){
        switch (bookingClass){
            case FIRST:
            initialBook[index] = flightSeats.get(32+index);
            flightSeats.set(32+index,String.valueOf(index+1)+"F-Booked");
            break;
            case BUSINESS:
            initialBook[index] = flightSeats.get(118+index);
            flightSeats.set(118+index,String.valueOf(index+1) + "B-Booked");
            break;
            case ECONOMY:
            initialBook[index] = flightSeats.get(279+index);
            flightSeats.set(279+index,initialBook[index]+"-Booked");
        }
    }
    private void setInitialBook(){
        int i=0;
        for (i=0;i<100;++i){
            if (i<32){
                flightSeats.add(i,String.valueOf(i+1)+"F-Booked");
            }
            else {
                flightSeats.add(i,String.valueOf(i+1)+"F");
            }
        }
        for(i=100;i<150;++i){
            if (i<118) {
                flightSeats.add(i, String.valueOf(i + 1) + "B-Booked");
            }else {
                flightSeats.add(i, String.valueOf(i + 1) + "B");
            }
        }
        for (int j=0;j<26;j++){
            char c = (char)(j+65);
            for (i=0;i<10;i++){
                if (((j*10)+(i+1))<130){
                    flightSeats.add(((j*10)+i+150),c+String.valueOf(i+1)+"EC-Booked");
                }else {
                    flightSeats.add(((j*10)+i+150),c+String.valueOf(i+1)+"EC");
                }
            }
        }

    }
    public void setFlightSeats(int index){
        bookSeats(index);
    }
    public String getFlightSeats(int index) {
        return flightSeats.get(index);
    }


    public void reserveTickets(int number){
        Scanner scanner = new Scanner(System.in);
        for (int repeat =number;repeat>0;--repeat) {
            System.out.println("Dear passenger please input the following messages for the No."+(number+1-repeat)+"passenger");
            System.out.println("Please enter Your FullName :");
            this.passengerFullName[number-repeat] = scanner.nextLine();
            System.out.println("Please enter Your gender(Male, Female or Other):");
            this.passengerGender[number-repeat]= scanner.nextLine();
            System.out.println("Please enter Your age:");
            this.passengerAge[number-repeat] =scanner.nextInt();
            scanner.nextLine();
        }
        System.out.println("\n\nPlease input some message for all people");
        System.out.println("Please enter The time you depart (in the form yyyy-mm-dd) :");
        String departString = scanner.nextLine();
        this.departureDate = LocalDate.parse(departString);
        System.out.println("Please enter The time you return (in the form yyyy-mm-dd) :");
        String returnString = scanner.nextLine();
        LocalDate returnDate = LocalDate.parse(returnString);
        setReturnDate(returnDate);
        System.out.println("Please enter ChildrenPassengers:");
        this.childPassengers = scanner.nextInt();
        System.out.println("Please enter AdultPassengers: ");
        this.adultPassengers = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Please choice the BookingClass(1.First;2.Business;3.Economy) NUMBER in STRING form:");
        String bookingClass =scanner.nextLine();
        setBookingClass(bookingClass);
        System.out.println("Please choice the TripType(1.One way;2.Return) NUMBER in STRING form:");
        String tripType =scanner.nextLine();
        setTripType(tripType);
        System.out.println("Please choice the TripSource( 1.Nanjing, 2.Beijing, 3.Oulu, 4.Helsinki) NUMBER in STRING form:");
        String tripSource =scanner.nextLine();
        setTripSource(tripSource);
        System.out.println("Please choice the TripDestination( 1.Nanjing, 2.Beijing, 3.Oulu, 4.Helsinki Not be the same as the TripSource) NUMBER in STRING form:(Number of tripSource,Number of tripDestination)");
        String tripDestination =scanner.nextLine();
        setTripDestination(tripSource,tripDestination);
        for (int repeat =number;repeat>0;--repeat){
            setTicketNumber(number-repeat);
            setFlightSeats(number-repeat);
        }
        setDepartingTicketPrice(childPassengers,adultPassengers);
        setReturnTicketPrice();
        setTotalTicketPrice();
    }


    public void setBookingClass(String bookingClass) {
        switch(bookingClass){
            case "1":
                this.bookingClass = BookingClass.FIRST;
                break;
            case "2":
                this.bookingClass = BookingClass.BUSINESS;
                break;
            case "3":
                this.bookingClass = BookingClass.ECONOMY;
                break;
            default:
                System.out.println("There was no match");
        }
    }
    public void setTripType(String tripType) {
        switch (tripType){
            case "1":
                this.tripType = TripType.ONE_WAY;
                break;
            case "2":
                this.tripType = TripType.RETURN;
                break;
            default:
                System.out.println("There was no match");
        }
    }
    public void setTripSource(String tripSource) {
        switch(tripSource){
            case "1":
                this.tripSource = TripSource.NANJING;
                this.sourceAirport = SourceAirport.NANJING_LUKOU_INTERNATIONAL_AIRPORT;
                break;
            case "2":
                this.tripSource = TripSource.BEIJING;
                this.sourceAirport =SourceAirport.BEIJING_CAPITAL_INTERNATIONAL_AIRPORT;
                break;
            case "3":
                this.tripSource = TripSource.OULU;
                this.sourceAirport =SourceAirport.OULU_AIRPORT;

                break;
            case "4":
                this.tripSource = TripSource.HELSINKI;
                this.sourceAirport =SourceAirport.HELSINKI_AIRPORT;
                break;
            default:
                System.out.println("There was no match");
                break;
        }
    }
    public void setSourceAirport(String sourceAirport) {
        switch (sourceAirport){
            case "1":
                this.sourceAirport = SourceAirport.NANJING_LUKOU_INTERNATIONAL_AIRPORT;
                break;
            case "2":
                this.sourceAirport =SourceAirport.BEIJING_CAPITAL_INTERNATIONAL_AIRPORT;
                break;
            case "3":
                this.sourceAirport =SourceAirport.OULU_AIRPORT;
                break;
            case "4":
                this.sourceAirport =SourceAirport.HELSINKI_AIRPORT;
                break;
            default:
                System.out.println("There was no match");
                break;
        }
    }
    public void setTripDestination(String tripSource, String tripDestination){

        if (tripDestination.equals(tripSource)){
            System.out.println("The tripDestination can't be the same as tripSource");
            System.out.println("Please reselect the tripDestination( 1.Nanjing, 2.Beijing, 3.Oulu, 4.Helsinki Not be the same as the TripSource)NUMBER in STRING form:(Number of tripSource,Number of tripDestination)");
            Scanner scanner = new Scanner(System.in);
            tripDestination = scanner.nextLine();
            setTripDestination(tripSource,tripDestination);
        }else {

            String a = tripSource + tripDestination;
            switch (tripDestination) {
                case "1":
                    this.tripDestination = TripDestination.NANJING;
                    this.destinationAirport = destinationAirport.NANJING_LUKOU_INTERNATIONAL_AIRPORT;
                    break;
                case "2":
                    this.tripDestination = TripDestination.BEIJING;
                    this.destinationAirport = destinationAirport.BEIJING_CAPITAL_INTERNATIONAL_AIRPORT;
                    break;
                case "3":
                    this.tripDestination = TripDestination.OULU;
                    this.destinationAirport = destinationAirport.OULU_AIRPORT;

                    break;
                case "4":
                    this.tripDestination = TripDestination.HELSINKI;
                    this.destinationAirport = destinationAirport.HELSINKI_AIRPORT;
                    break;
                default:
                    System.out.println("There was no match");
                    break;
            }
            if (a.equals("12") || a.equals("21") || a.equals("34") || a.equals("43")) {
                this.doi = "DOM";
            } else {
                this.doi = "INT";
            }
        }
    }
    public void setDestinationAirport(String sourceAirport,String destinationAirport) {
        switch (destinationAirport){
            case "1":
                this.sourceAirport = SourceAirport.NANJING_LUKOU_INTERNATIONAL_AIRPORT;
                break;
            case "2":
                this.sourceAirport =SourceAirport.BEIJING_CAPITAL_INTERNATIONAL_AIRPORT;
                break;
            case "3":
                this.sourceAirport =SourceAirport.OULU_AIRPORT;
                break;
            case "4":
                this.sourceAirport =SourceAirport.HELSINKI_AIRPORT;
                break;
            default:
                System.out.println("There was no match");
                break;
        }
    }


    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
        if (returnDate == null){

        }else {
            Period period = Period.between(departureDate, returnDate);
            if (period.getDays() < 2 && period.getMonths()<=0 && period.getYears()<=0) {
                this.testChange = true;
                this.oldDate =returnDate;
                this.returnDate = departureDate.plusDays(2);
            }
        }
    }



    public String getPassengerFullName(int index) {
        return passengerFullName[index];
    }
    public void setPassengerFullName(int index,String passengerFullName) {
        this.passengerFullName [index] = passengerFullName;
    }

    public String getPassengerGender(int index) {
        return passengerGender[index];
    }
    public void setPassengerGender(int index,String passengerGender) {
        this.passengerGender[index] = passengerGender;
    }

    public int getPassengerAge(int index) {
        return passengerAge[index];
    }
    public void setPassengerAge(int index,int passengerAge) {
        this.passengerAge[index] = passengerAge;
    }

    public void setDepartingTicketPrice(int childPassengers, int adultPassengers) {
        double departingTicketPrice = (childPassengers+adultPassengers)*getTicketPrice();
        this.departingTicketPrice = departingTicketPrice;
    }
    public void setReturnTicketPrice() {
        if (tripType.equals(TripType.ONE_WAY)){
            this.returnTicketPrice=0;
        }else {
            this.returnTicketPrice=departingTicketPrice;
        }
    }
    public void setTicketNumber(int index) {
        String ticketNumber = null;

        switch (tripType) {
            case ONE_WAY:
                ticketNumber = "11";
                break;
            case RETURN:
                ticketNumber = "22";
                break;
        }
        switch (bookingClass) {
            case FIRST:
                ticketNumber = ticketNumber + "F";
                break;
            case BUSINESS:
                ticketNumber = ticketNumber + "B";
                break;
            case ECONOMY:
                ticketNumber = ticketNumber + "E";
                break;
        }
        for (int i = 0; i < 4; i++) {
            char c = getRandomCapital();
            ticketNumber = ticketNumber + c;
        }
        ticketNumber = ticketNumber + doi;

        this.ticketNumber[index] = ticketNumber;
    }

    public BookingClass getBookingClass() {
        return bookingClass;
    }

    public TripType getTripType() {
        return tripType;
    }

    public String getTicketNumber(int index) {
        setTicketNumber(index);
        return ticketNumber[index];
    }

    public int getChildrenPassengers() {
        return childPassengers;
    }

    public int getAdultPassengers() {
        return adultPassengers;
    }

    public int getTotalPassengers() {
        return totalPassengers;
    }

    public String getFlightID() {
        return flightID;
    }

    public TripSource getTripSource() {
        return tripSource;
    }

    public SourceAirport getSourceAirport() {
        return sourceAirport;
    }

    public LocalDate getDepartingDate() {
        return departureDate;
    }


    public LocalDate getReturnDate() {
        return returnDate;
    }

    public TripDestination getTripDestination() {
        return tripDestination;
    }

    public DestinationAirport getDestinationAirport() {
        return destinationAirport;
    }

    public double getDepartingTicketPrice() {
        return departingTicketPrice;
    }

    public double getReturnTicketPrice() {
        return returnTicketPrice;
    }
    public double getTicketPrice(){
        double ticketPrice=0;
        if (doi.equals("DOM")){
            ticketPrice = 300+300*1/10+300*5/100;
        }else {
            ticketPrice = 300+300*15/100+300*10/100;
        }
        switch (bookingClass){
            case FIRST:
                ticketPrice=ticketPrice+250;
                break;
            case BUSINESS:
                ticketPrice=ticketPrice+150;
                break;
            case ECONOMY:
                ticketPrice=ticketPrice+50;
        }
        return ticketPrice;
    }
    public void setTotalTicketPrice() {
        this.totalTicketPrice = departingTicketPrice+returnTicketPrice;
    }

    public double getTotalTicketPrice() {
        this.totalTicketPrice = departingTicketPrice + returnTicketPrice;
        return totalTicketPrice;
    }

    public int getChildPassengers() {
        return childPassengers;
    }

    public String getFlightCompany() {
        return flightCompany;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setTotalPassengers(int childPassengers,int  adultPassengers) {
        this.totalPassengers = adultPassengers+childPassengers;
    }
    public String toString(){

        String initial = "Dear "+passengerFullName+". Thank you for booking your flight with "+
                flightCompany+".\nFollowing are the details of your booking and the trip:\nTicket Number: "+
                ticketNumber+"\nPassenger Name:"+passengerFullName+"\nFrom "+tripSource+" to "+tripDestination+"\nDate of departure: "+
                departureDate+"\nDate of return: "+ returnDate+"\nTotal passengers: "+
                totalPassengers+"\nTotal ticket price in Euros: "+totalTicketPrice;
        String changed = "Dear "+passengerFullName+". Thank you for booking your flight with "+
                flightCompany+".\nFollowing are the details of your booking and the trip:\nTicket Number: "+
                ticketNumber+"\nPassenger Name:"+passengerFullName+"\nFrom "+tripSource+" to "+tripDestination+
                "(Changed from old "+oldDate+" to new "+returnDate+")"+
                "\nDate of departure: "+ departureDate+"\nDate of return: "+ returnDate+"\nTotal passengers: "+
                totalPassengers+"\nTotal ticket price in Euros: "+totalTicketPrice+
                "\n\nIMPORTANT NOTICE: As per our policy, the return date was changed because it was \n" +
                "less than two days apart from your departure date.";
        if (testChange){
            return changed;
        }
        return initial;
    }
    private char getRandomCapital(){
        Random random = new Random();
        return (char)((random.nextInt(26) + 65));
    }

    public void setFlightID() {
        String chars =String.valueOf(getRandomCapital());

        for (int i=0;i<4;i++){
            char c =  getRandomCapital();
            chars = chars + String.valueOf(c);
        }
        int midNumber = (int)((Math.random()+1)*1000);
        String flightID = String.valueOf(chars.charAt(0))+String.valueOf(chars.charAt(1))+String.valueOf(chars.charAt(2))+String.valueOf(midNumber)+String.valueOf(chars.charAt(3))+String.valueOf(chars.charAt(4));
        this.flightID = flightID;
        /*String flightID = "FOF"+String.valueOf(midNumber)+"IN";*/
        this.flightID=flightID;
    }
    public void displayTripDetails(){
        String initial = "Thank you for booking your flight with "+
                flightCompany+"\nYou reserved a total of" + number +"tickets."+"\n\n";
        System.out.println(initial);
        if (testChange){
            for (int repeat=number;repeat>0;--repeat){
                System.out.println("Here are the trip details for Passenger No."+(number-repeat+1)+"\n\nPassenger's Ticket Number: "+
                        ticketNumber[number-repeat]+"\nPassenger's Seats: flightSeats:"+initialBook[number-repeat]+"\nPassenger's Full Name:"+passengerFullName[number-repeat]+
                        "\nPassenger's Age:"+passengerAge[number-repeat]+"\nPassenger's Gender:"+passengerGender[number-repeat]+
                        "\nFrom:"+tripSource+"("+sourceAirport+")"+"\nTo:"+tripDestination+"("+destinationAirport+")"+"\nThe flight departs on:"+
                        departureDate+"(Changed from old "+oldDate+" to new "+returnDate+")"+"\nAnd the return flight is on:"+ returnDate+"\n\n");
            }
            System.out.println("\nThe total ticket price is:"+totalTicketPrice+"\n\nIMPORTANT NOTICE: As per our policy, the return date was changed because it was \n" +
                    "less than two days apart from your departure date.");

        }else{
            for (int repeat=number;repeat>0;--repeat){
                System.out.println("Here are the trip details for Passenger No."+(number-repeat+1)+"\n\nPassenger's Ticket Number: "+
                        ticketNumber[number-repeat]+"\nPassenger's Seats: flightSeats:"+initialBook[number-repeat]+"\nPassenger's Full Name:"+passengerFullName[number-repeat]+
                        "\nPassenger's Age:"+passengerAge[number-repeat]+"\nPassenger's Gender:"+passengerGender[number-repeat]+
                        "\nFrom:"+tripSource+"("+sourceAirport+")"+"\nTo:"+tripDestination+"("+destinationAirport+")"+"\nThe flight departs on:"+
                        departureDate+"\nAnd the return flight is on:"+ returnDate+"\n\n");
            }
            System.out.println("\nThe total ticket price is:"+totalTicketPrice);
        }

    }
}
