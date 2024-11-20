//package org.firstinspires.ftc.teamcode;
//
//import android.util.ArrayMap;
//
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//
//import java.util.ArrayList;
//import java.util.Map;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
//
//@TeleOp(name = "Timing Tests Java")
//public class timingtests extends LinearOpMode {
//
//    private DcMotor frontLeft;
//    private DcMotor backLeft;
//    private DcMotor frontRight;
//    private DcMotor backRight;
//
//    private int time = 1000;
//    private double power = 1.00;
//
//    /**
//     * This function is executed when this Op Mode is selected from the Driver
//     * Station.
//     */
//
//    private void forward() {
//        frontLeft.setPower(power);
//        backLeft.setPower(power);
//        frontRight.setPower(power);
//        backRight.setPower(power);
//        sleep(time);
//        frontLeft.setPower(0);
//        backLeft.setPower(0);
//        frontRight.setPower(0);
//        backRight.setPower(0);
//    }
//
//    private void backward() {
//        frontLeft.setPower(-power);
//        backLeft.setPower(-power);
//        frontRight.setPower(-power);
//        backRight.setPower(-power);
//        sleep(time);
//        frontLeft.setPower(0);
//        backLeft.setPower(0);
//        frontRight.setPower(0);
//        backRight.setPower(0);
//    }
//
//    private void left() {
//        frontLeft.setPower(power);
//        backLeft.setPower(-power);
//        frontRight.setPower(-power);
//        backRight.setPower(power);
//        sleep(time);
//        frontLeft.setPower(0);
//        backLeft.setPower(0);
//        frontRight.setPower(0);
//        backRight.setPower(0);
//    }
//
//    private void right() {
//        frontLeft.setPower(-power);
//        backLeft.setPower(power);
//        frontRight.setPower(power);
//        backRight.setPower(-power);
//        sleep(time);
//        frontLeft.setPower(0);
//        backLeft.setPower(0);
//        frontRight.setPower(0);
//        backRight.setPower(0);
//    }
//
//    private void turnleft() {
//        frontLeft.setPower(-power);
//        backLeft.setPower(-power);
//        frontRight.setPower(power);
//        backRight.setPower(power);
//        sleep(time);
//        frontLeft.setPower(0);
//        backLeft.setPower(0);
//        frontRight.setPower(0);
//        backRight.setPower(0);
//    }
//
//    private void turnright() {
//        frontLeft.setPower(power);
//        backLeft.setPower(power);
//        frontRight.setPower(-power);
//        backRight.setPower(-power);
//        sleep(time);
//        frontLeft.setPower(0);
//        backLeft.setPower(0);
//        frontRight.setPower(0);
//        backRight.setPower(0);
//    }
//
//    final private ArrayList<Runnable> actions = new ArrayList<>();
//    final private Map<Runnable, String> actionnames = new ArrayMap<>();
//    private int action = 0;
//
//    private int currentoption = 0;
//
//    @Override
//    public void runOpMode() {
//
//        actionnames.put(this::forward, "Forward");
//        actionnames.put(this::backward, "Backward");
//        actionnames.put(this::left, "Left");
//        actionnames.put(this::right, "Right");
//        actionnames.put(this::turnleft, "Turn Left");
//        actionnames.put(this::turnright, "Turn Right");
//        actions.add(this::forward);
//        actions.add(this::backward);
//        actions.add(this::left);
//        actions.add(this::right);
//        actions.add(this::turnleft);
//        actions.add(this::turnright);
//
//        // vuforiaPOWERPLAY = new VuforiaCurrentGame();
//
//        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
//        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
//        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
//        backRight = hardwareMap.get(DcMotor.class, "backRight");
//
//        DcMotor.RunMode runmode = DcMotor.RunMode.RUN_WITHOUT_ENCODER;
//
//        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
//        backLeft.setDirection(DcMotorSimple.Direction.FORWARD);
//        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
//        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
//        waitForStart();
//        if (opModeIsActive()) {
//
//            int inputblock = 0;
//            // Put run blocks here.
//            while (opModeIsActive()) {
//                inputblock = Math.max(0, inputblock - 1);
//                if (gamepad1.a) {
//                    actions.get(action).run();
//                    continue;
//                }
//                if (gamepad1.dpad_down && inputblock == 0) {
//                    currentoption  = (currentoption - 1) % 3;
//                    inputblock = 3;
//                } else if (gamepad1.dpad_up && inputblock == 0) {
//                    currentoption = (currentoption + 1) % 3;
//                    inputblock = 3;
//                }
//                switch (currentoption) {
//                    case 0:
//                        telemetry.addData("ACTION", "");
//                        if (gamepad1.dpad_right && inputblock == 0) {
//                            action = (action + 1) % 6;
//                            inputblock = 3;
//                        } else if (gamepad1.dpad_left && inputblock == 0) {
//                            action = (action - 1) % 6;
//                            inputblock = 3;
//                        }
//                    case 1:
//                        telemetry.addData("POWER", "");
//                        if (gamepad1.dpad_right && inputblock == 0) {
//                            power = Math.max(-1.0, Math.min(1.0, power + 0.1));
//                            inputblock = 3;
//                        } else if (gamepad1.dpad_left && inputblock == 0) {
//                            power = Math.max(-1.0, Math.min(1.0, power - 0.1));
//                            inputblock = 3;
//                        }
//                    case 2:
//                        telemetry.addData("TIME", "");
//                        if (gamepad1.dpad_right && inputblock == 0) {
//                            time = Math.max(0, time + 50);
//                            inputblock = 3;
//                        } else if (gamepad1.dpad_left && inputblock == 0) {
//                            time = Math.max(0, time - 50);
//                            inputblock = 3;
//                        }
//                }
//                telemetry.addData("Current Action: ", actionnames.get(actions.get(action)));
//                telemetry.addData("Current Power: ", power);
//                telemetry.addData("Current Time (ms): ", time);
//                telemetry.update();
//            }
//        }
//    }
//}