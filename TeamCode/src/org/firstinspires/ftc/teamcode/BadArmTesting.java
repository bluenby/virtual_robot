//package org.firstinspires.ftc.teamcode;
//
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorEx;
//import com.qualcomm.robotcore.hardware.TouchSensor;
//
//@TeleOp(name = "BadArmTesting (Blocks to Java)")
//public class BadArmTesting extends LinearOpMode {
//
//  private DcMotor armAsDcMotor;
//  private TouchSensor limitAsTouchSensor;
//
//  /**
//   * This function is executed when this OpMode is selected from the Driver Station.
//   */
//  @Override
//  public void runOpMode() {
//    armAsDcMotor = hardwareMap.get(DcMotor.class, "armAsDcMotor");
//    limitAsTouchSensor = hardwareMap.get(TouchSensor.class, "limitAsTouchSensor");
//
//    // Put initialization blocks here.
//    armAsDcMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//    ((DcMotorEx) armAsDcMotor).setTargetPositionTolerance(10);
//    armAsDcMotor.setTargetPosition(0);
//    armAsDcMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//    waitForStart();
//    if (opModeIsActive()) {
//      // Put run blocks here.
//      while (opModeIsActive()) {
//        // Put loop blocks here.
//        telemetry.addData("limit", limitAsTouchSensor.isPressed());
//        telemetry.addData("encoder", armAsDcMotor.getCurrentPosition());
//        telemetry.update();
//        armAsDcMotor.setPower(0.5);
//        if (gamepad1.a) {
//          armAsDcMotor.setTargetPosition(20000);
//        } else {
//          armAsDcMotor.setTargetPosition(0);
//        }
//      }
//    }
//  }
//}
