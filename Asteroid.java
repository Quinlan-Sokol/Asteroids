
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
public class Asteroid extends VectorSprite
{
    int size;
    int rngColor;
    int type;
    
    public Asteroid(int t)
    {
        size = 3;
        type = t;
        initAsteroid();
    }
    public Asteroid(double x, double y, int s, int t)
    {
        size = s;
        type = t;
        initAsteroid();
        xposition = x;
        yposition = y;
    }
    
    public void initAsteroid()
    {
        shape = new Polygon();
        rngColor = (int)Math.random();
        //points for the asteroid shape
        switch(type)
        {
            case 1:
                shape.addPoint(15*size, 6*size);
                shape.addPoint(7*size, 17*size);
                shape.addPoint(-13*size, 8*size);
                shape.addPoint(-11*size, -10*size);
                shape.addPoint(12*size, -16*size);
                drawShape = new Polygon();
                drawShape.addPoint(15*size, 6*size);
                drawShape.addPoint(7*size, 17*size);
                drawShape.addPoint(-13*size, 8*size);
                drawShape.addPoint(-11*size, -10*size);
                drawShape.addPoint(12*size, -16*size);
                break;
            case 2:
                shape.addPoint(-12*size, 0*size);
                shape.addPoint(-8*size, 12*size);
                shape.addPoint(3*size, 14*size);
                shape.addPoint(-3*size, 4*size);
                shape.addPoint(-3*size, -4*size);
                shape.addPoint(3*size, -14*size);
                shape.addPoint(-8*size, -12*size);
                drawShape = new Polygon();
                drawShape.addPoint(-12*size, 0*size);
                drawShape.addPoint(-8*size, 12*size);
                drawShape.addPoint(3*size, 14*size);
                drawShape.addPoint(-3*size, 4*size);
                drawShape.addPoint(-3*size, -4*size);
                drawShape.addPoint(3*size, -14*size);
                drawShape.addPoint(-8*size, -12*size);
                break;
            case 3:
                shape.addPoint(6*size, -6*size);
                shape.addPoint(12*size, -9*size);
                shape.addPoint(15*size, 0*size);
                shape.addPoint(9*size, 12*size);
                shape.addPoint(0*size, 10*size);
                shape.addPoint(-9*size, 12*size);
                shape.addPoint(-15*size, 3*size);
                shape.addPoint(-12*size, -9*size);
                shape.addPoint(-3*size, -15*size);
                shape.addPoint(6*size, -12*size);
                drawShape = new Polygon();
                drawShape.addPoint(6*size, -6*size);
                drawShape.addPoint(12*size, -9*size);
                drawShape.addPoint(15*size, 0*size);
                drawShape.addPoint(9*size, 12*size);
                drawShape.addPoint(0*size, 10*size);
                drawShape.addPoint(-9*size, 12*size);
                drawShape.addPoint(-15*size, 3*size);
                drawShape.addPoint(-12*size, -9*size);
                drawShape.addPoint(-3*size, -15*size);
                drawShape.addPoint(6*size, -12*size);
                break;
            case 4:
                shape.addPoint(3*size, -6*size);
                shape.addPoint(12*size, 12*size);
                shape.addPoint(-9*size, 15*size);
                shape.addPoint(-21*size, 0*size);
                shape.addPoint(-3*size, -6*size);
                drawShape = new Polygon();
                drawShape.addPoint(3*size, -6*size);
                drawShape.addPoint(12*size, 12*size);
                drawShape.addPoint(-9*size, 15*size);
                drawShape.addPoint(-21*size, 0*size);
                drawShape.addPoint(-3*size, -6*size);
                break;
            
        }
        
        xposition = 450;
        yposition = 300;
        double h, a;
        h = Math.random() + 2;
        a = Math.random() * 2*Math.PI;
        xspeed = Math.cos(a) * h;
        yspeed = Math.sin(a) * h;
        h = Math.random()+ 400 + 100;
        a = Math.random() * 2*Math.PI;
        xposition = Math.cos(a) * h + 450;
        yposition = Math.sin(a) * h + 300;
        rotation = 0.15;
    }
    
    public void updatePosition()
    {
        angle += rotation;
        super.updatePosition();
    }
}
