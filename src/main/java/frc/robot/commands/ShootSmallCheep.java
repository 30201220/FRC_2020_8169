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

public class ShootSmallCheep extends Command {
  double x,y,area,Arcw,Ircw;
  double Aerror,Ierror;
  double derivative;
  double P = 0.8;
  double I = 0.01;
  double D = 1;
  int integral, previous_error = 0;
  boolean modify;
  int modify1;
  public ShootSmallCheep() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_shooter);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    modify1 = 0;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    /*NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");

    x = tx.getDouble(0.0);
    y = ty.getDouble(0.0);
    area = ta.getDouble(0.0);

    PIDAngle();
    PIDIntance();

    SmartDashboard.putNumber("LimelightX", x);
    SmartDashboard.putNumber("LimelightY", y);
    SmartDashboard.putNumber("LimelightArea", area);
    SmartDashboard.putNumber("Arcw", Arcw);
    SmartDashboard.putNumber("Ircw", Ircw);
    SmartDashboard.putNumber("modify1", modify1);
    SmartDashboard.setDefaultBoolean("modify", modify);
    
    if(Arcw>2 || Arcw<-2){
      Robot.m_drivetrain.setLeftMotors(-Arcw/27);
      Robot.m_drivetrain.setRightMotors(Arcw/27);
      modify = false;
    } else{
      modify = true;
      modify1 = modify1 + 1;
    }
    if(modify == true && modify1 > 20){
      Robot.m_drivetrain.setLeftMotors(-Ircw/20.5);
      Robot.m_drivetrain.setRightMotors(-Ircw/20.5);
    }*/

    Robot.m_shooter.setMotorShooter2(0.3);
    if(Robot.m_oi.getOperatorRawAxis(RobotMap.LEFT_TRIGGER) >= 0.9){
      Robot.m_shooter.setMotorShooter1(1);
    } else{
      Robot.m_shooter.setMotorShooter1(0);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(Robot.m_oi.getOperatorButton(RobotMap.BUTTON_BACK) == true){
      return true;
    }else{
      return false;
    }
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_shooter.setMotorShooter1(0);
    Robot.m_shooter.setMotorShooter2(0);
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
    Ierror = y-(-7); // Error = Target - Actual
    //this.integral += (error*.02); // Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
    //derivative = (error - this.previous_error) / .02;
    Ircw = P*Ierror ;//+ I*this.integral + D*derivative;
  }
}
