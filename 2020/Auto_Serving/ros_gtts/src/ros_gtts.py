#!/usr/bin/env python

import rospy
from std_msgs.msg import String
from pygame import mixer


def speaker(data):
	mixer.init()
	if data.data == "first":
		rospy.loginfo(data.data)
		mixer.music.load("fff.mp3")
		mixer.music.play()
	elif data.data == "second":
		rospy.loginfo(data.data)
		mixer.music.load("sss.mp3")
		mixer.music.play()
	elif data.data == "third":
		rospy.loginfo(data.data)
		mixer.music.load("ttt.mp3")
		mixer.music.play()
	elif data.data == "fourth":
		rospy.loginfo(data.data)
		mixer.music.load("fff.mp3")
		mixer.music.play()
	elif data.data == "fifth":
		rospy.loginfo(data.data)
		mixer.music.load("ffff.mp3")
		mixer.music.play()
	else:
		rospy.loginfo("No TTS Message")


def listener():
	rospy.init_node('listener', anonymous=True)

	rospy.Subscriber("play_sound_message", String, speaker)

	rospy.spin()


if __name__ == '__main__':
	listener()
