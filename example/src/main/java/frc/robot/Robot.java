/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import javax.lang.model.util.ElementScanner6;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick.ButtonType;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SolenoidBase;
import edu.wpi.first.hal.sim.PCMSim;
import edu.wpi.first.hal.sim.mockdata.PCMDataJNI;
import edu.wpi.first.hal.sim.PDPSim;
import edu.wpi.first.hal.PDPJNI;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.VisionThread;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.cscore.CameraServerJNI;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import java.lang.Object;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.NetworkButton;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.GyroBase;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class Robot extends TimedRobot {
  WPI_VictorSPX Motor1 = new WPI_VictorSPX(6);
 WPI_VictorSPX Motor2 = new WPI_VictorSPX(7);
 Timer timer = new Timer();
 Joystick stick1 = new Joystick(0);
 Joystick stick2 = new Joystick(1);
 DifferentialDrive myRobot= new DifferentialDrive(Motor1, Motor2);
 Compressor compressor = new Compressor();
 Solenoid sol1 = new Solenoid(1);
 Solenoid sol2 = new Solenoid(2);
 Solenoid sol3 = new Solenoid(3);
 Solenoid sol0 = new Solenoid(0);
 private JoystickButton button3,button4,button5,button6;
 private NetworkTable table;
 private SmartDashboard smartdashboard;
 private Gyro gyro;
  @Override
 public void robotInit() {
  //---------------------------------------------------------------------------------------//
 //button setting
 //---------------------------------------------------------------------------------------//
  button3 = new JoystickButton(stick1,3);
 button4 = new JoystickButton(stick1,4);
 button5 = new JoystickButton(stick1,5);
 button6 = new JoystickButton(stick1,6);
  //---------------------------------------------------------------------------------------//
 //camera setting
 //---------------------------------------------------------------------------------------//
  new Thread(() ->{
     UsbCamera cam1 = CameraServer.getInstance().startAutomaticCapture();
     UsbCamera cam2 = CameraServer.getInstance().startAutomaticCapture();
     cam1.setResolution(320, 240);
     cam2.setResolution(320, 240);

     CvSink cvSink1 = CameraServer.getInstance().getVideo();
     CvSink cvSink2 = CameraServer.getInstance().getVideo();
     CvSource outputStream1 = CameraServer.getInstance().putVideo("Blur", 320, 240);
     CvSource outputStream2 = CameraServer.getInstance().putVideo("Blur", 320, 240);

     Mat source1 = new Mat();
     Mat source2 = new Mat();
     Mat output1 = new Mat();
     Mat output2 = new Mat();
    
     if(!Thread.interrupted()){
       cvSink1.grabFrame(source1, 0.0033);
       cvSink2.grabFrame(source2, 0.0033);
       Imgproc.cvtColor(source1, output1, Imgproc.COLOR_BGR2GRAY);
       Imgproc.cvtColor(source2, output2, Imgproc.COLOR_BGR2GRAY);
       outputStream1.putFrame(output1);
       outputStream2.putFrame(output2);
     }
   }).start();
  
   //---------------------------------------------------------------------------------------//
   //motor setting
   //---------------------------------------------------------------------------------------//

   Motor1.setNeutralMode(NeutralMode.Brake);
   Motor2.setNeutralMode(NeutralMode.Brake);
    
   //---------------------------------------------------------------------------------------//
   //solenoid setting
   //---------------------------------------------------------------------------------------//

   sol1.set(false);
   sol2.set(false);
   sol3.set(false);
   sol0.set(false);

   //---------------------------------------------------------------------------------------//
   //Networktable setting
   //---------------------------------------------------------------------------------------//

   table = NetworkTable.getTable("datatable");

   //---------------------------------------------------------------------------------------//
   //smartdashboard showing
   //---------------------------------------------------------------------------------------//

   smartdashboard.putNumber("stick1 X", stick1.getX());
   smartdashboard.putNumber("stick1 Y", stick1.getY());
   smartdashboard.putNumber("stick1 Z", stick1.getZ());

  
   //---------------------------------------------------------------------------------------//
   //gyro init
   //---------------------------------------------------------------------------------------//
  
   gyro.calibrate();
   gyro.reset();

 }

 @Override
 public void autonomousInit()
 {
 //---------------------------------------------------------------------------------------//
 //compressor setting
 //---------------------------------------------------------------------------------------//
  
   timer.reset();
   timer.start();
   if(compressor.getCompressorCurrentTooHighStickyFault() == true)
 {
   compressor.setClosedLoopControl(false);
   compressor.close();
 }
 else if(compressor.getCompressorCurrentTooHighFault()==false&&stick2.getRawButton(7))
 {
   compressor.setClosedLoopControl(true);
   compressor.start();
 }
}

 @Override
 public void autonomousPeriodic()
 {
 //---------------------------------------------------------------------------------------//
 //drive
 //---------------------------------------------------------------------------------------// 
    myRobot.arcadeDrive(-stick1.getY(), stick1.getZ());
  //---------------------------------------------------------------------------------------//
 //stair
 //---------------------------------------------------------------------------------------// 
  if(stick2.getRawButton(6))
 {
   sol2.set(true);
 }
 else
 {
   sol2.set(false);
 }
  //---------------------------------------------------------------------------------------//
 //arm
 //---------------------------------------------------------------------------------------// 
  if(stick2.getRawButton(3))
 {
   sol3.set(true);
 }
 else
 {
   sol3.set(false);
 }
  //---------------------------------------------------------------------------------------//
 //shooting
 //---------------------------------------------------------------------------------------//
  if(stick2.getTrigger())
 {
   sol0.set(true);
   sol1.set(true);
 }
 else
 {
   sol0.set(false);
   sol1.set(false);
 }
}

 @Override
 public void teleopInit() {
 //---------------------------------------------------------------------------------------//
 //compressor setting
 //---------------------------------------------------------------------------------------// 
  if(compressor.getCompressorCurrentTooHighStickyFault() == true)
 {
   compressor.setClosedLoopControl(false);
   compressor.close();
 }
 else if (compressor.getCompressorCurrentTooHighFault() == false&&stick2.getRawButton(7))
 {
   compressor.setClosedLoopControl(true);
   compressor.start();
 } 
}
 @Override
 public void teleopPeriodic() {
 //---------------------------------------------------------------------------------------//
 //gryo input
 //---------------------------------------------------------------------------------------//

 new Thread(()->{
   gyro.getAngle();
   timer.delay(0.1);
 }).start();
  //---------------------------------------------------------------------------------------//
 //gyro contorlller
 //---------------------------------------------------------------------------------------//

 //360
 if(stick1.getRawButton(1))
 {
   if(gyro.getAngle()>3 && gyro.getAngle()<357)
   {
     if(gyro.getAngle()>180)
     {
       myRobot.arcadeDrive(0,0.5);
     }
     else if(gyro.getAngle()<180)
     {
       myRobot.arcadeDrive(0, -0.5);
     }
   }
 }
 //90
 if(stick1.getRawButton(2))
 {
   if(gyro.getAngle()<87 && gyro.getAngle()>93)
   {
     if(gyro.getAngle()>270 || gyro.getAngle()<90)
     {
       myRobot.arcadeDrive(0, 0.5);
     }
     else if(gyro.getAngle()<270 && gyro.getAngle()>90)
     {
       myRobot.arcadeDrive(0,-0.5);
     }
   }
 } 
 //180
 if(stick1.getRawButton(3))
   {
   if(gyro.getAngle()<177 && gyro.getAngle()>183)
   {
     if(gyro.getAngle()<180)
     {
       myRobot.arcadeDrive(0, 0.5);
     }
     else if(gyro.getAngle()>180)
     {
       myRobot.arcadeDrive(0, -0.5);
     }
   }
 }
 //270
 if(stick1.getRawButton(4))
 {
   if(gyro.getAngle()<267 || gyro.getAngle()>273)
   {
     if(gyro.getAngle()<270 && gyro.getAngle()>90)
     {
       myRobot.arcadeDrive(0, 0.5);
     }
     else if(gyro.getAngle()>270 || gyro.getAngle()<90)
     {
       myRobot.arcadeDrive(0, -0.5);
     }
   }
 }
 //45 left Rocket
 if(stick1.getRawButton(5))
 {
   if(gyro.getAngle()<42 || gyro.getAngle()>48)
   {
     if(gyro.getAngle()>45 && gyro.getAngle()<255)
     {
       myRobot.arcadeDrive(0, -0.5);
     }
     else if(gyro.getAngle()<45 || gyro.getAngle()>255)
     {
       myRobot.arcadeDrive(0, 0.5);
     }
   }
 } 
 //135 left Rocket
 if(stick1.getRawButton(6))
 {
   if(gyro.getAngle()<132 || gyro.getAngle()>138)
   {
     if(gyro.getAngle()>135 && gyro.getAngle()<315)
     {
       myRobot.arcadeDrive(0, -0.5);
     }
     else if(gyro.getAngle()<135 || gyro.getAngle()>315)
     {
       myRobot.arcadeDrive(0, 0.5);
     }
   }
 }
 //255 right Rocket
 if(stick1.getRawButton(7))
 {
   if(gyro.getAngle()<252 || gyro.getAngle()>258)
   {
     if(gyro.getAngle()<255 && gyro.getAngle()>45)
     {
       myRobot.arcadeDrive(0, 0.5);
     }
     else if(gyro.getAngle()>255 || gyro.getAngle()<45)
     {
       myRobot.arcadeDrive(0, -0.5);
     }
   }
 }
  //315 right Rocket
 if(stick1.getRawButton(8))
 {
   if(gyro.getAngle()<312 ||gyro.getAngle()>318)
   {
     if(gyro.getAngle()>135 && gyro.getAngle()<315)
     {
       myRobot.arcadeDrive(0, 0.5);
     }
     else if(gyro.getAngle()>318 || gyro.getAngle()<135)
     {
       myRobot.arcadeDrive(0, 0.5);
     }
   }
 }


 //---------------------------------------------------------------------------------------//
 //drive
 //---------------------------------------------------------------------------------------// 
  myRobot.arcadeDrive(-stick1.getY(), stick1.getZ());
  //---------------------------------------------------------------------------------------//
 //stair
 //---------------------------------------------------------------------------------------// 
  
 if(stick2.getRawButton(6))
   {
     sol2.set(true);
   }
 else
   {
     sol2.set(false);
   }
  //---------------------------------------------------------------------------------------//
 //arm
 //---------------------------------------------------------------------------------------// 
  
 if(stick2.getRawButton(3))
   {
     sol3.set(true);
   }
 else
   {
     sol3.set(false);
   }
 //---------------------------------------------------------------------------------------//
 //shooting
 //---------------------------------------------------------------------------------------//
   if(stick2.getTrigger())
   {
     sol0.set(true);
     sol1.set(true);
   }
   else
   {
     sol0.set(false);
     sol1.set(false);
   }
}

@Override
 public void testPeriodic() {
 }


}



