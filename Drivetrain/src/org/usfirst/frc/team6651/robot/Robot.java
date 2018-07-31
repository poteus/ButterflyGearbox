package org.usfirst.frc.team6651.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

/****   Class Declaration   ****/

public class Robot extends IterativeRobot {
	private static final String kDefaultAuto = "Default";
	private static final String kCustomAuto = "My Auto";
	private String m_autoSelected;
	private SendableChooser<String> m_chooser = new SendableChooser<>();
	
	public static DifferentialDrive DT;
	
	Joystick controller = new Joystick(0);
	
	@Override
	public void robotInit() {
		WPI_TalonSRX leftMotor1 = new WPI_TalonSRX(10);
		WPI_TalonSRX leftMotor2 = new WPI_TalonSRX(11);
		WPI_TalonSRX rightMotor1 = new WPI_TalonSRX(12);
		WPI_TalonSRX rightMotor2 = new WPI_TalonSRX(13);
		leftMotor2.follow(leftMotor1);
    		rightMotor2.follow(rightMotor1);
		
		DT = new DifferentialDrive(leftMotor1, rightMotor1);
	}

	@Override
	public void autonomousInit() {
		m_autoSelected = m_chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + m_autoSelected);
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		switch (m_autoSelected) {
			case kCustomAuto:
				// Put custom auto code here
				break;
			case kDefaultAuto:
			default:
				// Put default auto code here
				break;
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	
	@Override
	public void teleopPeriodic() {
		
		// Normal arcade
		int X_axis = 1, Y_axis = 0, Rotation = 2, Throttle = 3;
		double forward = controller.getRawAxis(X_axis); 
    		double turn = controller.getRawAxis(Y_axis); 
		
		/* Adjust sensitivity */
    		forward=0.4*forward+0.6*forward*forward*forward;
    		turn=0.4*turn+0.6*turn*turn*turn;
		DT.arcadeDrive(forward, turn);
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		
	}
}
