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
| Swerve - Front Left 0 | Drive [Falcon](https://store.ctr-electronics.com/falcon-500-powered-by-talon-fx/) | 21 |
| Swerve - Front Left 0 | Rotate [Falcon](https://store.ctr-electronics.com/falcon-500-powered-by-talon-fx/) | 22 |
| Swerve - Front Left 0 | [CANcoder](https://store.ctr-electronics.com/cancoder/) | 23 |
| Swerve - Front Right 1 | Drive [Falcon](https://store.ctr-electronics.com/falcon-500-powered-by-talon-fx/) | 24 |
| Swerve - Front Right 1 | Rotate [Falcon](https://store.ctr-electronics.com/falcon-500-powered-by-talon-fx/) | 25 |
| Swerve - Front Right 1 | [CANcoder](https://store.ctr-electronics.com/cancoder/) | 26 |
| Swerve - Back Left 2 | Drive [Falcon](https://store.ctr-electronics.com/falcon-500-powered-by-talon-fx/) | 27 |
| Swerve - Back Left 2 | Rotate [Falcon](https://store.ctr-electronics.com/falcon-500-powered-by-talon-fx/) | 28 |
| Swerve - Back Left 2 | [CANcoder](https://store.ctr-electronics.com/cancoder/) | 29 |
| Swerve - Back Right 3 | Drive [Falcon](https://store.ctr-electronics.com/falcon-500-powered-by-talon-fx/) | 30 |
| Swerve - Back Right 3 | Rotate [Falcon](https://store.ctr-electronics.com/falcon-500-powered-by-talon-fx/) | 31 |
| Swerve - Back Right 3 | [CANcoder](https://store.ctr-electronics.com/cancoder/) | 32 |
| Arm - Elbow | [Falcon](https://store.ctr-electronics.com/falcon-500-powered-by-talon-fx/) | 10 |
| Arm - Elbow | [CANcoder](https://store.ctr-electronics.com/cancoder/) | 11 |
| Arm - Shoulder | [Falcon](https://store.ctr-electronics.com/falcon-500-powered-by-talon-fx/) | 12 |
| Arm - Shoulder | [CANcoder](https://store.ctr-electronics.com/cancoder/) | 13 |
| Module | [Pneumatic Hub](https://www.revrobotics.com/rev-11-1852/) | 2 |
| Module | [Power Distribution Hub](https://www.revrobotics.com/rev-11-1850/) | 1 |
| Sensor | [Pigeon IMU](https://store.ctr-electronics.com/pigeon-2/) | 40 |

**Swerve Offsets**
| Module | Corner | Production Bot Value | Practice Bot Value |
| --- | --- | --- | --- |
| 0 | Front Left | 354.46 | 73.83 |
| 1 | Front Right | 273.34 | 38.83 |
| 2 | Back Left | 130.69 | 3.78 |
| 3 | Back Right | 19.95 | 7.82 |
