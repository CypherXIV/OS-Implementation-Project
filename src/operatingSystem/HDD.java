package operatingSystem;

import static operatingSystem.Constants.LIVE;
import static operatingSystem.Constants.NEW;

import java.util.Random;

import javax.swing.DefaultListModel;

public class HDD {
	private DefaultListModel<Process> processBatchList;
	private boolean prRandom; //whether probability will be random o not
	private int probability;
	private int counter;

	public HDD() {
		processBatchList = new DefaultListModel<>();
		probability = 100;
		prRandom = false;
		counter = 0;
	}

	public Process randomProcessGenerator() {
		if (prRandom) {
			probability = new Random().nextInt(1, 100);
		}
		return (new Random().nextInt(1, 100) <= probability) ? new Process(++counter) : null;
	}

	public void setPrRandom() {
		prRandom = true;
	}

	public boolean processBatchBuffer(ProcessControl pc) {

		//try to create a process
		Process p = randomProcessGenerator();
		//If succedes 
		if (p != null) {
			//add process to process control block
			p.status = NEW;
			p.LiveTime()[NEW] += 1;
			p.LiveTime()[LIVE] += 1;
			pc.addRow(p);
			//update the status in PCB
			pc.changeStatus(p);

			addProcess(p);
			return true;
		}

		return false;
	}

	// Method to verify if the 'processBatchList' has been created.
	public boolean isBatchCreated() {
		return !processBatchList.isEmpty();
	}

	// Method takes a Process object as argument and adds it to the
	// 'processBatchList' if not null.
	public void addProcess(Process p) {
		if (p != null)
			processBatchList.addElement(p);
	}

	public void clearBatch() {
		processBatchList.clear();
	}

	public DefaultListModel<Process> ProcessBatchList() {
		return processBatchList;
	}

	public int getProbability() {
		return probability;
	}

	public void setProbability(int probability) {
		this.probability = probability;
		prRandom = false;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}
}
