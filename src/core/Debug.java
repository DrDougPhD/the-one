/*
 * Copyright 2010 Aalto University, ComNet
 * Released under GPLv3. See LICENSE.txt for details.
 */
package core;

import java.io.PrintStream;
import java.util.Formatter;
import java.util.Locale;

/**
 * Debugging info printer with time stamping. This class is not to be actively
 * used but convenient for temporary debugging.
 */
public class Debug {
	/* TODO: Populate these values upon initialization. Requires the explicit
	 * setting of this stuff at some very high level, most likely in the
	 * src/core/SimScenario.java file. You'll need to add the appropriate
	 * config keywords to the namespace.
	 */
	private static final String DEBUG_LEVEL_S = "debugLevel";
	private static final String DEBUG_LOG_FILE_S = "logToFile";
	
	private static PrintStream out = System.out;
	private static int debugLevel = 0;
	private static long timingStart = -1;
	private static String timingCause;
	
	private static final StringBuilder sb = new StringBuilder();
	private static final Formatter formatter = new Formatter(sb, Locale.US);

	/**
	 * Sets the current debug level (smaller level -> more messages)
	 * @param level The level to set
	 */
	public static void setDebugLevel(int level) {
		debugLevel = level;
	}

	/**
	 * Sets print stream of debug output.
	 * @param outStrm The stream
	 */
	public static void setPrintStream(PrintStream outStrm) {
		out = outStrm;
	}
	
	/**
	 * Write all logs to a debug log file.
	 * @param fileUrl URL of the file to which logs should be written
	 * @todo Implement this functionality.
	 */
	public static void writeLogsToFile(String fileUrl) {
	}

	/**
	 * Prints text to output with level 0
	 * @param txt text to print
	 */
	public static void p(String txt) {
		p(txt, 0, false);
	}

	/**
	 * Prints text to output given with level
	 * @param level The debug level
	 * @param txt text to print
	 */
	public static void p(String txt, int level) {
		p(txt,level, false);
	}


	/**
	 * Debug print with a timestamp
	 * @param txt Text to print
	 * @param level Debug level
	 */
	public static void pt(String txt, int level) {
		p(txt,level,true);
	}

	/**
	 * Debug print with a timestamp and 0 level
	 * @param txt Text to print
	 */
	public static void pt(String txt) {
		p(txt,0,true);
	}

	/**
	 * Print the formatted text template.
	 * @param format Template string with format specifiers
	 * @param args the objects to insert into the template string
	 */
	public static void pfln(String format, Object ... args) {
		p(formatter.format(format, args).toString());
	}
	
	/**
	 * Print text to debug output.
	 * @param txt The text to print
	 * @param level The debug level (only messages with level >= debugLevel
	 * are printed)
	 * @param timestamp If true, text is (sim)timestamped
	 */
	public static void p(String txt, int level, boolean timestamp) {
		String time = "";
		int simTime = SimClock.getIntTime();
		if (level < debugLevel) {
			return;
		}

		if (timestamp) {
			time = "[@"+simTime+"]";
		}
		out.println("D" + time + ": " + txt);
	}

	/**
	 * Start timing an action.
	 * @see #doneTiming()
	 */
	public static void startTiming(String cause) {
		if (timingStart != -1) {
			doneTiming();
		}
		timingCause = cause;
		timingStart = System.currentTimeMillis();
	}

	/**
	 * End timing an action. Information how long the action took is
	 * printed to debug stream.
	 * @see #startTiming(String)
	 */
	public static void doneTiming() {
		long end = System.currentTimeMillis();
		long diff = end-timingStart;
		if (end-timingStart > 0)
			pt(timingCause + " took "+ diff/1000.0 + "s" );

		timingStart = -1;
	}
}
