package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

@TeleOp(name = "PID TESTS")
public class PIDdriver extends LinearOpMode {

    private ElapsedTime timer = new ElapsedTime();
    private PIDMotor backleft;
    private PIDMotor backright;
    private PIDMotor frontleft;
    private PIDMotor frontright;

    private BNO055IMU imu;

    private double firstangle;

    final private double MAX_RPM = 6000.0;

    final private double TICKS_PER_REV = 28.0;

    final private double GEAR = 20.0;

    final private double MAX_TICKS_PER_SEC = MAX_RPM / 60 * TICKS_PER_REV * GEAR;

    final private double P = 1.0;
    final private double I = 0.0;
    final private double D = 0.0;

    private double targetx = 0;
    private double maxerrorx = 0;

    private double targety = 0;
    private double maxerrory = 0;

    public boolean busy() {
        return backleft.motor.isBusy() || backright.motor.isBusy() || frontleft.motor.isBusy() || frontright.motor.isBusy();
    }

    public boolean driveinit() {

        backleft = new PIDMotor(hardwareMap.get(DcMotorEx.class, "back_left_motor"), "backleft");
        backright = new PIDMotor(hardwareMap.get(DcMotorEx.class, "back_right_motor"), "backright");
        frontleft = new PIDMotor(hardwareMap.get(DcMotorEx.class, "front_left_motor"), "frontleft");
        frontright = new PIDMotor(hardwareMap.get(DcMotorEx.class, "front_right_motor"), "frontleft");

        backleft.motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backright.motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontleft.motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontright.motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        backleft.motor.setDirection(DcMotorSimple.Direction.REVERSE);
        backright.motor.setDirection(DcMotorSimple.Direction.FORWARD);
        frontleft.motor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontright.motor.setDirection(DcMotorSimple.Direction.FORWARD);

        backleft.motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backright.motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontleft.motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontright.motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        backleft.motor.setTargetPosition(10);
        backright.motor.setTargetPosition(10);
        frontleft.motor.setTargetPosition(10);
        frontright.motor.setTargetPosition(10);

        backleft.motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backright.motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontleft.motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontright.motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        backleft.motor.setPower(0.1);
        backright.motor.setPower(0.1);
        frontleft.motor.setPower(0.1);
        frontright.motor.setPower(0.1);

        while (busy()) {
            sleep(20);
            idle();
        }

        backleft.motor.setPower(0);
        backright.motor.setPower(0);
        frontleft.motor.setPower(0);
        frontright.motor.setPower(0);

        backleft.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backright.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontleft.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontright.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        return backleft.motor != null && frontleft.motor != null && backright.motor != null && frontright.motor != null;

    }

    public boolean imuinit() {

        BNO055IMU.Parameters params = new BNO055IMU.Parameters();

        //params.mode = BNO055IMU.SensorMode.IMU;
        params.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        params.loggingEnabled = true;
        params.loggingTag = "IMU";

        imu = hardwareMap.get(BNO055IMU.class, "imu");

        imu.initialize(params);

//        while (!imu.isGyroCalibrated() && opModeInInit()) {
//            telemetry.addData("IMU:", "INITIALIZING");
//            telemetry.update();
//            sleep(20);
//            idle();
//        }

        firstangle = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZXY, AngleUnit.DEGREES).firstAngle;

        return true;

    }

    public void pid(PIDMotor motor, int x) {
        double tx = targetx / (75.0 * Math.PI) * GEAR * TICKS_PER_REV;
        double ty = targety / (75.0 * Math.PI) * GEAR * TICKS_PER_REV;
        double t = tx * x + ty;

        double err = t - motor.motor.getCurrentPosition();

        double p = P * err;

        double dt = motor.timer.seconds();
        motor.timer.reset();

        double d = D * ((err - motor.old) / dt);

        double i = I * (motor.sum);

        motor.motor.setVelocity(p + i + d);

        telemetry.addLine()
                .addData("\nMOTOR", motor.name)
                .addData("\nPOS || TARGET", "%s, || %s", motor.motor.getCurrentPosition(), t)
                .addData("\nERROR", "%s", err)
                .addData("\nP", "%s", p)
                .addData("\nI", "%s", i)
                .addData("\nD", "%s", d);

        motor.old = motor.motor.getCurrentPosition();

        motor.sum += err;

    }

    public void debug() {
        telemetry.addData("", "BL: %s <- %s || %s", backleft.motor.getTargetPosition(), backleft.motor.getCurrentPosition(), backleft.motor.getMode());
        telemetry.addData("", "BR: %s <- %s || %s", backright.motor.getTargetPosition(), backright.motor.getCurrentPosition(), backright.motor.getMode());
        telemetry.addData("", "FL: %s <- %s || %s", frontleft.motor.getTargetPosition(), frontleft.motor.getCurrentPosition(), frontleft.motor.getMode());
        telemetry.addData("", "FR: %s <- %s || %s", frontright.motor.getTargetPosition(), frontright.motor.getCurrentPosition(), frontright.motor.getMode());
    }

    @Override
    public void runOpMode() {

        if (driveinit()) {
            telemetry.addData("DRIVE:", "READY");
            telemetry.update();
        }
        if (imuinit()) {
            telemetry.addData("DRIVE:", "READY");
            telemetry.addData("IMU:", "READY");
            telemetry.update();
        }

        waitForStart();
        if (opModeIsActive()) {

            int input = 0;

            boolean go = true;

            timer.reset();

            

            while (opModeIsActive()) {



                telemetry.addData("HEADING", "%s", imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZXY, AngleUnit.DEGREES).firstAngle);

                input = Math.max(input - 1, 0);

                if (gamepad1.a && input == 0) {
                    targety = -500;
                    input += 200;
                } else if (gamepad1.b && input == 0) {
                    targety = 500;
                    input += 200;
                } else if (gamepad1.x && input == 0) {
                    targetx = 500;
                    input += 20;
                } else if (gamepad1.y && input == 0) {
                    targetx = -500;
                    input += 200;
                }

                pid(backleft, -1);
                pid(backright, 1);
                pid(frontleft, 1);
                pid(frontright, -1);

                telemetry.addData("", "%s %s", frontleft.motor.getCurrentPosition(), frontright.motor.getCurrentPosition());
                telemetry.addData("", "%s %s", backleft.motor.getCurrentPosition(), backright.motor.getCurrentPosition());

                telemetry.update();
            }
        }

    }

}
