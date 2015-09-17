Course Project Server Part 
========================

Project template for a REST service exposed through dropwizard 

This contains:
        dropwizard -.0.8.1 for setting up the server 
        
        JDBI for connect with mysql 
        
        AngularJS Jquery bootstrap for front page design 

which works together out-of-the-box.


DropWizard :
  Dropwizard pulls together stable, mature libraries from the Java ecosystem into a simple, light-weight package that lets you focus on getting things done. read more on http://www.dropwizard.io/


Summary:
------------------------

This project is mainly about how to use RESTful web service in the IOT project ,basically It is how to set up a running system with both a device get the real data and a  server store and display the data. I will talk in detail about how to set up the running system first, and then I will talk about how the hardware and software 's functions and how they work together. which works together out-of-the-box.

The System Architecture:
------------------------

              
            1st Level       Web Browser 
     
                              |     |
            2nd Level       RESTful Server
                             |        |
                            |          |
            3rd Level  End Device   End Device


setting up the system 
------------------------
Then I am going to talk about how to make the system work , be sure you have downloaded all the code  !! and import them in the eclipse projects , by the way I am using eclipse for setting up this system not in the raspberry but in my local PC .If you really want to programming in the raspberry pi ,you can do it ,but I am sure it is very slow .you can use my projects also ,cause you still can use maven in the raspberry pi. I am going to lead you guys from both hardware and software side.

Hardware:
===============

1. the raspberry OS:
	The first is you need to install and configure the system in the raspberry pi ,which using as SD card as its memeory and storage ,I am 		not going to talk about how to install the r aspberry OS here you can find more tutorials here: https://www.raspberrypi.org
2.The connection of the DHT11 temp sensor and the device 
	2.1 connect temp sensor
	you need three wires to get data from the DHT11 sensor, which is power/ground/data .below is my connection:
                              
![alt tag](https://github.com/wentixiaogege/CourseProjectEndDevice/blob/master/readme_img/temp_connection.png)                                                          













I mainly get the inspiration from this website: http://hirt.se/blog/?p=493









   2.2 connect the relay device
       relay something is just like relay a led device ,you only need to wires to relay a device which is state and ground: connection like below:
             
![alt tag](https://github.com/wentixiaogege/CourseProjectEndDevice/blob/master/readme_img/relay_connection.png)   










 


  

 
Software:
===============

After the hardware part is done! You need to install some libraries and configure to make the system runing.

1.install DHT-11 driver :(optional in server part)
	download code from here:   https://github.com/adafruit/Adafruit-Raspberry-Pi- Python-Code/tree/master/Adafruit_DHT_Driver 
	cd into this folder and then using the command  : make 
	you will get a runnable file Adafruit_DHT;put it into you PATH environment.
 
2.install jdk8
	download  form here http://www.oracle.com/technetwork/java/javase/downloads/jdk8-arm-downloads-2187472.html
	unzip it into somewhere I installed it in /opt/java 
	cd /opt/java/yourjdkversion/
	sudo update-alternatives --install "/usr/bin/java" "java" "/opt/java/yourjdkversion/java" 1
	sudo update-alternatives --set java /opt/java/yourjdkversion/bin/java

   open /etc/profile add those lines in the bottom:


   export JAVA_HOME=/opt/ yourjdkversion
   export JRE_HOME=$JAVA_HOME/jre 
   export CLASSPATH=.:$JAVA_HOME/lib:$JRE_HOME/lib 
   export PATH=$JAVA_HOME/bin:$PATH 


  Now you should be able to execute java


set up the server part 
   	Be sure you have download maven2 .if you haven't run below command in the  command line:
                sudo apt-get install maven2
                
   Be sure you have download 
        CourseProject : https://github.com/wentixiaogege/CourseProject   ;
		CourseProjectV2: https://github.com/wentixiaogege/CourseProjectV2;
	    import those projects into your eclipse you will see all the avaibleable code,make sure there is no error show up.

![alt tag](https://github.com/wentixiaogege/CourseProjectEndDevice/blob/master/readme_img/eclipse_server_init.png)      



   cd to the CourseProject folder and then run below:
		mvn clean install 
   cd to the CourseProjectV1 folder and then run below :
		mvn clean install 
   everything should went well and then you will get a XXXX.jar package like below:
   
   
![alt tag](https://github.com/wentixiaogege/CourseProjectEndDevice/blob/master/readme_img/eclipse_server_compiled.png)   

copy and paste both config.yml and this XXXX.jar package into your raspberry OS system anywhere you want and run the below command:


sudo java -Djava.library.path=/usr/lib/jni/ -jar yourpackagename.jar server config.yml



you are set!!!!!!






API designs:
============

	GET     /service/devices (edu.itu.course.dropwizard.resources.DeviceResourceImpl)
    PUT     /service/devices (edu.itu.course.dropwizard.resources.DeviceResourceImpl)
    DELETE  /service/devices/{deviceId} (edu.itu.course.dropwizard.resources.DeviceResourceImpl)
    GET     /service/devices/{deviceId} (edu.itu.course.dropwizard.resources.DeviceResourceImpl)
    POST    /service/devices/{deviceId} (edu.itu.course.dropwizard.resources.DeviceResourceImpl)
    GET     /service/devices/{deviceId}/all/temp (edu.itu.course.dropwizard.resources.DeviceResourceImpl)
    GET     /service/devices/{deviceId}/peroid/temp (edu.itu.course.dropwizard.resources.DeviceResourceImpl)
    POST    /service/devices/{deviceId}/peroid/temp (edu.itu.course.dropwizard.resources.DeviceResourceImpl)
    POST    /service/devices/{deviceId}/peroid/temp1 (edu.itu.course.dropwizard.resources.DeviceResourceImpl)
    POST    /service/devices/{deviceId}/temperature (edu.itu.course.dropwizard.resources.DeviceResourceImpl)
    POST    /service/devices/{deviceId}/{relayState} (edu.itu.course.dropwizard.resources.DeviceResourceImpl)
    POST    /service/notification/broadcast (edu.itu.course.dropwizard.api.Notification)
    POST    /service/notification/{deviceId} (edu.itu.course.dropwizard.api.Notification)



Usage
=====

To start the server,

- create the distributable by running "mvn clean install"
- move to target folder and run "java -jar CourseProjectV1-0.0.1-SNAPSHOT.jar server ../config.yml"

The server will start at http://localsever:8888. You can also get into admin interface by going to http://localhost:8081/admin/.

//add device
curl -i -X PUT -H "Content-Type: application/json" -d '{"id":11,"name":"test Device","status":0,"dataType":"lightding"}' http://localhost:8080/devices

//get device
curl -i -X GET -H "Content-Type: application/json"  http://localhost:8080/devices/11

//get device Data 
curl -i -X GET -H "Content-Type: application/json"  http://localhost:8080/devices/1/all/temp

//get period device data
//using json 
curl -i -X POST -H "Content-Type: application/json" -d '{"intervals":3600,"starttime":"2015-07-13 18:18:04","endtime":"2015-07-13 23:31:15"}' http://localhost:8080/devices/1/peroid/temp

curl -i -X GET -H "Content-Type: application/json" -d '{"glossary":{"intervals":3600,"starttime":"2015-07-13 18:18:04","status":0,"endtime":"2015-07-13 23:31:15"}}' http://localhost:8080/devices/1/peroid/temp
//using url pathparam

//delete device

curl -i -X DELETE -H "Content-Type: application/json"  http://localhost:8080/service/devices/11


/////////////////////////////////////////////////you may concern///////////////////////////////////////////////////////////////
//relay on the device

curl -i -X POST -H "Content-Type: application/json" -d '{"relayOn":1,"relayOff":0,"frequency":2000}' http://localhost:8888/service/notification/{deviceId}

//relay off the device

curl -i -X POST -H "Content-Type: application/json" -d '{"relayOn":0,"relayOff":1,"frequency":2000}' http://localhost:8888/service/notification/{deviceId}

//change freqency of the device 1000-100000(1s -- 100s)

curl -i -X POST -H "Content-Type: application/json" -d '{"relayOn":0,"relayOff":0,"frequency":2000}' http://localhost:8888/service/notification/broadcast


Thanks !
-----------------------------------------
any questions:mail wentixiaogege@gmail.com 
	 