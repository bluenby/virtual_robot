//package org.firstinspires.ftc.teamcode;
//
//import com.qualcomm.hardware.bosch.BNO055IMU;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorEx;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
//
//import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
//import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
//import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
//
//@TeleOp(name = "imudriver")
//public class imudriver extends LinearOpMode {
//
//    public DcMotorEx backleft;
//    public DcMotorEx backright;
//    public DcMotorEx frontleft;
//    public DcMotorEx frontright;
//
//    private BNO055IMU imu;
//    /*
//    backleft
//    backright
//    frontleft
//    frontright
//     */
//
//    public void driveinit() {
//         backleft = hardwareMap.get(DcMotorEx.class, "backleft");
//         backright = hardwareMap.get(DcMotorEx.class, "backright");
//         frontleft = hardwareMap.get(DcMotorEx.class, "frontleft");
//         frontright = hardwareMap.get(DcMotorEx.class, "frontright");
//         backleft.setDirection(DcMotorSimple.Direction.REVERSE);
//         backright.setDirection(DcMotorSimple.Direction.FORWARD);
//         frontleft.setDirection(DcMotorSimple.Direction.REVERSE);
//         frontright.setDirection(DcMotorSimple.Direction.FORWARD);
//         backleft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//         backright.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//         frontleft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//         frontright.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//         backleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//         backright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//         frontleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//         frontright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//
//        imu = hardwareMap.get(BNO055IMU.class, "imu");
//
//        BNO055IMU.Parameters params = new BNO055IMU.Parameters();
//
//        params.angleUnit = BNO055IMU.AngleUnit.DEGREES;
//        params.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
//        params.mode = BNO055IMU.SensorMode.IMU;
//        params.loggingEnabled = false;
//
//        imu.initialize(params);
//
//        telemetry.addData("Calibrating", "");
//        telemetry.update();
//
//        while (!imu.isGyroCalibrated()) {
//            sleep(50);
//            idle();
//        }
//        telemetry.addData("Ready", "");
//        telemetry.update();
//
//    }
//
//    public float heading(int x) {
//        return imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZXY, AngleUnit.DEGREES).firstAngle * -1 + 180 + x;
//    }
//
//    public void setTurn(double theta) {
//        double target = (heading(0) + theta) % 360;
//        int a = (int) (Math.abs(theta)/theta);
//
//         backleft.setVelocity(-280*a);
//         backright.setVelocity(280*a);
//         frontleft.setVelocity(-280*a);
//         frontright.setVelocity(280*a);
//
//        while (heading(-1) > target || heading(1) < target) {
//            telemetry.addData("", "%s %s", heading(0), a);
//            telemetry.update();
//            sleep(10);
//        }
//
//         backleft.setVelocity(0);
//         backright.setVelocity(0);
//         frontleft.setVelocity(0);
//         frontright.setVelocity(0);
//
//    }
//
//    @Override
//    public void runOpMode() {
//        driveinit();
//        waitForStart();
//        while (opModeIsActive()) {
//            if (gamepad1.a) {
//                setTurn(90);
//            }
//            if (gamepad1.b) {
//                setTurn(-90);
//            }
//            telemetry.addData("", heading(0));
//            telemetry.update();
//        }
//    }
//
//}
