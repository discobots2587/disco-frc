package org.discobots.frc.ascent.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.discobots.frc.ascent.HW;
import org.discobots.frc.ascent.commands.DoNothing;
import org.discobots.frc.ascent.framework.CounterEncoder;

public class Shooter extends Subsystem {

    private Victor frontVictor, backVictor;
    private CounterEncoder frontEncoder, backEncoder;
    private DoubleSolenoid mainShoot, armSolenoid;
    double pwmSetpoint = 0.7; // For Open-Loop Shooter
    double rpmSetpoint = 5000; // For Closed-Loop Shooter

    public Shooter() {
        frontVictor = new Victor(HW.motorShooterFrontSlot, HW.motorShooterFrontChannel);
        backVictor = new Victor(HW.motorShooterBackSlot, HW.motorShooterBackChannel);
        mainShoot = new DoubleSolenoid(HW.solenoidShootFwdChannel, HW.solenoidShootRevChannel);
        armSolenoid=new DoubleSolenoid(HW.solenoidArmDownChannel,HW.solenoidArmUpChannel);
//        frontEncoder = new CounterEncoder(HW.encoderShooterFrontSlot, HW.encoderShooterFrontChannel, 2);
//        backEncoder = new CounterEncoder(HW.encoderShooterBackSlot, HW.encoderShooterBackChannel, 2);
    }

    protected void initDefaultCommand() {
        new DoNothing(this).start();
    }

    public double getFrontPWM() {
        return frontVictor.get();
    } 

    public double getBackPWM() {
        return backVictor.get();
    }

    public void setFrontPWM(double speed) {
        frontVictor.set(speed);
    }

    public void setBackPWM(double speed) {
        backVictor.set(speed);
    }

    public double getFrontRPM() {
        return frontEncoder.getFilteredRPM();
    }

    public double getBackRPM() {
        return backEncoder.getFilteredRPM();
    }

    public void setSetpointRPM(double setpoint) {
        this.rpmSetpoint = setpoint;
    }

    public void setSetpointPWM(double setpoint) {
        this.pwmSetpoint = setpoint;
    }

    public double getSetpointRPM() {
        return this.rpmSetpoint;
    }

    public double getSetpointPWM() {
        return this.pwmSetpoint;
    }

    public boolean getMainShootPosition() {
        return mainShoot.get()==DoubleSolenoid.Value.kForward;//in
    }
    
    public void setMainShootPosition(boolean in) {
        if(in){
            mainShoot.set(DoubleSolenoid.Value.kForward);//in
        }else{
            mainShoot.set(DoubleSolenoid.Value.kReverse);//out
        }
    }
    
    public boolean getArmPosition(){
        return armSolenoid.get()==DoubleSolenoid.Value.kForward;//down
    }
    
    public void setArmPosition(boolean down){
        if(down){
            armSolenoid.set(DoubleSolenoid.Value.kForward);
        }
        else{
            armSolenoid.set(DoubleSolenoid.Value.kReverse);
        }
    }
}