import java.awt.*;

/**
 * Created by chales on 11/6/2017.
 */
public class Bird {

    //VARIABLE DECLARATION SECTION
    //Here's where you state which variables you are going to use.
    public String name;                //holds the name of the hero
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public int width;
    public int height;
    public boolean isAlive;            //a boolean to denote if the birds are alive or dead
public Rectangle rec;
public boolean iscrash;                // shows if the birds are crashing
public boolean up;                     // makes the bird go up
public boolean down;                     // makes the bird go down
public boolean left;                     // makes the bird go left
public boolean right;                     // makes the bird go right
    // METHOD DEFINITION SECTION

    // Constructor Definition
    // A constructor builds the object when called and sets variable values.


    //bird peramiters eg if it alive
    public Bird(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx = 2;
        dy = 1;
        width = 60;
        height = 60;
        isAlive = true;
rec=new Rectangle(xpos,ypos,width, height);
iscrash = false;
up=false;
down=false;
left=false;
right=false;

    } // constructor

    //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
    public void move() {
        xpos = xpos + dx;
        ypos = ypos + dy;
        rec=new Rectangle(xpos,ypos,width, height);

    }


    //changes signs of dx and dy when they hit the edges of the screen to make them bounce
    public void bounce() {
        xpos = xpos + dx;
        ypos = ypos + dy;
        rec=new Rectangle(xpos,ypos,width, height);
        if(up==true){
            dy=-5;
        }
        if(down== true){
            dy=5;
        }
        if(left== true){
            dx=-5;
        }
        if(right== true){
            dx=5;
        }
        if (xpos > 1000) {
            dx = -dx;

        }
        if (ypos > 690) {
            dy = -dy;

        }

        if (ypos < 0) {
            dy = -dy;
        }


        if (xpos < 2) {
            dx = -dx;
        }
        xpos = xpos + dx;
        ypos = ypos + dy;
        rec=new Rectangle(xpos,ypos,width, height);



    }

        public void wrap() {
            xpos = xpos + dx;
            ypos = ypos + dy;
            if (xpos > 1000) {
              xpos=1;

            }
            if (ypos > 700) {
                ypos=1;

            }
            if (ypos < 0) {
                ypos=700;

            }
            if (xpos < 1) {
                xpos=700;

            }
            rec=new Rectangle(xpos,ypos,width, height);
        }
        public void control(){
            xpos = xpos + dx;
            ypos = ypos + dy;
            rec=new Rectangle(xpos,ypos,width, height);

        if(up==true){
                dy=-5;
            }
            if(down== true){
                dy=5;
            }
            if(left== true){
                dx=-5;
            }
            if(right== true){
                dx=5;
            }
            if (xpos > 1000) {
                dx = -dx;

            }
            if (ypos > 690) {
                dy = -dy;

            }

            if (ypos < 0) {
                dy = -dy;
            }


            if (xpos < 2) {
                dx = -dx;
            }



        }





    }





