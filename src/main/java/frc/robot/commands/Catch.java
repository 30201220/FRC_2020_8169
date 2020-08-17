/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class Catch extends Command {
  double a;
  boolean oldState;
  public Catch() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_catchball);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_catchball.setcatchcylinderup();
    a = 0;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(a == 0){
      if(Robot.m_oi.getDriverButton(RobotMap.BUTTON_B) == true){
        Robot.m_catchball.setMotorCatchBall(0.6);
        Robot.m_catchball.setcatchcylinderdown();
        if(Robot.m_oi.getDriverButton(RobotMap.BUTTON_B) != oldState){
          a = 1;
        }
      }
    } else if(a == 1){
      if(Robot.m_oi.getDriverButton(RobotMap.BUTTON_B) == true){
        Robot.m_catchball.setMotorCatchBall(0);
        Robot.m_catchball.setcatchcylinderup();
        if(Robot.m_oi.getDriverButton(RobotMap.BUTTON_B) != oldState){
          a = 0;
        }
      }
    }
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
