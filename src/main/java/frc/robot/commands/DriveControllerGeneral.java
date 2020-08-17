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

public class DriveControllerGeneral extends Command {

  public DriveControllerGeneral() {
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
    double speedr;
    double speedl;

    double triggerVal = Robot.m_oi.getDriverRawAxis(RobotMap.LEFT_TRIGGER) - Robot.m_oi.getDriverRawAxis(RobotMap.RIGHT_TRIGGER);
    double stick = Utilities.scale(Robot.m_oi.getDriverRawAxis(RobotMap.LEFT_STICK_X), RobotMap.TURNING_RATE);

    speedl = (triggerVal - stick);
    speedr = (triggerVal + stick);

    Robot.m_drivetrain.setLeftMotors(speedl*RobotMap.nSpeed);
    Robot.m_drivetrain.setRightMotors(speedr*RobotMap.nSpeed);

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
}
