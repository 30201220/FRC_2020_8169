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

public class autoRotate extends Command {
  double angle;
  double Aerror,Arcw;
  double P = 0.8;
  double I = 0.01;
  double D = 1;
  boolean modify;
  public autoRotate() {
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
    Aerror = 135-angle; // Error = Target - Actual
    //this.integral += (error*.02); // Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
    //derivative = (error - this.previous_error) / .02;
    Arcw = P*Aerror ;//+ I*this.integral + D*derivative;
  }
}
