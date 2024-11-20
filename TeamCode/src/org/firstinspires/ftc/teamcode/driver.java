package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "jusdriver")
public class driver extends LinearOpMode {

    private DcMotor backleft; 
    private DcMotor backright; 
    private DcMotor frontleft; 
    private DcMotor frontright;

    public void driveinit(boolean encoder) {
        backleft = hardwareMap.get(DcMotor.class, "backleft");
        backright = hardwareMap.get(DcMotor.class, "backright");
        frontleft = hardwareMap.get(DcMotor.class, "frontleft");
        frontright = hardwareMap.get(DcMotor.class, "frontright");
        backleft.setDirection(DcMotorSimple.Direction.FORWARD);
        backright.setDirection(DcMotorSimple.Direction.REVERSE);
        frontleft.setDirection(DcMotorSimple.Direction.FORWARD);
        frontright.setDirection(DcMotorSimple.Direction.REVERSE);

        if (encoder) {
            backleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            frontleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            frontright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        } else {
            backleft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            backright.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            frontleft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            frontright.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }

    public DcMotor getMotor(int port) {
        switch (port) {
            case 0:
                return backleft;
            case 1:
                return frontleft;
            case 2:
                return backright;
            case 3:
                return frontright;
        }
        return backleft;
    }

    public void setTarget(int target) {
        backleft.setTargetPosition(target);
        backright.setTargetPosition(target);
        frontleft.setTargetPosition(target);
        frontright.setTargetPosition(target);
    }
    
    @Override
    public void runOpMode() {
        driveinit(false);
        waitForStart();
        while (opModeIsActive()) {
            drive();
            telemetry.addData("", gamepad1.left_stick_y);
            telemetry.update();
        }
    }

    public void drive() {
        backleft.setPower((-gamepad1.left_stick_y + -gamepad1.left_stick_x + gamepad1.right_stick_x) / 4.0);
        backright.setPower((-gamepad1.left_stick_y + gamepad1.left_stick_x + -gamepad1.right_stick_x)  / 4.0);
        frontleft.setPower((-gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x) / 4.0);
        frontright.setPower((-gamepad1.left_stick_y + -gamepad1.left_stick_x + -gamepad1.right_stick_x) / 4.0);
    }

}
