//For use with QuestionTree and QuestionMain
public class QuestionNode {
   
   //Fields for data and branches
   public String data;
   public QuestionNode left;//yes
   public QuestionNode right;//no
   
   //Pre: Accepts data value
   //Post: Initializes terminal node
   public QuestionNode(String input) {
      this.data = input;
   }
   
   //Pre: Accepts data and branch values (blank must be null)
   //Post: Initializes non-terminal node
   public QuestionNode(String input, QuestionNode yes, QuestionNode no) {
      this.data = input;
      this.left = yes;
      this.right = no;
   }

}