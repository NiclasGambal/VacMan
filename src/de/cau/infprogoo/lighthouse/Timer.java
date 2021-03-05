package de.cau.infprogoo.lighthouse;

import acm.util.JTFTools;

/**
 * Class that supports executing tasks at a certain period. This can be used as
 * follows: - Construct a Timer object with desired period - Call reset()
 * whenever periodic execution should (re-)start - Call pause() once at each
 * period, e.g. while (true) { // Do something, like moving an object
 * timer.pause(); }
 * 
 * @author rvh
 *
 */
public class Timer {
	// Ivars.
	private double nanoTime;
	private double period;
	private double delay;

	// Constant.
	// Dampening factor used in pause method, should be between 0 and 1.
	private static final double DAMPENING = 0.1;

	/**
	 * The Constructor.
	 * 
	 * @param period The desired cycle period in msec.
	 */
	public Timer(double period) {
		this.period = period;
		reset();
	}

	/**
	 * Resets the timer.
	 */
	public void reset() {
		nanoTime = System.nanoTime();
		delay = period;
	}

	/**
	 * A Method to call at each cycle. A delay adjusts itself such that the cycle
	 * period approaches the desired value.
	 */
	public void pause() {
		double preNanoTime = nanoTime;
		nanoTime = System.nanoTime();

		// cycleTime indicates (in msec) the time passed between the previous call of
		// pause() and this time of pause(). Ideally, it would be cycleTime == period.
		double cycleTime = (nanoTime - preNanoTime) / 1e6;

		// rawDelayAdjustment indicates the difference between the measured cycleTime
		// and the desired period. Ideally, it would be rawDelayAdjustment == 0.
		// rawDelayAdjustment < 0 indicates cycleTime > period, i.e., the last cycle
		// took too long. rawDelayAdjustment > 0 indicates cycleTime < period, i.e., the
		// last cycle was too short.
		double rawDelayAdjustment = period - cycleTime;

		// delayAdjustment indicates how much the current delay (see below) should
		// change such that the actual cycleTime becomes the desired period.
		// Conceptually, the delayAdjustment should be the rawDelayAdjustment computed
		// above. However, to avoid an oscillation in this control loop, we do not use
		// the full rawDelayAdjustment, but apply some dampening factor DAMPENING, which
		// should be between 0 and 1.
		double delayAdjustment = DAMPENING * rawDelayAdjustment;

		// delay indicates how long we have to pause such that the cycle time is
		// (approximately) the desired period.
		delay += delayAdjustment;

		// If delay is positive, we have to pause.
		// If delay stays negative for many cycles, with a high amount, this
		// means that the measured cycle time is significantly longer than the
		// desired period even without any extra delay. I.e., we degrade to a
		// "run as fast as possible mode". Then the whole Timer business has no
		// effect, and we should probably think about increasing our period or
		// decreasing the computational load of each cycle.
		// A possible extension would be to watch for this case and to react
		// somehow to it, e.g. by printing a warning on the console.
		if (delay > 0) {
			JTFTools.pause(delay);
		}
	}
}
