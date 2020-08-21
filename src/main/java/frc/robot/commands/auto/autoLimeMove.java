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

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class autoLimeMove extends Command {
  double firstangle;
  double angle;
  double m_time,m_intence;
  double Aerror,Arcw;
  double P = 0.8;
  double I = 0.01;
  double D = 1;
  public autoLimeMove(double time, double intence) {
    m_intence = intence;
    m_time = time;
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_shooter.setMotorShooterSeaUrchin(-1);
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
    Robot.m_shooter.setMotorShooterSeaUrchin(0);
    //Robot.m_intake.setMotorIntake(0);
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
