import becker.robots.*;
public class SuperBot extends Robot
{   
    public SuperBot(City c, int s, int a, Direction d, int b)
    {
        super (c,s,a,d,b);
    }

    public void turnRight()
    {
        for(int i = 0; i <3; i++)
        {
            super.turnLeft();
        }
    }

    public void move()
    {
        if (super.frontIsClear() == true )
        {
            super.move();
        }
        else 
        {
            System.out.println("Sorry. Can't move");
        }
    }

    public void move(int timesToMove)
    {
        for( int i = 0; i< timesToMove; i++)
        {
            if (super.frontIsClear() == true )
            {
                super.move();
            }
            else 
            {
                System.out.println("Sorry. Can't move");
            } 
        }

    }

    public void faceRight()
    {
        if (super.getDirection() == Direction.NORTH) {this.turnRight();}
        else if  (super.getDirection() == Direction.SOUTH) {this.turnLeft();}
        else if (super.getDirection() == Direction.WEST) {this.turnRight();}
    }

    public void moveRight()
    {
        this.faceRight();
        this.move();
    }

    public void moveLeft()
    {
        this.faceRight();
        this.turnLeft();
        this.turnLeft();
        this.move();
    }

    public void moveDown()
    {
        this.faceRight();
        this.turnRight();
        this.move();
    }

    public void moveUp()
    {
        this.faceRight();
        this.turnLeft();
        this.move();
    }

}
