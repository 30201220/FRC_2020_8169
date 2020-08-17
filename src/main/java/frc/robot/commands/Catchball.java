/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class Catchball extends Command {
  
  public Catchball() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_catchball);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_catchball.setclimbcylinder();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Robot.m_oi.getOperatorButton(RobotMap.BUTTON_LB) == true){
      Robot.m_catchball.setMotorCatchBall(0.6);
    } else{
      Robot.m_catchball.setMotorCatchBall(0);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(Robot.m_oi.getOperatorButton(RobotMap.BUTTON_BACK) == true){
      return true;
    } else {
      return false;
    }
  }  
  

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_catchball.setMotorCatchBall(0);
    Robot.m_catchball.singleIdle();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
