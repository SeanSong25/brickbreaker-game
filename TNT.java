import java.awt.*;
public class TNT extends Brick
{
    private Brick[] neighbors = new Brick[8];
    public TNT(int x, int y, int width, int height, int h,Image i, Brick[] b)
    {
        super(x,y,width,height,h,i);
        for(int ind = 0; ind<8; ind++)
        {
            neighbors[ind]=b[ind];
        }
    }
     public void hit()
    {
       setHealth(getHealth()-1);
       BrickBreakerGame.setScore(BrickBreakerGame.getScore()+1);
       if(getHealth() == 0)
       {
            setVisible(false);
       }
       for(int i = 0; i<8; i++)
       {
           if(neighbors[i].getVisible())
           {
               neighbors[i].hit();
            }
        }
    }
}