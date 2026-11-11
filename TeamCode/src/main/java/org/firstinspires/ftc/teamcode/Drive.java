package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class Drive extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        // Make sure your ID's match your configuration
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("fl");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("bl");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("fr");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("br");
        DcMotor Intake = hardwareMap.dcMotor.get("Intake");
        Servo IntkR = hardwareMap.get(Servo.class, "IntkR");
        Servo IntkL = hardwareMap.get(Servo.class, "IntkL");
        DcMotor transfer = hardwareMap.dcMotor.get("transfer");
        DcMotor Shoot1 = hardwareMap.dcMotor.get("Shoot1");
        DcMotor Shoot2 = hardwareMap.dcMotor.get("Shoot2");


        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        Shoot1.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y;  //Y stick value is reversed
            double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x;
            boolean a = gamepad1.a;
            boolean b = gamepad1.b;


            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            frontLeftMotor.setPower(frontLeftPower);
            backLeftMotor.setPower(backLeftPower);
            frontRightMotor.setPower(frontRightPower);
            backRightMotor.setPower(backRightPower);


            if(b) {
                Intake.setPower(0.5);
                transfer.setPower(0.5);
                IntkL.setPosition(3);
                IntkR.setPosition(3);
            }
            else if(a) {
                Intake.setPower(-0.5);
                transfer.setPower(-0.5);
                IntkL.setPosition(-3);
                IntkR.setPosition(-3);
            }
            else if(gamepad1.x) {
                Shoot1.setPower(0.8);
                Shoot2.setPower(0.8);
            }
            else {
                Intake.setPower(0);
                transfer.setPower(0);
                IntkL.setPosition(0.5);
                IntkR.setPosition(0.5);
                Shoot1.setPower(0);
                Shoot2.setPower(0);
            }
        }
    }
}