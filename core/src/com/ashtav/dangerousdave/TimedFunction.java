package com.ashtav.dangerousdave;

public class TimedFunction {

	private Integer secondsTillExecute;
	private Runnable functionToExecute;

	public TimedFunction(Integer seconds, Runnable functionToExecute) {
		this.secondsTillExecute = seconds;
		this.functionToExecute = functionToExecute;
	}

	public TimedFunction update(Integer seconds) {
		this.secondsTillExecute -= seconds;
		if (this.secondsTillExecute <= 0) {
			functionToExecute.run();
			return this;
		}
		return null;
	}
}
