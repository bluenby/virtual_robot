package org.firstinspires.ftc.teamcode;

/*
backleft.setPower(0.2);
*/

import com.acmerobotics.roadrunner.util.Angle;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
@TeleOp(name = "Concept Encoder Driver")
public class encoderdriveconcept extends LinearOpMode {

    public DcMotorEx backleft;
    public DcMotorEx backright;
    public DcMotorEx frontleft;
    public DcMotorEx frontright;

    private BNO055IMU imu;

    public void driveinit() {
        backleft = (DcMotorEx)(hardwareMap.get(DcMotor.class, "back_left_motor"));
        backright = (DcMotorEx)(hardwareMap.get(DcMotor.class, "back_right_motor"));
        frontleft = (DcMotorEx)(hardwareMap.get(DcMotor.class, "front_left_motor"));
        frontright = (DcMotorEx)(hardwareMap.get(DcMotor.class, "front_right_motor"));
        backleft.setDirection(DcMotorSimple.Direction.FORWARD);
        backright.setDirection(DcMotorSimple.Direction.REVERSE);
        frontleft.setDirection(DcMotorSimple.Direction.FORWARD);
        frontright.setDirection(DcMotorSimple.Direction.REVERSE);
        backleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        imu = hardwareMap.get(BNO055IMU.class, "imu");

        BNO055IMU.Parameters params = new BNO055IMU.Parameters();

        params.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        params.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        //params.mode = BNO055IMU.SensorMode.IMU;
        params.loggingEnabled = false;

        imu.initialize(params);

        telemetry.addData("Calibrating", "");
        telemetry.update();

//        while (!imu.isGyroCalibrated()) {
//            sleep(50);
//            idle();
//        }
        telemetry.addData("Ready", "");
        telemetry.update();
    }

    public void debug() {
        telemetry.addData("", "BL: %s <- %s || %s", backleft.getTargetPosition(), backleft.getCurrentPosition(), backleft.getMode());
        telemetry.addData("", "BR: %s <- %s || %s", backright.getTargetPosition(), backright.getCurrentPosition(), backright.getMode());
        telemetry.addData("", "FL: %s <- %s || %s", frontleft.getTargetPosition(), frontleft.getCurrentPosition(), frontleft.getMode());
        telemetry.addData("", "FR: %s <- %s || %s", frontright.getTargetPosition(), frontright.getCurrentPosition(), frontright.getMode());
    }

    public void runOffset(double targetx, double targety) {

        int tx = (int) (targetx / (75.0 * Math.PI) * 560.0);
        int ty = (int) (targety / (75.0 * Math.PI) * 560.0);

        backleft.setTargetPosition(-tx + ty);
        ((DcMotorEx) backleft).setTargetPositionTolerance(10);
        backright.setTargetPosition(tx + ty);
        ((DcMotorEx) backright).setTargetPositionTolerance(10);
        frontleft.setTargetPosition(tx + ty);
        ((DcMotorEx) frontleft).setTargetPositionTolerance(10);
        frontright.setTargetPosition(-tx + ty);
        ((DcMotorEx) frontright).setTargetPositionTolerance(10);

        backleft.setPower(0.15);
        backright.setPower(0.15);
        frontleft.setPower(0.15);
        frontright.setPower(0.15);

        while (busy()) {
            telemetry.addData("", "busy: %s", busy());
            telemetry.update();
        }

        backleft.setPower(0.0);
        backright.setPower(0.0);
        frontleft.setPower(0.0);
        frontright.setPower(0.0);
    }

    public void runOffset(double targetx, double targety, double r) {


        int x = (int) (targetx / (75.0 * Math.PI) * 560.0);
        int y = (int) (targety / (75.0 * Math.PI) * 560.0);

        int tx = (int) (((x*Math.cos(Math.toRadians(r))) - (y*Math.sin(Math.toRadians(r)))) * 1);
        int ty = (int) (((x*Math.sin(Math.toRadians(r))) + (y*Math.cos(Math.toRadians(r)))) * 1);

        setTurn(r);

        reset();

        backleft.setTargetPosition(-tx + ty);
        ((DcMotorEx) backleft).setTargetPositionTolerance(10);
        backright.setTargetPosition(tx + ty);
        ((DcMotorEx) backright).setTargetPositionTolerance(10);
        frontleft.setTargetPosition(tx + ty);
        ((DcMotorEx) frontleft).setTargetPositionTolerance(10);
        frontright.setTargetPosition(-tx + ty);
        ((DcMotorEx) frontright).setTargetPositionTolerance(10);

        backleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontright.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        backleft.setPower(0.15);
        backright.setPower(0.15);
        frontleft.setPower(0.15);
        frontright.setPower(0.15);

        while (busy()) {
            telemetry.addData("", "busy: %s targetx %s targety %s tx %s ty %s", busy(), targetx, targety, tx, ty);
            telemetry.addData("", "%s %s", frontleft.getCurrentPosition(), frontright.getCurrentPosition());
            telemetry.addData("", "%s %s", backleft.getCurrentPosition(), backright.getCurrentPosition());
            telemetry.update();
        }

        backleft.setPower(0.0);
        backright.setPower(0.0);
        frontleft.setPower(0.0);
        frontright.setPower(0.0);

    }

    public void reset() {
        backleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        backleft.setTargetPosition(0);
        ((DcMotorEx) backleft).setTargetPositionTolerance(10);
        backright.setTargetPosition(0);
        ((DcMotorEx) backright).setTargetPositionTolerance(10);
        frontleft.setTargetPosition(0);
        ((DcMotorEx) frontleft).setTargetPositionTolerance(10);
        frontright.setTargetPosition(0);
        ((DcMotorEx) frontright).setTargetPositionTolerance(10);

        backleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontright.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        backleft.setPower(0);
        backright.setPower(0);
        frontleft.setPower(0);
        frontright.setPower(0);

    }

    public double heading() {
        return imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZXY, AngleUnit.DEGREES).firstAngle;
    }

    public void setTurn(double theta) {

        backleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        double target = (heading() + theta) % 360;
        int a = (int) (Math.abs(theta)/theta);

        backleft.setVelocity(280*a);
        backright.setVelocity(-280*a);
        frontleft.setVelocity(280*a);
        frontright.setVelocity(-280*a);

        while (heading() > target || heading() < target) {
            telemetry.addData("", "%s %s", heading(), a);
            telemetry.update();
            sleep(10);
        }

        backleft.setVelocity(0.2);
        backright.setVelocity(0.2);
        frontleft.setVelocity(0.2);
        frontright.setVelocity(0.2);

    }

//    public void drive() {
//        backleft.setPower((-gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x) / 3.0 * 2.0);
//        backright.setPower((-gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x)  / 3.0 * 2.0);
//        frontleft.setPower((-gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x) / 3.0 * 2.0);
//        frontright.setPower((-gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x) / 3.0 * 2.0);
//    }

    public void runCourse() {
        runOffset(0.0, 0.0);
    }

    public boolean busy() {
        return (backleft.isBusy() || backright.isBusy() || frontleft.isBusy() || frontright.isBusy());
    }

    @Override
    public void runOpMode() {

        driveinit();

        double xoff = 0;
        double yoff = 0;
        double headingoff = heading();

        boolean isinput = false;
        boolean wasinput = false;

        boolean change = false;

        waitForStart();
        reset();
        if (opModeIsActive()) {

            while (opModeIsActive()) {

                if (change) {
                    int x = (int) (xoff / (75.0 * Math.PI) * 560.0);
                    int y = (int) (yoff / (75.0 * Math.PI) * 560.0);

                    int tx = (int) (((x*Math.cos(Math.toRadians(heading() * -1 - 180))) - (y*Math.sin(Math.toRadians(heading() * -1 - 180)))) * 1);
                    int ty = (int) (((x*Math.sin(Math.toRadians(heading() * -1 - 180))) + (y*Math.cos(Math.toRadians(heading() * -1 - 180)))) * 1);

                    backleft.setTargetPosition(-tx + ty);
                    ((DcMotorEx) backleft).setTargetPositionTolerance(10);
                    backright.setTargetPosition(tx + ty);
                    ((DcMotorEx) backright).setTargetPositionTolerance(10);
                    frontleft.setTargetPosition(tx + ty);
                    ((DcMotorEx) frontleft).setTargetPositionTolerance(10);
                    frontright.setTargetPosition(-tx + ty);
                    ((DcMotorEx) frontright).setTargetPositionTolerance(10);

                    backleft.setPower(0.2);
                    backright.setPower(0.2);
                    frontleft.setPower(0.2);
                    frontright.setPower(0.2);

                    change = false;

                }

                double error = headingoff - heading();
                if (error > 180) error = error % -180;
                if (error < -180) error = error % 180;

                if (Math.abs(error) > 0.5) {
                    int a = error < 0 ? -1 : 1;

                    backleft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                    backright.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                    frontleft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                    backleft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

                    backleft.setVelocity(140*a);
                    backright.setVelocity(-140*a);
                    frontleft.setVelocity(140*a);
                    frontright.setVelocity(-140*a);

                    while (heading() - 0.5 > headingoff || heading() + 0.5 < headingoff) {
                        telemetry.addData("", "HEADING ADJUSTMENT", heading(), a);
                        telemetry.update();
                        sleep(10);
                    }

                    backleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    backright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    frontleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    frontright.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                }

                telemetry.addData("", "busy: %s || isinput: %s || wasinput: %s \n error: %s", busy(), isinput, wasinput, error);
                debug();
                telemetry.update();
                if ((isinput = gamepad1.a) && !wasinput) {
                    yoff += 50;
                    change = true;
                } else if ((isinput = gamepad1.b) && !wasinput) {
                    xoff += 50;
                    change = true;
                } else if ((isinput = gamepad1.y) && !wasinput) {
                    yoff += -50;
                    change = true;
                } else if ((isinput = gamepad1.x) && !wasinput) {
                    xoff += -50;
                    change = true;
                } else if ((isinput = gamepad1.dpad_right) && !wasinput) {
                    headingoff += 45;
                    if (headingoff > 180) headingoff = headingoff % -180;
                    if (headingoff < 180) headingoff = headingoff % 180;
                } else if ((isinput = gamepad1.dpad_left) && !wasinput) {
                    headingoff += -45;
                    if (headingoff > 180) headingoff = headingoff % -180;
                    if (headingoff < 180) headingoff = headingoff % 180;
                }
                wasinput = isinput;
            }
        }
    }

}

