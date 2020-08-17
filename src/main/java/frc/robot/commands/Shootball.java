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

public class Shootball extends Command {
  public Shootball() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_shooter);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    setTimeout(RobotMap.SHOOTERMOTOR_DELAY);
    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.m_shooter.setMotorShooter2(0.6);
    if((isTimedOut()) == true){
      if(Robot.m_oi.getOperatorButton(RobotMap.BUTTON_B) == true){
        Robot.m_shooter.setMotorShooter1(1);
      } else{
        Robot.m_shooter.setMotorShooter1(0);
      }
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(Robot.m_oi.getOperatorButton(RobotMap.BUTTON_A) == true){
      return true;
    }else{
      return false;
    }

  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_shooter.setMotorShooter1(0);
    Robot.m_shooter.setMotorShooter2(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
