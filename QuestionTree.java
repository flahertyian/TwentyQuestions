import java.util.*;
import java.io.*;

public class QuestionTree {
   
   private int totalGames;
   private int gamesWon;
   private QuestionNode overallRoot;
   private UserInterface u;
   
   //Pre: 
   //Post:
   public QuestionTree(UserInterface ui) {
      if (ui == null) throw new IllegalArgumentException("Method passed null parameter");
      
      totalGames = 0;
      gamesWon = 0;
      overallRoot = new QuestionNode("A:computer");
      u = ui;
   }
   
   //
   //Pre: 
   //Post: 
   public void play() {
      totalGames++;
      overallRoot = player(overallRoot);     
   }
   
   //Pre: 
   //Post:
   public void save(PrintStream output) {
      if (output == null) throw new IllegalArgumentException("Method passed null parameter");
      
      saver(output, overallRoot);
   }
   
   //Pre: 
   //Post:
   public void load(Scanner input) {
      if (input == null) throw new IllegalArgumentException("Method passed null parameter");
      
      overallRoot = null;
      overallRoot = loader(input, overallRoot);      
   }
   
   //Post: Returns value of totalGames
   public int totalGames() {
      return totalGames;
   }
   
   //Post: Returns value of gamesWon
   public int gamesWon() {
      return gamesWon;
   }
   
   //Pre:
   //Post:
   private QuestionNode player(QuestionNode root) {
      
      String s = root.data.substring(2);
      if (root.data.charAt(0) == 'A') {
          u.print("Would your object happen to be " + s + "?");
          if(u.nextBoolean()) {
            gamesWon++;
          } else {
            u.println("I lose.  What is your object? ");
            String a = u.nextLine();
            u.println("Type a yes/no question to distinguish your item from " + s + ": ");
            String q = u.nextLine();
            u.println("What is the answer for your object? ");
            boolean d = u.nextBoolean();
            return adder(q, a, d, root);
          }
      } else {
         u.print(root.data);
         if(u.nextBoolean()) {
            root.left = player(root.left);
         } else {
            root.right = player(root.right);
         }
      }
      return root;
   }
   
   //Pre: Untested
   //Post:
   private void saver(PrintStream output, QuestionNode root) {      
      output.println(root.data);
      if (root.left != null) saver(output, root.left);
      if (root.right != null) saver(output, root.right);
   }
   
   //Pre: Worked in test, needs load() to clear overallRoot first
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
   
//    Do after player()
//    Pre:
//    Post: 
   private QuestionNode adder(String q, String a, boolean d, QuestionNode root) {
      QuestionNode temp = root;
      String que = "Q:" + q;
      String ans = "A:" + a;
      QuestionNode newTerm = new QuestionNosde(ans);
      if (d) {
         root = new QuestionNode(que, newTerm, temp);
      } else {
         root = new QuestionNode(que, temp, newTerm);
      }
      return root;
   }

}