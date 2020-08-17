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

public class AutoRotate extends Command {
  double angle;
  double Aerror,Arcw;
  double P = 0.8;
  double I = 0.01;
  double D = 1;
  boolean modify;

  public AutoRotate() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    angle = Robot.m_ahrs.getAngle();
    PIDAngle();
    Robot.m_drivetrain.setLeftMotors(Arcw/180);
    Robot.m_drivetrain.setRightMotors(-Arcw/180);
    SmartDashboard.putNumber("naxp_angle", angle);
    SmartDashboard.putNumber("Arcw", Arcw/180);
    if(Arcw<15 && Arcw>-15){
      modify = true;
    } else {
      modify = false;
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return modify;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_drivetrain.setRightMotors(0);
    Robot.m_drivetrain.setLeftMotors(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
  public void PIDAngle(){
    Aerror = 180-angle; // Error = Target - Actual
    //this.integral += (error*.02); // Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
    //derivative = (error - this.previous_error) / .02;
    Arcw = P*Aerror ;//+ I*this.integral + D*derivative;
  }
}
