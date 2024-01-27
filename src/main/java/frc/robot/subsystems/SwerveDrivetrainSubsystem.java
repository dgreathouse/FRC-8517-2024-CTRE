// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.function.Supplier;

import com.ctre.phoenix6.mechanisms.swerve.SwerveDrivetrain;
import com.ctre.phoenix6.mechanisms.swerve.SwerveDrivetrainConstants;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModuleConstants;
import com.ctre.phoenix6.mechanisms.swerve.SwerveRequest;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.lib.EDriveMode;

public class SwerveDrivetrainSubsystem extends SwerveDrivetrain implements Subsystem {
  EDriveMode m_driveMode = EDriveMode.FIELD_CENTRIC;



  public SwerveDrivetrainSubsystem(SwerveDrivetrainConstants driveTrainConstants, double OdometryUpdateFrequency,
      SwerveModuleConstants... modules) {
    super(driveTrainConstants, OdometryUpdateFrequency, modules);

  }

  public SwerveDrivetrainSubsystem(SwerveDrivetrainConstants driveTrainConstants, SwerveModuleConstants... modules) {
    super(driveTrainConstants, modules);

  }

  public Command applyRequest(Supplier<SwerveRequest> requestSupplier) {
    return run(() -> this.setControl(requestSupplier.get()));
  }

  public ChassisSpeeds getCurrentRobotChassisSpeeds() {
    return m_kinematics.toChassisSpeeds(getState().ModuleStates);
  }

  public void changeDriveMode() {
    switch (m_driveMode) {
      case FIELD_CENTRIC_FACINGANGLE:
        m_driveMode = EDriveMode.FIELD_CENTRIC;
        break;
      case FIELD_CENTRIC:
        m_driveMode = EDriveMode.ROBOT_CENTRIC;
        break;
      case ROBOT_CENTRIC:
        m_driveMode = EDriveMode.FIELD_CENTRIC_FACINGANGLE;
        break;
      default:
        break;
    }
  }
  public EDriveMode getDriveMode() {
    return m_driveMode;
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}