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

package frc.robot.commands.ballcatch;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class ballCatch extends Command {
  boolean oldStateB, oldStateX;
  boolean b,x;
  public ballCatch() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_intake);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_intake.setcatchcylinderup();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    /*if(Robot.m_oi.trueFalseSwitch(RobotMap.BUTTON_B, oldStateB)){
      Robot.m_intake.setcatchcylinderup();
    } else {
      Robot.m_intake.setcatchcylinderdown();
    }
    oldStateB = Robot.m_oi.getDriverButton(RobotMap.BUTTON_B);

    if (Robot.m_oi.trueFalseSwitch(RobotMap.BUTTON_X, oldStateX)) {
      Robot.m_intake.setMotorIntake(1);
    } else{
      Robot.m_intake.setMotorIntake(0);
    }
    oldStateX = Robot.m_oi.getDriverButton(RobotMap.BUTTON_X);*/


    if(b == false){
      if(Robot.m_oi.getDriverButton(RobotMap.BUTTON_B) == true){
        Robot.m_intake.setcatchcylinderdown();
        if(Robot.m_oi.getDriverButton(RobotMap.BUTTON_B) != oldStateB){
          b = true;
        }
      }
    } else if(b == true){
      if(Robot.m_oi.getDriverButton(RobotMap.BUTTON_B) == true){
        Robot.m_intake.setcatchcylinderup();
        if(Robot.m_oi.getDriverButton(RobotMap.BUTTON_B) != oldStateB){
          b = false;
        }
      }
    }
    oldStateB = Robot.m_oi.getDriverButton(RobotMap.BUTTON_B);

    if(x == false){
      if(Robot.m_oi.getOperatorButton(RobotMap.BUTTON_X) == true){
        Robot.m_intake.setMotorIntake(1);
        if(Robot.m_oi.getOperatorButton(RobotMap.BUTTON_X) != oldStateX){
          x = true;
        }
      }
    } else if(x == true){
      if(Robot.m_oi.getOperatorButton(RobotMap.BUTTON_X) == true){
        Robot.m_intake.setMotorIntake(0);
        if(Robot.m_oi.getOperatorButton(RobotMap.BUTTON_X) != oldStateX){
          x = false;
        }
      }
    }

    SmartDashboard.putBoolean("motorIntake", x);
    oldStateX = Robot.m_oi.getOperatorButton(RobotMap.BUTTON_X);

    



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
