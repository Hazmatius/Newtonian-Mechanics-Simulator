import java.util.*;
import java.awt.*;
import java.awt.geom.*;

public class Vector
{
    private double x;
    private double y;
    private double z;
    
    private String toName = "";
    
    public Vector(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public String getToName()
    {
        return toName;
    }
    
    public void setToName(String s)
    {
        toName = s;
    }
    
    public double getXVal()
    {
        return x;
    }
    
    public double getYVal()
    {
        return y;
    }
    
    public double getZVal()
    {
        return z;
    }
    
    public static Vector add(Vector v1, Vector v2)
    {
        double newX = v1.getXVal()+v2.getXVal();
        double newY = v1.getYVal()+v2.getYVal();
        double newZ = v1.getZVal()+v2.getZVal();
        return new Vector(newX, newY, newZ);
    }
    
    public static Vector minus(Vector v2, Vector v1)
    {
        double newX = v2.getXVal()-v1.getXVal();
        double newY = v2.getYVal()-v1.getYVal();
        double newZ = v2.getZVal()-v1.getZVal();
        return new Vector(newX, newY, newZ);
    }
    
    public static Vector add(ArrayList<Vector> vectors)
    {
        double newX = 0;
        double newY = 0;
        double newZ = 0;
        for(int i=0; i<vectors.size(); i++)
        {
            newX+=vectors.get(i).getXVal();
            newY+=vectors.get(i).getYVal();
            newZ+=vectors.get(i).getZVal();
        }
        return new Vector(newX, newY, newZ);
    }
    
    public static double dot(Vector v1, Vector v2)
    {
        double newX = v1.getXVal()*v2.getXVal();
        double newY = v1.getYVal()*v2.getYVal();
        double newZ = v1.getZVal()*v2.getZVal();
        return newX+newY+newZ;
    }
    
    public double getSize()
    {
        return Math.sqrt(x*x+y*y+z*z);
    }
    
    public static Vector multiply(double d, Vector v)
    {
        return new Vector(v.getXVal()*d, v.getYVal()*d, v.getZVal()*d);
    }
    
    public void multiply(double d)
    {
        x*=d;
        y*=d;
        z*=d;
    }
    
    public Vector getUnit()
    {
        double size = getSize();
        return new Vector(x/size, y/size, z/size);
    }
    
    public void draw(Graphics2D g2, double x0, double y0, double z0)
    {
        double forceScale = 1.0E12;
        Point p0 = new Point(x0, y0, z0);
        Point p1 = new Point(x0+x/forceScale, y0+y/forceScale, z0+z/forceScale);
        Point p2 = Observer.getDrawPoint(p0);
        Point p3 = Observer.getDrawPoint(p1);
        Polygon line = new Polygon();
        line.addPoint((int)p2.getX(), (int)p2.getY());
        line.addPoint((int)p3.getX(), (int)p3.getY());
        g2.setColor(Color.RED);
        g2.draw(line);
        //g2.setColor(Color.WHITE);
        //g2.drawString(toName, (int)p3.getX(), (int)p3.getY());
    }
}