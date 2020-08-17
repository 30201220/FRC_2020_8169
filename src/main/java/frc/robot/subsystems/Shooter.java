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
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ballshoot.seaUrchin;

/**
 * Add your docs here.
 */
public class Shooter extends Subsystem {
  private TalonSRX motorShooterFly = new TalonSRX(RobotMap.MOTOR_SHOOTER_FLY_ID); 
  private VictorSPX motorShooter2 = new VictorSPX(RobotMap.MOTOR_SHOOTER_2_ID);
  private VictorSPX motorShooterSeaUrchin = new VictorSPX(RobotMap.MOTOR_SHOOTER_SEAURCHIN_ID);
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new seaUrchin());
  }
  public void setMotorShooterFly(final double speed1){
    motorShooterFly.set(ControlMode.Velocity,-speed1);
  }
  public void setMotorShooter2(final double speed2){
    motorShooter2.set(ControlMode.PercentOutput,speed2);
  }
  public void setMotorShooterSeaUrchin(final double speed3){
    motorShooterSeaUrchin.set(ControlMode.PercentOutput,speed3);
  }
  public double getMotorShooEncoder(){
    return motorShooterFly.getSelectedSensorVelocity();
  }
}
