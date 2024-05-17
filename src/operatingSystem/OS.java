package operatingSystem;

import static operatingSystem.Constants.NEW;
import static operatingSystem.Constants.READY;

import java.util.ArrayList;

import javax.swing.DefaultListModel;

public class OS {
	ArrayList<Process> returnedToDis;
	CPU[] cpu;
	Dispatcher dispatcher;
	HDD hdd;
	ProcessControl pcb;
	Blocked blocked;
	OS_GUI gui;

	public OS(CPU[] cpu, Dispatcher dispatcher, HDD hdd, ProcessControl pcb, Blocked blocked, OS_GUI gui) {
		returnedToDis = new ArrayList<>();
		this.cpu = cpu;
		this.hdd = hdd;
		this.dispatcher = dispatcher;
		this.pcb = pcb;
		this.blocked = blocked;
		this.gui = gui;
	}

	//manages the processes control flow
	public void processControl() {
		
		//tracks the last process created
		Process mostRecentProcess = null;
		//controls if it will transfer process to dispatch
		boolean dispatch = true;
		
		//check if hdd is not full
		if (hdd.ProcessBatchList().size() < 16) {
			//try to creates a process 
			if (hdd.processBatchBuffer(pcb)) {
				//if proccess was created 
				//we get the last created process 
				mostRecentProcess = hdd.ProcessBatchList().lastElement();
			}
			
			//verify if the only element in hdd is the last created process 
			//if so it wont continue to dispatcher until next click
			if (mostRecentProcess != null && hdd.ProcessBatchList().size() == 1) {
				dispatch = false;
			}
		}

		//store the processes that completed blocked time
		addToReturnedToDis(blocked.timeOut(pcb));

		
		for (CPU cpu_ : cpu) {
			if (cpu_.isEnabled) {
				//store the processes that reached their maxed quantum time
				addToReturnedToDis(dispatcher.cpuScheduling(cpu_, pcb, blocked));
			}
		}

		//if there are processes in disp count the click they spent in there
		if (dispatcher.isDispatching()) {
			ClockTick(dispatcher.DispatcherBatchList(), READY, pcb);
		}

		//if dispatcher is not full and we can dispatch
		if (dispatcher.dispatcherBatchList.size() < 11 && dispatch) {
			//dequeue a process from hdd and transfer to dispatcher
			//insert by priority order
			dispatcher.priorityScheduling(hdd, pcb);
		}

		//is there are processes in hdd after dispatching
		//we count the click they spent in there
		if (hdd.isBatchCreated() && dispatch) {
			ClockTick(hdd.ProcessBatchList(), mostRecentProcess, NEW, pcb);
		}

		//add all the process that were returned from blocked and cpu to the disp
		dispatcher.dispatcherBatchList.addAll(returnedToDis);
		returnedToDis.clear();
	}

	//Add process to temporary list so when it goes from blocked, or if a CPU expends its quantum time resource
	private void addToReturnedToDis(Process p) {
		if (p != null) {
			returnedToDis.add(p);
		}
	}


	private void ClockTick(DefaultListModel<Process> list, int ct, ProcessControl pcb) { // Updates the CTT of every
																							// process in the list
		Process process;
		for (int i = 0; i < list.size(); i++) {
			process = list.getElementAt(i);
			process.LiveTime()[ct] += 1;
			pcb.updateCT(process, ct);
		}
	}

	private void ClockTick(DefaultListModel<Process> list, Process last, int ct, ProcessControl pcb) { // Updates the
																										// CTT of every
																										// process in
																										// the list
																										//except the last 
																										//created
		Process process;
		for (int i = 0; i < list.size(); i++) {
			process = list.getElementAt(i);
			if (process != last) {
				process.LiveTime()[ct] += 1;
				pcb.updateCT(process, ct);
			}
		}
	}

	public void setNewProcessPR(int probability) {
		hdd.setProbability(probability);
	}

	public void setNewProcessPRtoRandom() {
		hdd.setPrRandom();
	}

	public void setNumberOfCPUs(int cores) {
		for (int i = 0; i < cpu.length; i++) {
			if (i < cores) {
				cpu[i].isEnabled = true;
			} else {
				cpu[i].isEnabled = false;
				cpu[i].evict(pcb, dispatcher);
			}
		}
		gui.updateCPUFields(cpu);
		gui.updateEnabledCPU(cpu);
	}

	public void setProcessSize(int size) {
		Process.maxSize = size;
	}

	public void setMaxTimeBlocked(int max) {
		CPU.maxBlockedTime = max;
	}
}
