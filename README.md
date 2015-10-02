# sbs
Secure Banking System

## Guidelines
* Do not add anything to master branch directly.
* Create your own branch and use that for your tasks any when you feel good to go to master create a pull request
* To start working with this repo do a clone into the directory you want using `git clone https://github.com/chasethenag420/sbs.git`
* To Create branch you can do something like `git checkout -b YOUR_BRANCH_NAME`
* To add files to repo `git add --all` to push all the modifed files else specify the file name
* To commit the files to repo use `git commit` and fill the description about what this commit is for be as clear as possible
* To Push the code to your branch you can use `git push origin YOUR_BRANCH_NAME`
* To pull latest code to your branch you can use `git pull origin YOUR_BRANCH_NAME`
* Do pull request to master branch but do not merge on your own others will review and merge it.

## Installtion Instructions

* Install jdk7
* Install tomcat8
* Install mysql5.5
* Make sure to install Eclipse Java EE IDE for Web Developers.
* Install maven  you can follow steps here MAVEN http://www.mkyong.com/maven/how-to-install-maven-in-windows/
* Import the cloned project into eclipse as maven project
* Configure tomcat8 directory(eclipse properties -> targeted runttime -> add new) and JDK7(eclipse properties -> compiler) with eclipse
* Do Maven clean from Eclipse -> Run As -> Maven clean
* Do Maven install 
* To understand more check out http://www.beingjavaguys.com/2013/08/spring-maven-web-application-in-eclipse.html
* (optional if setup using eclipse) Can create maven project from eclipse or else using below steps and integrate with eclipse
	a. create project structure using maven below command
		mvn archetype:generate -DgroupId=com.chasethenag420.maven -DartifactId=SimpleSpringMaven -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false
	b. to integrate with eclipse run below command inside the project dir SimpleSpringMaven
		mvn eclipse:eclipse -Dwtpversion=2.0
