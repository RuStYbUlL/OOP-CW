import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Formula1ChampionshipManager implements ChampionshipManager{
    
    private final int numberOfTeams; //change to numberOfDrivers
    protected static ArrayList<Formula1Driver> championship = new ArrayList<>();
    protected static ArrayList<Race> raceArray = new ArrayList<>();
    private static Scanner myScanner = new Scanner(System.in);
    private static File championFile = new File("champion.txt");
    private static File raceFile = new File("race.txt");

    public Formula1ChampionshipManager(int numberOfTeams){
        this.numberOfTeams = numberOfTeams;
        consoleMenu(); //called when instance of this class is created
    }

    private void consoleMenu() {
        boolean loop = true;
        while(loop == true){
            System.out.println();
            System.out.println("********** Formula 1 Racing Car Championship **********");
            System.out.println();
            
            System.out.println("Press 1: Create a new Driver. \n"
            + "Press 2: Delete a driver. \n"
            + "Press 3: Change a diver. \n"
            + "Press 4: Display statistics of a driver. \n"
            + "Press 5: Display Formula 1 Diver Table. \n"
            + "Press 6: Add a completed race. \n"
            + "Press 7: Save information. \n"
            + "Press 8: Load file. \n"
            + "Press 9: Start GUI. \n"
            + "Press 10: Exit the Program");
            System.out.println();
            

            String stringUserInput = myScanner.nextLine();
            int intUserInput = 0;

            //makes sure that the input is number
            try{
                intUserInput = Integer.parseInt(stringUserInput);
            }
            catch (Exception e){
                System.out.println("You must enter a number");
            }

            switch (intUserInput){
                case 1 :
                    addNewDriver();      
                    break;
                case 2:
                    deleteDriver();     
                    break;
                case 3:
                    changeDriver();     
                    break;
                case 4:
                    displayStatistics();    
                    break;
                case 5:
                    displayAllStatisticsTable();
                    break;
                case 6:
                    addCompletedRace();
                    break;
                case 7:
                    saveData();
                    break;
                case 8:
                    loadData();
                    break;
                case 9:
                    startGUI();
                    break;
                case 10:
                    System.out.println("Exiting menu...");
                    if (intUserInput == 10){
                        loop = false;
                    }
                    break;
                default:
                    System.out.println("Try again");
            
            }   
        }
    }

    private void addNewDriver(){
        //display drivers if number of drivers added is greater than 0
        if(championship.size()>0){
            showDrivers();
        }
        if(championship.size() == this.numberOfTeams){
            System.out.println("Maximum number of teams reached. \nCannot add another team.");
            return;
        }

        Formula1Driver team = new Formula1Driver();
        System.out.println("Enter Team name: ");
        String stringUserInput = myScanner.nextLine();
        
        if(checkIfTeamExists(stringUserInput)==false){
            if(stringUserInput.equals("")){
                System.out.println("You must enter a team name");
                return;
            }

            team.setTeamName(stringUserInput);

            if(championship.contains(team)){
                System.out.println("This team in already in the championship");
                return;        //return statement ends the execution of a function, and returns control to the calling function
            }

            System.out.println("Enter location: ");
            stringUserInput = myScanner.nextLine();
            team.setLocation(stringUserInput);

            System.out.println("Enter driver name: ");
            stringUserInput = myScanner.nextLine();
            team.setName(stringUserInput);
            
            championship.add(team);//add team info to championship arraylist
        }
        else{
            System.out.println("\nTeam alredy exixts...\n");
        }


    }

    public static boolean checkIfTeamExists(String team){
        for(Formula1Driver driverTeam: championship){
            if(driverTeam.getTeamName().equals(team)){
                return true;
            }
        }
        return false;
    }

    private void deleteDriver(){
        showDrivers();
        System.out.println("Enter driver name: ");
        String stringUserInput = myScanner.nextLine();
        for(Formula1Driver team: championship){
            if(team.getName().equals(stringUserInput)){
                championship.remove(team);
                System.out.println("\nTeam = " + team.getTeamName() + " and driver = " + team.getName() +  " is deleted");
                return;
            }
        }
        System.out.println("No existing drivers by that name."); //display when user input is invalid
    }

    private void displayStatistics(){
        System.out.println("Enter team name: ");
        String userInput = myScanner.nextLine();
        for(Formula1Driver team: championship){
            if (team.getTeamName().equals(userInput)){
                System.out.println("Driver name: " + team.getName() + " Team name: " + team.getTeamName());
                System.out.println("Driver name: " + team.getName() + " Location: " + team.getLocation());
                System.out.println("Driver name: " + team.getName() + " First places won: " + team.getFirstPosition());
                System.out.println("Driver name: " + team.getName() + " Second places won: " + team.getSecondPosition());
                System.out.println("Driver name: " + team.getName() + " Third places won: " + team.getThirdPosition());
                System.out.println("Driver name: " + team.getName() + " Points: " + team.getNumberOfPoints() + "\n");
                return;
            }
        }
        System.out.println("No existing team by that name.");
    }

    private void displayAllStatisticsTable(){
        Collections.sort(championship, new MyComparator());
        System.out.println("STATISTICS TABLE:\n");
        System.out.printf("%-10s%-10s%-10s%-20s%-25s%-25s%-22s%-29s\n","Team","Name","Location","First Positions Won","Second Positions Won","Third Positions Won","Points Scored","Number of Races Participated");
       for (int i = 0; i < championship.size(); i++) {
           System.out.printf("%-10s%-10s%-10s%-20s%-25s%-25s%-22s%-29s\n",
                championship.get(i).getTeamName(),
                championship.get(i).getName(),
                championship.get(i).getLocation(),
                championship.get(i).getFirstPosition(),
                championship.get(i).getSecondPosition(),
                championship.get(i).getThirdPosition(),
                championship.get(i).getNumberOfPoints(),
                championship.get(i).getNumberOfRaces());
            System.out.println();
       }
        
    }

    private static void addCompletedRace(){

        boolean loop = true;
        while(loop){
            ArrayList<String> raceTeamData = new ArrayList<>();
            ArrayList<Integer> raceTeamPosition = new ArrayList<>();

            System.out.println("Do you want to update existing driver/team position in this race? (y or n)");
            String userInput = myScanner.nextLine().toLowerCase();
            if(userInput.equals("y")){
                System.out.println("User chose 'y'...");
                
                showDrivers();


                //get team name
                System.out.println("Search team name: ");

                String raceTeamName = myScanner.nextLine();
                //add team name to race team array
                raceTeamData.add(raceTeamName); 

                Formula1Driver addedTeam = null; 
                for(Formula1Driver team : championship){  
                    if(team.getTeamName().equals(raceTeamName)){ //if user input matches existing teams then that will be the addRace
                        addedTeam = team;  
                        System.out.println("Found team " + raceTeamName + "\n");
                    }
                }
                if (addedTeam == null){ 
                    System.out.println("No existing team by that name...\n");
                    return; 
                }
                



                //get driver position
                System.out.println("Enter driver position: ");
                String raceDriverPosition = myScanner.nextLine();
                if(checkIfPositionWithinRange(raceDriverPosition) == true){
                    //add position to race team position array
                    raceTeamPosition.add(Integer.parseInt(raceDriverPosition));

                    int addedDriverPosition = -1; //initialise
                    try{
                        addedDriverPosition = Integer.parseInt(raceDriverPosition);
                    }
                    catch (Exception e){
                    }
                    if(addedDriverPosition == -1){
                        System.out.println("You must enter a position (1 - 10)");
                        loop = false;
                    }

                    addedTeam.setPosition(addedDriverPosition);



                    //get date
                    System.out.println("Instert month-day-year (mm-dd-yyyy): ");
                    String raceDate = myScanner.nextLine();
                    Date date;

                    //displayed when user inputs date in wrong format
                    try{
                        date = new SimpleDateFormat("MM-dd-yyyy").parse(raceDate);

                    }
                    catch(ParseException e){
                        System.out.println("You must enter the date in this format: mm-dd-yyyy"); 
                        continue;
                    }

                    //convert date to string
                    DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
                    String stringRaceDate = dateFormat.format(date);

                    System.out.println("\nRace scheduled on " + stringRaceDate + ".");//make date readable
                    

                    //add race info to raceArray
                    Race race = new Race(stringRaceDate, raceTeamData, raceTeamPosition);
                    raceArray.add(race);
                }
                else{
                    System.out.println("\nOnly 10 positions allowed\n");
                    return;
                }


            }
            
            else if(userInput.equals("n")){
                System.out.println("User choes 'n' \nExiting add race...");
                loop = false;

            }
            else{
                System.out.println("Wrong input \n");
                continue;

            }


        }

        
    }

    public static boolean checkIfPositionWithinRange(String position){
        int intPos = Integer.parseInt(position);
        if(intPos >10  || intPos <1){
            return false;
        }
        return true;
    }

    private void changeDriver(){

        System.out.println("Enter team name: ");
        String teamName = myScanner.nextLine();

        for(Formula1Driver team: championship){
            if(championship.contains(teamName)){
                System.out.println("Enter new Driver: ");
                String newDriveName = myScanner.nextLine();
                team.setName(newDriveName);
            }
            else{
                System.out.println("team doesnt exist...\n");
                return;  //takes you out of the method
            }
        }
        showDrivers();
    }
    
    private void saveData(){
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("champion.txt"))){
            //championship info
            for (int i = 0; i < championship.size() ;i++){
                bufferedWriter.write(championship.get(i).getName()+ "," );       
                bufferedWriter.write(championship.get(i).getTeamName()+ ",");    
                bufferedWriter.write(championship.get(i).getLocation()+ ",");       
                bufferedWriter.write(String.valueOf(championship.get(i).getFirstPosition()+ ","));       
                bufferedWriter.write(String.valueOf(championship.get(i).getSecondPosition()+ ","));       
                bufferedWriter.write(String.valueOf(championship.get(i).getThirdPosition()+ ","));       
                bufferedWriter.write(String.valueOf(championship.get(i).getNumberOfPoints()+ ","));       
                bufferedWriter.write(String.valueOf(championship.get(i).getNumberOfRaces()));       
                bufferedWriter.newLine();
            }
            
                      
        }catch(IOException e){
            e.printStackTrace();
        }
        System.out.println("Champion data stored");

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("race.txt"))){
            //add race info
            for (int i = 0; i < raceArray.size() ;i++){
                bufferedWriter.write(raceArray.get(i).getDate()+ ",");
                bufferedWriter.write(String.valueOf(raceArray.get(i).getRacePosition())+ ",");//buffer writer doesnt write integer to file
                bufferedWriter.write(String.valueOf(raceArray.get(i).getRaceTeamName()));
                bufferedWriter.newLine();
            }
            
                      
        }catch(IOException e){
            e.printStackTrace();
        }
        System.out.println("Race data stored...\n");
    }

    private void loadData(){
        try{
            Scanner s = new Scanner(new File(String.valueOf(championFile)));
            while(s.hasNextLine()){
                String championLoadVariables = s.nextLine();    
                String[] splits = championLoadVariables.split(",");  
                Formula1Driver championData = new Formula1Driver(splits[0], splits[1], splits[2], Integer.parseInt(splits[3]), Integer.parseInt(splits[4]), Integer.parseInt(splits[5]), Integer.parseInt(splits[6]), Integer.parseInt(splits[7]), Integer.parseInt(splits[8]));
                championship.add(championData);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        
        System.out.println("\nChampions data loaded...\n");

        try{
        Scanner s2 = new Scanner(new File(String.valueOf(raceFile)));
            while(s2.hasNextLine()){
                String loadVariables = myScanner.nextLine();    
                String[] splits = loadVariables.split(",");     
                String loadedDate = splits[0];
                String loadedRaceTeamName = splits[1];
                String loadedRacePosition = splits[2];
                ArrayList<String> stringList = new ArrayList<>(Arrays.asList(loadedRaceTeamName));
                ArrayList<Integer> intList = new ArrayList<Integer>();
                intList.add(Integer.parseInt(loadedRacePosition));

                Race loadedRaces = new Race(loadedDate,stringList,intList);
                raceArray.add(loadedRaces);
                
            }                              
        }
        catch(IOException e){
            e.printStackTrace();
        }

        System.out.println("Race data loaded...\n");


        /*
            (╯°□°)╯︵ ┻━┻ (>_<)
        */
    }
    
    
    private static void showDrivers(){
        System.out.println("Displaying existing drivers...\n");
        System.out.printf("%-15s%-15s%-15s\n", "Driver name", "Driver team", "Driver position");

        for(Formula1Driver team: championship){
            System.out.printf("%-15s%-15s%-15s\n",
                team.getName(),
                team.getTeamName(),
                team.getPosition());
        }
        System.out.println();
    }

    private void startGUI(){
        GUI m = new GUI();
    }

}















/*
References

https://www.geeksforgeeks.org/comparable-vs-comparator-in-java/
https://examples.javacodegeeks.com/core-java/text/parseexception/java-text-parseexception-how-to-solve-parseexception/#:~:text=ParseException%20.,should%20have%20a%20specified%20format
https://www.w3schools.com/java/java_switch.asp
https://www.youtube.com/watch?v=9PsQdsjij1ohttps://www.w3schools.com/java/java_files.asp
https://stackoverflow.com/questions/878573/java-multiline-string
https://stackoverflow.com/questions/46081270/java-manually-sort-a-string-array-using-compareto-method
https://www.geeksforgeeks.org/bubble-sort/
https://www.youtube.com/watch?v=hgF21imQ_Is
https://www.javatpoint.com/java-date-to-string
https://www.tabnine.com/code/java/methods/java.io.BufferedWriter/newLine
https://www.journaldev.com/312/read-file-java-bufferedreader-fileinputstream-files-scanner-randomaccessfile
https://www.tutorialspoint.com/how-to-read-contents-of-a-file-using-scanner-class
https://www.youtube.com/watch?v=-Aud0cDh-J8
https://www.geeksforgeeks.org/split-string-java-examples/
https://stackoverflow.com/questions/20588900/bufferedwriter-write-method-doesnt-write-integers-to-file
https://www.studytonight.com/java-examples/how-to-convert-string-to-arraylist-in-java
https://www.geeksforgeeks.org/conversion-of-array-to-arraylist-in-java/
https://itsallbinary.com/java-printing-to-console-in-table-format-simple-code-with-flexible-width-left-align-header-separator-line/https://www.youtube.com/watch?v=Cz3N2xvQsOU
*/