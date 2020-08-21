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

package frc.robot.commands.ballshoot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class angleModify extends Command {
  double x,y,area,Arcw,Ircw;
  double Aerror,Ierror;
  double derivative;
  double I = 0.01;
  double D = 1;
  double Pa = 1.2;
  double Pi = 0.65;
  boolean modify;
  int modify1;
  public angleModify() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_intake.setcatchcylinderdown();
 //   Robot.m_compressor.stop();
    Robot.m_shooter.setMotorShooterFly(0.75);
    modify1 = 0;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight-shoot");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    y = ty.getDouble(0.0);
    x = tx.getDouble(0.0);
    PIDAngle();
    //PIDDistance();
    Robot.m_drivetrain.setLeftMotors(/*-Ircw*/ + Arcw/25);
    Robot.m_drivetrain.setRightMotors(/*-Ircw*/ - Arcw/25);

    if(( Arcw/27) < 0.05 && ( Arcw/27) > -0.05){
      modify = true;
      modify1 += 1;
    } else{
      modify = false;
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(modify1 > 100){
      return modify;
    } else if(Robot.m_oi.getDriverButton(RobotMap.BUTTON_BACK)){
      return true;
    } else {
      return false;
    }
    
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
   Robot.m_drivetrain.setLeftMotors(0);
   Robot.m_drivetrain.setRightMotors(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }

  public void PIDAngle(){
    Aerror = x-(RobotMap.LIMELIGHT_SHOOT_TX); // Error = Target - Actual
    //this.integral += (error*.02); // Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
    //derivative = (error - this.previous_error) / .02;
    Arcw = Pa*Aerror ;//+ I*this.integral + D*derivative;
  }

  public void PIDDistance(){
    Ierror = y - (-6.3);
    Ircw = Pi*Ierror;
  }
}
//tx: -3.43°
//ty: -7.22°
//ta: +0.531%

//tx: -6.35°
//ty: -7.56°
//ta: +0.420%

//tx: -4.63°
//ty: -7.55°
//ta: +0.420%