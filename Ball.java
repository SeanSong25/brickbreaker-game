import java.awt.*;
import javax. swing.*;
public class Ball
{
    private int x, y, size, dx, dy;
    private Color color;
    public Ball(int x, int y, int size, int dx, int dy, Color color)
    {
        this.x = x;
        this.y = y;
        this.size = size;
        this.dx = dx;
        this.dy = dy;
        this.color = color;
    }

    public Ball()
    {
        x = 0;
        y = 0;
        size = 20;
        dx = 0;
        dy = 0;
        color = new Color(255,0,0);//red
    }

    public int getX(){return x;}

    public int getY(){return y;}

    public int getDx(){return dx;}  

    public int getDy(){return dx;}

    public int getSize(){return size;}

    public Color getColor(){return color;}

    public void setX(int a){x = a;}

    public void setY(int a){y = a;}

    public void setDx(int a){dx = a;}

    public void setDy(int a){dy = a;}

    public void setSize(int a){size = a;}

    public void setColor(Color c){color = c;}

    public void move(Graphics g)
    { 
        x+=dx;
        y+=dy;
        if(x<5)
        {
            x = 5;
            dx = -dx;
        }
        else if(x+size>495)
        {
            x = 495 - size;
            dx = -dx;
        }

        if(y<5)
        {
            y = 5;
            dy= -dy;
        }
        else if(y+size>495)
        {
            y = 495-size;
            dy = 0;
            dx = 0;
        }
        draw(g);
    }

    public void collidesWith(Brick b)
    {
        Rectangle ball = new Rectangle(x,y,size,size);
        Rectangle brick = new Rectangle(b.getX(),b.getY(),b.getWidth(),b.getHeight());
        
        if(ball.intersects(brick))
        {
            b.hit();
            if(x<b.getX()||x+size>b.getX()+b.getWidth())
            {
                if(x<=b.getX())
                {
                    x = b.getX()-size;        
                }
                else
                {
                    x = b.getX()+b.getWidth();
                }
                dx = -dx;
                
            }
            else
            {    
                
                if(y+size>b.getY()+b.getHeight())
                {
                    y = b.getY()+b.getHeight();
                }
                else 
                {
                    y = b.getY()-size;
                }
                dy = -dy;
                
            }
        }
        

    }

    public void draw(Graphics g)
    {
        g.setColor(color);
        g.fillOval(x,y,size,size);
    }
}