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
      loader(input, overallRoot);      
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
   private QuestionNode loader(Scanner input, QuestionNode current) {
      String[] dev = input.nextLine().split(":");
      if(dev[0].equals("A")){
         return new QuestionNode(dev[1]);
      }
      if(current == null&& current==overallRoot){ //if root node and not initalized
         current = loader(input, new QuestionNode(dev[1]));
      } else {
         current.left= loader(input, new QuestionNode(dev[1])); //yes
         dev = input.nextLine().split(":");
         current.right= loader(input, new QuestionNode(dev[1])); //no
      }
   }
   
   private void add(String q, String a, boolean r, QuestionNode root) {
      
   }

}