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
import frc.robot.subsystems.DriveTrain;

public class Drive extends Command {
  public Drive() {
    // Use requires() here to declare subsystem dependencies
   requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double leftStickY = Robot.m_oi.GetDriverRawAxis(RobotMap.LEFT_STICK_Y);
    double rightStickY = Robot.m_oi.GetDriverRawAxis(RobotMap.RIGHT_STICK_Y);
    double rightStickX = Robot.m_oi.GetDriverRawAxis(RobotMap.RIGHT_STICK_X);
    double leftStickX = Robot.m_oi.GetDriverRawAxis(RobotMap.LEFT_STICK_X);
    
    double speedr;
    double speedl;
    
    speedr = rightStickY/3;
    speedl = leftStickY/3;

    Robot.driveTrain.setLeftMotors(speedl);
    Robot.driveTrain.setRightMotors(speedr);

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
    Robot.driveTrain.setLeftMotors(0);
    Robot.driveTrain.setRightMotors(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    this.end();
  }
}