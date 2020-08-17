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

public class ShowCurrent extends Command {
  public ShowCurrent() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_PDPCurrent);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    SmartDashboard.putNumber("Current0",Robot.m_PDPCurrent.getcurrent(0));
    SmartDashboard.putNumber("Current1",Robot.m_PDPCurrent.getcurrent(1));
    SmartDashboard.putNumber("Current2",Robot.m_PDPCurrent.getcurrent(2));
    SmartDashboard.putNumber("Current3",Robot.m_PDPCurrent.getcurrent(3));
    SmartDashboard.putNumber("Current4",Robot.m_PDPCurrent.getcurrent(4));
    SmartDashboard.putNumber("Current5",Robot.m_PDPCurrent.getcurrent(5));
    SmartDashboard.putNumber("Current6",Robot.m_PDPCurrent.getcurrent(6));
    SmartDashboard.putNumber("Current7",Robot.m_PDPCurrent.getcurrent(7));
    SmartDashboard.putNumber("Current8",Robot.m_PDPCurrent.getcurrent(8));
    SmartDashboard.putNumber("Current9",Robot.m_PDPCurrent.getcurrent(9));
    SmartDashboard.putNumber("Current10",Robot.m_PDPCurrent.getcurrent(10));
    SmartDashboard.putNumber("Current11",Robot.m_PDPCurrent.getcurrent(11));
    SmartDashboard.putNumber("Current12",Robot.m_PDPCurrent.getcurrent(12));
    SmartDashboard.putNumber("Current13",Robot.m_PDPCurrent.getcurrent(13));
    SmartDashboard.putNumber("Current14",Robot.m_PDPCurrent.getcurrent(14));
    SmartDashboard.putNumber("Current15",Robot.m_PDPCurrent.getcurrent(15));
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
