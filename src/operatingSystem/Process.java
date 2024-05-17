package operatingSystem;

import static operatingSystem.Constants.BLOCKED;
import static operatingSystem.Constants.LIVE;
import static operatingSystem.Constants.NEW;
import static operatingSystem.Constants.READY;
import static operatingSystem.Constants.RUNNING;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Process {

	private Random random;

	private int size;
	private int pID;
	private int priority;
	protected static int maxSize = 10;
	protected int status;
	private int[] liveTime;

	public Process(int pID) {
		random = new Random();
		this.pID = pID;
		size = random.nextInt(1, maxSize);
		priority = random.nextInt(1, 10);
		status = NEW;
		liveTime = new int[7];
	}

	protected int[] LiveTime() {
		return liveTime;
	}

	protected String getStatus() {
		String s_status = null;
		switch (status) {
		case NEW:
			s_status = "New";
			break;
		case READY:
			s_status = "Ready";
			break;
		case RUNNING:
			s_status = "Running";
			break;
		case BLOCKED:
			s_status = "Blocked";
			break;
		case LIVE:
			s_status = "Finished";
			break;
		}

		return s_status;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * @return the pID
	 */
	public int getPID() {
		return pID;
	}

	/**
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}

	// Overrides the toString method.
	@Override
	public String toString() {
		return "Process ID: " + pID + ", Size: " + size + ", Priority: " + priority;
	}

}

//define how process objects can be compared

class SortbyPriority {
	
	List<Process> processing = new LinkedList<Process>();
	
	public void compare(){
		processing.sort(Comparator.comparing(Process::getPriority).reversed());	
		}
}
