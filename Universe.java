import java.util.*;
import java.awt.*;

public class Universe
{
    private ArrayList<Aster> asters = new ArrayList<Aster>();
    public static double SCALE = 1.0/500000000;
    public static double TIME = 1;
    public static int xShift;
    public static int yShift;
    
    public boolean drawPaths = true;
    
    private double centerX;
    private double centerY;
    
    private Aster relativeAster;
    
    public Universe(int n)
    {
        this.addAsters(n);
    }
    
    public void setRelativeAster(Aster a)
    {
        relativeAster = a;
    }
    
    public Aster getRelativeAster()
    {
        return relativeAster;
    }
    
    public void purgeType(String desc)
    {
        for(int i=0; i<asters.size(); i++)
        {
            if(asters.get(i).getDescription().equals(desc))
            {
                asters.remove(i);
                i--;
            }
        }
    }
    
    public Vector getMomentum()
    {
        ArrayList<Vector> momentums = new ArrayList<Vector>();
        for(int i=0; i<asters.size(); i++)
        {
            Aster a = asters.get(i);
            double mass = a.getMass();
            double vx = a.getVelocity().getXVal();
            double vy = a.getVelocity().getYVal();
            double vz = a.getVelocity().getZVal();
            Vector p = new Vector(mass*vx, mass*vy, mass*vz);
            momentums.add(p);
        }
        Vector momentum = Vector.add(momentums);
        return momentum;
    }
    
    public double getTotalMass()
    {
        double mass = 0;
        for(int i=0; i<asters.size(); i++)
        {
            mass+=asters.get(i).getMass();
        }
        return mass;
    }
    
    public double getCenterMassX()
    {
        double centerMassX = 0;
        for(int i=0; i<asters.size(); i++)
        {
            centerMassX+=(asters.get(i).getMass()*asters.get(i).getX());
        }
        return centerMassX/getTotalMass();
    }
    
    public double getCenterMassY()
    {
        double centerMassY = 0;
        for(int i=0; i<asters.size(); i++)
        {
            centerMassY+=(asters.get(i).getMass()*asters.get(i).getY());
        }
        return centerMassY/getTotalMass();
    }
    
    public void addAster()
    {
        double mass = (Math.random())*(2E30)+(5E22);
        double radius = (Math.random())*(7E8)+(1.5E6);
        double x = (Math.random()*1000)/SCALE;
        //System.out.println(x);
        double y = (Math.random()*1000)/SCALE;
        //System.out.println(y);
        double z = (Math.random()*1000)/SCALE;
        Aster a = new Aster(this, mass, radius, x, y, z);
        asters.add(a);
    }
    
    public void addAsters(int n)
    {
        for(int i=0; i<n; i++)
        {
            this.addAster();
        }
    }
    
    public void addAster(Aster a)
    {
        asters.add(a);
    }
    
    public void preMove()
    {
        for(int i=0; i<asters.size(); i++)
        {
            asters.get(i).preMove(TIME, asters);
        }
    }
    
    public void Move()
    {
        for(int i=0; i<asters.size(); i++)
        {
            asters.get(i).Move(TIME);
        }
    }
    
    public void draw(Graphics2D g2)
    {
        for(int i=0; i<asters.size(); i++)
        {
            asters.get(i).draw(g2);
        }
        Rectangle scale = new Rectangle(100, 100, 100, 0);
        g2.setColor(Color.WHITE);
        g2.draw(scale);
        g2.drawString(""+(100/SCALE/1000)+" km", 102, 98);
//         double centerMassX = getCenterMassX();
//         double centerMassY = getCenterMassY();
//         int cmx = (int)(centerMassX*SCALE+xShift);
//         int cmy = (int)(centerMassY*SCALE+yShift);
//         int n = 4;
//         Rectangle r1 = new Rectangle(cmx, cmy-n, 0, 2*n);
//         Rectangle r2 = new Rectangle(cmx-n, cmy, 2*n, 0);
//         g2.setColor(Color.RED);
//         g2.draw(r1);
//         g2.draw(r2);
        double max = getMaxAcceleration();
        //System.out.println(TIME);
        if(max!=Double.MIN_VALUE)
        {
            TIME=500/getMaxAcceleration();
        }
    }
    
    public void shiftScale(double mult)
    {
        SCALE*=mult;
    }
    
    public void shiftX(int n)
    {
        xShift+=n;
    }
    
    public void shiftY(int n)
    {
        yShift+=n;
    }
    
    public void findCenter(int width, int height)
    {
        centerX = (width/2-xShift)/SCALE;
        centerY = (height/2-yShift)/SCALE;
    }
    
    public void center(int width, int height)
    {
        xShift = (int)(width/2-centerX*SCALE);
        yShift = (int)(height/2-centerY*SCALE);
    }
    
    public double getDistance(double x, double y, Aster a)
    {
        Point op = Observer.getDrawPoint(a);
        double xDist = (op.getX())-x;
        double yDist = (op.getY())-y;
        double distance = Math.sqrt(xDist*xDist + yDist*yDist);
        return distance;
    }
    
    public Aster getClosestAster(double x, double y)
    {
        Aster closeAster = asters.get(0);
        double dist = getDistance(x, y, closeAster);
        for(int i=0; i<asters.size(); i++)
        {
            double newDist = getDistance(x, y, asters.get(i));
            if(newDist<dist)
            {
                dist = newDist;
                closeAster = asters.get(i);
            }
        }
        return closeAster;
    }
    
    public void setCenter(double theX, double theY)
    {
        centerX = (theX-xShift)/SCALE;
        centerY = (theY-yShift)/SCALE;
    }
    
    public double getMaxAcceleration()
    {
        double max = Double.MIN_VALUE;
        for(Aster a : asters)
        {
            if(a.acceleration>max)
            {
                max = a.acceleration;
            }
        }
        return max;
    }
    
    public void setTime()
    {
        
    }
}