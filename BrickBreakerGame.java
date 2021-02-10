import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;

public class BrickBreakerGame extends JPanel implements KeyListener, ActionListener, MouseListener
{
    private Ball ball;
    private Timer timer;
    private boolean play;
    private Brick[][] brick = new Brick[5][7];
    private Paddle paddle;
    private int screen;
    private Image bg,bg2,diamond,dirt,iron,pd,tnt,screen2;
    private boolean restart = false;
    private static int score = 0;
    public static int getScore(){return score;}

    public static void setScore(int x){score = x;}

    public BrickBreakerGame()
    {
        ball = new Ball(250,300,10,1,-1, Color.green);
        addKeyListener(this);
        addMouseListener(this);
        timer = new Timer(20,this);
        timer.start();
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        screen = 1;
        play = false;
        try
        {
            bg = ImageIO.read(new File("spacebg.jpg"));
            bg2 = ImageIO.read(new File("spacebg2.jpg"));
            this.diamond = ImageIO.read(new File("diamond.jpg"));
            this.dirt = ImageIO.read(new File("dirt.jpg"));
            this.tnt = ImageIO.read(new File("tnt.jpg"));
            this.iron = ImageIO.read(new File("iron.jpg"));
            this.pd = ImageIO.read(new File("pd.jpg"));
            this.screen2 = ImageIO.read(new File("screen3.jpg"));
        }
        catch(IOException e)
        {
        }

        
    }

    public void actionPerformed(ActionEvent e)
    {
        if(play)
            repaint();
    }
    public TNT createTNT(int a, int b, int i, int j)
    {
        Brick[] br = new Brick[8];
        if(brick[i-1][j-1].getVisible())
            br[0]=brick[i-1][j-1];
        else
            br[0]=new Brick();
        if(brick[i][j-1].getVisible())
            br[1]=brick[i][j-1];
        else
            br[1]=new Brick();
        if(brick[i+1][j-1].getVisible())
            br[2]=brick[i+1][j-1];
        else
            br[2]=new Brick();
        if(brick[i-1][j].getVisible())
            br[3]=brick[i-1][j];
        else
            br[3]=new Brick();
        if(brick[i+1][j].getVisible())
            br[4]=brick[i+1][j];
        else
            br[4]=new Brick();
        if(brick[i-1][j+1].getVisible())
            br[5]=brick[i-1][j+1];
        else
            br[5]=new Brick();
        if(brick[i][j+1].getVisible())
            br[6]=brick[i][j+1];
        else
            br[6]=new Brick();
        if(brick[i+1][j+1].getVisible())
            br[7]=brick[i+1][j+1];
        else
            br[7]=new Brick();
        return new TNT(a,b,45,18,1,tnt,br);
    }

    public Brick createBrick(int a, int b, int i, int j)
    {
        int x =(int)(Math.random()*10)+1;
        
        if(x <=6 )
        {
            return new Brick(a,b,45,18,2,dirt);
        }
        else if(x<=8)
        {
            return new Brick(a,b,45,18,4,iron);
        }
        else 
        {
            return new Brick(a,b,45,18,8,diamond);
        }

    }

    public void paint(Graphics g)
    {  
        if(screen == 1)
        {
            drawStartScreen(g);
        }
        else if(screen ==2)
        {
            drawMode(g);
        }
        else if(screen == 3)
        {
            drawPlayScreen(g);
        }
    }
    
    public void drawMode(Graphics g)
    {
        g.drawImage(screen2,0,0,null);
    }

    public void drawPlayScreen(Graphics g)
    {
        g.setColor(Color.white);
        g.fillRect(0,0,500,500);
        g.drawImage(bg2,5,5,null);
        ball.move(g);
        for(int i = 0; i<5; i++)
        {
            for(int j = 0; j<7; j++)
            {
                if(brick[i][j].getVisible())
                {
                    brick[i][j].draw(g);                    
                    ball.collidesWith(brick[i][j]); 
                }

            }
        }
        paddle.draw(g);
        ball.collidesWith(paddle);
        g.setFont(new Font("Times New Roman", Font.BOLD,40));
        g.drawString((Integer.toString(score)),400,100);
        boolean flag = true;
        for(int i = 0; i<5; i++)
        {
            for(int j = 0; j<7; j++)
            {
                if(brick[i][j].getVisible())
                {
                    flag = false;
                }
            }
        }
        if(flag)
        {
            ball.setDx(0);
            ball.setDy(0);
        }
        if(ball.getDy()==0&&ball.getDx()==0)
        {
             drawEndScreen(g);
        }
    }

    public void drawEndScreen(Graphics g)
    {
        g.setColor(Color.orange);
        g.setFont(new Font("Times New Roman", Font.BOLD,40));
        g.drawString(("Click Anywhere to Restart"),20,250);
        g.drawString(("Your Final Score is: "+Integer.toString(score)),20,400);
        restart = true;
        ball.setX(250);
        ball.setY(300);
    }

    public void drawStartScreen(Graphics g)
    {
        g.drawImage(bg,0,0,null);
        for(int i = 0; i<5; i++)
        {
            for(int j = 0; j<7; j++)
            {
                if(!(i==1&&(j==1||j==3||j==5)))
                {
                    brick[i][j] = createBrick(55+55*j,100+20*i, i, j);
                }
            }
        }
        brick[1][1]=createTNT(55+55*1,100+20*1, 1, 1);
        brick[1][3]=createTNT(55+55*3,100+20*1, 1, 3);
        brick[1][5]=createTNT(55+55*5,100+20*1, 1, 5);
        paddle = new Paddle(255,470,100,10,5,pd,20);
    }

    public void mouseClicked(MouseEvent e)
    {
        int x = e.getX();
        int y = e.getY();
        if(screen == 1)
        {
            screen = 2;
            repaint();
        }
        else if(screen == 2)
        {
            if(x>155&&x<380&&y>260&&y<330)
            {
                ball.setDx(2);
                ball.setDy(-2);
                screen = 3;
                play = false;
                setScore(0);
                repaint();
            }
            else if(x>155&&x<380&&y>380&&y<450)
            {
                ball.setDx(5);
                ball.setDy(-5);
                screen = 3;
                play = false;
                setScore(0);
                repaint();
            }
        }
        else if(screen == 3 && restart)
        {
            screen = 1;
        }
    }

    public void mousePressed(MouseEvent e)
    {        
    }

    public void mouseReleased(MouseEvent e)
    {       
    }

    public void mouseEntered(MouseEvent e)
    {        
    }

    public void mouseExited(MouseEvent e)
    {    
    }

    public void keyPressed(KeyEvent e)
    {
        play = true;
        if(e.getKeyCode()==KeyEvent.VK_RIGHT)
        {
            paddle.moveRight();
        }
        else if(e.getKeyCode()==KeyEvent.VK_LEFT)
        {
            paddle.moveLeft();
        }

    }

    public void keyTyped(KeyEvent e)
    {
    }

    public void keyReleased(KeyEvent e)
    {
    }
}