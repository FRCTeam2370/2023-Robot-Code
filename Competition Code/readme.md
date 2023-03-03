# 2370 Competition Robot Code.

This is the Final robot code used in competitions.

**@Todo**
- [x] CAN ID all devices the same
- [x] Document All IDs Below
- [ ] Build Constants file for both robots
- [ ] Clean up arm setpoint pathing

**Button Mappings**




**CAN IDs**
| Module | Device | ID |
| --- | --- | --- |
| Swerve - Front Left 0 | Drive Falcon | 21 |
| Swerve - Front Left 0 | Rotate Falcon | 22 |
| Swerve - Front Left 0 | CANcoder | 23 |
| Swerve - Front Right 1 | Drive Falcon | 24 |
| Swerve - Front Right 1 | Rotate Falcon | 25 |
| Swerve - Front Right 1 | CANcoder | 26 |
| Swerve - Back Left 2 | Drive Falcon | 27 |
| Swerve - Back Left 2 | Rotate Falcon | 28 |
| Swerve - Back Left 2 | CANcoder | 29 |
| Swerve - Back Right 3 | Drive Falcon | 30 |
| Swerve - Back Right 3 | Rotate Falcon | 31 |
| Swerve - Back Right 3 | CANcoder | 32 |
| Arm - Elbow | Falcon | 10 |
| Arm - Elbow | CANcoder | 11 |
| Arm - Shoulder | Falcon | 12 |
| Arm - Shoulder | CANcoder | 13 |
| Module | Pneumatic Hub | 2 |
| Module | Power Distribution Hub | 1 |
| Sensor | Pideon IMU | 40 |

**Swerve Offsets**
| Module | Corner | Production Bot Value | Practice Bot Value |
| --- | --- | --- | --- |
| 0 | Front Left | 354.46 | 73.83 |
| 1 | Front Right | 273.34 | 38.83 |
| 2 | Back Left | 130.69 | 3.78 |
| 3 | Back Right | 19.95 | 7.82 |
