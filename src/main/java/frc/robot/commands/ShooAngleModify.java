/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class ShooAngleModify extends Command {
  double y,Ircw;
  double Ierror;
  double x,Arcw;
  double Aerror;
  double derivative;
  double Pi = 1;
  double I = 0.01;
  double D = 1;
  double Pa = 0.75;
  boolean modify;
  int modify1;
  public ShooAngleModify() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_shooter.setMotorShooterFly(0.9);
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
    PIDIntance();

    Robot.m_drivetrain.setLeftMotors(-Ircw/20.5 + Arcw/27);
    Robot.m_drivetrain.setRightMotors(-Ircw/20.5 - Arcw/27);

    if((Ircw/20.5 + Arcw/27) < 0.05 && (Ircw/20.5 + Arcw/27) > -0.05){
      modify = true;
      modify1 += 1;
    } else{
      modify = false;
    }

    SmartDashboard.putNumber("Limelight-shootX", x);
    SmartDashboard.putNumber("Limelight-shootY", y);
    SmartDashboard.putNumber("Ircw", Ircw);
    SmartDashboard.putNumber("Arcw", Arcw);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(modify1 > 100){
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
    Aerror = x - (-3.58); // Error = Target - Actual
    //this.integral += (error*.02); // Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
    //derivative = (error - this.previous_error) / .02;
    Arcw = Pa*Aerror ;//+ I*this.integral + D*derivative;
  }
  public void PIDIntance(){
    Ierror = y-(-7.92); // Error = Target - Actual
    //this.integral += (error*.02); // Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
    //derivative = (error - this.previous_error) / .02;
    Ircw = Pi*Ierror ;//+ I*this.integral + D*derivative;
  }
}
