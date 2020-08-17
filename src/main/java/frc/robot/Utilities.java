package frc.robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Utilities is designed to be a class for generic methods used across the 
 * various commands. Not all methods will be used each year.
 */
public class Utilities {
  public static double scale(double value, double scalar) {
    return value * scalar;
  }
  
}