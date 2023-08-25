package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.Gamepad;
@TeleOp
public class TestMecanum extends OpMode {


        DcMotor frontLeftMotor;
        DcMotor frontRightMotor;
        DcMotor backLeftMotor;
        DcMotor backRightMotor;
        Servo servoArm;
        @Override
        public void init()
        {
            frontLeftMotor= hardwareMap.dcMotor.get("frontLeftMotor");
            frontRightMotor= hardwareMap.dcMotor.get("frontRightMotor");
            backLeftMotor= hardwareMap.dcMotor.get("backLeftMotor");
            backRightMotor=hardwareMap.dcMotor.get("backRightMotor");///in sursa era scris asa, nu e preferabil hardwareMap.get(... .class String"...")  ?
            servoArm=hardwareMap.get(Servo.class, "Arm");

        }
        boolean servoState=false;//true<=>inchis, false<=>deschis
        boolean lastLoopPress=false;
        public void loop()
        {
            double cnst=1.1;
            double y=-gamepad1.left_stick_y;
            double x=gamepad1.left_stick_x*cnst;
            double rx=gamepad1.right_stick_x;
            double denominator = Math.max(Math.abs(y)+Math.abs(x)+Math.abs(rx),1);
            double flp =(y+x+rx)/denominator;
            double blp=(y-x+rx)/denominator;
            double frp=(y-x-rx)/denominator;
            double brp=(y+x-rx)/denominator;
            frontLeftMotor.setPower(flp);
            frontRightMotor.setPower(frp);
            backLeftMotor.setPower(blp);
            backRightMotor.setPower(brp);
            if(gamepad1.a && !lastLoopPress)
            {
                servoState = !servoState;
            }
            lastLoopPress=gamepad1.a;
            if(!servoState)
            {
                servoArm.setPosition(0);
            }
            else servoArm.setPosition(0.9);
        }




}
