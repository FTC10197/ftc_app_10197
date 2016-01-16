package com.qualcomm.ftcrobotcontroller.opmodes;

//------------------------------------------------------------------------------
//
// PushBotTelemetry
//
/**
 * Provide telemetry provided by the PushBotHardware class.
 *
 * Insert this class between a custom op-mode and the PushBotHardware class to
 * display telemetry available from the hardware class.
 *
 * @author SSI Robotics
 * @version 2015-08-02-13-57
 *
 * Telemetry Keys
 *     00 - Important message; sometimes used for error messages.
 *     01 - The power being sent to the left drive's motor controller and the
 *          encoder count returned from the motor controller.
 *     02 - The power being sent to the right drive's motor controller and the
 *          encoder count returned from the motor controller.
 *     03 - The power being sent to the left arm's motor controller.
 *     04 - The position being sent to the left and right hand's servo
 *          controller.
 *     05 - The negative value of gamepad 1's left stick's y (vertical; up/down)
 *          value.
 *     06 - The negative value of gamepad 1's right stick's y (vertical;
 *          up/down) value.
 *     07 - The negative value of gamepad 2's left stick's y (vertical; up/down)
 *          value.
 *     08 - The value of gamepad 2's X button (true/false).
 *     09 - The value of gamepad 2's Y button (true/false).
 *     10 - The value of gamepad 1's left trigger value.
 *     11 - The value of gamepad 1's right trigger value.
 */
public class SeaBotTelemetry extends SeaBotHardware

{
    //--------------------------------------------------------------------------
    //
    // PushBotTelemetry
    //
    /**
     * Construct the class.
     *
     * The system calls this member when the class is instantiated.
     */
    public SeaBotTelemetry()

    {
        //
        // Initialize base classes.
        //
        // All via self-construction.

        //
        // Initialize class members.
        //
        // All via self-construction.

    } // PushBotTelemetry

    //--------------------------------------------------------------------------
    //
    // update_telemetry
    //
    /**
     * Update the telemetry with current values from the base class.
     */
    public void update_telemetry ()

    {
        if (a_warning_generated ())
        {
            set_first_message (a_warning_message ());
        }
        //
        // Send telemetry data to the driver station.
        //
        telemetry.addData
            ( "01"
            , "Left Drive Back Power: "
                + a_left_drive_power()
                + ", "
                + a_left_encoder_count()
            );
        telemetry.addData
            ( "02"
            , "Right Drive Back Power: "
                + a_right_drive_power()
                + ", "
                + a_right_encoder_count()
            );
        telemetry.addData
                ( "03"
                        , "Left Drive Front Power: "
                                + a_left_drive_front_power()

                );
        telemetry.addData
                ( "04"
                        , "Right Drive Front Power: "
                                + a_right_drive_front_power ()

                );
        //telemetry.addData
        //    ( "03"
        //    , "Arm: " + a_arm_power ()
        //    );
        telemetry.addData
            ( "05"
                    , "Climber Drop Position: " + a_climber_position ()
            );
        telemetry.addData
                ( "06"
                        , "Touch pressed?: " + is_touch_sensor_pressed ()
                );
        telemetry.addData
                ( "07"
                        , "Linear Actuator Position:  " + a_linear_actuator_position ()
                );

    } // update_telemetry

    //--------------------------------------------------------------------------
    //
    // update_gamepad_telemetry
    //
    /**
     * Update the telemetry with current gamepad readings.
     */
    public void update_gamepad_telemetry ()

    {
        //
        // Send telemetry data concerning gamepads to the driver station.
        //
        telemetry.addData ("07", "GP1 Left Stick: " + -gamepad1.left_stick_y);
        telemetry.addData ("08", "GP1 Right Stick: " + -gamepad1.right_stick_y);
        telemetry.addData ("09", "GP2 Left Stick: " + -gamepad2.left_stick_y);
        telemetry.addData ("10", "GP2 Right Stick: " + -gamepad2.right_stick_y);
        telemetry.addData ("11", "GP2 X: " + gamepad2.x + ", B: " + gamepad2.b);
        telemetry.addData ("12", "GP2 Y: " + gamepad2.y + ", A: " + gamepad2.a);

    } // update_gamepad_telemetry

    //--------------------------------------------------------------------------
    //
    // set_first_message
    //
    /**
     * Update the telemetry's first message with the specified message.
     */
    public void set_first_message (String p_message)

    {
        telemetry.addData ( "00", p_message);

    } // set_first_message

    //--------------------------------------------------------------------------
    //
    // set_error_message
    //
    /**
     * Update the telemetry's first message to indicate an error.
     */
    public void set_error_message (String p_message)

    {
        set_first_message ("ERROR: " + p_message);

    } // set_error_message

} // PushBotTelemetry
