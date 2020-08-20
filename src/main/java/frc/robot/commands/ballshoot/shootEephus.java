/*----------------------------------------------------------------------------------------------------*/
/*            88888888888888888888   11111111111111       66666666666666666666  99999999999999999999  */
/*           88888888888888888888   11111111111111       66666666666666666666  99999999999999999999   */
/*          888               88              111       666                   999               99    */
/*         888               88              111       666                   999               99     */
/*        888               88              111       666                   999               99      */
/*       88888888888888888888              111       66666666666666666666  99999999999999999999       */
/*      888               88              111       666               66                    99        */
/*     888               88              111       666               66                    99         */
/*    888               88              111       666               66                    99          */
/*   88888888888888888888  11111111111111111111  66666666666666666666  99999999999999999999           */
/*  88888888888888888888  11111111111111111111  66666666666666666666  99999999999999999999            */
/*----------------------------------------------------------------------------------------------------*/

package frc.robot.commands.ballshoot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class shootEephus extends Command {
  double Aerror,Arcw;
  double a;
  double targetTurnSpeed = 28000;
  double Pa;
  public shootEephus() {
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
    a = Robot.m_shooter.getMotorShooEncoder();
    SmartDashboard.putNumber("a", a);
    PIDAngle();
    Robot.m_shooter.setMotorShooterFly((targetTurnSpeed + Arcw)/29000);
    if(Aerror<500){
      Pa = 20;
    } else {
      Pa = 10;
    }
    
    if(Robot.m_oi.getDriverButton(RobotMap.BUTTON_Y)){
      Robot.m_shooter.setMotorShooter2(1);
    } else {
      Robot.m_shooter.setMotorShooter2(0);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.m_oi.getDriverButton(RobotMap.BUTTON_BACK);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_shooter.setMotorShooterFly(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
  
  public void PIDAngle(){
    Aerror = targetTurnSpeed - (-a); // Error = Target - Actual
    //this.integral += (error*.02); // Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
    //derivative = (error - this.previous_error) / .02;
    Arcw = Pa*Aerror ;//+ I*this.integral + D*derivative;
  }
}
