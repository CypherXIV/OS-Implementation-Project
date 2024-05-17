package operatingSystem;

import static operatingSystem.Constants.BLOCKED;
import static operatingSystem.Constants.LIVE;
import static operatingSystem.Constants.READY;
import static operatingSystem.Constants.RUNNING;

import java.util.Random;

public class CPU {

	protected boolean isEnabled;
	private int processProgress; //tracks the progress in the execution of a process
	private final int quantumTime; //max time a process can be executed
	private Process currentProcess;
	private final int probabilityBlock; //probability of a process to be blocked
	protected static int maxBlockedTime = 10;

	public CPU() {
		processProgress = 0;
		quantumTime = 5;
		currentProcess = null;
		probabilityBlock = 7;
		isEnabled = false;
	}

	//cpu is available if no process is assigned
	public boolean isAvailable() {
		return currentProcess == null;
	}

	public void freeCPU() {
		currentProcess = null;
		processProgress = 0;
	}

	//altern free_cpu method where it return the process being executed
	public Process u_freeCPU() {
		Process temp = currentProcess;
		currentProcess = null;
		processProgress = 0;
		return temp;
	}

	//force cpu to stop the execution and return the current process to disp
	public void evict(ProcessControl pc, Dispatcher dis) {
		if (!isEnabled && !isAvailable()) {
			//update the process size
			currentProcess.setSize(currentProcess.getSize() - processProgress);
			currentProcess.status = READY;
			pc.changeStatus(currentProcess);
			dis.dispatcherBatchList.addElement(currentProcess);
			freeCPU();
		}
	}

	// Method takes a Process object as an argument. This method simulates the
	// execution of a process in the batch
	public Process execute(ProcessControl pc, Blocked blocked) {
		//if it is not available there is a process to execute
		if (!isAvailable()) {
			//check if progress is less than the process size 
			//and if the progress hasnt reached the quantum time
			if (processProgress < currentProcess.getSize() && processProgress % 10 < quantumTime) {

				//check if the process will be blocke
				if (!randomBlockedGenerator()) {
					//if not
					processProgress++;
					//increase the clockticks the process spent in cpu
					currentProcess.LiveTime()[RUNNING] += 1;
					pc.updateCT(currentProcess, RUNNING);
				} else {
					//if yes
					//update the process size
					currentProcess.setSize(currentProcess.getSize() - processProgress);
					currentProcess.status = BLOCKED;
					pc.changeStatus(currentProcess);
					//we transfer the process to blocked
					blocked.addProcess(currentProcess, getrandomBlockedTime());
					freeCPU();
				}
			} else { // if the process reached quantum time or execution finished

				//if progress is less than size execution is not finished
				//quantum time was reached
				if (processProgress < currentProcess.getSize()) {
					//update size
					currentProcess.setSize(currentProcess.getSize() - processProgress);
					currentProcess.status = READY;					
					pc.changeStatus(currentProcess);

					//transfer to disp
					return u_freeCPU();
				} else {
					//if process was finished
					currentProcess.setSize(currentProcess.getSize() - processProgress);
					//set status as finished
					currentProcess.status = LIVE;
					pc.changeStatus(currentProcess);
				}
				freeCPU();
			}
		}
		return null;
	}

	public boolean randomBlockedGenerator() {
		//randomly decides to block or not
		return (new Random().nextInt(1, 100) <= probabilityBlock) ? true : false;
	}

	public int getrandomBlockedTime() {
		return new Random().nextInt(1, maxBlockedTime);
	}

	public int getProcessProgress() {
		return processProgress;
	}

	public void setProcessProgress(int processProgress) {
		this.processProgress = processProgress;
	}

	public Process getCurrentProcess() {
		return currentProcess;
	}

	public void setCurrentProcess(Process currentProcess, ProcessControl pc) {
		if (isAvailable()) {
			this.currentProcess = currentProcess;
			this.currentProcess.status = RUNNING;
			pc.changeStatus(currentProcess);
		}

	}

}
