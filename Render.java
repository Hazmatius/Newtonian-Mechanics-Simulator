import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Render
{
    public static void main(String[] args)
    {
        //all distances and speeds are at aphelion
        final JFrame frame = new JFrame();
        final Universe cosmos = new Universe(0);
        
        final Aster mercury = new Aster(cosmos, 3.30104E23, 2440000, 0.5E12, 0.5E12-69816900000.0, 0, 38862.99558788179, 0, 0, "Mercury", "planet");
        
        final Aster venus = new Aster(cosmos, 4.8676E24, 6052000, 0.5E12, 0.5E12-108939000000.0, 0, 34790.90974580616, 0, 0, "Venus", "planet");
        
        final Aster earth = new Aster(cosmos, 5.972E24, 6371000, 0.5E12, 0.5E12-152098232000.0, 0, 29294.34173938910, 0, 0, "Earth", "planet");
        final Aster moon = new Aster(cosmos, 7.34767309E22, 1737400, 0.5E12, 0.5E12-152098232000.0-405503000, 0, 29294.3417393891+963.8066214795371, 0, 0, "Moon", "moon");
        
        final Aster earth2 = new Aster(cosmos, 5.972E24, 6371000, 0.5E12, 0.5E12-152098232000.0, 0, 29294.34173938910*1.40, 0, 0, "Antiearth", "planet");
        
        final Aster mars = new Aster(cosmos, 6.4185E23, 3362000, 0.5E12, 0.5E12-249209300000.0, 0, 21976.14107968647, 0, 0, "Mars", "planet");
        final Aster phobos = new Aster(cosmos, 1.072E16, 11100, 0.5E12, 0.5E12-249209300000.0-9518800, 0, 21976.14107968647+2105.279363696795, 0, 0, "Phobos", "moon");
        final Aster deimos = new Aster(cosmos, 1.48E15, 6200, 0.5E12, 0.5E12-249209300000.0-23464692, 0, 21976.14107968647+1350.9951977229002, 0, 0, "Deimos", "moon");
        
        final Aster jupiter = new Aster(cosmos, 1.8986E27, 69911000, 0.5E12, 0.5E12-816520800000.0, 0, 12435.500749628362, 0, 0, "Jupiter", "planet");
        final Aster europa = new Aster(cosmos, 4.7998E22, 1560800, 0.5E12, 0.5E12-816520800000.0-676938000.0, 0, 12435.500749628362+13617.652837654306, 0, 0, "Europa", "moon");
        final Aster io = new Aster(cosmos,  8.9319E22, 1821300, 0.5E12, 0.5E12-816520800000.0-421700000.0, 0, 12435.500749628362+17263.236319014337, 0, 0, "Io", "moon");
        final Aster ganymede = new Aster(cosmos, 1.4819E23, 2634100, 0.5E12, 0.5E12-816520800000.0-1071600000.0, 0, 12435.500749628362+10865.93243410321, 0, 0, "Ganymede", "moon");
        final Aster callisto = new Aster(cosmos, 1.075938E23, 2410300, 0.5E12, 0.5E12-816520800000.0-1897000000.0, 1000000000, 12435.500749628362+8143.29624771113, 0, 0, "Callisto", "moon");
        
        final Aster saturn = new Aster(cosmos, 5.6846E26, 60268000, 0.5E12, 0.5E12-1513325783000.0, 0, 9100.99187376967, 0, 0, "Saturn", "planet");
        final Aster mimas = new Aster(cosmos, 3.749E19, 198200, 0.5E12, 0.5E12-1513325783000.0-189176000.0, 0, 9100.99187376967+14021.903785356622, 0, 0, "Mimas", "moon");
        final Aster enceladus = new Aster(cosmos, 1.08022E20, 252100, 0.5E12, 0.5E12-1513325783000.0-239066356.0, 0, 9100.99187376967+12567.694701829489, 0, 0, "Enceladus", "moon");
        final Aster tethys = new Aster(cosmos, 6.17449E20, 531100, 0.5E12, 0.5E12-1513325783000.0-294648462.0, 0, 9100.99187376967+11346.55560190006, 0, 0, "Tethys", "moon");
        final Aster dione = new Aster(cosmos, 1.095452E21, 561400, 0.5E12, 0.5E12-1513325783000.0-378226371.0, 0, 9100.99187376967+10004.232007206774, 0, 0, "Dione", "moon");
        final Aster rhea = new Aster(cosmos, 2.306518E21, 763800, 0.5E12, 0.5E12-1513325783000.0-527771260.0, 0, 9100.99187376967+8473.085207591452, 0, 0, "Rhea", "moon");
        final Aster titan = new Aster(cosmos, 1.3452E23, 2576000, 0.5E12, 0.5E12-1513325783000.0-1257060000.0, 0, 9100.99187376967+5413.949603657866, 0, 0, "Titan", "moon");
        final Aster iapetus = new Aster(cosmos, 1.805635E21, 734500, 0.5E12, 0.5E12-1513325783000.0-3662704000.0, 0, 9100.99187376967+3172.0001327064383, 0, 0, "Iapetus", "moon");
        
        final Aster uranus = new Aster(cosmos, 8.6810E25, 25559000, 0.5E12, 0.5E12-3004419704000.0, 0, 6497.73188846182, 0, 0, "Uranus", "planet");
        final Aster ariel = new Aster(cosmos, 1.353E21, 578900, 0.5E12, 0.5E12-3004419704000.0-191249224.0, 0, 6497.73188846182+5500.627040072435, 0, 0, "Ariel", "moon");
        final Aster umbriel = new Aster(cosmos, 1.172E21, 584700, 0.5E12, 0.5E12-3004419704000.0-267037400.0, 0, 6497.73188846182+4648.771479525127, 0, 0, "Umbriel", "moon");
        final Aster titania = new Aster(cosmos, 3.527E21, 788400, 0.5E12, 0.5E12-3004419704000.0-436389501.0, 0, 6497.73188846182+3641.6368347533635, 0, 0, "Titania", "moon");
        final Aster oberon = new Aster(cosmos, 3.014E21, 761400, 0.5E12, 0.5E12-3004419704000.0-584336928.0, 0, 6497.73188846182+3146.5663052430123, 0, 0, "Oberon", "moon");
        
        final Aster neptune = new Aster(cosmos, 1.0243E26, 24764000, 0.5E12, 0.5E12-4553946490000.0, 0, 5368.616978486837, 0, 0, "Neptune", "planet");
        final Aster triton = new Aster(cosmos, 2.14E22, 1353400, 0.5E12, 0.5E12-4553946490000.0-354764676.0, 0, 5368.616978486837+4389.629056247509, 0, 0, "Triton", "moon");
        
        final Aster pluto = new Aster(cosmos, 1.305E22, 1153000, 0.5E12, 0.5E12-7311000000000.0, 0, 3703.2139452479582, 0, 0, "Pluto", "planet");
        final Aster charon = new Aster(cosmos, 1.52E21, 603500, 0.5E12, 0.5E12-7311000000000.0-19571000, 0, 3703.2139452479582+210.9534549984197, 0, 0, "Charon", "moon");
        
        final Aster asterX = new Aster(cosmos, 1E26, 25000000, 0.5E12, 0.5E12+500000000000.0, 0, -50, 0, 0, "X", "planet");
        final Aster asterY = new Aster(cosmos, 1E26, 25000000, 0.5E12, 0.5E12-500000000000.0, 0, 50, 0, 0, "Y", "planet");
        final Aster asterZ = new Aster(cosmos, 1E26, 25000000, 0.5E12, 0.5E12, 100000000000.0, 0, 0, 0, "Z", "planet");
        
        //cosmos.addAster(asterX);
        //cosmos.addAster(asterY);
        //cosmos.addAster(asterZ);
        
        //final Aster alex = new Aster(cosmos, 6E24, 1, 0.5E12, 0.5E12-600000000000.0, 2000, 0, "Alex");
        
        cosmos.addAster(mercury);
        
        cosmos.addAster(venus);
        
        cosmos.addAster(earth);
        cosmos.addAster(moon);
        
        //cosmos.addAster(earth2);
        
        
        
        cosmos.addAster(mars);
        cosmos.addAster(phobos);
        cosmos.addAster(deimos);
        
        cosmos.addAster(jupiter);
        cosmos.addAster(europa);
        cosmos.addAster(io);
        cosmos.addAster(ganymede);
        cosmos.addAster(callisto);
        
        cosmos.addAster(saturn);
        cosmos.addAster(mimas);
        cosmos.addAster(enceladus);
        cosmos.addAster(tethys);
        cosmos.addAster(dione);
        cosmos.addAster(rhea);
        cosmos.addAster(titan);
        cosmos.addAster(iapetus);
        
        cosmos.addAster(uranus);
        cosmos.addAster(ariel);
        cosmos.addAster(umbriel);
        cosmos.addAster(titania);
        cosmos.addAster(oberon);
        
        cosmos.addAster(neptune);
        cosmos.addAster(triton);
        
        cosmos.addAster(pluto);
        cosmos.addAster(charon);
        
        //cosmos.addAster(alex);
        
        Vector p = cosmos.getMomentum();
        final Aster sun = new Aster(cosmos, 1.989E30, 695500000, 0.5E12, 0.5E12, 0, 0, 0, 0, "Sun", "star");
        Vector v = new Vector(-1*p.getXVal()/sun.getMass(), -1*p.getYVal()/sun.getMass(), -1*p.getZVal()/sun.getMass());
        sun.setVelocity(v);
        cosmos.addAster(sun);
        
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setSize(1500, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Render");
        
        final AsterHolder focusAster = new AsterHolder(sun);
        cosmos.setRelativeAster(sun);
        final MyBool focus = new MyBool(false);
        final MyBool pause = new MyBool(true);
        final MyBool tab = new MyBool(false);
        
        sun.setDims(new int[]{0, 1});
        earth2.setDims(new int[]{1});
        
        //cosmos.purgeType("moon");
        
        class Comp extends JComponent
        {
            public void paintComponent(Graphics g)
            {
                Graphics2D g2 = (Graphics2D) g;
                if(!pause.getBoolean())
                {
                    cosmos.preMove();
                    cosmos.Move();
                }
                if(focus.getBoolean())
                {
                    Point p = Observer.getDrawPoint(focusAster.getAster());
                    double theX = p.getX();
                    double theY = p.getY();
                    
                    cosmos.setCenter(theX, theY);
                    cosmos.center(frame.getWidth(), frame.getHeight());
                }
                cosmos.draw(g2);
                Observer.turnFrame();
            }
        }
        
        class Physics implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
            {
                frame.repaint();
            }
        }
        
        class Mouse extends MouseAdapter
        {
            public void mouseClicked(MouseEvent e)
            {
                
            }
            
            public void mouseReleased(MouseEvent e)
            {
                
            }
            
            public void mousePressed(MouseEvent e)
            {
                Aster a = cosmos.getClosestAster(e.getX()-1, e.getY()-24);
                if(tab.getBoolean())
                {
                    cosmos.setRelativeAster(a);
                }
                else
                {
                    focusAster.setAster(a);
                }
            }
            
            
        }
        
        class MouseDrag extends MouseMotionAdapter
        {
            public void mouseDragged(MouseEvent e)
            {
                
            }
        }
        
        class Keyboard extends KeyAdapter
        {
            public void keyPressed(KeyEvent k)
            {
                int n = 25;
                if(k.getKeyCode()==KeyEvent.VK_UP)
                {
                    cosmos.shiftY(n);
                }
                if(k.getKeyCode()==KeyEvent.VK_DOWN)
                {
                    cosmos.shiftY(-n);
                }
                if(k.getKeyCode()==KeyEvent.VK_LEFT)
                {
                    cosmos.shiftX(n);
                }
                if(k.getKeyCode()==KeyEvent.VK_RIGHT)
                {
                    cosmos.shiftX(-n);
                }
                if(k.getKeyCode()==KeyEvent.VK_MINUS)
                {
                    cosmos.findCenter(frame.getWidth(), frame.getHeight());
                    cosmos.shiftScale(.5);
                    cosmos.center(frame.getWidth(), frame.getHeight());
                }
                if(k.getKeyCode()==KeyEvent.VK_EQUALS)
                {
                    cosmos.findCenter(frame.getWidth(), frame.getHeight());
                    cosmos.shiftScale(2);
                    cosmos.center(frame.getWidth(), frame.getHeight());
                }
                if(k.getKeyCode()==KeyEvent.VK_ENTER)
                {
                    boolean temp = !focus.getBoolean();
                    focus.setBoolean(temp);
                }
                if(k.getKeyCode()==KeyEvent.VK_SPACE)
                {
                    boolean temp = !pause.getBoolean();
                    pause.setBoolean(temp);
                }
                if(k.getKeyCode()==KeyEvent.VK_R)
                {
                    Observer.toggleRelative();
                    //System.out.println(Observer.isRelative());
                }
                if(k.getKeyCode()==KeyEvent.VK_SHIFT)
                {
                    cosmos.preMove();
                    cosmos.Move();
                }
                if(k.getKeyCode()==KeyEvent.VK_T)
                {
                    cosmos.drawPaths = !cosmos.drawPaths;
                }
                
                if(k.getKeyCode()==KeyEvent.VK_A)
                {
                    Observer.setA(true);
                }
                if(k.getKeyCode()==KeyEvent.VK_D)
                {
                    Observer.setD(true);
                }
                if(k.getKeyCode()==KeyEvent.VK_Q)
                {
                    Observer.setQ(true);
                }
                if(k.getKeyCode()==KeyEvent.VK_E)
                {
                    Observer.setE(true);
                }
                if(k.getKeyCode()==KeyEvent.VK_W)
                {
                    Observer.setW(true);
                }
                if(k.getKeyCode()==KeyEvent.VK_S)
                {
                    Observer.setS(true);
                }
                if(k.getKeyCode()==KeyEvent.VK_Z)
                {
                    tab.setBoolean(true);
                }
            }
            
            public void keyReleased(KeyEvent k)
            {
                if(k.getKeyCode()==KeyEvent.VK_A)
                {
                    Observer.setA(false);
                }
                if(k.getKeyCode()==KeyEvent.VK_D)
                {
                    Observer.setD(false);
                }
                if(k.getKeyCode()==KeyEvent.VK_Q)
                {
                    Observer.setQ(false);
                }
                if(k.getKeyCode()==KeyEvent.VK_E)
                {
                    Observer.setE(false);
                }
                if(k.getKeyCode()==KeyEvent.VK_W)
                {
                    Observer.setW(false);
                }
                if(k.getKeyCode()==KeyEvent.VK_S)
                {
                    Observer.setS(false);
                }
                if(k.getKeyCode()==KeyEvent.VK_Z)
                {
                    tab.setBoolean(false);
                }
            }
            
            public void keyTyped(KeyEvent k)
            {
                
            }
        }
        
        Comp comp = new Comp();
        frame.add(comp);
        
        MouseAdapter clicker = new Mouse();
        frame.addMouseListener(clicker);
        
        KeyAdapter keyer = new Keyboard();
        frame.addKeyListener(keyer);
        
        MouseMotionListener dragger = new MouseDrag();
        frame.addMouseMotionListener(dragger);
        
        Physics time = new Physics();
        Timer t = new Timer(0, time);
        t.start();
        
        frame.repaint();
        frame.setVisible(true);
    }
}