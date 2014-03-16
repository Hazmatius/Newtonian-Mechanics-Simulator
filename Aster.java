import java.util.*;
import java.awt.*;
import java.awt.geom.*;

public class Aster
{
    private Universe universe;
    private double mass;
    private double radius;
    private double density;
    private double volume;
    private Vector velocity = new Vector(0, 0, 0);
    private Vector force = new Vector(0, 0, 0);
    private ArrayList<Vector> forces = new ArrayList<Vector>();
    private double x;
    private double y;
    private double z;
    ArrayList<Point> path = new ArrayList<Point>();
    private String name;
    private String desc;
    public double acceleration;
    
    private int[] dimensions;
    
    public Aster(Universe universe, double mass, double radius, double x, double y, double z)
    {
        this.universe = universe;
        this.mass = mass;
        this.radius = radius;
        this.x = x;
        this.y = y;
        this.z = z;
        volume = (4.0/3.0)*Math.PI*radius*radius*radius;
        density = mass/volume;
        dimensions = new int[]{0};
    }
    
    public int[] getDims()
    {
        return dimensions;
    }
    
    public void setDims(int[] dims)
    {
        dimensions = dims;
    }
    
    public boolean sharedDim(Aster a)
    {
        boolean shared = false;
        for(int i=0; i<dimensions.length; i++)
        {
            for(int j=0; j<a.getDims().length; j++)
            {
                shared = shared || dimensions[i]==a.getDims()[j];
            }
        }
        return shared;
    }
    
    public void addForce(Vector v)
    {
        force = Vector.add(force, v);
    }
    
    public Aster(Universe universe, double mass, double radius, double x, double y, double z, double xv, double yv, double zv, String name, String desc)
    {
        this.universe = universe;
        this.mass = mass;
        this.radius = radius;
        this.x = x;
        this.y = y;
        this.z = z;
        volume = (4.0/3.0)*Math.PI*radius*radius*radius;
        density = mass/volume;
        velocity = new Vector(xv, yv, zv);
        this.name = name;
        this.desc = desc;
        dimensions = new int[]{0};
    }
    
    public String getDescription()
    {
        return desc;
    }
    
    public Vector getVelocity()
    {
        return velocity;
    }
    
    public void setVelocity(Vector v)
    {
        velocity = v;
    }
    
    public double getMass()
    {   return mass;    }
    
    public double getRadius()
    {   return radius;  }
    
    public double getX()
    {   return x;   }
    
    public double getY()
    {   return y;   }
    
    public double getZ()
    {   return z;   }
    
    public String getName()
    {
        return name;
    }
    
    public double getMass(double r)
    {
        if(r<radius)
        {
            double newVolume = (4.0/3.0)*Math.PI*r*r*r;
            return density*newVolume;
        }
        else
        {
            return mass;
        }
    }
    
    public Vector getForce(ArrayList<Aster> asters)
    {
        ArrayList<Vector> vectors = new ArrayList<Vector>();
        for(int i=0; i<asters.size(); i++)
        {
            if(!asters.get(i).equals(this) && sharedDim(asters.get(i)))
            {
                vectors.add(Gravity.getForce(this, asters.get(i)));
            }
        }
        forces = vectors;
        force = Vector.add(forces);
        return force;
    }
    
    public ArrayList<Point> getPath()
    {
        return path;
    }
    
    public Vector getAcceleration(Vector force)
    {
        Vector acc = new Vector(force.getXVal()/mass, force.getYVal()/mass, force.getZVal()/mass);
        acceleration = acc.getSize();
        return acc;
    }
    
    public Vector getDeltaVelocity(Vector acceleration, double time)
    {
        return new Vector(acceleration.getXVal()*time, acceleration.getYVal()*time, acceleration.getZVal()*time);
    }
    
    public void computeNewVelocity(Vector deltaVelocity)
    {
        Vector newVelocity = Vector.add(velocity, deltaVelocity);
        velocity = newVelocity;
        //if(name.equals("Earth"))
        //{
        //    System.out.println(newVelocity.getZVal());
        //}
    }
    
    public void preMove(double time, ArrayList<Aster> asters)
    {
        Vector force = this.getForce(asters);
        Vector accel = this.getAcceleration(force);
        Vector delVel = this.getDeltaVelocity(accel, time);
        this.computeNewVelocity(delVel);
    }
    
    public void userMove(double time, Vector force)
    {
        Vector accel = this.getAcceleration(force);
        Vector delVel = this.getDeltaVelocity(accel, time);
        this.computeNewVelocity(delVel);
    }
    
    public void Move(double time)
    {   
        //if(name.equals("Jupiter"))
        //{
            Point newPoint = new Point(x, y, z);
            path.add(newPoint);
        //}
        x += velocity.getXVal()*time;
        y += velocity.getYVal()*time;
        z += velocity.getZVal()*time;
    }
    
    public void draw(Graphics2D g2)
    {
        Point drawPoint = Observer.getDrawPoint(this);
        int drawX = (int)drawPoint.getX();
        int drawY = (int)drawPoint.getY();
        
        g2.setColor(Color.GREEN);
        double drawRadius = Universe.SCALE*radius;
        
        Ellipse2D.Double sphere = new Ellipse2D.Double(drawX-drawRadius, drawY-drawRadius, 2*drawRadius, 2*drawRadius);
        
        g2.fill(sphere);
        g2.draw(sphere);
        
        
        g2.setColor(Color.RED);
        g2.drawString(name, drawX, drawY);
        if(universe.drawPaths)
        {
            //System.out.println("" + drawX + ", " + drawY);    
            for(int i=0; i<path.size(); i++)
            {
                Color fade = new Color( (int)((i+0.0)/path.size()*255), (int)((i+0.0)/path.size()*255), 0);
                g2.setColor(fade);
                g2.setColor(Color.YELLOW);
                //if(name.equals("Sun"))
                //{
                //    g2.setColor(Color.BLUE);
                //}
                Point pointPoint = Observer.getDrawPoint(path.get(i));
                int drawPointX = 0;
                int drawPointY = 0;
                if(i<universe.getRelativeAster().getPath().size())
                {
                    if(Observer.isRelative())
                    {
                        Point relativePoint = universe.getRelativeAster().getPath().get(i);
                        double xDif = path.get(i).getX()-relativePoint.getX();
                        double yDif = path.get(i).getY()-relativePoint.getY();
                        double zDif = path.get(i).getZ()-relativePoint.getZ();
                        
                        double xNow = universe.getRelativeAster().getX();
                        double yNow = universe.getRelativeAster().getY();
                        double zNow = universe.getRelativeAster().getZ();
                        
                        Point newPoint = new Point(xNow+xDif, yNow+yDif, zNow+zDif);
                        Point newRelPoint = Observer.getDrawPoint(newPoint);
                        drawPointX = (int)newRelPoint.getX();
                        drawPointY = (int)newRelPoint.getY();
                    }
                    else
                    {
                        drawPointX = (int)pointPoint.getX();
                        drawPointY = (int)pointPoint.getY();
                    }
                    Rectangle point = new Rectangle(drawPointX, drawPointY, 0, 0);
                    
                    g2.draw(point);
                }
            }
        }
        
        if(path.size()>(int)(31536000.0/Universe.TIME))// && (!name.equals("Sun")))
        {
            path.remove(0);
        }
        
//         for(int i=0; i<forces.size(); i++)
//         {
//             forces.get(i).draw(g2, x, y, z);
//         }
        //force.draw(g2, x, y, z);
    }
}