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

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class autoAngleModify extends Command {
  double x,y,Arcw;
  double Aerror;
  double derivative;
  double I = 0.01;
  double D = 1;
  double Pa = 1;
  double Rerror,Rrcw;
  double r;
  double targetTurnSpeed = RobotMap.TARGET_TURN_SPEED;
  double Pt;
  boolean modify;
  int modify1;
  public autoAngleModify() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    //Robot.m_compressor.stop();
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

    SmartDashboard.putNumber("x", x);
    SmartDashboard.putNumber("distance", y);

    PIDAngle();

    Robot.m_drivetrain.setLeftMotors (Arcw/27);
    Robot.m_drivetrain.setRightMotors(-Arcw/27);

    if(( Arcw/27) < 0.1 && ( Arcw/27) > -0.1){
      modify = true;
      modify1 += 1;
    } else{
      modify = false;
    }

    SmartDashboard.putNumber("distance", y);

    if(Rerror<500){
      Pt = 20;
    } else {
      Pt = 10;
    }
    PIDRotateSpeed();
    Robot.m_shooter.setMotorShooterFly((targetTurnSpeed + Rrcw)/29000);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(modify1 > 50){
      return modify;
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
    Aerror = x - (RobotMap.LIMELIGHT_SHOOT_TX); // Error = Target - Actual
    //this.integral += (error*.02); // Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
    //derivative = (error - this.previous_error) / .02;
    Arcw = Pa*Aerror ;//+ I*this.integral + D*derivative;
  }
  public void PIDRotateSpeed(){
    Rerror = targetTurnSpeed - (-r); // Error = Target - Actual
    //this.integral += (error*.02); // Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
    //derivative = (error - this.previous_error) / .02;
    Rrcw = Pt*Rerror ;//+ I*this.integral + D*derivative;
  }
}
