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

package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.commands.UBCommand;
import frc.robot.commands.auto.Auto;
import frc.robot.commands.carcontrol.driveControl;
import frc.robot.commands.carcontrol.tankControl;
import frc.robot.subsystems.*;

public class Robot extends TimedRobot {
  public static Intake m_intake = new Intake();
  public static DriveTrain m_drivetrain = new DriveTrain();
  public static Shooter m_shooter = new Shooter();
  public static Spinner m_spinner = new Spinner();
  public static Climbing m_climbing = new Climbing();
  public static OI m_oi = new OI();
  public static AHRS m_ahrs = new AHRS(SPI.Port.kMXP);
  public static Compressor m_compressor = new Compressor();

  public static String sControlMode;

  Command UBcommand = new UBCommand();
  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  @Override
  public void robotInit() {
    UBcommand.start();
    
    //m_chooser.addOption("My Auto", new Auto());
    //m_chooser.addOption("Default Auto", null);
    SmartDashboard.putData("Auto mode", m_chooser);

  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void disabledInit() {
    m_intake.setMotorIntake(0);
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    m_autonomousCommand = new Auto();
    m_autonomousCommand.start();
    /*m_autonomousCommand = m_chooser.getSelected();
    String autoSelected = SmartDashboard.getString("Auto Selector","Default");
    switch(autoSelected) {
      case "My Auto":
        m_autonomousCommand = new Auto();
        break;
      case "Default Auto":
        m_autonomousCommand = null;
        break;
      default:
        m_autonomousCommand = null;
        break;
    }

    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }*/
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    m_drivetrain.mSetDefaultCommand();
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    //sControlMode="tankMode";
    /*switch (sControlMode) {
      case "tankMode":
        new tankControl().start();
        break;
      case "defaultMode":
        new driveControl().start();
        break;
      default:
        new tankControl().start();
        break;
    }*/
  }

  @Override
  public void testPeriodic() {
  }
}

