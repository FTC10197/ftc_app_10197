package com.qualcomm.ftcrobotcontroller.opmodes;

//------------------------------------------------------------------------------
//
// PushBotAutoSensors
//
/**
 * Provide a basic autonomous operational mode that uses the left and right
 * drive motors and associated encoders, the left arm motor and associated touch
 * sensor, IR seeker V3 and optical distance sensor implemented using a state
 * machine for the Push Bot.
 *
 * @author SSI Robotics
 * @version 2015-08-13-19-48
 */
public class SeaBotAuto extends PushBotTelemetrySensors1

{
    //--------------------------------------------------------------------------
    //
    // PushBotAutoSensors
    //
    /**
     * Construct the class.
     *
     * The system calls this member when the class is instantiated.
     */
    public SeaBotAuto()

    {
        //
        // Initialize base classes.
        //
        // All via self-construction.

        //
        // Initialize class members.
        //
        // All via self-construction.

    } // PushBotAutoSensors

    //--------------------------------------------------------------------------
    //
    // start
    //
    /**
     * Perform any actions that are necessary when the OpMode is enabled.
     *
     * The system calls this member once when the OpMode is enabled.
     */
    @Override public void start ()

    {
        //
        // Call the PushBotHardware (super/base class) start method.
        //
        super.start ();

        //
        // Reset the motor encoders on the drive wheels.
        //
        reset_drive_encoders ();

    } // start

    //--------------------------------------------------------------------------
    //
    // loop
    //
    /**
     * Implement a state machine that controls the robot during auto-operation.
     * The state machine uses a class member and sensor input to transition
     * between states.
     *
     * The system calls this member repeatedly while the OpMode is running.
     */
    @Override public void loop ()

    {
        //
        // Update the state machines
        //
        switch (v_state)
        {
            //
            // State 0.
            //
            case 1:
                //
                // Wait for the encoders to reset.  This might take multiple cycles.
                //
                if (have_drive_encoders_reset ())
                {
                    //
                    // Begin the next state.  Drive forward.
                    //
                    drive_using_encoders (0.5f, 0.5f, 2880, 2880);

                    //
                    // Transition to the next state.
                    //
                    v_state++;
                }

                break;
            //
            // State 1.
            //

            case 2:
                if(move_forward_until_touch())
                {
                    v_state++;
                }

                //
                // Perform no action - stay in this case until the OpMode is stopped.
                // This method will still be called regardless of the state machine.
                //
            default:
                //
                // The autonomous actions have been accomplished (i.e. the state has
                // transitioned into its final state.
                //
                break;
        }

        //
        // Update the arm state machine.
        //
        update_arm_state ();

        //
        // Send telemetry data to the driver station.
        //
        update_telemetry (); // Update common telemetry
        telemetry.addData ("11", "Drive State: " + v_state);
        telemetry.addData ("12", "Arm State: " + v_arm_state);

    } // loop

    //--------------------------------------------------------------------------
    //
    // update_arm_state
    //
    /**
     * Implement a state machine that controls the arm during auto-operation.
     */
    public void update_arm_state ()

    {
        //
        // Update the arm state machine.
        //
        switch (v_arm_state)
        {
            //
            // State 0.
            //
            case 0:
                //
                // Wait until a command is given (i.e. v_state is set to 1).
                //
                break;
            //
            // State 1.
            //
            case 1:
                //
                // Continue moving the arm up.  If the touch sensor is
                // triggered, then the arm will stop and this call will perform
                // no action.  If the touch sensor has not been triggered, then
                // motor power will still be applied.
                //
                if (move_arm_upward_until_touch ())
                {
                    //
                    // Transition to the stop state.
                    //
                    v_arm_state++;
                }
                break;
            //
            // Perform no action - stay in this case until the OpMode is
            // stopped.  This method will still be called regardless of the
            // state machine.
            //
            default:
                //
                // The autonomous actions have been accomplished (i.e. the state
                // has transitioned into its final state.
                //
                break;
        }

    } // update_arm_state

    //--------------------------------------------------------------------------
    //
    // v_state
    //
    /**
     * This class member remembers which state is currently active.  When the
     * start method is called, the state will be initialize (0).  During the
     * first iteration of the loop method, the state will change from initialize
     * to state_1.  When state_1 actions are complete, the state will change to
     * state_2.  This implements a state machine for the loop method.
     */
    private int v_state = 0;

    //--------------------------------------------------------------------------
    //
    // v_arm_state
    //
    /**
     * This class member remembers which state is currently active.  When the
     * start method is called, the state will be initialize (0).  During the
     * first iteration of the loop method, the state will change from initialize
     * to state_1.  When state_1 actions are complete, the state will change to
     * state_2.  This implements a state machine for the loop method.
     */
    private int v_arm_state = 0;

} // PushBotAutoSensors
