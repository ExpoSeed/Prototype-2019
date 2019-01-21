/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

//pitch diam 1.432 inches
//use that diameter to find the arc length
//18 tooth from vex

/**
 * Add your docs here.
 */
public class ElevatorSys extends InjectedSubsystem {
  private static int motorLeftPort = 5;
  private static int motorRightPort = 6;

  private boolean done = true;

  private static WPI_TalonSRX motorLeft;
  private static WPI_TalonSRX motorRight;

  //placeholder values
  private static int[] hatchHeights = {0, 1, 2}; 
  private static int[] ballHeights = {0, 1, 2}; 

  private static Encoder enc;

  private static int encChannel0 = 0;
  private static int encChannel1 = 1;

  private static DifferentialDrive elevator; 

  public ElevatorSys() {
    motorLeft = new WPI_TalonSRX(motorLeftPort);
    motorRight = new WPI_TalonSRX(motorRightPort);

    elevator = new DifferentialDrive(motorLeft, motorRight);

    enc = new Encoder(encChannel0,encChannel1, false, Encoder.EncodingType.k4X);
  }

  public void moveToHatch(int num) {
    done = false;
    if(enc.getRaw() > hatchHeights[num])
    {
      while (enc.getRaw() < hatchHeights[num])
        elevator.arcadeDrive(0.2, 0); 
      elevator.stopMotor();  
    }
    if(enc.getRaw() < hatchHeights[num])
    {
      while (enc.getRaw() < hatchHeights[num])
        elevator.arcadeDrive(-0.2, 0); 
      elevator.stopMotor();  
    }
    done = true;
  }

  public void moveToBall(int num) {
    done = false;
    if(enc.getRaw() > ballHeights[num])
    {
      while (enc.getRaw() < ballHeights[num])
        elevator.arcadeDrive(0.2, 0); 
      elevator.stopMotor();  
    }
    if(enc.getRaw() < ballHeights[num])
    {
      while (enc.getRaw() < ballHeights[num])
        elevator.arcadeDrive(-0.2, 0); 
      elevator.stopMotor();  
    }
    done = true;
  }

  public boolean isComplete() {
    return done;
  }

  public void stop() {
    elevator.stopMotor();
  }
}
