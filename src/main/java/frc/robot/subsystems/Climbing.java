/*----------------------------------------------------------------------------------------------------*/
/*            88888888888888888888   1111111111111        66666666666666666666  99999999999999999999  */
/*           88888888888888888888   1111111111111        66666666666666666666  99999999999999999999   */
/*          888               88             111        666                   999               99    */
/*         888               88             111        666                   999               99     */
/*        888               88             111        666                   999               99      */
/*       88888888888888888888             111        66666666666666666666  99999999999999999999       */
/*      888               88             111        666               66                    99        */
/*     888               88             111        666               66                    99         */
/*    888               88             111        666               66                    99          */
/*   88888888888888888888  11111111111111111111  66666666666666666666  99999999999999999999           */
/*  88888888888888888888  11111111111111111111  66666666666666666666  99999999999999999999            */
/*----------------------------------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.climb.climb;

/**
 * 爬升機構
 * 
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
    setDefaultCommand(new climb());
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
