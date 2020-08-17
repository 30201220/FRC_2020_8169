/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Spinner extends Subsystem {
  private VictorSPX motorSpinner = new VictorSPX(RobotMap.MOTOR_SPINNER_1_ID); 
  private DoubleSolenoid cylinderSpinner = new DoubleSolenoid(RobotMap.CYLINDER_SPINNER_1_ID, RobotMap.CYLINDER_SPINNER_2_ID);
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void doubleDOWN(){
    cylinderSpinner.set(Value.kReverse);
  }
  
  public void doubleOFF() {
    cylinderSpinner.set(Value.kOff);
  }
  
  public void doubleUP() {
    cylinderSpinner.set(Value.kForward);
  }

  public void setSpinnerMotor(double sspeed) {
    motorSpinner.set(ControlMode.PercentOutput, sspeed);
  }
}
