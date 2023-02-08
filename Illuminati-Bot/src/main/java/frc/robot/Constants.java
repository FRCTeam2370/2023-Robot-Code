// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final int kDriverControllerPort = 0;

  
    public static int Frontleftdrive = 21;
    public static int Frontleftturn = 25;
    public static int Frontleftencoder = 26;
    public static double Frontleftencoderoffset = -40; //1.2; //174.6;
    public static double Frontleftx = 24;
    public static double Frontlefty = 28;
  
    public static int FrontRightdrive = 24;
    public static int FrontRightturn = 22;
    public static int FrontRightencoder = 23;
    public static double FrontRightencoderoffset = -255.5; //356;//280; //6; //93.1;
    public static double Frontrightx = -24;
    public static double Frontrighty = 28;
    
    public static int BackLeftdrive = 30;
    public static int BackLeftturn = 31;
    public static int BackLeftencoder = 32;
    public static double BackLeftencoderoffset = -0;//231; //260; //309.6;
    public static double BackLeftx = 24;
    public static double BackLefty = -28;
   
    public static int BackRightdrive = 28;
    public static int BackRightturn = 27;
    public static int Backrightencoder = 29;
    public static double Backrightencoderoffset = 0;//160-180; //58;//200.9;
    public static double Backrightx = -24;
    public static double Backrighty = -28;

    public static int IntakeMotor = 0; 
  
    public static int LShoulderMotor = 10;
    public static int LElebowMotor = 0; 
 
    public static int LElbowCANCoder = 0; 
    public static int LShoulderCANCoder = 0; 

    public static int LElbowMagneticSensor = 0; 
    public static int LShoulderMagneticSensor = 0; 

    public static int Sol1ForwardChannel = 0; 
    public static int Sol1ReverseChannel = 1; 

    public static int LShoulder_kp = 0; 
    public static int LShoulder_ki = 0; 
    public static int LShoulder_kd = 0;

    public static int LElbow_kp = 0; 
    public static int LElbow_ki = 0; 
    public static int LElbow_kd = 0;

    public static int LEDPort = 9; 

    public static int LogoStartLength = 38; 
    public static int LogoEndLength = 48; 
    public static int ColorStartLength = 0;
    public static int ColorEndLength = 38;
    public static int LogoStartLength2 = 48;
    public static int LogoEndLength2 = 58; 
    public static int ColorStartLength2 = 58;
    public static int ColorEndLength2 = 96; 
    public static int LED_Strip_Length = 96;
    
    
}
