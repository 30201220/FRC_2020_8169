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

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.ballcatch.ballTracking;
import frc.robot.commands.ballshoot.NormalShoot;
import frc.robot.commands.ballshoot.shootEephus;
import frc.robot.commands.turntable.spinnerUB;

public class OI {
  XboxController operatorController = new XboxController(RobotMap.OPERATOR_CONTROLLER);
  XboxController driverController = new XboxController(RobotMap.DRIVER_CONTROLLER);

  Button dButtonA = new JoystickButton(this.driverController, RobotMap.BUTTON_A);
	Button dButtonB = new JoystickButton(this.driverController, RobotMap.BUTTON_B);
	Button dButtonX = new JoystickButton(this.driverController, RobotMap.BUTTON_X);
  Button dButtonY = new JoystickButton(this.driverController, RobotMap.BUTTON_Y);
  Button dButtonRB = new JoystickButton(this.driverController, RobotMap.BUTTON_RB);
  Button dButtonLB = new JoystickButton(this.driverController, RobotMap.BUTTON_LB);
  Button dButtonSTART = new JoystickButton(this.driverController, RobotMap.BUTTON_START);
  Button dButtonLEFT = new JoystickButton(this.driverController, RobotMap.BUTTON_LEFT);
  Button dButtonRIGHT = new JoystickButton(this.driverController, RobotMap.BUTTON_RIGHT);
  
	Button oButtonA = new JoystickButton(this.operatorController, RobotMap.BUTTON_A);
	Button oButtonB = new JoystickButton(this.operatorController, RobotMap.BUTTON_B);
	Button oButtonY = new JoystickButton(this.operatorController, RobotMap.BUTTON_Y);
	Button oButtonX = new JoystickButton(this.operatorController, RobotMap.BUTTON_X);
  Button oButtonLB = new JoystickButton(this.operatorController, RobotMap.BUTTON_LB);
  Button oButtonRB = new JoystickButton(this.operatorController, RobotMap.BUTTON_RB);
  Button oButtonSTART = new JoystickButton(this.operatorController, RobotMap.BUTTON_START);
  Button oButtonBACK = new JoystickButton(this.operatorController, RobotMap.BUTTON_BACK);

  public boolean getOperatorButton(int axis) {
		return this.operatorController.getRawButton(axis);
	}

	public boolean getDriverButton(int axis) {
		return this.driverController.getRawButton(axis);
	}

	public double getOperatorRawAxis(int axis) {
		return this.operatorController.getRawAxis(axis);
  }

	public double getDriverRawAxis(int axis) {
		return this.driverController.getRawAxis(axis);
  }

  public int getDriverPOV(){
    return this.driverController.getPOV();
  }

  public int getOperatorPOV(){
    return this.operatorController.getPOV();
  }

  public OI(){
    this.dButtonSTART.whenPressed(new NormalShoot());
    //this.dButtonRB.whenPressed(new ballTracking());
    //this.dButtonSTART.whenPressed(new shootEephus());
    //this.dButtonLB.whenPressed(new spinnerUB());
    this.dButtonX.whenPressed(new shootEephus());
  }

  public boolean trueFalseSwitch(int button, boolean oldState){
    boolean a = false;
    boolean flag = false;
    if(a){
      if(Robot.m_oi.getDriverButton(button)){
        flag = false;
        if(Robot.m_oi.getDriverButton(button) != oldState){
          a = false;
        }
      }
    } else {
      if(Robot.m_oi.getDriverButton(button)){
        flag = true;
        if(Robot.m_oi.getDriverButton(button) != oldState){
          a = true;
        }
      }
    }
    return flag;
  }
  //// CREATI
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
}
