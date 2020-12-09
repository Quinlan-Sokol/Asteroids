/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//these are the impotant libs to run the game
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.Timer;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.io.*;
import java.util.Random;
/**
 *
 * @author RP4K
 */
//this is ou main class
public class Asteroids extends Applet implements KeyListener, ActionListener, MouseListener
{
    //this is where we declare new variables/objects
    //this is the player object
        SpaceCraft ship, enemySpawnCounter, x2Counter, MouseCounter,menuShip;
        //these are for double buffering
        Image offscreen;
        Graphics offg;
        Timer timer;
        int score;
        int lives;
        double bulletDelay;
        int lives2;
        int mrandom;
        int mouseClickX;
        int mouseClickY;
        int fontsize = 24;
        int cycleColor = 1;
        int x2 = 1;
        int spawnrate;
        int startingAsteroids;
        int Level;
        int Money;
        int laserTimer;
        int moneyValue;
        int LaserCost;
        int selectedSpeed;
        int selectedSpeed2;
        int MKindex;
        int EHindex;
        boolean d1 = true;
        boolean d2 = true;
        int animationShipCounter = 0;
        int angleCounter = 0;
        int rngAngle;
        double rnd;
        double rnd2;
        double rnd3;
        double playerX;
        double playerY;
        double mouseX;
        double mouseY;
        double menuMouseX;
        double menuMouseY;
        double shipX;
        double shipY;
        double Angle;
        int[] highscores = {score};
        boolean ifWritten = true;
        boolean ascensionGameMode;
        AudioClip laser,shiphit,asteroidhit,thruster,win,music;
        boolean upKey,downKey,rightKey,leftKey, sKey, spaceKey,rKey,wKey,aKey,dKey,oneKey;
        boolean inMenu;
        boolean hasLaser;
        boolean mouseMode;
        boolean mouseClick;
        boolean leftClick;
        boolean rightClick;
        //contains list of asteroids
        ArrayList<Asteroid> asteroidList;
        ArrayList<Bullet> bulletList;
        ArrayList<Debris> debrisList;
        ArrayList<AudioClip> musicList;
        ArrayList<Money>moneyList;
        ArrayList<ExtraLife>extraLifeList;
        ArrayList<ScoreMultiplier> scoreMultiplierList;
        private Graphics globalGraphics = null;
        //asigning values to variables
    public void init() 
    {
        //size of window
        this.setSize(900,600);
        //needed for keyboard input
        this.addKeyListener(this);
        this.addMouseListener(this);
        //creates a duplicate of window for double buffering
        offscreen = createImage(this.getWidth(),this.getHeight());
        //double buffering
        offg = offscreen.getGraphics();
        //sets value to ship object
        ship = new SpaceCraft();
        enemySpawnCounter = new SpaceCraft();
        x2Counter = new SpaceCraft();
        MouseCounter = new SpaceCraft();
        menuShip = new SpaceCraft();
        startingAsteroids = 4;
        mouseMode = false;
        asteroidList = new ArrayList();
        bulletList = new ArrayList();
        debrisList = new ArrayList();
        musicList = new ArrayList();
        extraLifeList = new ArrayList();
        moneyList = new ArrayList();
        scoreMultiplierList = new ArrayList();
        timer = new Timer(20,this);
        inMenu = true;
        score = 0;
        lives = 5;
        Level = 0;
        MKindex = 1;
        EHindex = 1;
        selectedSpeed = 2;
        selectedSpeed2 = 2;
        moneyValue = (int)Math.random()*10 + 10;
        Money = 0;
        bulletDelay = 4;
        LaserCost = 200;
        spawnrate = 250;
        laserTimer = 0;
        mouseClick = false;
        leftClick = false;
        readFile("HighScores.txt");
        mrandom = (int)Math.round(Math.random());
        laser = getAudioClip(getCodeBase(),"laser80.wav");
        thruster = getAudioClip(getCodeBase(),"thruster.wav");
        asteroidhit = getAudioClip(getCodeBase(),"explode0.wav");
        shiphit = getAudioClip(getCodeBase(),"explode1.wav");
        music = getAudioClip(getCodeBase(),"music3.wav");
        win = getAudioClip(getCodeBase(),"win1.wav");
        musicList.add(win);
        musicList.add(music);
        musicList.get(mrandom).loop();
        ascensionGameMode = false;
    }
    //this is where keyboard input is handled
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_D)
        {
            dKey = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_A)
        {
            aKey = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_W)
        {
            wKey = true;
            thruster.loop();
        }
        if(e.getKeyCode() == KeyEvent.VK_S)
        {
            sKey = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            spaceKey = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_R)
        {
            rKey = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_1)
        {
            oneKey = true;
        }
    }
    public void keyCheck()
    {
        if(wKey)
        {
            if (mouseMode == false)
            {
                ship.accelerate();
                for (int k = 0; k < 50;k++)
                {
                    if (ship.active)
                    {
                    debrisList.add(new Debris(ship.drawShape.xpoints[2], ship.drawShape.ypoints[2], ship.angle + Math.random()*2-4));
                    }
                }
                for (int k = 0; k < 50;k++)
                {
                    if (ship.active)
                    {
                    debrisList.add(new Debris(ship.drawShape.xpoints[1], ship.drawShape.ypoints[1], ship.angle + Math.random()*2-4));
                    }
                }
            }
        }
        if(aKey)
        {
            if (mouseMode == false){
            ship.rotateLeft();
            }
        }
        if(dKey)
        {
            if (mouseMode == false){
            ship.rotateRight();
            }
        }
        if(sKey)
        {
            if(aKey == false && dKey == false && wKey == false){
                if(ship.xspeed != 0){
                    if(ship.xspeed < 0){
                        ship.xspeed += 0.1;
                    }
                    else{
                        ship.xspeed -= 0.1;
                    }
                }
                if(ship.yspeed != 0){
                    if(ship.yspeed < 0){
                        ship.yspeed += 0.1;
                    }
                    else{
                        ship.yspeed -= 0.1;
                    }
                }
            }
            //ship.deaccelerate();
        }
        if(spaceKey)
        {
            fireBullet();
        }
        if(rKey)
        {
            resetGame();
            ship.reset();
        }
        if(oneKey)
        {
            if (ascensionGameMode == false)
            {
                if (Money >= LaserCost)
                {
                    bulletDelay = 0.5;
                    hasLaser = true;
                    Money = Money - LaserCost;
                }
            }
        }
    }
    public void keyReleased(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_D)
        {
            dKey = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_A)
        {
            aKey = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_W)
        {
            wKey = false;
            thruster.stop();
        }
        if(e.getKeyCode() == KeyEvent.VK_S)
        {
            sKey = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            spaceKey = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_R)
        {
            rKey = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_1)
        {
            oneKey = false;
        }
    }
    public void keyTyped(KeyEvent e){}
    // main game loop
    public double ShipMouseAngle(double firstX, double firstY, double secondX, double secondY) 
    {
        firstX = ship.xposition;
        firstY = ship.yposition;
        secondX = mouseX;
        secondY = mouseY;
        double mouseAngle = Math.atan2((secondX - firstX), (secondY - firstY)) * 180 / Math.PI;
        if (mouseAngle < 0) 
        {
            return (360 + mouseAngle);
        } 
        else 
        {
            return (mouseAngle);
        }
    }
    public void actionPerformed(ActionEvent e)
    {
        keyCheck();
        if(inMenu == true)
            timer.stop();
        else
        {
            respawnShip();
            if (ascensionGameMode == false)
            {
                enemySpawnCounter.counter++;
                if(enemySpawnCounter.counter >= spawnrate)
                {
                    asteroidList.add(new Asteroid((int)Math.round(4*Math.random())));
                    enemySpawnCounter.counter = 0;

                    if (spawnrate > 60)
                    {
                        spawnrate -= 5;
                    }
                }
            }
            x2Counter.counter++;
            if(x2Counter.counter >=300)
            {
                x2 = 1;
                x2Counter.counter = 0;
            }
            
            
            ship.updatePosition();
            playerX = ship.xposition;
            playerY = ship.yposition;
            if (mouseMode == true)
            {
                mouseX=MouseInfo.getPointerInfo().getLocation().x;
                mouseY=MouseInfo.getPointerInfo().getLocation().y;
                shipX = ship.xposition;
                shipY = ship.yposition;
                Angle = Math.atan2(mouseY - shipY,mouseX - shipX);
                ship.angle = Angle + 80;
                if(leftClick)
                {
                    ship.accelerate();
                    for (int k = 0; k < 1;k++)
                    {
                        if (ship.active)
                        {
                            debrisList.add(new Debris(ship.drawShape.xpoints[2], ship.drawShape.ypoints[2], ship.angle + Math.random()*2-4));
                        }
                    }
                    for (int k = 0; k < 1;k++)
                    {
                        if (ship.active)
                        {
                            debrisList.add(new Debris(ship.drawShape.xpoints[1], ship.drawShape.ypoints[1], ship.angle + Math.random()*2-4));
                        }
                    }
                }
                if (rightClick)
                {
                    ship.deaccelerate();
                }
            }
            for(int i = 0; i<asteroidList.size();i++)
            {
                asteroidList.get (i).updatePosition();
            }
            for(int i = 0; i<bulletList.size();i++)
            {
                bulletList.get(i).updatePosition();
                if(bulletList.get(i).counter == 50 || bulletList.get(i).active == false)
                {
                    bulletList.remove(i);
                }
            }
            for(int i = 0; i<debrisList.size();i++)
            {
                debrisList.get (i).updatePosition();
                if(debrisList.get(i).counter == 25)
                {
                    debrisList.remove(i);
                }
            }
            for(int i = 0; i<scoreMultiplierList.size();i++)
            {
                scoreMultiplierList.get (i).updatePosition();
                if(scoreMultiplierList.get(i).counter == 350)
                {
                    scoreMultiplierList.remove(i);
                }
            }
            for(int i = 0; i<moneyList.size();i++)
            {
                moneyList.get (i).updatePosition();
                if(moneyList.get(i).counter == 350)
                {
                    moneyList.remove(i);
                }
            }
            for(int i = 0; i<extraLifeList.size();i++)
            {
                extraLifeList.get (i).updatePosition();
                if(extraLifeList.get(i).counter == 600)
                {
                    extraLifeList.remove(i);
                }
            }
        
            checkCollisions();
            checkAsteroidDestruction();
            if (highscores[0]<score)
            {
                highscores[0] = score;
            }
            if (ascensionGameMode == true)
            {
                if (asteroidList.isEmpty())
                {
                    Level = Level + 1;
                    startingAsteroids = startingAsteroids + 1;
                    for(int i = 0; i < startingAsteroids;i ++)
                    {
                        asteroidList.add(new Asteroid((int)Math.round(4*Math.random())));
                    }
                }
            }
        }
    }
    public void start()
    {
        timer.start();
    }
    public void stop()
    {
        timer.stop();
    }
    public void update(Graphics g)
    {
        paint(g);
    }
    //handling graphics
    public void paint (Graphics g)
    {
        //setting color
        offg.setColor(Color.BLACK);
        offg.fillRect(0, 0, 900, 600);
        offg.setColor (Color.BLUE);
        if(!inMenu)
        {
            fontsize = 16;
            offg.setFont(new Font("TimesRoman",Font.BOLD, fontsize));
            offg.drawString("lives: "+lives, 100, 20);
            offg.drawString("highscore: "+highscores[0], 200, 20);
            //int x,y,d;
            //d = 200;
            //x = 450-d/2;
            //y = 300-d/2;
            //offg.drawOval(x, y, d, d);
            for(int i = 0; i<asteroidList.size();i++)
            {
                asteroidList.get(i).paint(offg);
            }
            for(int i = 0; i<debrisList.size();i++)
            {
                randomColor(offg);
                debrisList.get(i).paint(offg);
            }
            for(int i = 0; i<scoreMultiplierList.size();i++)
            {
                offg.setColor(Color.RED);
                scoreMultiplierList.get(i).paint(offg);
            }
            for(int i = 0; i<extraLifeList.size();i++)
            {
                offg.setColor(Color.PINK);
                extraLifeList.get(i).paint(offg);
            }
            for(int i = 0; i<moneyList.size();i++)
            {
                offg.setColor(Color.YELLOW);
                moneyList.get(i).paint(offg);
            }
            //renders out player
            if (ship.active)
            {
                chooseColor(offg);
                ship.paint(offg);   
                for(int i = 0; i<bulletList.size();i++)
            {
                bulletList.get(i).paint(offg);
            }
            }
            if (lives == 0)
            {
                if (ascensionGameMode == true)
                {
                    asteroidList.clear();
                    resetGame();
                }
                else if (ascensionGameMode == false)
                {
                asteroidList.clear();
                offg.setColor(Color.CYAN);
                offg.drawString("gameover you lose  Press R to restart", 300, 300);
                for(int i =0; i<scoreMultiplierList.size();i++)
                {
                    scoreMultiplierList.remove(i);
                }
                for(int i =0; i<moneyList.size();i++)
                {
                    moneyList.remove(i);
                }
                for(int i =0; i<extraLifeList.size();i++)
                {
                    extraLifeList.remove(i);
                }
                writeFile("HighScores.txt");
                }
            }
            if(ascensionGameMode == true)
            {
                offg.setColor(Color.BLUE);
                offg.drawString("Level: " + Level, 350,20 );
                for(int i = 0; i<extraLifeList.size();i++)
                {
                    offg.setColor(Color.PINK);
                    extraLifeList.get(i).paint(offg);
                }
            }
            else 
            {
                offg.setColor(Color.BLUE);
                offg.drawString("score: "+score, 10, 20);
                offg.drawString("Money: "+Money, 350, 20);
                offg.drawString("Press 1 to buy Laser  Cost=200", 450, 20);
            }
        }
        else
        {
            //BACK ANIMATION
            if(d1){
                menuShip.xposition = 450;
                menuShip.yposition = 300;
                menuShip.yspeed = 2;
                d1 = false;
            }
            
            if(animationShipCounter == 200)
            {
                if(d2){
                    rngAngle = (int)Math.round(90*Math.random());
                    d2 = false;
                }
                if(Math.round(menuShip.angle) != rngAngle){
                    menuShip.angle += 1;
                }
                else{
                    d2 = true;
                    animationShipCounter = 0;
                }
                
            }
            else{
                animationShipCounter++;
            }
            
            menuShip.accelerate();
            menuShip.updatePosition();
            menuShip.paint(offg);
            
            
            
            
            
            fontsize = 150;
            offg.setColor(Color.ORANGE);
            offg.setFont(new Font("TimesRoman",Font.BOLD, fontsize));
            offg.drawString("ASTEROIDS", 20,150 );
            
            fontsize = 24;
            offg.setFont(new Font("TimesRoman",Font.BOLD, fontsize));
            offg.setColor(Color.YELLOW);
            offg.drawString("Movement Speed", 50,200);
            Polygon speedPlus = new Polygon();
            speedPlus.addPoint(45,225);
            speedPlus.addPoint(45,210);
            speedPlus.addPoint(55,210);
            speedPlus.addPoint(55,225);
            speedPlus.addPoint(70,225);
            speedPlus.addPoint(70,235);
            speedPlus.addPoint(55,235);
            speedPlus.addPoint(55,250);
            speedPlus.addPoint(45,250);
            speedPlus.addPoint(45,235);
            speedPlus.addPoint(30,235);
            speedPlus.addPoint(30,225);
            offg.setColor(Color.green);
            offg.fillPolygon(speedPlus);
            if(speedPlus.contains(menuMouseX, menuMouseY))
            {
                selectedSpeed += 1;
                menuMouseX = 0;
                menuMouseY = 0;
            }
            
            Polygon speedMinus = new Polygon();
            speedMinus.addPoint(200,225);
            speedMinus.addPoint(240,225);
            speedMinus.addPoint(240,235);
            speedMinus.addPoint(200,235);
            offg.setColor(Color.red);
            offg.fillPolygon(speedMinus);
            if(speedMinus.contains(menuMouseX, menuMouseY))
            {
                selectedSpeed -= 1;
                menuMouseX = 0;
                menuMouseY = 0;
            }
            
            if(selectedSpeed > 3){
                selectedSpeed = 3;
            }
            else if(selectedSpeed < 1){
                selectedSpeed = 1;
            }
            switch(selectedSpeed){
                case 1:
                    offg.setColor(Color.DARK_GRAY);
                    offg.fillRect(90, 210, 25, 35);
                    offg.fillRect(125, 210, 25, 35);
                    offg.setColor(Color.LIGHT_GRAY);
                    offg.fillRect(160, 210, 25, 35);
                    ship.thrust = 3;
                    ship.speedChoice = 50;
                    break;
                case 2:
                    offg.setColor(Color.DARK_GRAY);
                    offg.fillRect(90, 210, 25, 35);
                    offg.setColor(Color.LIGHT_GRAY);
                    offg.fillRect(125, 210, 25, 35);
                    offg.fillRect(160, 210, 25, 35);
                    ship.thrust = 5;
                    ship.speedChoice = 20;
                    break;
                case 3:
                    offg.setColor(Color.LIGHT_GRAY);
                    offg.fillRect(90, 210, 25, 35);
                    offg.fillRect(125, 210, 25, 35);
                    offg.fillRect(160, 210, 25, 35);
                    ship.thrust = 8;
                    ship.speedChoice = 5;
                    break;
            }
            
            offg.setColor(Color.CYAN);
            offg.setFont(new Font("TimesRoman",Font.BOLD, fontsize));
            offg.drawString("Movement Style", 620,210 );
            Polygon easy = new Polygon();
            offg.setColor(Color.BLACK);
            easy.addPoint(670,255); //top left
            easy.addPoint(620,255); //top right
            easy.addPoint(620,230); //bottom right
            easy.addPoint(670,230); //bottom left
            offg.drawPolygon(easy);
            offg.setColor(Color.GREEN);
            offg.setFont(new Font("TimesRoman",Font.BOLD, fontsize));
            offg.drawString("Easy", 620,250 );
            if (easy.contains(mouseClickX, mouseClickY))
            {
                ship.isMoveHard = false;
                EHindex = 1;
            }
            Polygon hard = new Polygon();
            offg.setColor(Color.BLACK);
            hard.addPoint(790,255); //top left
            hard.addPoint(735,255); //top right
            hard.addPoint(735,230); //bottom right
            hard.addPoint(790,230); //bottom left
            offg.drawPolygon(hard);
            offg.setColor(Color.RED);
            offg.setFont(new Font("TimesRoman",Font.BOLD, fontsize));
            offg.drawString("Hard", 735,250 );
            if (hard.contains(mouseClickX, mouseClickY))
            {
                EHindex = 0;
                ship.isMoveHard = true;
            }
            
            offg.setColor(Color.yellow);
            if(EHindex == 0)
            {
                offg.drawPolygon(hard);
            }
            else if(EHindex == 1)
            {
                offg.drawPolygon(easy);
            }
            
            offg.setColor(Color.BLACK);
            Polygon start = new Polygon();
            start.addPoint(515,235); //top left
            start.addPoint(390,235); //top right
            start.addPoint(390,210); //bottom right
            start.addPoint(515,210); //bottom left
            offg.drawPolygon(start);
            if (start.contains(mouseClickX, mouseClickY))
            {
                ascensionGameMode = false;
                inMenu = false;
                timer.start();
             
                for(int i = 0; i < startingAsteroids;i ++)
                {
                    asteroidList.add(new Asteroid((int)Math.round(4*Math.random())));
                }
            }
            fontsize = 24;
            offg.setColor(Color.RED);
            offg.setFont(new Font("TimesRoman",Font.BOLD, fontsize));
            offg.drawString("Start Game", 395,230 );
            offg.setColor(Color.BLACK);
            Polygon design = new Polygon();
            design.addPoint(515,275); //top left
            design.addPoint(390,275); //top right
            design.addPoint(390,250); //bottom right
            design.addPoint(515,250); //bottom left
            offg.drawPolygon(design);
            chooseColor(offg);
            offg.setFont(new Font("TimesRoman",Font.BOLD, fontsize));
            offg.drawString("Ship Customize", 375,270 );
            if (design.contains(mouseClickX, mouseClickY))
            {
                cycleColor = cycleColor + 1;
                chooseColor(offg);
                offg.drawString("Customize", 400,270 );
                mouseClickX = 0;
                mouseClickY = 0;
            }
            Polygon ascension = new Polygon();
            offg.setColor(Color.BLACK);
            ascension.addPoint(505,305); //top left
            ascension.addPoint(395,305); //top right
            ascension.addPoint(395,280); //bottom right
            ascension.addPoint(505,280); //bottom left
            offg.drawPolygon(ascension);
            offg.setColor(Color.GREEN);
            offg.setFont(new Font("TimesRoman",Font.BOLD, fontsize));
            offg.drawString("Ascension", 400,300 );
            if (ascension.contains(mouseClickX, mouseClickY))
            {
                ascensionGameMode = true;
                inMenu = false;
                timer.start();
                startingAsteroids = 0;
            }
            Polygon mouse = new Polygon();
            offg.setColor(Color.BLACK);
            mouse.addPoint(440,335); //top left
            mouse.addPoint(370,335); //top right
            mouse.addPoint(370,310); //bottom right
            mouse.addPoint(440,310); //bottom left
            offg.drawPolygon(mouse);
            offg.setColor(Color.GRAY);
            offg.setFont(new Font("TimesRoman",Font.BOLD, fontsize));
            offg.drawString("Mouse", 370,330 );
            if (mouse.contains(mouseClickX, mouseClickY))
            {
                mouseMode = true;
                MKindex = 0;
            }
            Polygon key = new Polygon();
            offg.setColor(Color.BLACK);
            key.addPoint(530,335); //top left
            key.addPoint(485,335); //top right
            key.addPoint(485,310); //bottom right
            key.addPoint(530,310); //bottom left
            offg.drawPolygon(key);
            offg.setColor(Color.DARK_GRAY);
            offg.setFont(new Font("TimesRoman",Font.BOLD, fontsize));
            offg.drawString("Key", 485,330 );
            if (key.contains(mouseClickX, mouseClickY))
            {
                mouseMode = false;
                MKindex = 1;
            }
            
            offg.setColor(Color.yellow);
            if(MKindex == 0)
            {
                offg.drawPolygon(mouse);
            }
            else if(MKindex == 1)
            {
                offg.drawPolygon(key);
            }
        }
        //double buffering
        ///must go last
        g.drawImage(offscreen, 0, 0, this);
        repaint();
    }
    public boolean collision(VectorSprite thing1, VectorSprite thing2)
    {
        int x, y;
        for(int i = 0; i < ship.drawShape.npoints;i++)
        {
            x = thing1.drawShape.xpoints[i];
            y = thing1.drawShape.ypoints[i];
            if(thing2.drawShape.contains(x,y))
            {
            return true;
            } 
        }
         for(int i = 0; i < thing2.drawShape.npoints;i++)
        {
            x = thing2.drawShape.xpoints[i];
            y = thing2.drawShape.ypoints[i];
            if(thing1.drawShape.contains(x,y))
            {
            return true;
            } 
        }
         return false;
    }
    public void checkCollisions()
    {
        for(int i = 0; i< asteroidList.size();i++)
        {
            double rng;
            if((collision(ship,asteroidList.get(i)) == true && ship.active))
            {
                ship.hit();
                rng = Math.random()*5 + 5;
                for(int k = 0;k < rng;k++)
                {
                    debrisList.add(new Debris(ship.xposition, ship.yposition));
                }
                shiphit.play();
                lives = lives - 1;
                if (lives < 0)
                {
                    lives = 0;
                }
            }
            for(int j = 0; j< bulletList.size();j++)
            {
                if(collision(bulletList.get(j),asteroidList.get(i)))
                {
                    asteroidList.get(i).active = false;
                    bulletList.get(j).active = false;
                    for(int k = 0;k < 100;k++)
                    {
                        debrisList.add(new Debris(asteroidList.get(i).xposition, asteroidList.get(i).yposition));
                    }
                    rnd3 = (Math.random()*10);
                    if (rnd3>= 9.8 && rnd3<= 10)
                    {
                        extraLifeList.add(new ExtraLife(asteroidList.get(i).xposition, asteroidList.get(i).yposition));
                    }
                    if (ascensionGameMode == false)
                    {
                        rnd = (Math.random()*10);
                        if (rnd>= 9 && rnd<= 10)
                        {   
                            scoreMultiplierList.add(new ScoreMultiplier(asteroidList.get(i).xposition, asteroidList.get(i).yposition));
                        }
                        rnd2 = (Math.random()*10);
                        if (rnd2>= 9.5 && rnd2<= 10)
                        {
                            moneyList.add(new Money(asteroidList.get(i).xposition, asteroidList.get(i).yposition));
                        }
                        rnd3 = (Math.random()*10);
                        if (rnd3>= 9.9 && rnd3<= 10)
                        {
                            extraLifeList.add(new ExtraLife(asteroidList.get(i).xposition, asteroidList.get(i).yposition));
                        }
                    }
                    asteroidhit.play();
                    Money = Money + 1;
                }
            }
        }
        for(int i = 0;i < scoreMultiplierList.size();i++)
        {
            if (collision(ship,scoreMultiplierList.get(i))&& ship.active)
            {
                scoreMultiplierList.remove(i);
                x2 = 2;
                x2Counter.counter = 0;
            }
        }
        for(int i = 0;i < moneyList.size();i++)
        {
            if (collision(ship,moneyList.get(i))&& ship.active)
            {
                moneyList.remove(i);
                Money = Money + moneyValue;
            }
        }
        for(int i = 0;i < extraLifeList.size();i++)
        {
            if (collision(ship,extraLifeList.get(i))&& ship.active)
            {
                extraLifeList.remove(i);
                lives = lives + 1;
            }
        }
    }
    public void respawnShip()
    {
        if (!ship.active && ship.counter > 50 && isRespawnSafe())
        {
            ship.reset();
            hasLaser = false;
        }
    }
    public boolean isRespawnSafe()
    {
        double x,y,h;
        for(int i = 0; i < asteroidList.size();i ++)
        {
            x = asteroidList.get(i).xposition - 450;
            y = asteroidList.get(i).yposition - 300;
            h = Math.sqrt(x*x + y*y);
            if(h<100)
            {
                return false;
            }
        }
        return true;
    }
    public void fireBullet()
    {
        if(ship.counter > bulletDelay && ship.active)
        {
            laserTimer ++;
            if (hasLaser && laserTimer < 1000)
            {
               bulletList.add(new Bullet(ship.drawShape.xpoints[0], ship.drawShape.ypoints[0], ship.angle + Math.PI/2));
            }
            else if (!hasLaser || laserTimer >=5)
            {
                bulletList.add(new Bullet(ship.drawShape.xpoints[0], ship.drawShape.ypoints[0], ship.angle + Math.PI/2));
                hasLaser = false;
                laserTimer = 0;
                bulletDelay = 4;
                
            }
            ship.counter = 0;
            laser.play();
        }
    }
    public void checkAsteroidDestruction()
    {
        for(int i = 0; i < asteroidList.size();i ++)
        {
            if(!asteroidList.get(i).active)
            {
                if(asteroidList.get(i).size > 1)
                {
                    asteroidList.add(new Asteroid(asteroidList.get(i).xposition, asteroidList.get(i).yposition, asteroidList.get(i).size - 1, asteroidList.get(i).type));
                    asteroidList.add(new Asteroid(asteroidList.get(i).xposition, asteroidList.get(i).yposition, asteroidList.get(i).size - 1, asteroidList.get(i).type));   
                }
                asteroidList.remove(i);
                if (ascensionGameMode == false)
                {
                    score = x2 * 10 + score;
                }
            }
        }
    }
    public void randomColor(Graphics offg)
    {
        switch((int)Math.round(9*Math.random()))
                {
                    case 1:
                        offg.setColor(Color.MAGENTA);
                        break;
                    case 2:
                        offg.setColor(Color.CYAN);
                        break;
                    case 3:
                        offg.setColor(Color.BLUE);
                        break;
                    case 4:
                        offg.setColor(Color.ORANGE);
                        break;
                    case 5:
                        offg.setColor(Color.RED);
                        break;
                    case 6:
                        offg.setColor(Color.GREEN);
                        break;
                    case 7:
                        offg.setColor(Color.YELLOW);
                        break;
                    case 8:
                        offg.setColor(Color.PINK);
                        break;
                    case 9:
                        offg.setColor(Color.WHITE);
                        break;
                }
    }
    public void chooseColor(Graphics offg)
    {
        switch(cycleColor)
                {
                    case 1:
                        offg.setColor(Color.MAGENTA);
                        break;
                    case 2:
                        offg.setColor(Color.CYAN);
                        break;
                    case 3:
                        offg.setColor(Color.BLUE);
                        break;
                    case 4:
                        offg.setColor(Color.ORANGE);
                        break;
                    case 5:
                        offg.setColor(Color.RED);
                        break;
                    case 6:
                        offg.setColor(Color.GREEN);
                        break;
                    case 7:
                        offg.setColor(Color.YELLOW);
                        break;
                    case 8:
                        offg.setColor(Color.PINK);
                        break;
                    case 9:
                        offg.setColor(Color.WHITE);
                        break;
                }
                        if (cycleColor >= 10)
                        {
                            cycleColor = 1;
                        }
    }
    public void writeFile(String filePath)
    {
        File outputFile;
        BufferedWriter outputWriter;
        
        try
        {
            outputFile = new File(filePath);
            outputWriter = new BufferedWriter(new FileWriter(outputFile));
        
            for(int i = 0; i<highscores.length; i++)
            {
                outputWriter.write(Integer.toString(highscores[i])+"\n");
            }
            outputWriter.close();
        }   
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void readFile(String filePath)
    {
        File inputFile;
        BufferedReader inputReader;
        
        try
        {
            inputFile = new File(filePath);
            inputReader = new BufferedReader(new FileReader(inputFile));
            
            for(int i = 0; i<highscores.length;i++)
            {
                highscores[i] = Integer.parseInt(inputReader.readLine());
            }
            
            inputReader.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void resetGame()
    {
        startingAsteroids = 4;
        inMenu = true;
        while(!asteroidList.isEmpty()){
            for(int i =0; i < asteroidList.size();i++)
            {
                asteroidList.remove(i);
            }
        }
        for(int i =0; i<scoreMultiplierList.size();i++)
        {
            scoreMultiplierList.remove(i);
        }
        for(int i =0; i<moneyList.size();i++)
        {
            moneyList.remove(i);
        }
        for(int i =0; i<extraLifeList.size();i++)
        {
            extraLifeList.remove(i);
        }
        for(int i =0; i<debrisList.size();i++)
        {
            debrisList.remove(i);
        }
        lives = 5;
        Level = 0;
        score = 0;
        Money = 0;
        bulletDelay = 4;
        spawnrate = 250;
        readFile("HighScores.txt");
        for(int i =0; i<asteroidList.size();i++)
        {
            asteroidList.add(new Asteroid((int)Math.round(4*Math.random())));
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) 
    {
        menuMouseX = e.getX();
        menuMouseY = e.getY();
    }

    @Override
    
    public void mousePressed(MouseEvent e) 
    {
        mouseClickX = e.getX();
        mouseClickY = e.getY();
        final int button;
        button = (e.getButton());
        if (button == 1)
        {
            leftClick = true;
        }
        if (button == 3)
        {
            rightClick = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) 
    {
        mouseClickX = 0;
        mouseClickY = 0;
        leftClick = false;
        rightClick = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) 
    {
        
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
        
    }
} 
