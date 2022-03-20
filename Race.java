import java.util.ArrayList;
import java.util.Date;

public class Race{
    private String date;
    private ArrayList<String> raceTeamName = new ArrayList<>();
    private ArrayList<Integer> racePosition = new ArrayList<>();

    
    //default constructor
    public Race(){

    }

    public Race(String date, ArrayList<String> raceTeamName, ArrayList<Integer> racePosition) {
        this.date = date;
        this.raceTeamName = raceTeamName;
        this.racePosition = racePosition;
    }



    public ArrayList<String> getRaceTeamName() {
        return this.raceTeamName;
    }

    public void setRaceTeamName(ArrayList<String> raceTeamName) {
        this.raceTeamName = raceTeamName;
    }

    public ArrayList<Integer> getRacePosition() {
        return this.racePosition;
    }

    public void setRacePosition(ArrayList<Integer> racePosition) {
        this.racePosition = racePosition;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }



    
}
