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
      overallRoot = new QuestionNode("Computer");
   }
   
   //Pre: 
   //Post: 
   public void play() {
      
   }
   
   //Pre: 
   //Post:
   public void save(PrintStream output) {
      saver(output, overallRoot);
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
   
   //Pre: Untested
   //Post:
   private void saver(PrintStream output, QuestionNode input) {
      output.println(input.data);
      saver(output, root.left);
      saver(output, root.left);
   }
   
   //Pre: Untested
   //Post:
   private QuestionNode loader(Scanner input, QuestionNode root) {
      if (!input.hasNext()) return null;
      
      String line = input.nextLine();
      
      if (root == null) root = new QuestionNode(line);
      
      if (line.charAt(0) == 'Q') {
         root.left = loader(input, root.left);
         root.right = loader(input, root.right);
      }
      
      return root;
   }
   
   private QuestionNode add() {
   
   }

}