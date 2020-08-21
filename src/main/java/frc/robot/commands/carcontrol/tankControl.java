/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.carcontrol;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class tankControl extends Command {
  public tankControl() {
    // Use requires() here to declare subsystem dependencies
    //if(Robot.sControlMode == "tankMode")
      requires(Robot.m_drivetrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_drivetrain.setLeftMotors(0);
    Robot.m_drivetrain.setRightMotors(0);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.m_drivetrain.setRightMotors(-Robot.m_oi.getDriverRawAxis(RobotMap.RIGHT_STICK_Y));
    Robot.m_drivetrain.setLeftMotors(-Robot.m_oi.getDriverRawAxis(RobotMap.LEFT_STICK_Y));
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
  }
}
