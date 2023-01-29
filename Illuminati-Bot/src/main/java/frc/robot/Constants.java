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
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }
    public static int Frontleftdrive = 21;
    public static int Frontleftturn = 22;
    public static int Frontleftencoder = 23;
    public static double Frontleftencoderoffset = 186.56-180; //1.2; //174.6;
    public static double Frontleftx = 24;
    public static double Frontlefty = 28;
  
    public static int FrontRightdrive = 24;
    public static int FrontRightturn = 25;
    public static int FrontRightencoder = 26;
    public static double FrontRightencoderoffset = 85.41; //356;//280; //6; //93.1;
    public static double Frontrightx = -24;
    public static double Frontrighty = 28;
    
    public static int BackLeftdrive = 27;
    public static int BackLeftturn = 28;
    public static int BackLeftencoder = 29;
    public static double BackLeftencoderoffset = 230;//231; //260; //309.6;
    public static double BackLeftx = 24;
    public static double BackLefty = -28;
  
    public static int BackRightdrive = 30;
    public static int BackRightturn = 31;
    public static int Backrightencoder = 32;
    public static double Backrightencoderoffset = -22;//160-180; //58;//200.9;
    public static double Backrightx = -24;
    public static double Backrighty = -28;
  
  public static int Leftarm = 10;
 
}
