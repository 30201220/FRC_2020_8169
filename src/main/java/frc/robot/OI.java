/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.Catchball;
import frc.robot.commands.Climbing;
import frc.robot.commands.ColorSensor;
import frc.robot.commands.ShootBall;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  XboxController operatorController = new XboxController(RobotMap.OPERATOR_CONTROLLER);
  XboxController driverController = new XboxController(RobotMap.DRIVER_CONTROLLER);
  Joystick dJoystick = new Joystick(RobotMap.DRIVER_CONTROLLER);
  
  Button dButtonA = new JoystickButton(this.driverController, RobotMap.BUTTON_A);
	Button dButtonB = new JoystickButton(this.driverController, RobotMap.BUTTON_B);
	Button dButtonX = new JoystickButton(this.driverController, RobotMap.BUTTON_X);
	Button dButtonY = new JoystickButton(this.driverController, RobotMap.BUTTON_Y);
  
  
	Button oButtonA = new JoystickButton(this.operatorController, RobotMap.BUTTON_A);
	Button oButtonB = new JoystickButton(this.operatorController, RobotMap.BUTTON_B);
	Button oButtonY = new JoystickButton(this.operatorController, RobotMap.BUTTON_Y);
	Button oButtonX = new JoystickButton(this.operatorController, RobotMap.BUTTON_X);
  Button oButtonLB = new JoystickButton(this.operatorController, RobotMap.BUTTON_LB);
  Button oButtonRB = new JoystickButton(this.operatorController, RobotMap.BUTTON_RB);
  Button oButtonSTART = new JoystickButton(this.operatorController, RobotMap.BUTTON_START);
  
  public double getFlyRawAxis(int axis){
    return this.dJoystick.getRawAxis(axis);
  }

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
	
	public int getOperatorPOV(){
		return this.operatorController.getPOV();
  }

  
  
  public OI(){
    this.oButtonRB.whenPressed(new ColorSensor());
    this.oButtonSTART.whenPressed(new ShootBall());
    this.dButtonX.whenPressed(new Climbing());
    this.oButtonLB.whenPressed(new Catchball());
  }
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
