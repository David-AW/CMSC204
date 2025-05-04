import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

/**
   This component draws two car shapes.
 */
public class CarPanel extends JComponent
{  
	private Car car1;
	private final int CAR_WIDTH = 60, CAR_HEIGHT = 30;
	private int x,y, delay;
	private CarQueue carQueue;
	private int direction;

	CarPanel(int x1, int y1, int d, CarQueue queue)
	{
		delay = d;
		x=x1;
		y=y1;
		car1 = new Car(x, y, this);
		carQueue = queue;
	}
	
	public void checkForCollisions() {
		switch(direction) {
		case 0:
			if (y-10 < 0)
				direction = 1;
			break;
		case 1:
			if (y+10+CAR_HEIGHT > this.getParent().getSize().height)
				direction = 0;
			break;
		case 2: 
			if (x+10+CAR_WIDTH > this.getParent().getSize().width)
				direction = 3;
			break;
		case 3:
			if (x-10 < 0)
				direction = 2;
			break;
		}
	}
	
	public void startAnimation()
	{
		class AnimationRunnable implements Runnable
		{
			public void run()
			{
				try
				{
					for (int j = 0; j < 10; j++) {
						int temp = carQueue.deleteQueue();
						if (temp != -1)
							direction = temp;
						for (int i = 0; i < 10; i++) {
							checkForCollisions();
							if (direction == 0) {
								y = y-10;
							}else if (direction == 1) {
								y = y+10;
							}else if (direction == 2) {
								x = x+10;
							}else if (direction == 3) {
								x = x-10;
							}
							repaint();
							Thread.sleep(delay*100);
						}
					}
				}
				catch (InterruptedException exception)
				{

				}
				finally
				{

				}
			}
		}

		Runnable r = new AnimationRunnable();
		Thread t = new Thread(r);
		t.start();
	}

	public void paintComponent(Graphics g)
	{  
		Graphics2D g2 = (Graphics2D) g;

		car1.draw(g2,x,y);    
	}
}
