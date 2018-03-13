// QuestionTree
// Ian Flaherty
// Michael McGinn
// 3/11/18
// CS145 C
// 
// This is a binary tree implementation of Twenty Questions with support for save/load and for learning new Q/As.  

import java.util.*;
import java.io.*;

public class QuestionTree {
   
   //Tracks total games
   private int totalGames;
   
   //Tracks games won by computer
   private int gamesWon;
   
   //Initial node
   private QuestionNode overallRoot;
   
   //Object passed from main for interaction with user interface
   private UserInterface u;
   
   //Pre: Accepts UserInterface object in main.
   //Post: Initializes fields
   public QuestionTree(UserInterface ui) {
      //Throws error if method parameter is null
      if (ui == null) throw new IllegalArgumentException("Method passed null parameter");
      
      totalGames = 0;
      gamesWon = 0;
      overallRoot = new QuestionNode("A:computer");
      u = ui;
   }
   
   //Pre: overallRoot is initalized
   //Post: totalGames is incremented, and overallRoot now contains the contents of a game
   //calls player private helper method.
   public void play() {
      totalGames++;
      overallRoot = player(overallRoot);     
   }
   
   //Pre: Accepts printstream object
   //Post: Uses saver() to output tree to file
   public void save(PrintStream output) {
      //Throws error if method parameter is null
      if (output == null) throw new IllegalArgumentException("Method passed null parameter"); 
         
      saver(output, overallRoot);
   }
   
   //Pre: Accepts scanner object
   //Post: Uses loader() to build tree from file
   public void load(Scanner input) {
      //Throws error if method parameter is null
      if (input == null) throw new IllegalArgumentException("Method passed null parameter");
      
      overallRoot = null;
      overallRoot = loader(input, overallRoot);      
   }
   
   //Post: Getter for totalGames
   public int totalGames() {
      return totalGames;
   }
   
   //Post: Getter for gamesWon
   public int gamesWon() {
      return gamesWon;
   }
   
   //Pre: Accpets overallRoot from play()
   //Post: Processes user interaction, calls adder() if player wins
   private QuestionNode player(QuestionNode root) {
      
      //root.data's value with prefix trimmed
      String s = root.data.substring(2);
      
      if (root.data.charAt(0) == 'A') {
          u.print("Would your object happen to be " + s + "?");
          if(u.nextBoolean()) {
            gamesWon++;
          } else {
            u.print("I lose.  What is your object? ");
            String a = u.nextLine();
            u.print("Type a yes/no question to distinguish your item from " + s + ": ");
            String q = u.nextLine();
            u.print("What is the answer for your object? ");
            boolean d = u.nextBoolean();
            return adder(q, a, d, root);
          }
      } else {
         u.print(s);
         if(u.nextBoolean()) {
            root.left = player(root.left);
         } else {
            root.right = player(root.right);
         }
      }
      return root;
   }
   
   //Pre: Accepts prinstream object and overallRoot in save().
   //Post: Outputs tree to save file in pre-traversal order.  
   private void saver(PrintStream output, QuestionNode root) {      
      output.println(root.data);
      if (root.left != null) saver(output, root.left);
      if (root.right != null) saver(output, root.right);
   }
   
   //Pre: Accepts scanner object and current node from load(). Requires overallRoot 
   //be set to null. Requires save file list tree nodes in pre-traversal order.
   //Post: Tree is built from scanned in save file. Returns QuestionNode object. 
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
   
   //Pre: From player(), accepts current node and user inputted question, answer and answer-boolean. 
   //Post: Adds and inserts new nodes to tree, returns QuestionNode. 
   private QuestionNode adder(String q, String a, boolean d, QuestionNode root) {
      String que = "Q:" + q;
      String ans = "A:" + a;
      if (d) {
         root = new QuestionNode(que, new QuestionNode(ans), root);
      } else {
         root = new QuestionNode(que, root, new QuestionNode(ans));
      }
      return root;
   }
}