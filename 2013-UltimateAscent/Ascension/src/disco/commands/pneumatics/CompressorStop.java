/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package disco.commands.pneumatics;

import disco.commands.CommandBase;


public class CompressorStop extends CommandBase {
    private boolean done;

    public CompressorStop() {
	// Use requires() here to declare subsystem dependencies
	requires(compressor);
	this.setRunWhenDisabled(true);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        done=false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
	compressor.set(false);
	done=true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
	return done;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
	end();
    }
}
