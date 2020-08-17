/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class Shoot extends Command {
  double Aerror,Arcw;
  double a;
  double targetTurnSpeed = 22000;
  double Pa;
  public Shoot() {
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
    
    if(Robot.m_oi.getDriverButton(RobotMap.BUTTON_RB)){
      Robot.m_shooter.setMotorShooter2(1);
    } else {
      Robot.m_shooter.setMotorShooter2(0);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(Robot.m_oi.getDriverButton(RobotMap.BUTTON_BACK)){
      return true;
    }else{
      return false;
    }
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
