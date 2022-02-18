 package robotx.modules;

 import com.qualcomm.robotcore.eventloop.opmode.OpMode;
 import com.qualcomm.robotcore.hardware.DcMotor;

 import robotx.libraries.XModule;

 public class IntakeSystem extends XModule {
 
     DcMotor intakeMotor;
 

 
     double power = 1;
 

 
     public IntakeSystem (OpMode op){
         super(op);
     }
 
     public void init() {
         intakeMotor = opMode.hardwareMap.dcMotor.get("IntakeMotor");

     }
 

 
     public void loop() {
         if (xGamepad2().x.isDown()) {
             intakeMotor.setPower(power);
         }
 
         else if (xGamepad2().y.isDown()) {
             intakeMotor.setPower(-power);
         }
 
         else {
             intakeMotor.setPower(0.0);
         }
 

     }
 }
