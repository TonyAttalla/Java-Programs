/*Tony Attalla
Mr.Vant Erve
ICS4U
January 3,2017 (HAPPY NEW YEAR)*/

//import required libraries
import java.io.*;
import java.util.*;
//PLEASE REMEMBER TO RESET THE JVM BEFORE RUNNING. THE CONSOLE WON'T PRINT IF YOU DON'T DO THIS.
public class AutoClaus
{
    public static void main(String[] args)
    {
        //two array lists, one for children and one for gifts. Arraylists because I'm not sure how big they'll be.
        ArrayList<child> childrenList = new ArrayList<child>(); 
        ArrayList<String> giftsList = new ArrayList<String>();
        //make a variable to keep track of which line the readers are on. Different lines contain different information about a kid, so I need to know which line i'm on.
        int line = 0;
        //a variable to keep track of what's on the line that i'm reading
        String lineOfText;
        //two filereaders (one for NiceList and one for StockingStuffers)
        FileReader in;
        FileReader inTwo;
        //two BufferedReaders (one for NiceList and one for StockingStuffers). Program gets upset if the instantiation for these isn't in a try block since they always throw exceptions
        BufferedReader readNiceList;
        BufferedReader readStuffers;
        //take input from santa claus so he can make changes
        Scanner input = new Scanner(System.in);
        //two new file objects
        File NiceList = new File ("NiceList.txt");
        File StockingStuffers = new File("StockingStuffers.txt");
        //make strings for the line which the reader is currently reading. These need to be different in order to assign them all to a certain child
        String currentAddress ="";
        String currentName = "";
        //currentNiceness is -1 because the program will only assign a niceness to a child if it's greater than 0  (later in the main method you'll see this)
        int currentNiceness = -1;
        //by default, you want santa to shuffle the gifts( later in the main method you'll see this)
        String santasResponse = "reshuffle";
        //variables if santa wants to change a certain Kid's gift.
        String kidToChange;
        String giftToGive;
        //try the following
        try
        {

            //make two new filerreaders to access the nicelist and stockingStuffers documents
            in = new FileReader(NiceList);
            inTwo = new FileReader(StockingStuffers);
            //two new buffered readers to read the Files
            readNiceList = new BufferedReader(in);
            readStuffers = new BufferedReader(inTwo);
            //first, read the nice list. While the line it's reading isnt empty, set that value to lineOfText.
            while ((lineOfText = readNiceList.readLine()) != null)
            {
                // every fourth line (as well as the first line) is a name. set that to the currentName 
                if (line % 4 == 0 || line == 0)
                {
                    currentName = lineOfText;
                }
                //every line that divides by 4 and gives the remainder 1 (3,5,9) is the address of a child, set this to currentAddress
                else if (line % 4 ==1)
                {
                    currentAddress = lineOfText;
                }
                //every linet that divides by 4 and gives a remainder 2 is a niceness value (6,10,14 etc...)
                else if (line % 4 == 2)
                {
                    currentNiceness = Integer.parseInt(lineOfText);
                }
                //change line to reflect the line it's currently on
                line+=1;
                //if all currentNiceness, currentAddress, and currentName have values then make a new child with those given values, and add them to the array of children. gift is just
                //given as "gift" since it will be determined later on.
                //Set the variables back so that it can change them for the next kid

                if(currentNiceness > -1 & currentAddress.equals("") == false & currentName.equals("") == false)
                {
                    childrenList.add(new child(currentName,currentNiceness,currentAddress,"gift")); 
                    currentNiceness = -1;
                    currentAddress="";
                    currentName = "";
                }

            }
            //close the file/buffered readers
            readNiceList.close(); 
            in.close();
            //much simpler, add some gifts to the giftsList array using a similar method to the one above.
            while((lineOfText= readStuffers.readLine()) != null)
            {

                giftsList.add(lineOfText);

            }
            //close the file/buffered readers
            readStuffers.close();
            inTwo.close();
            //convert the two arrayLists into arrays so that i can use functions that I already know to modify and search the arrays.
            String[] gifts = giftsList.toArray(new String[giftsList.size()]);
            child[] children = childrenList.toArray(new child[childrenList.size()]);
            //ok the object of typeprintstream is supposed to "override" the print method so that it prints to a file instead of the console. However,it won't change back
            //automatically after I print to a file, so I need to make a printStream of type FileOutputStream (I think PrintStream is the parent class and FileOutputStream 
            //is the child class?) and a normal printstream for the console. Looked for explanations on StackOverflow and I'm still not 100% sure how this works.
            PrintStream out = new PrintStream(new FileOutputStream("StuffedStockings.txt")); 
            PrintStream console = System.out;
            //by default, santas gonna wanna shuffle the gifts so that's why santasResponse is set to "reshuffle" by default.
            while (santasResponse.equals("reshuffle"))
            {
                //give the magical abstract GiftGiver class the arrays of children and gifts with the boolean "true" and two null strings (you'll see why in that class' comments)
                GiftGiver.giveGifts(children,gifts,true,"","");
                //print the gifts to santa's interface using the console
                GiftGiver.printGifts(children,console);
                //ask santa if he wants to do anything else
                System.out.println("Input reshuffle or edit to make changes,or just press enter to have your gifts relocated to a file.");
                // take his input 
                santasResponse=input.nextLine();
            }
            //if santa wants to ruin a certain kid's christmas
            while (santasResponse.equals("edit"))
            {
                //ask him which kid he wants to give a gift to and what gift he wants to give to them. Set these to kidToChange and giftToGive
                System.out.println("Which kid's gift do you wish to edit?"); kidToChange = input.nextLine();
                System.out.println("Enter a gift to give (doesn't have to be from the List of stocking stuffers)"); giftToGive = input.nextLine();
                //pass the magical abstract class GiftGiver both arrays and the Boolean "false" (you'll see why). Also give it the specifics for the kid and what gift santa wants to give
                //to him
                GiftGiver.giveGifts(children,gifts,false,kidToChange,giftToGive);
                //print the gifts to santa's interface using the console
                GiftGiver.printGifts(children,console);

                //ask santa if he wants to do anything else
                System.out.println("Input reshuffle or edit to make changes,or just press enter to have your gifts relocated to a file.");
                // take his input
                santasResponse=input.nextLine();
            }
            //if santa's done making changes (not shuffling or editing) put his gifts into the StuffedStockings file passing it the array of children and the FileOutputStream "out"
            System.out.println("Your gifts have been put in a file named StuffedStockings.Thanks for using AutoClaus. See you next year.");
            //NOTE: THE FILE WILL ONLY UPDATE ONCE SANTA IS DONE MAKING CHANGES
            GiftGiver.fileGifts(children,out);

        }
        // catch some stuff in case santa loses his files.
        catch (FileNotFoundException g)
        {
            System.out.println("Are you sure you haven't misplaced your nice list or stocking stuffers? Please check your documents and try again. Make sure these files are in the same package as AutoClaus.");
        }
        catch (IOException e)
        {
            System.out.println("not found");
        }

    }
}
