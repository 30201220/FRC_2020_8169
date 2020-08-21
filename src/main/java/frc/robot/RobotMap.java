/*----------------------------------------------------------------------------------------------------*/
/*            88888888888888888888   1111111111111        66666666666666666666  99999999999999999999  */
/*           88888888888888888888   1111111111111        66666666666666666666  99999999999999999999   */
/*          888               88             111        666                   999               99    */
/*         888               88             111        666                   999               99     */
/*        888               88             111        666                   999               99      */
/*       88888888888888888888             111        66666666666666666666  99999999999999999999       */
/*      888               88             111        666               66                    99        */
/*     888               88             111        666               66                    99         */
/*    888               88             111        666               66                    99          */
/*   88888888888888888888  11111111111111111111  66666666666666666666  99999999999999999999           */
/*  88888888888888888888  11111111111111111111  66666666666666666666  99999999999999999999            */
/*----------------------------------------------------------------------------------------------------*/
package frc.robot;

public class RobotMap {
  //PDP
  public static final int PDP_ID = 0;

  //Motor
  /*******************************************************/
  
  //sShooter
  public static final int MOTOR_SHOOTER_FLY_ID = 2;//2//TALON
  public static final int MOTOR_SHOOTER_2_ID = 11;//11
  public static final int MOTOR_SHOOTER_SEAURCHIN_ID = 13;//13

  //sCatchBall
  public static final int MOTOR_CATCH_1_ID = 2;//2//SPARK
  
  //sClimbing
  public static final int MOTOR_CLIMB_1_ID = 4;//4
  public static final int MOTOR_CLIMB_2_ID = 5;//5

  //sDriveTrain
  public static final int MOTOR_LEFT_1_ID = 3;//3//TALON
	public static final int MOTOR_LEFT_2_ID = 1;//1
	public static final int MOTOR_RIGHT_1_ID = 4;//4//TALON
  public static final int MOTOR_RIGHT_2_ID = 3;//3
  
  //sSpinner
  public static final int MOTOR_SPINNER_1_ID = 10;//10
  /********************************************************* */

  //Cylinder
  /******************************************************** */
  //sCatch
  public static final int CYLINDER_CATCH_ID = 0;
  
  //sClimb
  public static final int CYLINDER_CLIMB_ID = 1;
  
  //sSpinner
  public static final int CYLINDER_SPINNER_1_ID = 6;
  public static final int CYLINDER_SPINNER_2_ID = 7;
  /********************************************************* */

  //Xbox
  /********************************************************* */
  public static final int DRIVER_CONTROLLER = 1;
  public static final int OPERATOR_CONTROLLER = 2;
  
  public static final int LEFT_STICK_X = 0;
  public static final int LEFT_STICK_Y = 1;
  public static final int LEFT_TRIGGER = 2;
  public static final int RIGHT_TRIGGER = 3;
	public static final int RIGHT_STICK_X = 4;
  public static final int RIGHT_STICK_Y = 5;

  public static final int BUTTON_A = 1;
  public static final int BUTTON_B = 2;
  public static final int BUTTON_X = 3;
  public static final int BUTTON_Y = 4;
  public static final int BUTTON_LB = 5;
  public static final int BUTTON_RB = 6;
  public static final int BUTTON_BACK = 7;
  public static final int BUTTON_START = 8;
  public static final int BUTTON_LEFT = 9;
  public static final int BUTTON_RIGHT = 10;

  public static final int DPAD_UP = 0;
  public static final int DPAD_RIGHT = 90;
  public static final int DPAD_DOWN = 180;
  public static final int DPAD_LEFT = 270;
  /********************************************************* */


  


  
  public static final double TURNING_RATE = 0.5;

  public static final double nSpeed = 1;
  public static final double nIntakeSpeed = 0.4;

  public static final double SPEED_MOTOR_SPINNER = 0.5;
  public static final double LIMELIGHT_SHOOT_TX = -4.2;
  










  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;


}
