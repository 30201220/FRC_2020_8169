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
import frc.robot.commands.carcontrol.driveControl;

public class DriveTrain extends Subsystem {
  private final TalonSRX motorLeft1 = new TalonSRX(RobotMap.MOTOR_LEFT_1_ID);
  private final VictorSPX motorLeft2 = new VictorSPX(RobotMap.MOTOR_LEFT_2_ID);
  private final TalonSRX motorRight1 = new TalonSRX(RobotMap.MOTOR_RIGHT_1_ID);
  private final VictorSPX motorRight2 = new VictorSPX(RobotMap.MOTOR_RIGHT_2_ID);

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new driveControl());
  }
  
  public void setLeftMotors(final double speedl) {
    motorLeft1.set(ControlMode.PercentOutput, speedl);
    motorLeft2.set(ControlMode.PercentOutput, speedl);
  }

  public void setRightMotors(final double speedr) {
    motorRight1.set(ControlMode.PercentOutput, -speedr);
    motorRight2.set(ControlMode.PercentOutput, -speedr);
  }
  public double getRightMotorsEncoder(){
    return motorRight1.getSelectedSensorVelocity();
  }
  public double getLeftMotorsEncoder(){
    return motorLeft1.getSelectedSensorVelocity();
  }
}
