
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
public class Debris extends VectorSprite
{
    public Debris(double x, double y)
    {
        shape = new Polygon();
        shape.addPoint(1, 1);
        shape.addPoint(-1, -1);
        shape.addPoint(-1, 1);
        shape.addPoint(1, -1);
        drawShape = new Polygon();
        drawShape.addPoint(1, 1);
        drawShape.addPoint(-1, -1);
        drawShape.addPoint(-1, 1);
        drawShape.addPoint(1, -1);
        
        xposition = x;
        yposition = y;
        
        thrust = 10;
        
        double a;
        a = Math.random()*2*Math.PI;
        angle = a;
        xspeed = Math.cos(a)*a;
        yspeed = Math.sin(a)*a;
    }
    public Debris(double x, double y, double a)
    {
        int size;
        size = 2;
        shape = new Polygon();
        shape.addPoint(1*size, 1*size);
        shape.addPoint(-1*size, -1*size);
        shape.addPoint(-1*size, 1*size);
        shape.addPoint(1*size, -1*size);
        drawShape = new Polygon();
        drawShape.addPoint(1*size, 1*size);
        drawShape.addPoint(-1*size, -1*size);
        drawShape.addPoint(-1*size, 1*size);
        drawShape.addPoint(1*size, -1*size);
        
        xposition = x;
        yposition = y;
        
        angle = a;
        xspeed = Math.cos(a)*a/100;
        yspeed = Math.sin(a)*a/100;
    }
}
