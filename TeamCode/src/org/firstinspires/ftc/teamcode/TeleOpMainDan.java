// /*package org.firstinspires.ftc.teamcode;

// import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
// import com.qualcomm.robotcore.eventloop.opmode.Disabled;
// import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
// import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
// import com.qualcomm.robotcore.hardware.DcMotor;
// import com.qualcomm.robotcore.hardware.DcMotorSimple;
// import com.qualcomm.robotcore.hardware.Servo;
// import org.firstinspires.ftc.robotcore.external.ClassFactory;
// import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
// import org.firstinspires.ftc.robotcore.external.navigation.VuforiaCurrentGame;
// import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;


// @Disabled
// @TeleOp(name = "TeleOpMainDan Java")
// public class TeleOpMainDan extends LinearOpMode {
  
//   //private VuforiaCurrentGame vuforia;

//   private DcMotor frontLeft;
//   private DcMotor backLeft;
//   private DcMotor frontRight;
//   private DcMotor backRight;
  
//   private DcMotor hook;
  
//   private DcMotor arm0;
//   private DcMotor arm1;
  
//   private DcMotor armMain;
  
//   private Servo scoopBaseLeft;
//   private Servo scoopBaseRight;
//   private Servo scoopHandLeft;
//   private Servo scoopHandRight;
  
//   private Servo airplane;
  
//   /*
//   * This function is executed when this Op Mode is selected from the Driver Station.
//   *//*
//   @Override
//   public void runOpMode() {
    
//     //vuforia = new VuforiaCurrentGame();
    
//     frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
//     backLeft = hardwareMap.get(DcMotor.class, "backLeft");
//     frontRight = hardwareMap.get(DcMotor.class, "frontRight");
//     backRight = hardwareMap.get(DcMotor.class, "backRight");
    
//     hook = hardwareMap.get(DcMotor.class, "hook");
    
//     arm0 = hardwareMap.get(DcMotor.class, "arm0");
//     arm1 = hardwareMap.get(DcMotor.class, "arm1");
    
//     armMain = hardwareMap.get(DcMotor.class, "mainarm");
    
//     scoopBaseLeft = hardwareMap.get(Servo.class, "scooperBaseLeft");
//     scoopBaseRight = hardwareMap.get(Servo.class, "scooperBaseRight");
//     scoopHandLeft = hardwareMap.get(Servo.class, "scooperHandLeft");
//     scoopHandRight = hardwareMap.get(Servo.class, "scooperHandRight");
    
//     scoopBaseLeft.setPosition(1.0);
//     scoopBaseRight.setPosition(0);
//     scoopHandRight.setPosition(1.0);
    
//     //telemetry.addData("", "%.2f %.2f %.2f %.2f", 
//     //  scoopBaseLeft.getPosition(), scoopBaseRight.getPosition(), 
//     //  scoopHandLeft.getPosition(), scoopHandRight.getPosition());
//     //telemetry.update();
    
    
    
    
    
    
//     airplane = hardwareMap.get(Servo.class, "airplane");
    
//     // Put initialization blocks here
//     // Sets motors to run without encoder
//     frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//     backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//     frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//     backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    
//     hook.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    
//     /*vuforia.initialize(
//             "", // vuforiaLicenseKey
//             hardwareMap.get(WebcamName.class, "Webcam"), // cameraName
//             "", // webcamCalibrationFilename
//             false, // useExtendedTracking
//             true, // enableCameraMonitoring
//             VuforiaLocalizer.Parameters.CameraMonitorFeedback.AXES, // cameraMonitorFeedback
//             0, // dx
//             0, // dy
//             0, // dz
//             AxesOrder.XZY, // axesOrder
//             90, // firstAngle
//             90, // secondAngle
//             0, // thirdAngle
//             true); // useCompetitionFieldTargetLocations*/
    
//     //vuforia.activate();
    
    
    
//     //arm0.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//     //arm0.setPower(0);
//     //arm0.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//     //arm1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    
//     armMain.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    
//     //airplaneAsServo.setDirection(Servo.Direction.FORWARD);
//     //airplaneAsServo.setPosition(1);
    
//     // Sets direction of all motors to forward
//     frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
//     backLeft.setDirection(DcMotorSimple.Direction.FORWARD);
//     frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
//     backRight.setDirection(DcMotorSimple.Direction.REVERSE);
//     waitForStart();
//     if (opModeIsActive()) {
//       scoopBaseLeft.setPosition(0.70);
//       scoopBaseRight.setPosition(-0.70);
//       // Put run blocks here.
//       while (opModeIsActive()) {
        
//         scoopBaseLeft.setPosition(scoopBaseLeft.getPosition());
//         scoopBaseRight.setPosition(scoopBaseRight.getPosition());
        
//         if (gamepad1.a) {
//           airplane.setPosition(-1);
          
//         }
//         /*
//         if (gamepad1.dpad_down) {
//           arm0.setPower(-0.5);
//           arm1.setPower(-0.5);
//         } else if (gamepad1.dpad_up) {
//           arm0.setPower(0.5);
//           arm1.setPower(0.5);
//         } else {
//           arm0.setPower(0);
//           arm1.setPower(0);
//         }
        
//         if (gamepad2.dpad_up) {
//           armMain.setPower(1);
//         }if (gamepad2.dpad_down) {
//           armMain.setPower(-1);
//         }else {
//           armMain.setPower(0);
//         }
        
//         double handpos = gamepad2.right_stick_y/2.0;
//         double basepos = (gamepad2.left_stick_y*-1)/2.0;
        
//         //scoopBaseLeft.setPosition(basepos);
//         //scoopBaseRight.setPosition(-basepos);
//         //scoopHandLeft.setPosition(handpos);
//         //scoopHandRight.setPosition(-handpos);
        
//         double scoopos = scoopHandRight.getPosition() + gamepad2.left_stick_y / 800;
//         double baseposl = scoopBaseLeft.getPosition() + gamepad2.right_stick_y /800;
//         double baseposr = 1.0 - baseposl;
        
//         scoopHandRight.setPosition(scoopos);
//         scoopBaseLeft.setPosition(baseposl);
//         scoopBaseRight.setPosition(baseposr);
        
//         backLeft.setPower(gamepad1.left_stick_x * -1 + gamepad1.left_stick_y + gamepad1.right_stick_x * -1);
//         frontLeft.setPower(gamepad1.left_stick_x + gamepad1.left_stick_y + gamepad1.right_stick_x * -1);
//         backRight.setPower(gamepad1.left_stick_x  + gamepad1.left_stick_y + gamepad1.right_stick_x);
//         frontRight.setPower(gamepad1.left_stick_x * -1 + gamepad1.left_stick_y + gamepad1.right_stick_x);
        
//         //hook.setPower(gamepad2.left_stick_y * -1);
        
//         telemetry.addData("LeftStickX: ", gamepad1.left_stick_x);
//         telemetry.addData("LeftStickY: ", gamepad1.left_stick_y);
//         telemetry.addData("RightStickX: ", gamepad1.right_stick_x);
//         telemetry.addData("RightStickY: ", gamepad1.right_stick_y);
        
//         // Motor viewing values
//         telemetry.addData("FrontLeft: ", gamepad1.left_stick_x + gamepad1.left_stick_y);
//         telemetry.addData("BackLeft: ", gamepad1.left_stick_x * -1 + gamepad1.left_stick_y);
//         telemetry.addData("FrontRight: ", gamepad1.left_stick_x  + gamepad1.left_stick_y * -1);
//         telemetry.addData("BackRight: ", gamepad1.left_stick_x * -1 + gamepad1.left_stick_y * -1);
//         telemetry.update();
//       }
//     }
//   }*/
// }