 package robotx.opmodes.autonomous;

 import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
 import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
 import com.qualcomm.robotcore.hardware.DcMotor;
 import com.qualcomm.robotcore.hardware.Servo;

 import robotx.modules.DuckRotation;
 import robotx.modules.IntakeSystem;
 import robotx.modules.LiftSystem;
 import robotx.modules.MecanumDrive;
 import robotx.modules.MecanumLock;
 import robotx.modules.OrientationDrive;

 @Autonomous(name = "RobotXAutonomous2021", group = "Default")
 
 public class RobotXAutonomous2021 extends LinearOpMode {
 
     //private ElapsedTime runtime = new ElapsedTime();
 
     //Modules being imported
     MecanumDrive mecanumDrive;
     DuckRotation duckRotation;
     IntakeSystem intakeSystem;
     OrientationDrive orientationDrive;
     LiftSystem liftSystem;
     MecanumLock mecanumLock;

     public Servo duckServo;
 
 @Override
 
     public void runOpMode () {
 
         //Text at bottom of phone
         telemetry.addData("Status", "Initialized");
         telemetry.update();

         mecanumDrive = new MecanumDrive(this);
         mecanumDrive.init();

         duckRotation = new DuckRotation(this);
         duckRotation.init();

         intakeSystem = new IntakeSystem(this);
         intakeSystem.init();

         orientationDrive = new OrientationDrive(this);
         orientationDrive.init();

         liftSystem = new LiftSystem(this);
         liftSystem.init();

         mecanumDrive.start();
         duckRotation.start();
         intakeSystem.start();
         orientationDrive.start();

         mecanumDrive.frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
         mecanumDrive.frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
         mecanumDrive.backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
         mecanumDrive.backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
 
         telemetry.addData(">", "Press Play to Start Op Mode");
         telemetry.update();

         double power2 = 0.5;

         waitForStart();
         //runtime.reset();
 
         if (opModeIsActive()) {
             //Movement
              //duckServo.setPosition(0.2);

            DriveForward(power2,100);
            sleep(20000);
            DriveForward(power2,500);
             sleep(20000);
             StrafeRight(power2,100);
             sleep(20000);
             StrafeRight(power2,500);
             sleep(20000);
             StrafeLeft(power2,100);
             sleep(20000);
             StrafeLeft(power2,500);
             sleep(20000);
             DriveBackward(power2,100);
             sleep(20000);
             DriveBackward(power2,500);
             sleep(20000);

             sleep(10000);

             TurnRight(power2, 100);
             sleep(20000);
             TurnRight(power2, 200);
             sleep(20000);
             TurnRight(power2,500);

             sleep(30000);

             TurnLeft(power2,100);
             sleep(20000);
             TurnLeft(power2,200);
             sleep(20000);
             TurnLeft(power2,500);
             sleep(20000);

         }
     }

         //Controls
      public void DriveForward(double power, int time) {
         mecanumDrive.frontLeft.setPower(power);  //top left when rev is down and ducky wheel is right
         mecanumDrive.frontRight.setPower(power); //bottom left
         mecanumDrive.backLeft.setPower(-power);   //top right
         mecanumDrive.backRight.setPower(power); // bottom right
         sleep(time);
         mecanumDrive.frontLeft.setPower(0);
         mecanumDrive.frontRight.setPower(0);
         mecanumDrive.backLeft.setPower(0);
         mecanumDrive.backRight.setPower(0);
     }

     public void DriveBackward(double power, int time) {
         mecanumDrive.frontLeft.setPower(-power);
         mecanumDrive.frontRight.setPower(-power);
         mecanumDrive.backLeft.setPower(power);
         mecanumDrive.backRight.setPower(-power);
         sleep(time);
         mecanumDrive.frontLeft.setPower(0);
         mecanumDrive.frontRight.setPower(0);
         mecanumDrive.backLeft.setPower(0);
         mecanumDrive.backRight.setPower(0);
     }

     public void StrafeLeft(double power, int time) {
         mecanumDrive.frontLeft.setPower(-power);
         mecanumDrive.frontRight.setPower(power);
         mecanumDrive.backLeft.setPower(-power);
         mecanumDrive.backRight.setPower(-power);
         sleep(time);
         mecanumDrive.frontLeft.setPower(0);
         mecanumDrive.frontRight.setPower(0);
         mecanumDrive.backLeft.setPower(0);
         mecanumDrive.backRight.setPower(0);
     }

     public void StrafeRight(double power, int time) {
         mecanumDrive.frontLeft.setPower(power);
         mecanumDrive.frontRight.setPower(-power);
         mecanumDrive.backLeft.setPower(power);
         mecanumDrive.backRight.setPower(power);
         sleep(time);
         mecanumDrive.frontLeft.setPower(0);
         mecanumDrive.frontRight.setPower(0);
         mecanumDrive.backLeft.setPower(0);
         mecanumDrive.backRight.setPower(0);
     }

     public void TurnLeft(double power, int time) {
         mecanumDrive.frontLeft.setPower(power);
         mecanumDrive.frontRight.setPower(-power);
         mecanumDrive.backLeft.setPower(-power);
         mecanumDrive.backRight.setPower(-power);
         sleep(time);
         mecanumDrive.frontLeft.setPower(0);
         mecanumDrive.frontRight.setPower(0);
         mecanumDrive.backLeft.setPower(0);
         mecanumDrive.backRight.setPower(0);
     }

     public void TurnRight(double power, int time) {
         mecanumDrive.frontLeft.setPower(-power);
         mecanumDrive.frontRight.setPower(power);
         mecanumDrive.backLeft.setPower(power);
         mecanumDrive.backRight.setPower(power);
         sleep(time);
         mecanumDrive.frontLeft.setPower(0);
         mecanumDrive.frontRight.setPower(0);
         mecanumDrive.backLeft.setPower(0);
         mecanumDrive.backRight.setPower(0);
     }

     public void DuckSpin(double power, int time){
        duckRotation.duckMotor.setPower(power);
        sleep(time);
        duckRotation.duckMotor.setPower(0);
     }

     public void LiftPlatform(double power, int time){
         liftSystem.liftMotor.setPower(power);
         sleep(time);
         liftSystem.liftMotor.setPower(0);
     }
     public void LowerPlatform(double power, int time){
         liftSystem.liftMotor.setPower(-power);
         sleep(time);
         liftSystem.liftMotor.setPower(0);
     }


 }


