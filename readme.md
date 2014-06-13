####Description:

This is the one page application. User can add Letter and publish it simultaneously or later. All Letters listed at the bottom of the page. 

Letter have the following fields:

1.Number(required)

2.Date(required)

3.Subject(required)

4."Published"(optional)

5.JPG/PDF scan(required)

6.Note(optional)

User can publish the Letter later after submitting the form by clicking the button 'Publish' (AJAX request).

There are validator in the form in case of user enter incorrect values.

Also, application have internationalization(i18n) of all messages and localization(l10n) of dates format.

Uploaded files save on file system and DataBase(MySQL) save only the file name.:

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
