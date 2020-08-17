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
import frc.robot.commands.Catchball;

/**
 * Add your docs here.
 */
public class CatchBall extends Subsystem {
  private VictorSPX MotorCatchBall = new VictorSPX(RobotMap.MOTOR_CATCH_1_ID);
  private Solenoid cylinderCatchBall = new Solenoid(RobotMap.CYLINDER_CATCH_ID);

  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    //setDefaultCommand(new Catchball());
  }
  public void setMotorCatchBall(double speed){
    MotorCatchBall.set(ControlMode.PercentOutput,-speed);
  }

  public void setclimbcylinder(){
    cylinderCatchBall.set(true);
  }

  public void singleIdle(){
    cylinderCatchBall.set(false);
  }
}
