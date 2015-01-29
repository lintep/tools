package tools.util.systemmonitoring;

import org.apache.lucene.util.RamUsageEstimator;

import java.lang.instrument.Instrumentation;

public class Memory {

	private static Instrumentation instrumentation;

	public static void premain(String args, Instrumentation inst) {
		instrumentation = inst;
	}

	public static long getObjectSize(Object o) {
		return instrumentation.getObjectSize(o);
	}

	public static void callGC() {
		System.gc();
	}

	private final static Runtime runtime = Runtime.getRuntime();

	public static long getUsedMemmory() {
		callGC();
		return (runtime.totalMemory() - runtime.freeMemory());
	}

	public static long getUsedMemmoryMb() {
		return (long) (getUsedMemmory() / (1024 * 1024.0));
	}

	public static long getUsedMemmoryKb() {
		return (long) (getUsedMemmory() / (1024.0));
	}

	public static long getFreeMemmory() {
		callGC();
		return runtime.freeMemory();
	}

	public static long getFreeMemmoryMb() {
		return (long) (getFreeMemmory() / (1024.0 * 1024));
	}

	public static long getFreeMemmoryKb() {
		return (long) (getFreeMemmory() / (1024.0));
	}

	public static long getTotalMemmory() {
		callGC();
		return runtime.totalMemory();
	}

	public static long getTotalMemmoryMb() {
		return (long) (getTotalMemmory() / (1024.0 * 1024));
	}

	public static long getTotalMemmoryKb() {
		return (long) (getTotalMemmory() / (1024.0));
	}

	public static long getMaxMemmory() {
		callGC();
		return runtime.maxMemory();
	}

	public static long getMaxMemmoryMb() {
		return (long) (getTotalMemmory() / (1024.0 * 1024));
	}

	public static long getMaxMemmoryKb() {
		return (long) (getTotalMemmory() / (1024.0));
	}

	
	static RamUsageEstimator ramUsageEstimator = new RamUsageEstimator();
	public static long getObjectRamUsageWithLucene(Object obj) {		
		return ramUsageEstimator.estimateRamUsage(obj);
	}
}