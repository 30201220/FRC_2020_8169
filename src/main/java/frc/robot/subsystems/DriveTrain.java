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

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.carcontrol.*;

public class DriveTrain extends Subsystem {
  private final TalonSRX motorLeft1 = new TalonSRX(RobotMap.MOTOR_LEFT_1_ID);
  private final VictorSPX motorLeft2 = new VictorSPX(RobotMap.MOTOR_LEFT_2_ID);
  private final TalonSRX motorRight1 = new TalonSRX(RobotMap.MOTOR_RIGHT_1_ID);
  private final VictorSPX motorRight2 = new VictorSPX(RobotMap.MOTOR_RIGHT_2_ID);

  SendableChooser<Command> m_controlModeChooser = new SendableChooser<>();

  @Override
  public void initDefaultCommand() {
    m_controlModeChooser.addOption("Tank Mode", new tankControl());
    m_controlModeChooser.addOption("Default Mode", new driveControl());
    SmartDashboard.putData("Control Mode", m_controlModeChooser);
    



    //Robot.sControlMode = SmartDashboard.getString("Control Mode", "DefaultMode");
    //String ControlMode = Robot.m_controlModeChooser.getSelected();
    //SmartDashboard.putString("SCM", ControlMode);
    //setDefaultCommand(new driveControl());

    System.out.println("mTest:");

    //SmartDashboard.putString("SCM2", Robot.sControlMode==null?"NULL":Robot.sControlMode);
    /*if(Robot.sControlMode == null)
      setDefaultCommand(new driveControl());
    else if(Robot.sControlMode == "defaultMode")
      setDefaultCommand(new driveControl());
    else if(Robot.sControlMode == "tankMode")
      setDefaultCommand(new tankControl());*/

      //setDefaultCommand(new driveControl());
      //setDefaultCommand(new tankControl());
    /*switch (Robot.sControlMode) {
      case "tankMode":
        setDefaultCommand(new tankControl());
        break;
      case "defaultMode":
        setDefaultCommand(new driveControl());
        break;
      default:
        setDefaultCommand(new driveControl());
        break;
    }*/
    
  }
  
  public void mSetDefaultCommand() {
    Command sControlMode = m_controlModeChooser.getSelected();
    setDefaultCommand(sControlMode);
    System.out.println("getDefaultCommandName:"+getDefaultCommandName());
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
