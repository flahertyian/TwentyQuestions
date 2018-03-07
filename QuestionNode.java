public class QuestionNode {
   
   public String data;
   public QuestionNode left;//yes
   public QuestionNode right;//no


   public QuestionNode(String input) {
      this.data = input;
   }
   
   public QuestionNode(String input, QuestionNode yes, QuestionNode no) {
      this.data = input;
      this.left = yes;
      this.right = no;
   }

}