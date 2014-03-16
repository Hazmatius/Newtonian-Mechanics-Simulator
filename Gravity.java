public class Gravity
{
    public static final double G = 6.67384E-11;
    
    public static Vector getForce(Aster a1, Aster a2)
    {
        double x1 = a1.getX();
        double y1 = a1.getY();
        double z1 = a1.getZ();
        double x2 = a2.getX();
        double y2 = a2.getY();
        double z2 = a2.getZ();
        double x = x2-x1;
        double y = y2-y1;
        double z = z2-z1;
        
        //System.out.println("GRAVITY = "+G);
        double radius = Math.sqrt( x*x + y*y + z*z );
        //System.out.println("DISTANCE = "+radius);
        double m1 = a1.getMass(radius);
        //System.out.println("M1 = "+m1);
        double m2 = a2.getMass(radius);
        //System.out.println("M2 = "+m2);
        double force = Gravity.G*m1*m2/(radius*radius);
        //System.out.println("FORCE = " + force);
        //if(a1.getName().equals("Earth") && a2.getName().equals("Sun"))
        //{
        //    System.out.println(radius);
        //}
        Vector v = new Vector(x/radius*force, y/radius*force, z/radius*force);
        v.setToName(a2.getName());
        return v;
    }
}