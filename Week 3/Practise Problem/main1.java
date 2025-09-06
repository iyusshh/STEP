import java.util.Scanner;

class LongestName{
    Scanner input = new Scanner(System.in);
    String[] names = new String[5];
    void Input(){
        for(int i = 0; i<5; i++){
            System.out.print("Enter Name "+ (i+1)+": ");
            names[i] = input.nextLine().trim();
        }
    }
    String FindLongest(){
        String Longest = names[0];
        for(String it: names){
            if(it.length() > Longest.length());
            Longest = it;
        }
        return Longest;
    }
    
}
public class main1{
    public static void main(String[] args) {
        LongestName obj1 = new LongestName();
        obj1.Input();
        System.out.println("Longest name is :" + obj1.FindLongest());
    }
}