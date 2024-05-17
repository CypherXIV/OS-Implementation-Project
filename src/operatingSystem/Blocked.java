package operatingSystem;

import static operatingSystem.Constants.BLOCKED;
import static operatingSystem.Constants.READY;

import javax.swing.DefaultListModel;

public class Blocked {
	DefaultListModel<BlockedProccess> blockedBatchList;
	private BlockedProccess currentProcess;

	public Blocked() {
		blockedBatchList = new DefaultListModel<>();
	}

	public void addProcess(Process process, int timeout) {
		blockedBatchList.addElement(new BlockedProccess(process, timeout));
	}

	//execute the blocked time of process
	//return the process when blocked time finished
	public Process timeOut(ProcessControl pc) {
		Process p_toreturn = null;
		if (!blockedBatchList.isEmpty()) {
			currentProcess = blockedBatchList.getElementAt(0);
			if (currentProcess.blockedTime > 0) {
				currentProcess.blockedTime--;
				currentProcess.process.LiveTime()[BLOCKED] += 1;
				pc.updateCT(currentProcess.process, BLOCKED);
			}

			else {
				currentProcess.process.status = READY;
				pc.changeStatus(currentProcess.process);
				p_toreturn = currentProcess.process;
				blockedBatchList.removeElement(currentProcess);
				currentProcess = null;
			}
		}
		return p_toreturn;
	}
}

// encapsulate process in new class to add the blockedtime property
class BlockedProccess {
	Process process;
	int blockedTime;

	public BlockedProccess(Process p, int timeout) {
		process = p;
		blockedTime = timeout;
	}

	@Override
	public String toString() {
		return "PID: " + process.getPID() + ", SIZE: " + process.getSize() + ", PRIO: " + process.getPriority()
				+ ", BLKD: " + blockedTime;
	}
}
