/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.Utilities;

public class DriveController extends Command {
  double Aerror,Arcw;
  double direction;
  double angle;
  double target;
  double P = 0.8;
  double I = 0.01;
  double D = 1;
  public DriveController() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_drivetrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double triggerVal = Robot.m_oi.getDriverRawAxis(RobotMap.LEFT_TRIGGER) - Robot.m_oi.getDriverRawAxis(RobotMap.RIGHT_TRIGGER);
    double stick = Utilities.scale(Robot.m_oi.getDriverRawAxis(RobotMap.LEFT_STICK_X), RobotMap.TURNING_RATE);
    double speedl = (triggerVal - stick);
    double speedr = (triggerVal + stick);

    angle = Robot.m_ahrs.getAngle();
    direction = Robot.m_oi.getDriverPOV();
    if (direction != -1){
      target = direction;
      PIDAngle();
      Robot.m_drivetrain.setLeftMotors(Arcw/180);
      Robot.m_drivetrain.setRightMotors(-Arcw/180);

    } else {
      Robot.m_drivetrain.setLeftMotors(-speedl*RobotMap.nSpeed);
      Robot.m_drivetrain.setRightMotors(-speedr*RobotMap.nSpeed);
    }

    SmartDashboard.putNumber("speedl", speedl);
    SmartDashboard.putNumber("speedr", speedr);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.m_drivetrain.setLeftMotors(0);
    Robot.m_drivetrain.setRightMotors(0);
  }
  public void PIDAngle(){
    Aerror = target-angle; // Error = Target - Actual
    //this.integral += (error*.02); // Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
    //derivative = (error - this.previous_error) / .02;
    Arcw = P*Aerror ;//+ I*this.integral + D*derivative;
  }
}
