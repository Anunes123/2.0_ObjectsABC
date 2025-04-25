//Basic Game Application
//Version 2
// Basic Object, Image, Movement
// Astronaut moves to the right.
// Threaded

//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


// adds the Keylistener to the implements
public class BasicGameApp implements Runnable, KeyListener {


	//Sets the width and height of the program window
	final int WIDTH = 1000;
	final int HEIGHT = 700;

	//Declare the variables needed for the graphics
	public JFrame frame;
	public Canvas canvas;
	public JPanel panel;

	public BufferStrategy bufferStrategy;
	public Image bird1Pic;
	public Image bird2Pic;
	public Image bird3Pic;
	public Image BackgroundPic;

	//Declare the birds that are used
	private Bird bird1;
	private Bird bird2;
	private Bird bird3;
	// makes birds array and says how big the array is
	Bird[] birdsArray = new Bird[10];
	Bird [] birdsArray2 = new Bird[10];


	// Main method definition
	// This is the code that runs first and automatically
	public static void main(String[] args) {
		BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
		new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method
	}


	// Constructor Method
	public BasicGameApp() {

		setUpGraphics();

		//variable and objects
		//create (construct) the objects needed for the game and load up

		bird1Pic = Toolkit.getDefaultToolkit().getImage("brid.jpg"); //load the picture
		bird2Pic = Toolkit.getDefaultToolkit().getImage("Bird22.jpg");
		bird3Pic = Toolkit.getDefaultToolkit().getImage("robotbird.jpg");
		BackgroundPic = Toolkit.getDefaultToolkit().getImage("clouds-7050884_1280.jpg");
		bird1 = new Bird(300, 20);
		bird2 = new Bird(500, 600);
		bird3 = new Bird(100,200);

		bird2.dx = 0;
		bird2.dy = 0;

		//creates the bird array and gives the birds the locations to be placed in
		for(int x = 0; x< birdsArray.length; x++){
birdsArray[x]= new Bird((int)(Math.random()*900), (int)(Math.random()*400));

		}
for (int x = 0; x< birdsArray2.length; x++)
{birdsArray2[x]=  new Bird((int)(Math.random()*900), (int)(Math.random()*300));}
	}// BasicGameApp()


//*******************************************************************************
//User Method Section
//
// put your code to do things here.

	// main thread
	// this is the code that plays the game after you set things up
	public void run() {

		//for the moment we will loop things forever.
		while (true) {

			moveThings();  //move all the game objects
			render();  // paint the graphics
			pause(20); // sleep for 10 ms
		}
	}


	public void moveThings() {
		//calls the moves we want for each of the birds
		collisions();
		bird1.bounce();
		bird2.bounce();
		bird3.move();
		bird3.wrap();
		//	if(bird2.xpos>500) {
		//		bird2.isAlive = false;
		//		System.out.println("oops");
		//	}

		for (int y = 0; y < birdsArray.length; y++){birdsArray[y].bounce();}
		for (int y = 0; y < birdsArray2.length; y++){birdsArray2[y].wrap();}
	}
// changes directions of birds when they hit
	public void collisions() {
		if (bird1.rec.intersects(bird2.rec) && bird1.iscrash == false && bird2.isAlive && bird1.isAlive) {
			System.out.println("boom");
			bird1.dx = -bird1.dx;
			bird1.dy = -bird1.dy;
			bird2.dx = -bird2.dx;
			bird2.dy = -bird2.dy;
			bird1.dx = 2 + bird1.dx;
			bird1.dy = 2 + bird1.dy;
			bird2.height = bird2.height + 2;
			bird2.width = bird2.width + 2;
			bird1.iscrash = true;
			bird2.isAlive = false;

		}
		if (!bird1.rec.intersects(bird2.rec)) {
			bird1.iscrash = false;
			for (int b = 0; b < birdsArray.length; b++) {
				if (bird2.rec.intersects(birdsArray[b].rec)) {
					//System.out.println("crashing");
					bird2.isAlive=false;
				}
			}

		}

	}

	//Pauses or sleeps the computer for the amount specified in milliseconds
	public void pause(int time) {
		//sleep
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {

		}
	}

	//Graphics setup method
	private void setUpGraphics() {
		frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

		panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
		panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
		panel.setLayout(null);   //set the layout

		// creates a canvas which is a blank rectangular area of the screen onto which the application can draw
		// and trap input events (Mouse and Keyboard events)
		canvas = new Canvas();
		canvas.setBounds(0, 0, WIDTH, HEIGHT);
		canvas.setIgnoreRepaint(true);

		//Step2:  add key listener to canvas as this
		canvas.addKeyListener(this);
		panel.add(canvas);  // adds the canvas to the panel.

		// frame operations
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
		frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
		frame.setResizable(false);   //makes it so the frame cannot be resized
		frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

		// sets up things so the screen displays images nicely.
		canvas.createBufferStrategy(2);
		bufferStrategy = canvas.getBufferStrategy();
		canvas.requestFocus();
		System.out.println("DONE graphic setup");

	}


	//paints things on the screen using bufferStrategy
	private void render() {
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, WIDTH, HEIGHT);
		g.drawImage(BackgroundPic, 0, 0, WIDTH, HEIGHT, null);

		//draw the image of bird1
		g.drawImage(bird1Pic, bird1.xpos, bird1.ypos, bird1.width, bird1.height, null);

//draw the image of bird2
		if (bird2.isAlive == true) {
			g.drawImage(bird2Pic, bird2.xpos, bird2.ypos, bird2.width, bird2.height, null);
		}
//draw the image of bird3
if (bird3.isAlive == true) {
	g.drawImage(bird3Pic, bird3.xpos, bird3.ypos, bird3.width, bird3.height, null);
}


		for(int l = 0; l< birdsArray.length; l++){
			g.drawImage(bird1Pic, birdsArray[l].xpos, birdsArray[l].ypos, birdsArray[l].width, birdsArray[l].height, null);

		}

		for(int l = 0; l< birdsArray2.length; l++){
			g.drawImage(bird3Pic, birdsArray2[l].xpos, birdsArray2[l].ypos, birdsArray2[l].width, birdsArray2[l].height, null);

		}
		g.dispose();
		bufferStrategy.show();


	}

	@Override
	public void keyTyped(KeyEvent e) {//dont use

	}
// code that recognizes if key is pressed
	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("pressed");
		System.out.println(e.getKeyChar());
		System.out.println(e.getKeyCode());

		// if key 38 (up arrow) is pressed then up is made true and down is made false
		if (e.getKeyCode() == 38) {
			System.out.println("up");
			bird2.up = true;
			bird2.down = false;
		}
		// if key 40 (down arrow) is pressed then down is made true and up is made false
		if (e.getKeyCode() == 40) {
			System.out.println("down");
			bird2.down = true;
			bird2.up = false;
		}
		// if key 37 (left arrow) is pressed then left is made true and right is made false
		if (e.getKeyCode() == 37) {
			System.out.println("left");
			bird2.left = true;
			bird2.right = false;
		}
		// if key 39 (right arrow) is pressed then right is made true and left is made false
		if (e.getKeyCode() == 39) {
			System.out.println("right");
			bird2.right = true;
			bird2.left = false;
		}
		if (e.getKeyCode() == 40 && e.getKeyCode() == 39) {
			bird2.dx = 5;
			bird2.dy = 5;
		}
	}
	@Override
	//detects when key is released to make it so that the bird stops when a key is released so the bird
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == 38) {
			System.out.println("up");
			bird2.up = false;
			bird2.dy=0;
		}
		if (e.getKeyCode() == 40) {
			System.out.println("down");
			bird2.down = false;
			bird2.dy=0;

		}
		if (e.getKeyCode() == 37) {
			System.out.println("left");
			bird2.left = false;
			bird2.dx=0;
		}
		if (e.getKeyCode() == 39) {
			System.out.println("right");
			bird2.right = false;
			bird2.dx=0;
		}
		if (e.getKeyCode() == 40 && e.getKeyCode() == 39) {
			bird2.dx = 5;
			bird2.dy = 5;
		}
	}


	}

// step3: add methods keyreleased, key pressed, and key typed
	//homework idenitfy key codes for up down left right
	// up 38



	// down 40
	// left 37
	// right 39

