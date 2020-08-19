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

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ballcatch.ballCatch;

/**
 * Add your docs here.
 */
public class Intake extends Subsystem {
  private CANSparkMax motorIntake = new CANSparkMax(RobotMap.MOTOR_CATCH_1_ID, MotorType.kBrushless);
  private Solenoid cylinderCatchBall = new Solenoid(RobotMap.CYLINDER_CATCH_ID);
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new ballCatch());
  }
  public void setMotorIntake(final double speed){
    motorIntake.set(-speed * RobotMap.nIntakeSpeed);
  }

  public void setcatchcylinderdown(){
    cylinderCatchBall.set(true);
  }
  public void setcatchcylinderup(){
    cylinderCatchBall.set(false);
  }
}
