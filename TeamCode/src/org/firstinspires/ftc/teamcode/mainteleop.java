package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
@TeleOp(name = "5276 Main TeleOp")
public class mainteleop extends driver {
    
    //private DcMotor arm;

    @Override
    public void runOpMode() {
        //this is the code now nyehehehehe

        driveinit(false);
        //arm = hardwareMap.get(DcMotor.class, "arm");

        waitForStart();
        if (opModeIsActive()) {
        //     int targetrotations = 0;
        //     int inputblock = 0;
            while (opModeIsActive()) {
                // inputblock = Math.max(0, inputblock - 1);
                // if (gamepad1.a && inputblock == 0) {
                //     targetrotations++;
                //     inputblock = 5;
                // } else if (gamepad1.b && inputblock == 0) {
                //     targetrotations--;
                //     inputblock = 5;
                // }
                //arm.setPower(-gamepad1.left_trigger + gamepad1.right_trigger);
                drive();
                // setTarget(targetrotations);
                // double frontleftrot = getMotor(1).getCurrentPosition() / (28.0 * 20.0);
                // telemetry.addData("frontleft rotations | distance traveled (inches)", "%s | %s", frontleftrot, frontleftrot * 7.5 / 2.54 );
                // telemetry.addData("frontleft target rotations | current rotations", "%s | %s", targetrotations, getMotor(1).getTargetPosition() / 560.0);
                // telemetry.update();
            }
        }
    }

}
