
import java.awt.Polygon;

public class Money extends VectorSprite
{
    int size = 2;
    public Money(double x, double y)
    {
        shape = new Polygon();
        shape.addPoint(15/size, 25/size);//1
        shape.addPoint(-15/size, 25/size);//2
        shape.addPoint(-15/size, 0/size);//3
        shape.addPoint(15/size, 0/size);//4
        shape.addPoint(15/size, -25/size);//5
        shape.addPoint(-15/size, -25/size);//6
        shape.addPoint(0/size, -25/size);//7
        shape.addPoint(0/size, -35/size);//8
        shape.addPoint(0/size,35/size);//9
        shape.addPoint(0/size, 25/size);//10
        drawShape = new Polygon();
        drawShape.addPoint(15/size, 25/size);//1
        drawShape.addPoint(-15/size, 25/size);//2
        drawShape.addPoint(-15/size, 0/size);//3
        drawShape.addPoint(15/size, 0/size);//4
        drawShape.addPoint(15/size, -25/size);//5
        drawShape.addPoint(-15/size, -25/size);//6
        drawShape.addPoint(0/size, -25/size);//7
        drawShape.addPoint(0/size, -35/size);//8
        drawShape.addPoint(0/size, 35/size);//9
        drawShape.addPoint(0/size, 25/size);//10
        xposition = x;
        yposition = y;
        
        thrust = 10;
        
        double a;
        a = Math.random()*2*Math.PI;
        angle = a;
        xspeed = Math.cos(a)*a;
        yspeed = Math.sin(a)*a;
    }
}
