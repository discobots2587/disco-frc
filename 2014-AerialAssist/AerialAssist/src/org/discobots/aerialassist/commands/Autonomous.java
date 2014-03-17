package org.discobots.aerialassist.commands;

import org.discobots.aerialassist.commands.upperbody.AutonomousIntake;
import org.discobots.aerialassist.commands.drive.AutonomousTankDrive;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import org.discobots.aerialassist.commands.upperbody.FirePneumatapult;
import org.discobots.aerialassist.commands.upperbody.Intake;
import org.discobots.aerialassist.commands.upperbody.ToggleArm;

public class Autonomous extends CommandGroup {

    long time;

    public Autonomous(int mode) {
        switch (mode) {
            case 0:
                autonomousMode0Init();
                break;
            case 1:
                autonomousMode1Init();
                break;
            case 2:
                autonomousMode2Init();
                break;
            case 3:
                autonomousMode3Init();
                break;
        }
    }

    private void autonomousMode0Init() {
        // Do Nothing
    }

    private void autonomousMode1Init() { // TWO BALL: HIGH HIGH
        addSequential(new ToggleCompressor());
        addSequential(new SetPneumaticsRunnable(true));
        addSequential(new ToggleArm(false));
        addSequential(new AutonomousIntake(0.3 * Intake.IN, 1500));
        addSequential(new AutonomousTankDrive(-0.6, -0.6, 0.4 * Intake.IN, 2000));
        addSequential(new WaitCommand(1.500));
        addSequential(new FirePneumatapult(true, 2));
        addSequential(new WaitCommand(1.500));
        addSequential(new AutonomousIntake(1 * Intake.IN, 2500));
        addSequential(new FirePneumatapult(true, 2));
    }

    private void autonomousMode2Init() { // TWO BALL: LOW HIGH
        addSequential(new ToggleCompressor());
        addSequential(new SetPneumaticsRunnable(true));
        addSequential(new AutonomousTankDrive(-1, -1, 1550));
        addSequential(new AutonomousTankDrive(0.7, 0.7, 900));
        addSequential(new ToggleArm(false));
        addSequential(new AutonomousIntake(0.4 * Intake.IN, 3000));
        addSequential(new FirePneumatapult(true, 2));
        addSequential(new WaitCommand(55));
    }

    private void autonomousMode3Init() { // TEST
        addSequential(new FirePneumatapult(true, 4));
    }

    public void initialize() {
        super.initialize();
        time = System.currentTimeMillis();
    }

    public boolean isFinished() {
        if (super.isFinished()) {
            System.out.println("Autonomous completed in " + (System.currentTimeMillis() - this.time) + " milliseconds.");
            return true;
        } else {
            return false;
        }
    }
}
