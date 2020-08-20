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

package frc.robot.commands.carcontrol;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class driveControl extends Command {
  double Aerror,Arcw;
  double direction;
  double angle;
  double target;
  double P = 0.8;
  double I = 0.01;
  double D = 1;
  public driveControl() {
    // Use requires() here to declare subsystem dependencies
    if(Robot.sControlMode == "defaultMode")
      requires(Robot.m_drivetrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double triggerVal = Robot.m_oi.getDriverRawAxis(RobotMap.RIGHT_TRIGGER) - Robot.m_oi.getDriverRawAxis(RobotMap.LEFT_TRIGGER);
    double stick = Robot.m_oi.getDriverRawAxis(RobotMap.LEFT_STICK_X) * (RobotMap.TURNING_RATE);
    double speedl = (triggerVal + stick);
    double speedr = (triggerVal - stick);

    //angle = Robot.m_ahrs.getAngle();
    //direction = Robot.m_oi.getDriverPOV();
    /*if (direction != -1){
      target = direction;
      PIDAngle();
      Robot.m_drivetrain.setLeftMotors(-Arcw/180);
      Robot.m_drivetrain.setRightMotors(Arcw/180);
    } else {
      Robot.m_drivetrain.setLeftMotors(speedl*RobotMap.nSpeed);
      Robot.m_drivetrain.setRightMotors(speedr*RobotMap.nSpeed);
    }*/
    Robot.m_drivetrain.setLeftMotors(speedl*RobotMap.nSpeed);
    Robot.m_drivetrain.setRightMotors(speedr*RobotMap.nSpeed);

    SmartDashboard.putNumber("speedl", speedl);
    SmartDashboard.putNumber("speedr", speedr);
    SmartDashboard.putNumber("LeftEncoder", Robot.m_drivetrain.getLeftMotorsEncoder());
    SmartDashboard.putNumber("RightEncoder", Robot.m_drivetrain.getRightMotorsEncoder());
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
    Robot.m_drivetrain.setLeftMotors(0);
    Robot.m_drivetrain.setRightMotors(0);
  }
  public void PIDAngle(){
    Aerror = target-angle; // Error = Target - Actual
    //this.integral += (error*.02); // Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
    //derivative = (error - this.previous_error) / .02;
    Arcw = P*Aerror ;//+ I*this.integral + D*derivative;
  }
}
