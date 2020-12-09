
import java.awt.Polygon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author RP4K
 */
public class Bullet extends VectorSprite
{
    public Bullet(double x, double y, double a)
    {
        shape = new Polygon();
        shape.addPoint(0, 0);
        shape.addPoint(0, 0);
        shape.addPoint(0, 0);
        shape.addPoint(0, 0);
        drawShape = new Polygon();
        drawShape.addPoint(0, 0);
        drawShape.addPoint(0, 0);
        drawShape.addPoint(0, 0);
        drawShape.addPoint(0, 0);
        
        xposition = x;
        yposition = y;
        angle = a;
        thrust = 10;
        
        xspeed = Math.cos(angle)*thrust;
        yspeed = Math.sin(angle)*thrust;
        active = true;
    }
    public Bullet(double x, double y, double s, double a)
    {
        shape = new Polygon();
        shape.addPoint(0, 0);
        shape.addPoint(0, 0);
        shape.addPoint(0, 0);
        shape.addPoint(0, 0);
        drawShape = new Polygon();
        drawShape.addPoint(0, 0);
        drawShape.addPoint(0, 0);
        drawShape.addPoint(0, 0);
        drawShape.addPoint(0, 0);
        
        xposition = x;
        yposition = y;
        thrust = 10;
        angle = a;
        xspeed = Math.cos(angle)*thrust*s;
        yspeed = Math.sin(angle)*thrust*s;
        active = true;
    }
}
