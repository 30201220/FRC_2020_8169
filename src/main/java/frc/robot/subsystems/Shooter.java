/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.SeaUrchin;

/**
 * Add your docs here.
 */
public class Shooter extends Subsystem {
  private TalonSRX motorShooterFly = new TalonSRX(RobotMap.MOTOR_SHOOTER_1_ID); 
  private VictorSPX motorShooter2 = new VictorSPX(RobotMap.MOTOR_SHOOTER_2_ID);
  private VictorSPX motorShooterSeaUrchin = new VictorSPX(RobotMap.MOTOR_SHOOTER_3_ID);
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new SeaUrchin());
  }
  public void setMotorShooterFly(double speed1){
    motorShooterFly.set(ControlMode.PercentOutput,-speed1);
  }
  public void setMotorShooter2(double speed2){
    motorShooter2.set(ControlMode.PercentOutput,speed2);
  }
  public void setMotorShooterSeaUrchin(double speed3){
    motorShooterSeaUrchin.set(ControlMode.PercentOutput,speed3);
  }
  public double getMotorShooEncoder(){
    return motorShooterFly.getSelectedSensorVelocity();
  }
}
