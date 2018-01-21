package org.usfirst.frc.team6644.robot.subsystems;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Arduino extends Subsystem{
	protected I2C i2c;
	public Arduino(){
		i2c = new I2C(I2C.Port.kOnboard, 84);
	}
	
	public boolean isAlive() {
		return !i2c.addressOnly();
	}
	
	public void setHigh() {
		byte[] a = new byte[1];
		a[0] = 65;
		i2c.transaction(a, 1, null, 0);
	}
	
	public void setLow() {
		byte[] a = new byte[1];
		a[0] = 0;
		i2c.transaction(a, 1, null, 0);
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}

}
