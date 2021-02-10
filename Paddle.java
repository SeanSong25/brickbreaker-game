import java.awt.*;
public class Paddle extends Brick
{
    private int speed;
    public Paddle(int x, int y, int width, int height, int h,Image i, int sp)
    {
        super(x,y,width,height,h,i);
        speed = sp;
    }
    public int getSpeed()
    {
        return speed;
    }
    public void setSpeed(int s)
    {
        speed = s;
    }
    public void hit()
    {
        setVisible(true);
    }
    public void draw(Graphics g)
    {
        g.drawImage(getImage(),getX(),getY(),null);
        
    }
    public void moveRight()
    {
        if(getX()+getWidth()<=495)
            setX(getX()+speed);
    }
    public void moveLeft()
    {
        if(getX()>=5)
            setX(getX()-speed);
    }
}