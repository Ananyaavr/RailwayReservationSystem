package Main;

import java.util.ArrayList;
import java.util.Scanner;

class Train{
	String name;
	String time;
	int passengerStrength;
	int trainNumber;
	Train(String name, String time, int passengerStrength, int trainNumber) {
		this.name = name;
		this.time = time;
		this.passengerStrength = passengerStrength;
		this.trainNumber = trainNumber;
	}	
}

class Ticket{
	int ticketNumber;
	String passengerName;
	int trainNumber;
	String seatNumber;
	Ticket(int ticketNumber, String passengerName, int trainNumber, String seatNumber) {
		this.ticketNumber = ticketNumber;
		this.passengerName = passengerName;
		this.trainNumber = trainNumber;
		this.seatNumber = seatNumber;
	}
	
}

public class Reservation {
	
	static ArrayList<Train> train = new ArrayList<>();
	static ArrayList<Ticket> ticket = new ArrayList<>();
	static int ticketCounter = 1001;
	
	public static void main(String args[])
	{
		initializeTrains();
		
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.println("\n---------Railway Reservation System-----------\n");
			System.out.println("1. Train Information\n");
			System.out.println("2. Seat Availability\n");
			System.out.println("3. Booking\n");
			System.out.println("4. Cancellation\n");
			System.out.println("5. Ticket Display\n");
			System.out.println("6. Exit\n");
			System.out.println("Enter your choice : ");
			
			int choice = scanner.nextInt();
			scanner.nextLine();
			
			switch(choice) {
			case 1:
				displayTrainInformation();
				break;
			case 2:
				System.out.println("Enter the Train Number : ");
				int num = scanner.nextInt();
				checkSeatAvailability(num);
				break;
			case 3:
				System.out.println("Enter the name : ");
				String passengerName = scanner.nextLine();
				System.out.println("Enter the Train Number : ");
				int trainNumber = scanner.nextInt();
				bookTickets(passengerName,trainNumber);
				break;
			case 4:
				System.out.println("Enter the Ticket Number : ");
				int trainNum = scanner.nextInt();
				cancelTickets(trainNum);
				break;
			case 5:
				System.out.println("Enter the Ticket Number : ");
				int ticketNumber = scanner.nextInt();
				displayTicketDetails(ticketNumber);
				break;
			case 6:
				System.out.println("Thank you for using the Railway Reservation System!");
				System.exit(0);
			default:
				System.out.println("Invalid choice. Please Try again!");
			}		
	}
	}
	
	static void initializeTrains()
	{
		train.add(new Train("Chennai - Coimbatore","07:00",50,1010));
		train.add(new Train("Coimbatore - Trichy","11:00",50,2007));
		train.add(new Train("Hyderabad - Bangalore","03:00",100,3021));
	}
	
	static void displayTrainInformation() 
	{
		System.out.println("-----Train Information-----");
		System.out.println("Train Number\tTrain Name\tTime\tPassenger Strength");
		for(Train trains:train)
		{
			System.out.println(trains.trainNumber+"\t"+trains.name+"\t"+trains.time+"\t"+trains.passengerStrength);
		}
	}
	
	static void checkSeatAvailability(int trainNumber)
	{
		for(Train trains : train)
		{
			if(trains.trainNumber==trainNumber)
			{
				int availableSeat = trains.passengerStrength - countBookedSeats(trainNumber);
				System.out.println("Available seats on Train "+trainNumber+": "+availableSeat);
				return;
			}
		}
	}
	
	static int countBookedSeats(int trainNumber)
	{
		int count = 0;
		for(Ticket tickets : ticket)
		{
			if(tickets.trainNumber == trainNumber)
			{
				count++;
			}
		}
		return count;
	}
	
	static void bookTickets(String passengerName,int trainNumber)
	{
		Train selectedTrain = null;
		for(Train trains : train) {
			if(trains.trainNumber == trainNumber)
			{
				selectedTrain = trains;
				break;
			}
		}
		int availableSeat = selectedTrain.passengerStrength - countBookedSeats(trainNumber);
		if(availableSeat > 0)
		{
			String seatNumber ="S"+(ticketCounter++);
			Ticket ticketSelect = new Ticket(ticketCounter,passengerName,trainNumber,seatNumber);
			ticket.add(ticketSelect);
			System.out.println("Ticket booked successfully! Your seat number is "+seatNumber);
		}
		else
		{
			System.out.println("Sorry, no seats available on this train.");
		}
	}
	
	static void cancelTickets(int trainNumber)
	{
		for(Ticket tickets : ticket)
		{
			if(tickets.trainNumber == trainNumber)
			{
				ticket.remove(tickets);
				System.out.println("Ticket cancelled successfully!");
				return;
			}
		}
		System.out.println("Ticket not found!");
	}
	
	static void displayTicketDetails(int ticketNumber)
	{
		for(Ticket tickets : ticket)
		{
			if(tickets.ticketNumber == ticketNumber)
			{
				System.out.println("Ticket Details:");
				System.out.println("Ticket Number : "+tickets.trainNumber);
				System.out.println("Passenger Name : "+tickets.passengerName);
				System.out.println("Train Number : "+tickets.trainNumber);
				System.out.println("Seat Number : "+tickets.seatNumber);
				return;
			}
		}
		System.out.println("Tickets not found!");
	}

}
