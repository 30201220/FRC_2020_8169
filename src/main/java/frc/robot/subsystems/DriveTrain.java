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
import frc.robot.commands.DriveControllerGeneral;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
  private final VictorSPX motorLeft1 = new VictorSPX(RobotMap.MOTOR_LEFT_1_ID);
  private final VictorSPX motorLeft2 = new VictorSPX(RobotMap.MOTOR_LEFT_2_ID);
  private final VictorSPX motorRight1 = new VictorSPX(RobotMap.MOTOR_RIGHT_1_ID);
  private final VictorSPX motorRight2 = new VictorSPX(RobotMap.MOTOR_RIGHT_2_ID);
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new DriveControllerGeneral());
  }

  public void setLeftMotors(final double speedl) {
    motorLeft1.set(ControlMode.PercentOutput, -speedl);
    motorLeft2.set(ControlMode.PercentOutput, -speedl);
  }

  public void setRightMotors(final double speedr) {
    motorRight1.set(ControlMode.PercentOutput, speedr);
    motorRight2.set(ControlMode.PercentOutput, speedr);
  }
}
