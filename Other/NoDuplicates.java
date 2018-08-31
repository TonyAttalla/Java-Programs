//Tony Attalla
//Mr.Vant Erve
//ICS4U
//October 2016
import java.util.*;
public class NoDuplicates
{
    public static void main (String[] args)
    {
        System.out.println("Enter input:");
        int numToInsert;
        boolean numExists = false;
        Random numGen = new Random();
        int[] numbers = new int[10];
        for (int i = 0; i<numbers.length; i++)
        {
            numbers[i] = -1;
        }
        for (int i = 0; i<numbers.length; i++) // do this 10 times
        {
            numToInsert = numGen.nextInt(10); //make a number to put in 

            for (int x = 0; x<numbers.length; x++) //check all 10 indexes to see if the number already exists
            {
                if (numbers[x] == numToInsert)
                {
                    numExists = true;

                }
            }
            if (numExists == false)
            {
                numbers[i] = numToInsert;
                System.out.append(numbers[i] + " ");

            }
            else
            {
                i--;
                numExists = false;

            }

        }
       

    }
}