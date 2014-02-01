package org.discobots.aerialassist.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.discobots.aerialassist.OI;
import org.discobots.aerialassist.subsystems.Catapult;
import org.discobots.aerialassist.subsystems.CompressorSub;
import org.discobots.aerialassist.subsystems.Drivetrain;
import org.discobots.aerialassist.subsystems.RollerSub;

/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use
 * CommandBase.exampleSubsystem
 *
 * @author Author
 */
public abstract class CommandBase extends Command {

    public static OI oi;
    // Create a single static instance of all of your subsystems

    public static CompressorSub compressorSub = new CompressorSub();
    public static Drivetrain drivetrainSub = new Drivetrain();
    public static RollerSub intakeSub = new RollerSub();
    public static Catapult catapultSub = new Catapult();

    public static void init() {
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        oi = new OI();
        
        // Show what command your subsystem is running on the SmartDashboard
        SmartDashboard.putData(drivetrainSub);
        SmartDashboard.putData(compressorSub);
        SmartDashboard.putData(intakeSub);
        SmartDashboard.putData(catapultSub);
    }

    public CommandBase(String name) {
        super(name);
    }

    public CommandBase() {
        super();
    }
}
