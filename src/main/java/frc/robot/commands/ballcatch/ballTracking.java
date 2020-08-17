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

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class ballTracking extends Command {
  double Aerror,Ierror,Arcw,Ircw;
  double P = 0.3;
  double I = 0.01;
  double D = 1;
  double x,y,area;
  double modify1;
  boolean modify;
  public ballTracking() {
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
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight-catch");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");

    x = tx.getDouble(0.0);
    y = ty.getDouble(0.0);

    SmartDashboard.putNumber("limelight-catch-x", x);
    SmartDashboard.putNumber("limelight-catch-y", y);

    PIDAngle();
    PIDIntance();
    
    Robot.m_drivetrain.setLeftMotors((Ircw/20 + Arcw/27));
    Robot.m_drivetrain.setRightMotors((Ircw/20 - Arcw/27));

    SmartDashboard.putNumber("speed", Ircw/100 + Arcw/27);

    if((Ircw/20.5 + Arcw/27) < 0.1 && (Ircw/20.5 + Arcw/27) > -0.1){
      modify = true;
      modify1 += 1;
    } else{
      modify = false;
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(Robot.m_oi.getDriverButton(RobotMap.BUTTON_BACK)){
      return true;
    }
    if(modify1 > 20){
      return modify;
    } else{
      return false;
    }
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

  public void PIDAngle(){
    Aerror = x; // Error = Target - Actual
    //this.integral += (error*.02); // Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
    //derivative = (error - this.previous_error) / .02;
    Arcw = P*Aerror ;//+ I*this.integral + D*derivative;
  }

  public void PIDIntance(){
    Ierror = y-(-15.9); // Error = Target - Actual
    //this.integral += (error*.02); // Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
    //derivative = (error - this.previous_error) / .02;
    Ircw = P*Ierror ;//+ I*this.integral + D*derivative;
  }
}
