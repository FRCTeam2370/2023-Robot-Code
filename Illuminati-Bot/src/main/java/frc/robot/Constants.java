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
    public static double x = 1;
    public static double y = 1+(1/6);
  
    public static int Frontleftdrive = 21;
    public static int Frontleftturn = 25;
    public static int Frontleftencoder = 26;
    public static double Frontleftencoderoffset = -40+180; //1.2; //174.6;
    public static double Frontleftx = -x;
    public static double Frontlefty = y;
  
    public static int FrontRightdrive = 24;
    public static int FrontRightturn = 22;
    public static int FrontRightencoder = 23;
    public static double FrontRightencoderoffset = -255.5; //356;//280; //6; //93.1;
    public static double Frontrightx = x;
    public static double Frontrighty = y;
    
    public static int BackLeftdrive = 30;
    public static int BackLeftturn = 31;
    public static int BackLeftencoder = 32;
    public static double BackLeftencoderoffset = -10+180  ;//231; //260; //309.6;
    public static double BackLeftx = -x;
    public static double BackLefty = -y;
   
    public static int BackRightdrive = 28;
    public static int BackRightturn = 27;
    public static int Backrightencoder = 29;
    public static double Backrightencoderoffset = 0+180;//160-180; //58;//200.9;
    public static double Backrightx = x;
    public static double Backrighty = -y;
  
  public static int Leftarm = 10;
 
    public static int LED_Strip_Length = 144;
}
