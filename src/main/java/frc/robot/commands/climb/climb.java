/*----------------------------------------------------------------------------------------------------*/
/*            88888888888888888888   1111111111111        66666666666666666666  99999999999999999999  */
/*           88888888888888888888   1111111111111        66666666666666666666  99999999999999999999   */
/*          888               88             111        666                   999               99    */
/*         888               88             111        666                   999               99     */
/*        888               88             111        666                   999               99      */
/*       88888888888888888888             111        66666666666666666666  99999999999999999999       */
/*      888               88             111        666               66                    99        */
/*     888               88             111        666               66                    99         */
/*    888               88             111        666               66                    99          */
/*   88888888888888888888  11111111111111111111  66666666666666666666  99999999999999999999           */
/*  88888888888888888888  11111111111111111111  66666666666666666666  99999999999999999999            */
/*----------------------------------------------------------------------------------------------------*/
package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class climb extends Command {
  boolean a;
  boolean oldState;
  public climb() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_climbing);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_climbing.setclimbcylinderdown();
    a = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(a == false){
      if(Robot.m_oi.getDriverButton(RobotMap.BUTTON_A) == true){
        Robot.m_climbing.setclimbcylinderup();
        if(Robot.m_oi.getDriverButton(RobotMap.BUTTON_A) != oldState){
          a = true;
        }
      }
    } else if(a == true){
      if(Robot.m_oi.getDriverButton(RobotMap.BUTTON_A) == true){
        Robot.m_climbing.setclimbcylinderdown();
        if(Robot.m_oi.getDriverButton(RobotMap.BUTTON_A) != oldState){
          a = false;
        }
      }
    }
    oldState = Robot.m_oi.getDriverButton(RobotMap.BUTTON_A);

    if(Robot.m_oi.getOperatorPOV() == 180){
      Robot.m_climbing.setClimbMotor(1);//up
    } else if(Robot.m_oi.getOperatorPOV() == 0){
      Robot.m_climbing.setClimbMotor(-1);//down
    } else {
      Robot.m_climbing.setClimbMotor(0);
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
