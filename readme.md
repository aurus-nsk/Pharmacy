Description:
	This is the project where user can fill out the form with file. After submition the form, user can "publish" the Letter that he/she added.

Technologies in use: 
	Spring 3, Hibernate, MySQL, Tomcat7, Maven, jQuery, Bootstrap.

Short instruction to start this project:
	1.Create DB
		CREATE SCHEMA 'Pharmacy' DEFAULT CHARACTER SET utf8 ;
	2.Change credentials:(default - user:root, pswd:root):
		/Pharmacy/src/main/resources/META-INF/spring/database.properties
	3.Path to upload files from users($uploadRelativePath):
		/Pharmacy/src/main/resources/META-INF/spring/database.properties
	4.Check if the path exist, if doesn't - create it.
		Default the folder "upload" at the root of application:
			…tomcat7\webapps\Pharmacy-1\upload\
	4. maven goals: 'clean install tomcat7:run'
