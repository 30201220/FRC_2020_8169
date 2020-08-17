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

public class Climbing extends Command {

  public Climbing() {
  }

  // Ensures that the solenoid isn't already moving
  // before the command starts
  @Override
  protected void initialize() {
    Robot.m_climbing.setclimbcylinder();
    }

  // Punches the solenoid and 
  // keeps the soleoid in the "punch" configuration until 
  // the command goes into the end method
  @Override
  protected void execute() {
    if(Robot.m_oi.getDriverButton(RobotMap.BUTTON_B) == true){
      Robot.m_climbing.singleIdle();
      Robot.m_climbing.setClimbMotor(1);
    }
  }

  // Will only return true if the command is cancelled
  @Override
  protected boolean isFinished() {
    if(Robot.m_oi.getDriverButton(RobotMap.BUTTON_A) == true){
      return true;
    }else{
      return false;
    }
  }

  // Called once after isFinished returns true
  // Ensures the solenoid is no longer being "punched" before
  // exiting the program
  @Override
  protected void end() {
    Robot.m_climbing.singleIdle();
    Robot.m_climbing.setClimbMotor(0);
  }

  // Goes to end the command if the command is interrupted
  @Override
  protected void interrupted() {
    end();
  }
}