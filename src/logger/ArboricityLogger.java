package logger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Logger class
 * @author Shay Yacobinski
 */
public class ArboricityLogger {
	
	/**
	 * Data members
	 */
	
	/** Single instance */
	private static ArboricityLogger fResLogger = null;
	
	/**	Java Logger */
	private Logger fLogger;
	
	/**
	 * Constructor
	 * Initializes logger with console and file handlers
	 */
	private ArboricityLogger() {
		fLogger = Logger.getLogger(this.getClass().getName());
		
		// Add a file handler (The console handler is in the logger too by default)
		try {
			FileHandler fileHandler = new FileHandler("log.txt");
			fileHandler.setFormatter(new SimpleFormatter());
			fLogger.addHandler(fileHandler);
		} catch (IOException e) {
			// Problem with setting the file handler
			fLogger.log(Level.SEVERE, e.getMessage());
		}
		
		// Set logger level to INFO
		fLogger.setLevel(Level.INFO);
	}
	
	/**
	 * Writes the message to the log
	 * @param msgLevel - Message level
	 * @param sourceClass - Name of class from which we receive the message
	 * @param sourceMethod - Name of method from which we receive the message
	 * @param message - Message string
	 */
	public synchronized void log(Level msgLevel, String sourceClass, String sourceMethod, String message) {
		fLogger.logp(msgLevel, sourceClass, sourceMethod, message);
	}
	
	/**
	 * @return Logger single instance
	 */
	public static ArboricityLogger getInstance() {
		if (fResLogger == null) {
			fResLogger = new ArboricityLogger();
		}
		 
		return fResLogger;
	}
}
