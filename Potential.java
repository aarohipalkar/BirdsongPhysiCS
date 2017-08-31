import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Scrollbar;
import java.io.PrintStream;







public class Potential
  extends Applet
{
  static int height = 600;
  static int width = 900;
  double r1 = 20.0D;
  double rs = 10.0D;
  
  static double velocx;
  static double velocy;
  static double x1 = 300.0D;
  static double y1 = height / 2;
  
  static double x2 = 450.0D;
  static double y2 = height - 150;
  
  static double x3 = 450.0D;
  static double y3 = height - (height - 150);
  
  boolean go = false;
  int dx = 10;
  
  double tim;
  double dtim = 475.0D;
  
  public double[] x;
  
  static double q1 = -1.0D;
  static double q2 = 10.0D;
  static double q3 = 10.0D;
  
  int maxtim = 369000;
  
  int bconecx;
  int bconecy;
  double dist1;
  double dist2;
  double dist3;
  double compx;
  double compy;
  double comp;
  int istop;
  int nstop = 60;
  
  double dummy;
  int numline = 4;
  double distmin = 5.0D;
  double distm;
  static double emagmin = 1.0E-8D;
  
  int si;
  int sii;
  Circle cone;
  Circle ctwo;
  Circle cthree;
  Scrollbar dxScroll = new Scrollbar(0, dx, 1, 5, 50); Scrollbar vxScroll = new Scrollbar(0, (int)velocx, 1, 0, 700); Scrollbar vyScroll = new Scrollbar(0, (int)velocy, 1, 0, 200);
  
  public Label dxLabel = new Label("Faster =   " + dx); public Label vxLabel = new Label("vx = " + velocx); public Label vyLabel = new Label("vy = " + velocy);
  
  Button restartbutton = new Button("Restart"); Button gobutton = new Button("Go");
  



  double bq1 = q1;
  double bq2 = q2;
  double bq3 = q3;
  
  int bdx = dx;
  int bnumline = numline;
  
  double bx1 = x1;
  double by1 = y1;
  
  double bvelocx = velocx;
  double bvelocy = velocy;
  
  double bx2 = x2;
  double by2 = y2;
  
  double bx3 = x3;
  double by3 = y3;
  


  public void init()
  {
    setBackground(Color.white);
    



    restartbutton.setForeground(Color.black);
    restartbutton.setBackground(Color.lightGray);
    
    gobutton.setForeground(Color.black);
    gobutton.setBackground(Color.lightGray);
    
    dxScroll.setForeground(Color.black);
    dxScroll.setBackground(Color.lightGray);
    
    vxScroll.setForeground(Color.black);
    vxScroll.setBackground(Color.lightGray);
    
    vyScroll.setForeground(Color.black);
    vyScroll.setBackground(Color.lightGray);
    





    setLayout(new BorderLayout());
    Panel localPanel = new Panel();
    localPanel.setLayout(new FlowLayout(1));
    
    localPanel.add(dxScroll);
    localPanel.add(dxLabel);
    
    localPanel.add(vxScroll);
    localPanel.add(vxLabel);
    
    localPanel.add(vyScroll);
    localPanel.add(vyLabel);
    
    localPanel.add(gobutton);
    localPanel.add(restartbutton);
    add("North", localPanel);
  }
  

  public void paint(Graphics paramGraphics)
  {
    setBackground(Color.white);
    
    x = new double[4];
    
    x[0] = 300.0D;
    x[1] = (height / 2);
    x[2] = velocx;
    x[3] = velocy;
    
    double[] arrayOfDouble = new double[4];
    
    arrayOfDouble[0] = x[0];
    arrayOfDouble[1] = x[1];
    arrayOfDouble[2] = x[2];
    arrayOfDouble[3] = x[3];
    
    cone = new Circle(0, (int)rs, (int)x1, (int)y1);
    ctwo = new Circle(0, (int)r1, (int)x2, (int)y2);
    cthree = new Circle(0, (int)r1, (int)x3, (int)y3);
    
    RKDemo localRKDemo = new RKDemo(tim, x, 0.01D);
    Line localLine = new Line(0.0D, 0.0D, 0.0D, 0.0D);
    
    if (go == true)
    {
      while ((tim < maxtim) && (x1 > -300.0D) && (x1 < width + 600) && (y1 > 0.0D) && (y1 < height))
      {
        comp = 1.0D;
        
        cone.cx = ((int)x1);
        cone.cy = ((int)y1);
        
        x1 = arrayOfDouble[0];
        y1 = arrayOfDouble[1];
        
        if (q1 < 0.0D)
        {
          paramGraphics.setColor(Color.black);
          paramGraphics.fillOval((int)(x1 - rs / 2.0D), (int)(y1 - rs / 2.0D), (int)rs, (int)rs);
        }
        else if (q1 > 0.0D)
        {
          paramGraphics.setColor(Color.red);
          paramGraphics.fillOval((int)(x1 - rs / 2.0D), (int)(y1 - rs / 2.0D), (int)rs, (int)rs);
        }
        
        if (q2 < 0.0D)
        {
          paramGraphics.setColor(Color.black);
          paramGraphics.fillOval((int)(x2 - r1 / 2.0D), (int)(y2 - r1 / 2.0D), (int)r1, (int)r1);
        }
        else if (q2 > 0.0D)
        {
          paramGraphics.setColor(Color.red);
          paramGraphics.fillOval((int)(x2 - r1 / 2.0D), (int)(y2 - r1 / 2.0D), (int)r1, (int)r1);
        }
        
        if (q3 < 0.0D)
        {
          paramGraphics.setColor(Color.black);
          paramGraphics.fillOval((int)(x3 - r1 / 2.0D), (int)(y3 - r1 / 2.0D), (int)r1, (int)r1);
        }
        else if (q3 > 0.0D)
        {
          paramGraphics.setColor(Color.red);
          paramGraphics.fillOval((int)(x3 - r1 / 2.0D), (int)(y3 - r1 / 2.0D), (int)r1, (int)r1);
        }
        
        ang = 0.39269908169872414D;
        comp = 1.0D;
        istop = 0;
        
        while (ang < 6.283185307179586D)
        {
          sx = (x1 - 10.0D * Math.cos(ang));
          sy = (y1 - 10.0D * Math.sin(ang));
          
          dist1 = Math.sqrt(Math.pow(sx - x1, 2.0D) + Math.pow(sy - y1, 2.0D));
          dist2 = Math.sqrt(Math.pow(sx - x2, 2.0D) + Math.pow(sy - y2, 2.0D));
          dist3 = Math.sqrt(Math.pow(sx - x3, 2.0D) + Math.pow(sy - y3, 2.0D));
          distm = 9.0D;
          
          while ((istop < nstop) && (dist1 > distm) && (dist2 > distm) && (dist3 > distm) && (sx > 0.0D) && (sx < width) && (sy > 0.0D) && (sy < height))
          {
            dist1 = Math.sqrt(Math.pow(sx - x1, 2.0D) + Math.pow(sy - y1, 2.0D));
            dist2 = Math.sqrt(Math.pow(sx - x2, 2.0D) + Math.pow(sy - y2, 2.0D));
            dist3 = Math.sqrt(Math.pow(sx - x3, 2.0D) + Math.pow(sy - y3, 2.0D));
            
            compx = (-(q2 * (sx - x2) / Math.pow(dist2, 3.0D) + q1 * (sx - x1) / Math.pow(dist1, 3.0D) + q3 * (sx - x3) / Math.pow(dist3, 3.0D)));
            compy = (-(q2 * (sy - y2) / Math.pow(dist2, 3.0D) + q1 * (sy - y1) / Math.pow(dist1, 3.0D) + q3 * (sy - y3) / Math.pow(dist3, 3.0D)));
            
            comp = Math.sqrt(Math.pow(compx, 2.0D) + Math.pow(compy, 2.0D));
            
            if (comp < emagmin) ex = (sx + dx); else
              ex = (sx + compx / comp * dx);
            if (comp < emagmin) ey = (sy + dx); else {
              ey = (sy + compy / comp * dx);
            }
            paramGraphics.setColor(new Color(0, 190, 0));
            paramGraphics.drawLine((int)sx, (int)sy, (int)ex, (int)ey);
            
            sx = ex;
            sy = ey;
            istop += 1;
            distm = distmin;
          }
          ang += 0.7853981633974483D;
          comp = 1.0D;
          istop = 0;
        }
        

        ang = 0.39269908169872414D;
        comp = 1.0D;
        istop = 0;
        while (ang < 6.283185307179586D)
        {


          sx = (x2 + 10.0D * Math.cos(ang));
          sy = (y2 - 10.0D * Math.sin(ang));
          
          dist1 = Math.sqrt(Math.pow(sx - x1, 2.0D) + Math.pow(sy - y1, 2.0D));
          dist2 = Math.sqrt(Math.pow(sx - x2, 2.0D) + Math.pow(sy - y2, 2.0D));
          dist3 = Math.sqrt(Math.pow(sx - x3, 2.0D) + Math.pow(sy - y3, 2.0D));
          distm = 9.0D;
          
          while ((istop < nstop) && (dist1 > distm) && (dist2 > distm) && (dist3 > distm) && (sx < width) && (sy < height))
          {

            dist1 = Math.sqrt(Math.pow(sx - x1, 2.0D) + Math.pow(sy - y1, 2.0D));
            dist2 = Math.sqrt(Math.pow(sx - x2, 2.0D) + Math.pow(sy - y2, 2.0D));
            dist3 = Math.sqrt(Math.pow(sx - x3, 2.0D) + Math.pow(sy - y3, 2.0D));
            
            compx = (q2 * (sx - x2) / Math.pow(dist2, 3.0D) + q1 * (sx - x1) / Math.pow(dist1, 3.0D) + q3 * (sx - x3) / Math.pow(dist3, 3.0D));
            compy = (q2 * (sy - y2) / Math.pow(dist2, 3.0D) + q1 * (sy - y1) / Math.pow(dist1, 3.0D) + q3 * (sy - y3) / Math.pow(dist3, 3.0D));
            
            comp = Math.sqrt(Math.pow(compx, 2.0D) + Math.pow(compy, 2.0D));
            
            if (comp < emagmin) ex = (sx - dx); else
              ex = (sx + compx / comp * dx);
            if (comp < emagmin) ey = (sy - dx); else {
              ey = (sy + compy / comp * dx);
            }
            paramGraphics.setColor(new Color(0, 190, 0));
            paramGraphics.drawLine((int)sx, (int)sy, (int)ex, (int)ey);
            
            sx = ex;
            sy = ey;
            distm = distmin;
            istop += 1;
          }
          
          ang += 0.7853981633974483D;
          comp = 1.0D;
          istop = 0;
        }
        
        ang = 0.39269908169872414D;
        comp = 1.0D;
        istop = 0;
        while (ang < 6.283185307179586D)
        {

          sx = (x3 + 10.0D * Math.cos(ang));
          sy = (y3 + 10.0D * Math.sin(ang));
          
          dist1 = Math.sqrt(Math.pow(sx - x1, 2.0D) + Math.pow(sy - y1, 2.0D));
          dist2 = Math.sqrt(Math.pow(sx - x2, 2.0D) + Math.pow(sy - y2, 2.0D));
          dist3 = Math.sqrt(Math.pow(sx - x3, 2.0D) + Math.pow(sy - y3, 2.0D));
          comp = 1.0D;
          distm = 9.0D;
          
          while ((istop < nstop) && (dist1 > distm) && (dist2 > distm) && (dist3 > distm) && (sx < width) && (sy < height))
          {

            dist1 = Math.sqrt(Math.pow(sx - x1, 2.0D) + Math.pow(sy - y1, 2.0D));
            dist2 = Math.sqrt(Math.pow(sx - x2, 2.0D) + Math.pow(sy - y2, 2.0D));
            dist3 = Math.sqrt(Math.pow(sx - x3, 2.0D) + Math.pow(sy - y3, 2.0D));
            
            compx = (q2 * (sx - x2) / Math.pow(dist2, 3.0D) + q1 * (sx - x1) / Math.pow(dist1, 3.0D) + q3 * (sx - x3) / Math.pow(dist3, 3.0D));
            compy = (q2 * (sy - y2) / Math.pow(dist2, 3.0D) + q1 * (sy - y1) / Math.pow(dist1, 3.0D) + q3 * (sy - y3) / Math.pow(dist3, 3.0D));
            
            comp = Math.sqrt(Math.pow(compx, 2.0D) + Math.pow(compy, 2.0D));
            
            if (comp < emagmin) ex = (sx + dx); else
              ex = (sx + compx / comp * dx);
            if (comp < emagmin) ey = (sy + dx); else {
              ey = (sy + compy / comp * dx);
            }
            paramGraphics.setColor(new Color(0, 190, 0));
            paramGraphics.drawLine((int)sx, (int)sy, (int)ex, (int)ey);
            
            sx = ex;
            sy = ey;
            istop += 1;
          }
          ang += 0.7853981633974483D;
          istop = 0;
          distm = distmin;
        }
        bconecx = cone.cx;
        bconecy = cone.cy;
        
        localRKDemo.iterate();
        arrayOfDouble[0] = x[0];arrayOfDouble[1] = x[1];arrayOfDouble[2] = x[2];arrayOfDouble[3] = x[3];
        
        tim += dtim;
        
        paramGraphics.setColor(Color.white);
        paramGraphics.fillOval((int)(x1 - rs / 2.0D), (int)(y1 - rs / 2.0D), (int)rs, (int)rs);
        paramGraphics.fillRect(0, 30, 900, 600);
      }
    }
    
    if (q1 < 0.0D)
    {
      paramGraphics.setColor(Color.black);
      paramGraphics.fillOval((int)(x1 - rs / 2.0D), (int)(y1 - rs / 2.0D), (int)rs, (int)rs);
    }
    else if (q1 > 0.0D)
    {
      paramGraphics.setColor(Color.red);
      paramGraphics.fillOval((int)(x1 - rs / 2.0D), (int)(y1 - rs / 2.0D), (int)rs, (int)rs);
    }
    
    if (q2 < 0.0D)
    {
      paramGraphics.setColor(Color.black);
      paramGraphics.fillOval((int)(x2 - r1 / 2.0D), (int)(y2 - r1 / 2.0D), (int)r1, (int)r1);
    }
    else if (q2 > 0.0D)
    {
      paramGraphics.setColor(Color.red);
      paramGraphics.fillOval((int)(x2 - r1 / 2.0D), (int)(y2 - r1 / 2.0D), (int)r1, (int)r1);
    }
    
    if (q3 < 0.0D)
    {
      paramGraphics.setColor(Color.black);
      paramGraphics.fillOval((int)(x3 - r1 / 2.0D), (int)(y3 - r1 / 2.0D), (int)r1, (int)r1);
    }
    else if (q3 > 0.0D)
    {
      paramGraphics.setColor(Color.red);
      paramGraphics.fillOval((int)(x3 - r1 / 2.0D), (int)(y3 - r1 / 2.0D), (int)r1, (int)r1);
    }
  }
  















  public boolean mouseDown(Event paramEvent, int paramInt1, int paramInt2)
  {
    if ((paramInt1 < x2 + r1 / 2.0D) && (paramInt1 > x2 - r1 / 2.0D) && (paramInt2 < y2 + r1 / 2.0D) && (paramInt2 > y2 - r1 / 2.0D))
    {
      ctwo.hit = 1;
      System.out.println("Belt points left,  !");
    }
    
    if ((paramInt1 < x3 + r1 / 2.0D) && (paramInt1 > x3 - r1 / 2.0D) && (paramInt2 < y3 + r1 / 2.0D) && (paramInt2 > y3 - r1 / 2.0D))
    {
      cthree.hit = 1;
      System.out.println("Belt points right, you're alright!");
    }
    
    return true;
  }
  
  public boolean mouseUp(Event paramEvent, int paramInt1, int paramInt2)
  {
    ctwo.hit = 0;
    cthree.hit = 0;
    return true;
  }
  



  public boolean mouseDrag(Event paramEvent, int paramInt1, int paramInt2)
  {
    if (ctwo.hit == 1) { x2 = paramInt1;y2 = paramInt2;repaint(); }
    if (cthree.hit == 1) { x3 = paramInt1;y3 = paramInt2;repaint(); }
    return true;
  }
  
  public boolean handleEvent(Event paramEvent)
  {
    Object localObject = target;
    
    if (localObject == dxScroll)
    {
      dx = dxScroll.getValue();
      dxLabel.setText("Faster =  " + dx);
    }
    else if (localObject == vxScroll)
    {
      velocx = vxScroll.getValue();
      vxLabel.setText("vx = " + velocx);
    }
    else if (localObject == vyScroll)
    {
      velocy = vyScroll.getValue();
      vyLabel.setText("vy = " + velocy);
    }
    
    if (id == 1001)
    {

      if (localObject == restartbutton)
      {
        ctwo.hit = 0;
        cthree.hit = 0;
        



        x1 = bx1;
        y1 = by1;
        
        x2 = bx2;
        y2 = by2;
        
        x3 = bx3;
        y3 = by3;
        
        cone.cx = ((int)bx1);
        cone.cy = ((int)by1);
        
        ctwo.cx = ((int)bx2);
        ctwo.cy = ((int)by2);
        
        cthree.cx = ((int)bx3);
        cthree.cy = ((int)by3);
        
        tim = 0.0D;
        


        numline = bnumline;
        







        repaint();
        go = false;

      }
      else if (localObject == gobutton)
      {
        go = true;
        repaint();
      }
    }
    return super.handleEvent(paramEvent);
  }
  
  public Potential() {}
}
