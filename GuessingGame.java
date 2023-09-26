import java.util.Scanner;
import java.util.Random;
import java.util.*;

public class GuessingGame{

  public static void main(String []args){
    System.out.println("Welcome to the Guessing game!");
    Scanner in = new Scanner(System.in);
    System.out.println("Enter your guesses for the 3 integers in the range from 1 to 0 that have been selected:");
    int guesses = in.nextInt();
    int arr [] = new int[3];
    BagInterface<String> aBag = new ArrayBag<>();
    int[] contentsOfBag1 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    for(int i = 0; i < contentsOfBag1.length(); i++){
      for(int j = 0; j<random.length(); j++){
      if(contentsOfBag1[i] == random[j]){
      arr[j] = contentsOfBag1[i];
      }
    }
    
    
    
  }
}