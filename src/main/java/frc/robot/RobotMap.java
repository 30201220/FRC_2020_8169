/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  //sPDP
  public static int PDP_ID = 0;
  
  //sShooter
  public static int MOTOR_SHOOTER_1 = 11;//4
  public static int MOTOR_SHOOTER_2 = 10;

  //sCatchBall
  public static final int MOTOR_CATCH_1_ID = 7;
  
  //sClimbing
  public static final int MOTOR_CLIMB_1_ID = 5;
  public static final int MOTOR_CLIMB_2_ID = 8;

  
  //sDriveTrain
  public static final int MOTOR_LEFT_1_ID = 1;//2
	public static final int MOTOR_LEFT_2_ID = 2;//3
	public static final int MOTOR_RIGHT_1_ID = 3;//0
  public static final int MOTOR_RIGHT_2_ID = 4;//1
  
  //Xbox
  public static final int DRIVER_CONTROLLER = 1;
  public static final int OPERATOR_CONTROLLER = 2;
  
  public static final int LEFT_STICK_X = 0;
  public static final int LEFT_STICK_Y = 1;
	public static final int RIGHT_STICK_Y = 5;
	public static final int RIGHT_STICK_X = 4;
  
  public static final int BUTTON_A = 1;
  public static final int BUTTON_B = 2;
  public static final int BUTTON_X = 3;
  public static final int BUTTON_Y = 4;
  public static final int BUTTON_LB = 5;
  public static final int BUTTON_RB = 6;
  public static final int BUTTON_BACK = 7;
  public static final int BUTTON_START = 8;

  public static final int DPAD_UP = 0;
  public static final int DPAD_RIGHT = 90;
  public static final int DPAD_DOWN = 180;
  public static final int DPAD_LEFT = 270;

  public static final int LEFT_TRIGGER = 2;
	public static final int RIGHT_TRIGGER = 3;
  
  public static final double TURNING_RATE = 0.5;
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  public static double nSpeed = 1;

  public static double SHOOTERMOTOR_DELAY = 1.5;

  public static int CYLINDER_ID = 2;

  public static int MOTOR_SPINNER_1_ID = 6;//6
  public static int CYLINDER_SPINNER_1_ID = 4;
  public static int CYLINDER_SPINNER_2_ID = 5;

  public static double SPEED_MOTOR_SPINNER = 0.5;

  public static int LEFT_STICK_Z  = 2;

  public static int CYLINDER_CATCH_ID = 0;

  public static int BUTTON_RIGHT = 10;

  public static int MOTOR_SHOOTER_3 = 11;

  public static int BUTTON_LEFT = 9;






  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;


}
