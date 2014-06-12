**Description:**

	This is the project where user can fill out the form with file. After submition the form, user can "publish" the Letter that he/she added.

**Technologies in use: **

	Spring 3, Hibernate, MySQL, Tomcat7, Maven, jQuery, Bootstrap.

**Short instruction to start this project:**

	-Create DB

		CREATE SCHEMA 'Pharmacy' DEFAULT CHARACTER SET utf8 ;
	-Change credentials:(default - user:root, pswd:root):

		/Pharmacy/src/main/resources/META-INF/spring/database.properties
	-Path to upload files from users($uploadRelativePath):

		/Pharmacy/src/main/resources/META-INF/spring/database.properties
	-Check if the path exist, if doesn't - create it.

		Default the folder "upload" at the root of application:
			…tomcat7\webapps\Pharmacy-1\upload\
	-Maven goals: 'clean install tomcat7:run'
