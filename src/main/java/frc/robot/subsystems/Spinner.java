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

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
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

  public void doubleUP(){
    cylinderSpinner.set(Value.kReverse);
  }
  
  public void doubleOFF() {
    cylinderSpinner.set(Value.kOff);
  }
  
  public void doubleDOWN() {
    cylinderSpinner.set(Value.kForward);
  }

  public void setSpinnerMotor(double sspeed) {
    motorSpinner.set(ControlMode.PercentOutput, sspeed);
  }
}
