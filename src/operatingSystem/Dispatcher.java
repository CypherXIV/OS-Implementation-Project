package operatingSystem;

import static operatingSystem.Constants.READY;

import javax.swing.DefaultListModel;

public class Dispatcher {
	DefaultListModel<Process> dispatcherBatchList;

	public Dispatcher() {
		dispatcherBatchList = new DefaultListModel<>();
	}

	// dequeue a process from hdd and added to disp sorted by priority
	public void priorityScheduling(HDD hdd, ProcessControl pc) {
		// wether the batch in hdd is created or not
		if (hdd.isBatchCreated()) {

			// dequeue from hdd
			Process process = hdd.ProcessBatchList().remove(0);
			process.status = READY;
			process.LiveTime()[READY] += 1;

			// insert by priority
			addPrior(process);

			// update the status in PCB
			pc.changeStatus(process);
			pc.updateCT(process, READY);
		}
	}

	// insert the process in order
	public void addPrior(Process process) {
		int index;
		for (index = 0; index < dispatcherBatchList.size(); index++) {
			if (process.getPriority() >= dispatcherBatchList.elementAt(index).getPriority()) {
				break;
			}
		}

		// insert the process when found another process with lower or equal priority
		dispatcherBatchList.add(index, process);
	}

	// if cpu is available dequeue a proccess to it if not, ask cpu to execute its
	// current process
	// return the executed process or null if still executing
	public Process cpuScheduling(CPU cpu, ProcessControl pcb, Blocked blocked) {
		if (cpu.isAvailable() && isDispatching()) {
			cpu.setCurrentProcess(dispatcherBatchList.remove(0), pcb);
		} else if (!cpu.isAvailable()) {
			return cpu.execute(pcb, blocked);
		}
		return null;
	}

	// verify the 'dispatcher batch' been created
	public boolean isDispatching() {
		return !dispatcherBatchList.isEmpty();
	}

	// Getter dispatcher batch
	public DefaultListModel<Process> DispatcherBatchList() {
		return dispatcherBatchList;
	}

}
