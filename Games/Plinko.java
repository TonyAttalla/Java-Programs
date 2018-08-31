//Tony Attalla
//October 17, 2016
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//import the utilities library
import java.util.*; 
//public class Plinko
public class Plinko
{
//make a value for the chips that the player has (at least 1). (static because its used in more than 1 method)
static int chips = 1;
//make a scanner called in. (static because its used in more than 1 method)
static Scanner in = new Scanner(System.in);
    //this is the "plinko" method 
    public static void main (String[] args) throws InterruptedException, NumberFormatException
    {
        //make a variable for the total $ that the user has won
        int sum = 0;
        // call the questions method to determine how many chips the player gets
        chips = questions(0);
        //make an array for the slots in the plinko machine
        int[] results = {100,500,1000,0,10000,0,1000,500,100};
        //make a random number generator to generate moves
        Random numGen = new Random();
        //welcome the user
        System.out.println("Welcome To Plinko");
        //make a variable to determine if the chip will move left or right
        int move;
        //make a variable for the current location of the chip (0 so that java doesn't bug me about it "not being initialized"
        int location = 0 ;
        //tell the user the available prizes
        System.out.println("{100,500,1000,0,10000,0,1000,500,100}. ");
        //tell the user how many chips they have
        System.out.println("You have " + chips + " chips");
        //while the user still has chips available do the following:
        while (chips > 0){
            //while the location they wanna drop their chip is less than one or greater than 9 (invalid) ask them for input
            while (location < 1 || location > 9)
            {   try
                {
                    System.out.println(" Please enter a slot to drop your chip into: XEN, X<10");
                    //store what they inputted in the location variable
                    location = Integer.parseInt(in.nextLine());
                }
                catch (NumberFormatException e)
                //if they're stupid enough to enter a letter, set location to 0, bringing them back to the top of the loop  
                {
                    location = 0;
                }

            }

            
            //do the following 10 times (simulating the ball going down)
            for (int line=1; line<=10; line++)
            {
                // randomly generate a move  1 or 0, for left or right
                move = numGen.nextInt(1- 0 + 1) + 0;

                // if the ball goes right, increase location by 1 
                if (move == 1) {location += 1;}
                //otherwise, decrease location by 1 
                else {location -= 1;}
                //if the ball tries to go left at spot 1, force it to go right
                if (location < 1) {location = 2;}
                //if the ball tries to go right at spot 9, force it to go left
                if (location > 9) {location = 8;}
                //tell the user the current position of the ball
                System.out.println("The ball moves towards " + results[location - 1] + "$");
                //timer for suspense
                Thread.sleep(1000);

            }
            //decrease chips by 1 each time the user plays
            chips --;
            //tell the user how much they win
            System.out.println("You've won " + results[location - 1] + " $! You have " + chips + " chips left"); 
            //increase the total amount they have won
            sum += results[location - 1];
            //reset the value of location to -1 to ask them where they wanna place their chip again
            location = - 1;
        }
        //when the game is over, thank them for playing and tell them their total earned
        System.out.println("Thanks for playing, you earned " + sum + "$!");
    }
    public static int questions (int args)
    {  //ask the user a series of questions to answer to determine how many more chips they earn.(different responses depending on how mathematically challenged the user is
       System.out.println("Answer the following questions to earn more chips: What is 1 + 1?");
        int answer = Integer.parseInt(in.nextLine());
        if (answer == 2) {System.out.println("Congratulations,you've earned yourself a chip"); chips ++;}
        else {System.out.println("https://www.mathsisfun.com/numbers/addition.html");}
        System.out.println( "What is 10 / 2");
        answer = Integer.parseInt(in.nextLine());
        if (answer == 5) {System.out.println("Congratulations,you've earned yourself a chip"); chips ++;}
        else {System.out.println("Don't worry, division can be a challenge sometimes.You still have one more question to answer");}
        System.out.println( "What is 3!");
        answer = Integer.parseInt(in.nextLine());
        if (answer ==  6){System.out.println("Congratulations,you've earned yourself a chip"); chips ++;}
        else{System.out.println("Mr.Kim would be dissapointed");}
        //return the amount of chips they earned
        return chips;
        
    }

   
}


//IGNORE CODE FOR BALL SIMULATION
/* System.out.println("");

if (line % 2 != 0) 
{
for (int i = 0; i< location   ; i++) {System.out.print(" | ");}

System.out.print("O");

for (int x = 0; x<(10 -location  ); x++) {System.out.print(" | ");}

}
else
{
System.out.print(" ");
for(int i = 0; i< location  ; i++) {System.out.print(" | ");}
System.out.print("O");
for (int x = 0; x<(9-location ); x++) {System.out.print(" | ");}


} */
