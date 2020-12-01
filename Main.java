import java.util.*;
public class Main {
    public static void main(String[] args){

        //setup values for use within the factory
        int[] pieceLengths = {21, 22, 24, 25, 27, 29, 30, 31, 32, 33, 34, 35, 38, 39, 42, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 59, 60, 61, 63, 65, 66, 67};
        int[] quantities = {13, 15, 7, 5, 9, 9, 3, 15, 18, 17, 4, 17, 20, 9, 4, 19, 4, 12, 15, 3, 20, 14, 15, 6, 4, 7, 5, 19, 19, 6, 3, 7, 20, 5, 10, 17};
        int [] stockLengths = {120, 115, 110, 105, 100};
        double [] stockCosts = {12, 11.5, 11, 10.5, 10};
        //hashmap to hold the stock lengths and costs as a key-value pair
        HashMap<Integer, Double> factoryLogistics = new HashMap <Integer, Double>();
        for( int i = 0; i < stockLengths.length; i++){
            factoryLogistics.put(stockLengths[i],stockCosts[i]);
        }


        //create factory and order
        new Factory(factoryLogistics);
        new Order(pieceLengths, quantities);
        //recieve the cost of fulfilling the order on this randomly generated solution
        double cost = Factory.Fulfill();
        System.out.println(cost);
        }
}
