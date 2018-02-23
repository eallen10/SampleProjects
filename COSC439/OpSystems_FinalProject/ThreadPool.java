import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.*;
import java.util.concurrent.*;
import java.util.*;

class ThreadPool extends Thread
{
	Random rand = new Random();
	
	CountDownLatch latch;
	
	int loops;
	String name;
	CyclicBarrier cyc;
	static volatile boolean isStopped; 
		
    int[] userSpecs = new int[3];
	
	JFrame frame = new JFrame("Thread");
	JLabel labelOne = new JLabel("");
	JLabel labelTwo = new JLabel("");
		
	
	public ThreadPool(int l, String n, CyclicBarrier c, boolean s, int[] uS)
	{
		cyc = c;
		loops = l;
		name = n;
		isStopped = s;
		
		if(name. equals("Thread 1"))
		{
			userSpecs = uS;	
		}
        
        frame.setPreferredSize(new Dimension(1300, 200));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        if(name.equals("Thread 1"))
        {
        	frame.setLocation(250, 100);
        }
        else if(name.equals("Thread 2"))
        {
        	frame.setLocation(250, 300);
        }
        else if(name.equals("Thread 3"))
        {
        	frame.setLocation(250, 500);
        }
        else if(name.equals("Thread 4"))
        {
        	frame.setLocation(250, 700);
        }
        /*
        if(name.equals("Thread 1"))
        {
        	frame.setLocation(50, 300);
        }
        else if(name.equals("Thread 2"))
        {
        	frame.setLocation(450, 300);
        }
        else if(name.equals("Thread 3"))
        {
        	frame.setLocation(850, 300);
        }
        else if(name.equals("Thread 4"))
        {
        	frame.setLocation(1250, 300);
        }*/
        frame.setLayout(new FlowLayout());
        frame.add(labelOne);
        frame.add(labelTwo);
        labelTwo.setHorizontalAlignment(JLabel.RIGHT);
        //labelOne.setVerticalAlignment(JLabel.TOP);
        labelOne.setHorizontalAlignment(JLabel.LEFT);
        //labelOne.setVerticalAlignment(JLabel.CENTER);
        labelTwo.setText("hello");
        
        frame.pack();
        frame.setVisible(true);  

	} 
		
	public void run()
	{
		int npcSpecs[] = new int[3];
		
		if(!name.equals("Thread 1"))
		{
			
			npcSpecs = ranGen();
			printSpecs(npcSpecs);
		}
		else 
		{
			printSpecs(userSpecs);
		}
			
		try{cyc.await();} catch(InterruptedException e){} catch(BrokenBarrierException b) {}
		
		int x = 5 * (userSpecs[2] + npcSpecs[2]);
		for(int i=x; i<loops+1; i++)
		{
			labelOne.setFont(new Font("Courier", Font.BOLD, 40));
			try{sleep(1);} catch(InterruptedException ex){}
			labelOne.setText(Integer.toString(x));
			try{sleep(20 - userSpecs[0] - npcSpecs[0]);} catch(InterruptedException ex){}
			try{sleep((rand.nextInt(40) + 20)- npcSpecs[1] - userSpecs[1]);} catch(InterruptedException ex){}
			
			if(isStopped == true)
			{
				break;
			}
			x++;
		}
		isStopped = true;
		if(x > 499) 
			frame.getContentPane().setBackground(Color.GREEN);
		else
			frame.getContentPane().setBackground(Color.RED);
	}
	
	int[] ranGen()
	{
		int[] ar = new int[3];
		ar[0] = rand.nextInt(19) + 1;
		ar[1] = rand.nextInt(20-ar[0]) +1;
		ar[2] = 20 - ar[0] - ar[1];
		
		return ar;
	}
	
	void printSpecs(int[] specs)
	{
		System.out.println(name + "           " + specs[0] + "             " + specs[1] + "             " + specs[2]);
	} 
}
