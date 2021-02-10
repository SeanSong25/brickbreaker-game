import java.awt.*;
import javax. swing.*;
import java.awt.Font;
public class Brick
{
    private int x, y, width, height, health;
    private boolean visible = true;
    private int initialHealth;
    private Image im;
    public Brick()
    {
        x = 0;
        y = 0;
        visible = false;
        health = 0;
    }
    public Brick(int a, int b, int w, int h, int heal, Image i)
    {
        x = a;
        y = b;
        width = w;
        height = h;
        health = heal; 
        initialHealth = h;
        im = i;
    }
    public int getInitialHealth()
    {
        return initialHealth;
    }
    public int getWidth(){return width;}

    public int getHeight(){return height;}

    public int getHealth(){return health;}

    public int getX(){return x;}

    public int getY(){return y;}
    public Image getImage(){return im;}

    public boolean getVisible(){return visible;}

    public void setWidth(int w){width = w;}

    public void setHeight(int h){height = h;}

    public void setX(int a){x = a;}

    public void setY(int b){y = b;}

    public void setVisible(boolean v){visible = v;}

    public void setHealth(int h){health = h;}

    public void draw(Graphics g)
    {
        g.drawImage(im,x,y,null);
        g.setColor(Color.black);
        g.setFont(new Font("Times New Roman", Font.PLAIN,25));
        g.drawString(Integer.toString(health),x+17,y+18);
    }

    public void hit()
    {
       setHealth(getHealth()-1);
       BrickBreakerGame.setScore(BrickBreakerGame.getScore()+1);
       if(health == 0)
       {
            setVisible(false);
       }
    }
}