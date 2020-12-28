#include "ros/ros.h"
#include "std_msgs/Bool.h"
#include "wiringPi.h"
#include <iostream>

#define SWITCH 0

int main(int argc, char** argv)
{
	ros::init(argc, argv, "gpio_switch");

	ros::NodeHandle nh;
	ros::Publisher pub = nh.advertise<std_msgs::Bool>("/switch_state", 1);

	ros::Rate loop_rate(10);

	wiringPiSetup();
	pinMode(SWITCH, INPUT);
	bool states = TRUE;

	while(ros::ok()) {
		std_msgs::Bool msg;
		if(states != digitalRead(SWITCH)) {
			states = digitalRead(SWITCH);
			msg.data = states;
			pub.publish(msg);
		}
		ros::spinOnce();
		loop_rate.sleep();
	}
	return 0;
}
