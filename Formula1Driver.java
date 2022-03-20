public class Formula1Driver extends Driver{
    //instance fields
    private int firstPosition;
    private int secondPosition;
    private int thirdPosition;
    private int numberOfPoints;
    private int position;
    private int numberOfRaces;



    public Formula1Driver() {
    }

    public Formula1Driver(String name, String teamName,String location, int position) {
        super(name, location, teamName);
        this.position = position;
    }

    public Formula1Driver(String name, String teamName,String location, int firstPosition, int secondPosition, int thirdPosition, int numberOfPoints, int position, int numberOfRaces) {
        super(name, location, teamName);
        this.firstPosition = firstPosition;
        this.secondPosition = secondPosition;
        this.thirdPosition = thirdPosition;
        this.numberOfPoints = numberOfPoints;
        this.position = position;
        this.numberOfRaces = numberOfRaces;
    }

    public int getFirstPosition() {
        return this.firstPosition;
    }

    public void setFirstPosition(int firstPosition) {
        this.firstPosition = firstPosition;
    }

    public int getSecondPosition() {
        return this.secondPosition;
    }

    public void setSecondPosition(int secondPosition) {
        this.secondPosition = secondPosition;
    }

    public int getThirdPosition() {
        return this.thirdPosition;
    }

    public void setThirdPosition(int thirdPosition) {
        this.thirdPosition = thirdPosition;
    }

    public int getNumberOfPoints() {
        return this.numberOfPoints;
    }

    public void setNumberOfPoints(int numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int position) {
        this.position = position;
        if(position == 1){
            this.setNumberOfPoints(this.getNumberOfPoints()+ 25); //check if this works
            this.firstPosition++;
            this.numberOfRaces++;
        }
        else if(position == 2){
            this.setNumberOfPoints(this.getNumberOfPoints() + 18);
            this.secondPosition++;
            this.numberOfRaces++;

        }
        else if(position == 3){
            this.setNumberOfPoints(this.getNumberOfPoints() + 15);
            this.thirdPosition++;
            this.numberOfRaces++;

        }
        else if(position == 4){
            this.setNumberOfPoints(this.getNumberOfPoints() + 12);
            this.numberOfRaces++;

        }
        else if(position == 5){
            this.setNumberOfPoints(this.getNumberOfPoints() + 10);
            this.numberOfRaces++;

        }
        else if(position == 6){
            this.setNumberOfPoints(this.getNumberOfPoints() + 8);
            this.numberOfRaces++;

        }
        else if(position == 7){
            this.setNumberOfPoints(this.getNumberOfPoints() + 15);
            this.numberOfRaces++;

        }
        else if(position == 8){
            this.setNumberOfPoints(this.getNumberOfPoints() + 4);
            this.numberOfRaces++;

        }
        else if(position == 9){
            this.setNumberOfPoints(this.getNumberOfPoints() + 2);
            this.numberOfRaces++;

        }
        else if(position == 10){
            this.setNumberOfPoints(this.getNumberOfPoints() + 1);
            this.numberOfRaces++;

        }

    }

    public int getNumberOfRaces() {
        return this.numberOfRaces;
    }

    public void setNumberOfRaces(int numberOfRaces) {
        this.numberOfRaces = numberOfRaces;
    }



    @Override
    public String toString() {
        return "{" +
            " firstPosition='" + getFirstPosition() + "'" +
            ", secondPosition='" + getSecondPosition() + "'" +
            ", thirdPosition='" + getThirdPosition() + "'" +
            ", numberOfPoints='" + getNumberOfPoints() + "'" +
            ", position='" + getPosition() + "'" +
            ", numberOfRaces='" + getNumberOfRaces() + "'" +
            "}";
    }
}


    
    
