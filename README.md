dsauthorizdemo
==============

Deltaspike allows for securing views with access decision voters by annotating the view config classes in a typesafe view-config. The problem is, ADVs are evaluated before page parameters are set, and authorization could depend on page parameters, (example: a page that serves to create and edit entities, depending on a entity-id passed to it, or, a particular property of the entity with the passed id).

The application works like this (it is very simplistic, for the sake of demo):

- There are two users, 'john' and 'peter'. john is authorized to create and edit employees, and peter is authorized for edits only (permission codes are employee.create and employee.edit, and are stored in a set in User.java). A reference to the currently logged-in user is stored in the UserSession session-scoped bean. The currently logged-in user can be changed at the homepage.
- The application has a employee.xhtml page. If an employee id is not provided, the page will be used to create a employee. If an id is passed, it will be used to edit an employee.
- The ADV checks for the id passed to the page to find out what permission the user needs to access the page. But it is evaluated before the id is set (via a page parameter), resulting on 'peter' being unable to edit employees (the id is not set = 'employee.create' is required) and 'john' being authorized with the wrong permission (he has the 'employee.create' permission, but should be authorized with 'employee.edit'). See application logging.

Seam, for example, evaluates page parameters first, then restrict expressions (analogous to ADVs) and then page actions (see [Seam pages.xml](http://shrubbery.homeip.net/c/display/W/Seam+pages.xml#Seampagesxml-restrictrestrict)). My Seam application (which I'm porting to CDI) works without any problems with these authorization requirements.

The application implements a Deltaspike exception handler, which handles the authorization violations.

The project should be easily imported into Eclipse.

System requirements
-------------------
- JDK 8
- Java EE 7 capable application server, Wildfly 8.2.0+ preferred (the project has been created with the Java EE archetypes for  Wildfly 8.2.0)
- Maven 3.1 or better

Build and Deploy on Wildfly
---------------------------

1. Make sure you have started the JBoss Server.
2. Open a command line and navigate to the root directory of this quickstart.
3. Type this command to build and deploy the archive:

        mvn clean package wildfly:deploy

4. This will deploy `target/dsauthorizdemo.war` to the running instance of the server.
 
Access the application 
---------------------

The application will be running at the following URL: [http://localhost:8080/dsauthorizdemo/](http://localhost:8080/dsauthorizdemo/)


Undeploy the Archive
--------------------

1. Make sure you have started the JBoss Server as described above.
2. Open a command line and navigate to the root directory of this quickstart.
3. When you are finished testing, type this command to undeploy the archive:

        mvn wildfly:undeploy

Debug the Application
------------------------------------

If you want to debug the source code or look at the Javadocs of any library in the project, run either of the following commands to pull them into your local repository. The IDE should then detect them.

    mvn dependency:sources
    mvn dependency:resolve -Dclassifier=javadoc
