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

package frc.robot.commands.turntable;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class turnOnNumber extends Command {
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);
  private final ColorMatch colorMatcher = new ColorMatch();
  private final Color kBlueTarget = ColorMatch.makeColor(0.107, 0.385, 0.509);
  private final Color kGreenTarget = ColorMatch.makeColor(0.169, 0.597, 0.234);
  private final Color kRedTarget = ColorMatch.makeColor(0.586, 0.328, 0.085);
  private final Color kYellowTarget = ColorMatch.makeColor(0.344, 0.554, 0.101);
  private String targetColor;
  private int delay;
  private int circle;
  private String colorString;
  boolean a;
  public turnOnNumber(int round) {

    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    colorMatcher.addColorMatch(kBlueTarget);
    colorMatcher.addColorMatch(kGreenTarget);
    colorMatcher.addColorMatch(kRedTarget);
    colorMatcher.addColorMatch(kYellowTarget);
    Robot.m_spinner.doubleUP();
    targetColor = null;
    delay = 0;
    circle = 0;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Color detectedColor = colorSensor.getColor();
    ColorMatchResult match = colorMatcher.matchClosestColor(detectedColor);
    if (match.color == kBlueTarget) {
      colorString = "Blue";
    } else if (match.color == kRedTarget) {
      colorString = "Red";
    } else if (match.color == kGreenTarget) {
      colorString = "Green";
    } else if (match.color == kYellowTarget) {
      colorString = "Yellow";
    } else {
      colorString = "Unknown";
    }
    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Green", detectedColor.green);
    SmartDashboard.putNumber("Blue", detectedColor.blue);
    SmartDashboard.putNumber("Confidence", match.confidence);
    SmartDashboard.putString("Detected Color", colorString);
    if(Robot.m_oi.getOperatorButton(RobotMap.BUTTON_RIGHT)){
      Robot.m_spinner.setSpinnerMotor(RobotMap.SPEED_MOTOR_SPINNER);
    }
    
    if(Robot.m_oi.getOperatorButton(RobotMap.BUTTON_X)){
      Robot.m_spinner.setSpinnerMotor(RobotMap.SPEED_MOTOR_SPINNER);
      targetColor = "Red";
    }
    if(Robot.m_oi.getOperatorButton(RobotMap.BUTTON_Y)){
      Robot.m_spinner.setSpinnerMotor(RobotMap.SPEED_MOTOR_SPINNER);
      targetColor = "Green";
    }
    if(Robot.m_oi.getOperatorButton(RobotMap.BUTTON_B)){
      Robot.m_spinner.setSpinnerMotor(RobotMap.SPEED_MOTOR_SPINNER);
      targetColor = "Blue";
    }
    if(Robot.m_oi.getOperatorButton(RobotMap.BUTTON_A)){
      Robot.m_spinner.setSpinnerMotor(RobotMap.SPEED_MOTOR_SPINNER);
      targetColor = "Yellow";
    }
    if(colorString == targetColor){
      delay += 1;
      if( delay >= 8){
        Robot.m_spinner.setSpinnerMotor(0);
        a = true;
      }
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
      return a;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_spinner.doubleDOWN();
    Robot.m_spinner.setSpinnerMotor(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
