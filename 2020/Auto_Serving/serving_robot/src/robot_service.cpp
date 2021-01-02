#include "ros/ros.h"
#include "std_msgs/Bool.h"
#include "std_msgs/String.h"
#include "actionlib_msgs/GoalStatusArray.h"
#include "move_base_msgs/MoveBaseActionResult.h"
#include "geometry_msgs/PoseStamped.h"

#define TURTLEBOT 0
#define TABLE_ONE 1
#define TABLE_TWO 2
#define TABLE_THREE 3

#define NFC_ONE 0
#define NFC_TWO 1
#define NFC_THREE 2

class RobotService
{
private:
    
    ros::NodeHandle nh;
    
    // Pulisher
    ros::Publisher pubPoseStamped;
    ros::Publisher pubTextToSpeech;
    
    // Subscriber
    ros::Subscriber sub_arrival_status_one;
    ros::Subscriber sub_arrival_status_two;
    ros::Subscriber sub_arrival_status_three;
    ros::Subscriber sub_switch_bool;
    ros::Subscriber sub_table_number;
    
    // msgs
    geometry_msgs::PoseStamped poseStampedTable[4];
    
    std::vector<double> target_pose_position;
    std::vector<double> target_pose_orientation;
    
    int robot_sequence[3] = { 0, 0, 0 };
    
    bool robot_target[3] = {true, true, true};
    bool switch_states = false;

public:
    RobotService()
    {
        InitParam();

        pubPoseStamped = nh.advertise<geometry_msgs::PoseStamped>("/move_base_simple/goal", 1);
	pubTextToSpeech = nh.advertise<std_msgs::String>("/sound_message", 1);

        sub_arrival_status_one = nh.subscribe("/move_base/result", 1, &RobotService::cbCheckArrivalStatusOne, this);
        sub_arrival_status_two = nh.subscribe("/move_base/result", 1, &RobotService::cbCheckArrivalStatusTwo, this);
        sub_arrival_status_three = nh.subscribe("/move_base/result", 1, &RobotService::cbCheckArrivalStatusThree, this);

        sub_switch_bool = nh.subscribe("/switch_state", 1, &RobotService::CheckSwitchStates, this);
        sub_table_number = nh.subscribe("/numtable", 1, &RobotService::CheckTableNumber, this);

        ros::Rate loop_rate(5);

        while (ros::ok()) {

            fnPubPose();

            ros::spinOnce();

            loop_rate.sleep();

        }
    }

    void InitParam() {

        nh.getParam("table_pose_robot/position", target_pose_position);
        nh.getParam("table_pose_robot/orientation", target_pose_orientation);

        poseStampedTable[0].header.frame_id = "map";
        poseStampedTable[0].header.stamp = ros::Time::now();

        poseStampedTable[0].pose.position.x = target_pose_position[0];
        poseStampedTable[0].pose.position.y = target_pose_position[1];
        poseStampedTable[0].pose.position.z = target_pose_position[2];

        poseStampedTable[0].pose.orientation.x = target_pose_orientation[0];
        poseStampedTable[0].pose.orientation.y = target_pose_orientation[1];
        poseStampedTable[0].pose.orientation.z = target_pose_orientation[2];
        poseStampedTable[0].pose.orientation.w = target_pose_orientation[3];


        nh.getParam("table_pose_one/position", target_pose_position);
        nh.getParam("table_pose_one/orientation", target_pose_orientation);

        poseStampedTable[1].header.frame_id = "map";
        poseStampedTable[1].header.stamp = ros::Time::now();

        poseStampedTable[1].pose.position.x = target_pose_position[0];
        poseStampedTable[1].pose.position.y = target_pose_position[1];
        poseStampedTable[1].pose.position.z = target_pose_position[2];

        poseStampedTable[1].pose.orientation.x = target_pose_orientation[0];
        poseStampedTable[1].pose.orientation.y = target_pose_orientation[1];
        poseStampedTable[1].pose.orientation.z = target_pose_orientation[2];
        poseStampedTable[1].pose.orientation.w = target_pose_orientation[3];


        nh.getParam("table_pose_two/position", target_pose_position);
        nh.getParam("table_pose_two/orientation", target_pose_orientation);

        poseStampedTable[2].header.frame_id = "map";
        poseStampedTable[2].header.stamp = ros::Time::now();

        poseStampedTable[2].pose.position.x = target_pose_position[0];
        poseStampedTable[2].pose.position.y = target_pose_position[1];
        poseStampedTable[2].pose.position.z = target_pose_position[2];

        poseStampedTable[2].pose.orientation.x = target_pose_orientation[0];
        poseStampedTable[2].pose.orientation.y = target_pose_orientation[1];
        poseStampedTable[2].pose.orientation.z = target_pose_orientation[2];
        poseStampedTable[2].pose.orientation.w = target_pose_orientation[3];


        nh.getParam("table_pose_three/position", target_pose_position);
        nh.getParam("table_pose_three/orientation", target_pose_orientation);

        poseStampedTable[3].header.frame_id = "map";
        poseStampedTable[3].header.stamp = ros::Time::now();

        poseStampedTable[3].pose.position.x = target_pose_position[0];
        poseStampedTable[3].pose.position.y = target_pose_position[1];
        poseStampedTable[3].pose.position.z = target_pose_position[2];

        poseStampedTable[3].pose.orientation.x = target_pose_orientation[0];
        poseStampedTable[3].pose.orientation.y = target_pose_orientation[1];
        poseStampedTable[3].pose.orientation.z = target_pose_orientation[2];
        poseStampedTable[3].pose.orientation.w = target_pose_orientation[3];

    }

    void CheckSwitchStates(const std_msgs::Bool::ConstPtr& msg) {

        if (msg->data == true) {
            switch_states = true;
        } else {
            switch_states = false;
        }
    }
    
    void CheckTableNumber(const std_msgs::String::ConstPtr& msg) {
       
        if (msg->data == "one") {
            robot_sequence[NFC_ONE] = 1;
        }
        else if (msg->data == "two") {
            robot_sequence[NFC_TWO] = 1;
        }
        else if (msg->data == "three") {
            robot_sequence[NFC_THREE] = 1;
        }
    }
    
    void cbCheckArrivalStatusOne(const move_base_msgs::MoveBaseActionResult rcvMoveBaseActionResult) {
        
        if (rcvMoveBaseActionResult.status.status == 3) {
            robot_target[NFC_ONE] = true;
        } else {
            ROS_INFO("cbCheckArrivalStatusOne : %d", rcvMoveBaseActionResult.status.status);
        }
    }

    void cbCheckArrivalStatusTwo(const move_base_msgs::MoveBaseActionResult rcvMoveBaseActionResult) {
        
        if (rcvMoveBaseActionResult.status.status == 3) {
            robot_target[NFC_TWO] = true;
        } else {
            ROS_INFO("cbCheckArrivalStatusTwo : %d", rcvMoveBaseActionResult.status.status);
        }
    }

    void cbCheckArrivalStatusThree(const move_base_msgs::MoveBaseActionResult rcvMoveBaseActionResult) {
        
        if (rcvMoveBaseActionResult.status.status == 3) {
            robot_target[NFC_THREE] = true;
        } else {
            ROS_INFO("cbCheckArrivalStatusThree : %d", rcvMoveBaseActionResult.status.status);
        }
    }

    void TTSFileNum(const char* text) {

        std_msgs::String str;

        str.data = text;

        pubTextToSpeech.publish(str);

    }
    
    void fnPubPose() {
        
        if (robot_target[NFC_ONE]) {
            if (robot_sequence[NFC_ONE] == 1) {
                
                // start message
                ROS_INFO("start");
                                
                if (switch_states == true) {
                    robot_sequence[NFC_ONE] = 2;
                }

            } else if (robot_sequence[NFC_ONE] == 2) {
                
                // go to table
                ROS_INFO("Moving 1");

                TTSFileNum("first");

                pubPoseStamped.publish(poseStampedTable[TABLE_ONE]);

                robot_target[NFC_ONE] = false;
                
                robot_sequence[NFC_ONE] = 3;

                TTSFileNum("second");
                
            } else if (robot_sequence[NFC_ONE] == 3) {
                
                // arrived table
                ROS_INFO("arrived");

                if (switch_states == false) {
                    robot_sequence[NFC_ONE] = 4;
                }
                
            } else if (robot_sequence[NFC_ONE] == 4) {
                
                // return to table
                ROS_INFO("Moving 2");

                TTSFileNum("third");

                pubPoseStamped.publish(poseStampedTable[TURTLEBOT]);
                
                robot_target[NFC_ONE] = false;
                
                robot_sequence[NFC_ONE] = 5;
                
            } else if (robot_sequence[NFC_ONE] == 5) {
                
                // finish
                ROS_INFO("finish");

                TTSFileNum("fifth");
                
                robot_sequence[NFC_ONE] = 0;
                
                ROS_INFO("ended");
                
            }
        }


        if (robot_target[NFC_TWO]) {
            if (robot_sequence[NFC_TWO] == 1) {
                
                // start message
                ROS_INFO("start");
                                
                if (switch_states == true) {
                    robot_sequence[NFC_TWO] = 2;
                }
             
            } else if (robot_sequence[NFC_TWO] == 2) {
                
                // go to table
                ROS_INFO("Moving 1");

                TTSFileNum("first");

                ros::Duration(3).sleep();

                pubPoseStamped.publish(poseStampedTable[TABLE_TWO]);

                robot_target[NFC_TWO] = false;
                
                robot_sequence[NFC_TWO] = 3;
                
            } else if (robot_sequence[NFC_TWO] == 3) {
                
                // arrived table
                ROS_INFO("arrived");

                if (switch_states == false) {
                    robot_sequence[NFC_TWO] = 4;
                }
                
            } else if (robot_sequence[NFC_TWO] == 4) {
                
                // return to table
                ROS_INFO("Moving 2");

                TTSFileNum("third");

                ros::Duration(3).sleep();

                pubPoseStamped.publish(poseStampedTable[TURTLEBOT]);
                
                robot_target[NFC_TWO] = false;
                
                robot_sequence[NFC_TWO] = 5;
                
            } else if (robot_sequence[NFC_TWO] == 5) {
                
                // finish
                ROS_INFO("finish");

                TTSFileNum("fourth");
                
                robot_sequence[NFC_TWO] = 0;
                
                ROS_INFO("ended");
                
            }
        }


        if (robot_target[NFC_THREE]) {
            if (robot_sequence[NFC_THREE] == 1) {
                
                // start message
                ROS_INFO("start");
                                
                if (switch_states == true) {
                    robot_sequence[NFC_THREE] = 2;
                }

            } else if (robot_sequence[NFC_THREE] == 2) {
                
                // go to table
                ROS_INFO("Moving 1");

                TTSFileNum("first");

                pubPoseStamped.publish(poseStampedTable[TABLE_THREE]);

                robot_target[NFC_THREE] = false;
                
                robot_sequence[NFC_THREE] = 3;
                
            } else if (robot_sequence[NFC_THREE] == 3) {
                
                // arrived table
                ROS_INFO("arrived");

                if (switch_states == false) {
                    robot_sequence[NFC_THREE] = 4;
                }
                
            } else if (robot_sequence[NFC_THREE] == 4) {
                
                // return to table
                ROS_INFO("Moving 2");

                TTSFileNum("second");

                pubPoseStamped.publish(poseStampedTable[TURTLEBOT]);
                
                robot_target[NFC_THREE] = false;
                
                robot_sequence[NFC_THREE] = 5;
                
            } else if (robot_sequence[NFC_THREE] == 5) {
                
                // finish
                ROS_INFO("finish");

                TTSFileNum("third");
                
                robot_sequence[NFC_THREE] = 0;
                
                ROS_INFO("ended");
                
            }
        }
    }
};

int main(int argc, char** argv) {
    
    ros::init(argc, argv, "robot_service");
    
    RobotService robotService;
    
    ros::spin();
    
    return 0;

}
