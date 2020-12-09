import java.awt.Polygon;
public class ExtraLife extends VectorSprite
{
    public ExtraLife(double x, double y)
    {
        shape = new Polygon();
        shape.addPoint(0, -12);//1
        shape.addPoint(-10, 0);//2
        shape.addPoint(-8,10);//3
        shape.addPoint(-5, 10);//4
        shape.addPoint(0, 5);//5
        shape.addPoint(5, 10);//6
        shape.addPoint(8, 10);//7
        shape.addPoint(10, 0);//8
        drawShape = new Polygon();
        drawShape.addPoint(0, -12);//1
        drawShape.addPoint(-10, 0);//2
        drawShape.addPoint(-8,10);//3
        drawShape.addPoint(-5, 10);//4
        drawShape.addPoint(0, 5);//5
        drawShape.addPoint(5, 10);//6
        drawShape.addPoint(8, 10);//7
        drawShape.addPoint(10, 0);//8
        xposition = x;
        yposition = y;
        
        thrust = 10;
        
        double a;
        a = Math.random()*2*Math.PI;
        xspeed = Math.cos(a)*a;
        yspeed = Math.sin(a)*a;
    }
}