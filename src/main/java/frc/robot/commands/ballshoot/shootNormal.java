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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class shootNormal extends Command {
  double y,x,area;
  double Rerror,Rrcw;
  double r;
  double targetTurnSpeed = 28000;
  double Pa;
  public shootNormal() {
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
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight-shoot");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");
    y = ty.getDouble(0.0);
    x = tx.getDouble(0.0);
    area = ta.getDouble(0.0);
    r = Robot.m_shooter.getMotorShooEncoder();
    PIDRotateSpeed();
    Robot.m_shooter.setMotorShooterFly((targetTurnSpeed + Rrcw)/29000);
    if(Rerror<500){
      Pa = 20;
    } else {
      Pa = 10;
    }
    
    if(Robot.m_oi.getDriverButton(RobotMap.BUTTON_Y)){
      Robot.m_shooter.setMotorShooter2(1);
    } else {
      Robot.m_shooter.setMotorShooter2(0);
    }

    SmartDashboard.putNumber("r", r);
    SmartDashboard.putNumber("y", y);
    SmartDashboard.putNumber("x", x);
    SmartDashboard.putNumber("area", area);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.m_oi.getDriverButton(RobotMap.BUTTON_BACK);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
   // Robot.m_compressor.stop();
    Robot.m_shooter.setMotorShooterFly(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }

  public void PIDRotateSpeed(){
    Rerror = targetTurnSpeed - (-r); // Error = Target - Actual
    //this.integral += (error*.02); // Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
    //derivative = (error - this.previous_error) / .02;
    Rrcw = Pa*Rerror ;//+ I*this.integral + D*derivative;
  }
}
