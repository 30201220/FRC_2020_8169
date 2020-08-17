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

public class AutoLimeMove extends Command {
  double firstangle;
  double angle;
  double m_time,m_intence;
  double Aerror,Arcw;
  double P = 0.8;
  double I = 0.01;
  double D = 1;
  
  public AutoLimeMove(double time, double intence) {
    m_intence = intence;
    m_time = time;
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    firstangle = Robot.m_ahrs.getAngle();
    setTimeout(m_time);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    angle = Robot.m_ahrs.getAngle();
    PIDAngle();
    Robot.m_drivetrain.setLeftMotors(m_intence + Arcw/180);
    Robot.m_drivetrain.setRightMotors(m_intence - Arcw/180);
    SmartDashboard.putNumber("naxp_angle", angle);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
   return isTimedOut();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_drivetrain.setRightMotors(0);
    Robot.m_drivetrain.setLeftMotors(0);
    Robot.m_catchball.setMotorCatchBall(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }

  public void PIDAngle(){
    Aerror = firstangle - angle; // Error = Target - Actual
    //this.integral += (error*.02); // Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
    //derivative = (error - this.previous_error) / .02;
    Arcw = P*Aerror ;//+ I*this.integral + D*derivative;
  }
}
