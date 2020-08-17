/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.Climb;


/**
 * Add your docs here.
 */
public class Climbing extends Subsystem {
  private VictorSPX motorClimbing1 = new VictorSPX(RobotMap.MOTOR_CLIMB_1_ID);
  private VictorSPX motorClimbing2 = new VictorSPX(RobotMap.MOTOR_CLIMB_2_ID);
  private Solenoid cylinderClimbing = new Solenoid(RobotMap.CYLINDER_CLIMB_ID);
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new Climb());
  }

  public void setClimbMotor(double speedc){
    motorClimbing1.set(ControlMode.PercentOutput,-speedc);
    motorClimbing2.set(ControlMode.PercentOutput,speedc);
  }
  
  public void setclimbcylinderup(){
    cylinderClimbing.set(true);
  }

  public void setclimbcylinderdown(){
    cylinderClimbing.set(false);
  }
}
