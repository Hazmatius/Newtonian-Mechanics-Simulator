import java.util.*;
import java.awt.*;
import java.awt.geom.*;

public class Observer
{
    private static double alpha = 0;
    private static double beta = 0;
    private static double gamma = 0;
    
    private static boolean W = false;
    private static boolean S = false;
    
    private static boolean A = false;
    private static boolean D = false;
    
    private static boolean Q = false;
    private static boolean E = false;
    
    private static boolean relative = false;
    
    private static double delta = 0.001;
    
    public static void setW(boolean b)
    {
        W = b;
    }
    public static void setS(boolean b)
    {
        S = b;
    }
    public static void setA(boolean b)
    {
        A = b;
    }
    public static void setD(boolean b)
    {
        D = b;
    }
    public static void setQ(boolean b)
    {
        Q = b;
    }
    public static void setE(boolean b)
    {
        E = b;
    }
    
    public static void toggleRelative()
    {
        relative = !relative;
    }
    
    public static boolean isRelative()
    {
        return relative;
    }
    
    public static void turnFrame()
    {
        if(W && !S)
        {
            alpha+=delta;
        }
        if(S && !W)
        {
            alpha-=delta;
        }
        
        if(A && !D)
        {
            beta+=delta;
        }
        if(D && !A)
        {
            beta-=delta;
        }
        
        if(Q && !E)
        {
            gamma+=delta;
        }
        if(E && !Q)
        {
            gamma-=delta;
        }
    }
    
    public static Point getDrawPoint(Aster a)
    {
        double oldX = a.getX();
        double oldY = a.getY();
        double oldZ = a.getZ();
        
        double newX = 0;
        double newY = 0;
        double newZ = 0;
        
        newX = oldX;
        newY = oldY*Math.cos(alpha)-oldZ*Math.sin(alpha);
        newZ = -oldY*Math.sin(alpha)+oldZ*Math.cos(alpha);
        
        oldX = newX;
        oldY = newY;
        oldZ = newZ;
        
        newX = oldX*Math.cos(beta)+oldZ*Math.sin(beta);
        newY = oldY;
        newZ = -oldX*Math.sin(beta)+oldZ*Math.cos(beta);
        
        oldX = newX;
        oldY = newY;
        oldZ = newZ;
        
        newX = oldX*Math.cos(gamma)-oldY*Math.sin(gamma);
        newY = oldX*Math.sin(gamma)+oldY*Math.cos(gamma);
        newZ = oldZ;
        
        return new Point(Universe.SCALE*newX+Universe.xShift, Universe.SCALE*newY+Universe.yShift, 0);
    }
    
    public static Point getDrawPoint(Point d)
    {
        double oldX = d.getX();
        double oldY = d.getY();
        double oldZ = d.getZ();
        
        double newX = 0;
        double newY = 0;
        double newZ = 0;
        
        newX = oldX;
        newY = oldY*Math.cos(alpha)-oldZ*Math.sin(alpha);
        newZ = -oldY*Math.sin(alpha)+oldZ*Math.cos(alpha);
        
        oldX = newX;
        oldY = newY;
        oldZ = newZ;
        
        newX = oldX*Math.cos(beta)+oldZ*Math.sin(beta);
        newY = oldY;
        newZ = -oldX*Math.sin(beta)+oldZ*Math.cos(beta);
        
        oldX = newX;
        oldY = newY;
        oldZ = newZ;
        
        newX = oldX*Math.cos(gamma)-oldY*Math.sin(gamma);
        newY = oldX*Math.sin(gamma)+oldY*Math.cos(gamma);
        newZ = oldZ;
        
        return new Point(Universe.SCALE*newX+Universe.xShift, Universe.SCALE*newY+Universe.yShift, 0);
    }
    
    public static void altAlpha(double d)
    {
        alpha+=d;
    }
    
    public static void altBeta(double d)
    {
        beta+=d;
    }
    
    public static void altGamma(double d)
    {
        gamma+=d;
    }
}