/**
QuestionTree
Ian Flaherty , Mike McGinn
3/11/18
CS145 C

This program is designed to organize QuestionNode objects into a Tree of nodes so that they can represent
questions and answers in the 20 questions game. This object allows for the saving of a tree structure in 
a text file, and for the user to play a game with a tree generated from this file.  
*/



import java.util.*;
import java.io.*;

public class QuestionTree {
   
   private int totalGames;
   private int gamesWon;
   private QuestionNode overallRoot;
   private UserInterface u;
   
   // Constructor takes in a user interface object.
   // if User Interface is null throws a IllegalArguementException. 
   // initalizes totalGames, gamesWon, overallRoot, userInterface
   public QuestionTree(UserInterface ui) {
      if (ui == null) throw new IllegalArgumentException("Method passed null parameter");
      
      totalGames = 0;
      gamesWon = 0;
      overallRoot = new QuestionNode("A:computer");
      u = ui;
   }
   
   //
   //Pre: overallRoot is initalized
   //Post: totalGames is incremented, and overallRoot now contains the contence of a game
   //calls player private helper method.
   public void play() {
      totalGames++;
      overallRoot = player(overallRoot);     
   }
   
   //if the printSteam is null throws an IllegalArguementException
   //Pre: overallRoot is not null
   //calls saver private helper method
   public void save(PrintStream output) {
      if (output == null) throw new IllegalArgumentException("Method passed null parameter");
      
      saver(output, overallRoot);
   }
   
   // if scanner input object is null thows IllegalArguementException
   // calls loader private helper method.
   public void load(Scanner input) {
      if (input == null) throw new IllegalArgumentException("Method passed null parameter");
      
      overallRoot = null;
      overallRoot = loader(input, overallRoot);      
   }
   
   //Pre: totalGames is initalized
   //Post: Returns int value of totalGames
   public int totalGames() {
      return totalGames;
   }
   
   //Pre: gamesWon is initalized 
   //Post: Returns int value of gamesWon
   public int gamesWon() {
      return gamesWon;
   }
   
   //Pre: Tree/QuestionNode root is not null
   //Post: will have a tree that represents the user input
   //------------------------------------------------------
   //player is the main function that handles user input.
   //player also modifies the tree by calling the adder method. 
   //returns a QuestionNode 
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
   
   //Pre: the tree is not null 
   //Post: the contents of the tree are now represented in the output file  
   private void saver(PrintStream output, QuestionNode root) {      
      output.println(root.data);
      if (root.left != null) saver(output, root.left);
      if (root.right != null) saver(output, root.right);
   }
   
   //Pre: the input file is not empty
   //Post: the tree is initalized with the contents of the input file
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
   
   //Pre: the tree is already initalized
   //Post: the tree has been updated to reflect a new question/answer added by the user.
   //returns a QuestionNode
   private QuestionNode adder(String q, String a, boolean d, QuestionNode root) {
      QuestionNode temp = root;
      String que = "Q:" + q;
      String ans = "A:" + a;
      QuestionNode newTerm = new QuestionNode(ans);
      if (d) {
         root = new QuestionNode(que, newTerm, temp);
      } else {
         root = new QuestionNode(que, temp, newTerm);
      }
      return root;
   }

}