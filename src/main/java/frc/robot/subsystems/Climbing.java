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
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.UBClimb;

/**
 * Add your docs here.
 */
public class Climbing extends Subsystem {
  private VictorSPX motorClimbing1 = new VictorSPX(RobotMap.MOTOR_CLIMB_1_ID);
  private VictorSPX motorClimbing2 = new VictorSPX(RobotMap.MOTOR_CLIMB_2_ID);

  private Solenoid cylinderLeft = new Solenoid(RobotMap.CYLINDER_LEFT_ID);
  private Solenoid cylinderRight = new Solenoid(RobotMap.CYLINDER_RIGHT_ID);

  // Put methods for controlling this subsystem
  // here. Call these from Commands.

@Override  
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new UBClimb());
  }

  public void setClimbMotor(double speedc){
    motorClimbing1.set(ControlMode.PercentOutput,-speedc);
    motorClimbing2.set(ControlMode.PercentOutput,speedc);
  }
  
  public void setclimbcylinder(){
    cylinderLeft.set(true);
    cylinderRight.set(true);
  }

  public void singleIdle(){
    cylinderLeft.set(false);
    cylinderRight.set(false);  
  }

}
