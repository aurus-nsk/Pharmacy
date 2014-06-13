####Description:

This is the project where user can fill out the form with file. After submition the form, user can "publish" the Letter that he/she added.

####Screenshots

![Alt text] (https://raw.github.com/aurus-nsk/Pharmacy/screenshots/images/screen.gif)

####Technologies in use: 

Spring 3, Hibernate, MySQL, Tomcat7, Maven, jQuery, Bootstrap.

####A short instruction to start this project:

-Create DB

	CREATE SCHEMA 'Pharmacy' DEFAULT CHARACTER SET utf8 ;

-Change credentials in property file:

	/Pharmacy/src/main/resources/META-INF/spring/database.properties

-Change or check the path to upload folder where will store user's files in property file:

	/Pharmacy/src/main/resources/META-INF/spring/database.properties

-Check if the upload folder exist or not. If it doesn't exist - create it on a file system.

The default upload folder locates in the root of application:

	...\tomcat7\webapps\Pharmacy-1\upload\

-Apply and Run maven goals: 

	clean install tomcat7:run