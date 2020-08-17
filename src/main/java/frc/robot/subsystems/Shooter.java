/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Shooter extends Subsystem {
  private VictorSPX motorshooter1 = new VictorSPX(RobotMap.MOTOR_SHOOTER_1); 
  private VictorSPX motorshooter2 = new VictorSPX(RobotMap.MOTOR_SHOOTER_2);
  private VictorSPX motorshooter3 = new VictorSPX(RobotMap.MOTOR_SHOOTER_3);
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  public void setMotorShooter1(double speed1){
    motorshooter1.set(ControlMode.PercentOutput,speed1);
  }
  public void setMotorShooter2(double speed2){
    motorshooter2.set(ControlMode.PercentOutput,-speed2);
  }
  public void setMotorShooter3(double speed3){
    motorshooter3.set(ControlMode.PercentOutput,speed3);
  }
}
