import java.util.*;

public class QuestionTree {
   
   private int totalGames;
   private int gamesWon;
   private QuestionNode overallRoot;
   
   //Pre: 
   //Post:
   public Question Tree(UserInterface ui) {
   
      totalGames = 0;
      gamesWon = 0;
   
   }
   
   //Pre: 
   //Post: 
   public void play() {
   
   }
   
   //Pre: 
   //Post:
   public void save(PrintStream output) {
   
   }
   
   //Pre: 
   //Post:
   public void load(Scanner input) {
      loader(input);      
   }
   
   //Post: Returns value of totalGames
   public int totalGames() {
      return totalGames;
   }
   
   //Post: Returns value of gamesWon
   public int gamesWon() {
      return gamesWon;
   }
   
   //Note: presently garbled
   //Pre: 
   //Post:
   private QuestionNode loader(Scanner input) {
      if (input.nextLine()) {
         return null;
      } else if () {
         
      }
   }
   
   private void add(String q, String a, boolean r, QuestionNode root) {
      
   }

}