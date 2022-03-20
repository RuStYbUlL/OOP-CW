import java.util.Comparator;

public class MyComparator implements Comparator<Formula1Driver>{
    
    @Override
    public int compare(Formula1Driver f1, Formula1Driver f2){

        //sort according to points
        if(f1.getNumberOfPoints() > f2.getNumberOfPoints()){
            return -1; //negative for descending order
        }
        else if(f1.getNumberOfPoints() < f2.getNumberOfPoints()){
            return 1;
        }
        /*when points are the same then sort according to number of firstpositions*/
        else if(f1.getFirstPosition() > f2.getFirstPosition()) {
            return -1;
        }
        else if(f1.getFirstPosition() < f2.getFirstPosition()){
            return 1;
        }
        else return 0;
             
    }
}



/*
References
https://www.geeksforgeeks.org/sort-arraylist-in-descending-order-using-comparator-in-java/
*/