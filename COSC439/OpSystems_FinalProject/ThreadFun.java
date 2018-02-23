import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.LinkedList;
import java.util.concurrent.*;
import java.util.*;

class ThreadFun
{
	public static void main(String[] args)
	{
		int loops = 500;
		Scanner scan = new Scanner(System.in);
		Random rand = new Random();
		int[] userSpecs = new int[3];
		
		CyclicBarrier gate = new CyclicBarrier(1);

		boolean isStopped = false;
		
		int burst;
		int wait;
		int hstart;
		int numTokens = 20;
		
		System.out.println("You have 20 tokens to spend");
		
		System.out.print("Burst Time (" + numTokens + " tokens remaining): ");
		userSpecs[0] = scan.nextInt();
		System.out.println();
		
		numTokens -= userSpecs[0];
		
		System.out.print("Wait Time (" + numTokens + " tokens remaining): ");
		userSpecs[1] = scan.nextInt();
		System.out.println();
		
		userSpecs[2] = 20 - userSpecs[0]-userSpecs[1];
		
		System.out.print("Head Start Time: " + userSpecs[2]);
		System.out.println();
		System.out.println();
		System.out.println("              Burst Time      Wait Time      Head Start");
		
		ThreadPool t1 = new ThreadPool(loops, "Thread 1", gate, isStopped, userSpecs);
		ThreadPool t2 = new ThreadPool(loops, "Thread 2", gate, isStopped, userSpecs);
		ThreadPool t3 = new ThreadPool(loops, "Thread 3", gate, isStopped, userSpecs);
		ThreadPool t4 = new ThreadPool(loops, "Thread 4", gate, isStopped, userSpecs);
		
		t1.start();
		t2.start(); 
		t3.start();
		t4.start();
		
		try{gate.await();} catch(InterruptedException e){} catch(BrokenBarrierException b) {}
	}
 
}

 
