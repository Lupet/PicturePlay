name := """PlayPictures"""

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  javaCore,
  javaJdbc,
  javaWs % "test",
  "com.h2database" % "h2" % "1.4.181",
  "org.hsqldb" % "hsqldb" % "2.3.2",
  "org.springframework" % "spring-context" % "4.1.1.RELEASE",
  "org.springframework" % "spring-context-support" % "4.1.1.RELEASE",
  "org.springframework" % "spring-orm" % "4.1.1.RELEASE",
  "org.springframework" % "spring-oxm" % "4.1.1.RELEASE",
  "org.springframework" % "spring-beans" % "4.1.1.RELEASE",
  "org.springframework" % "spring-web" % "4.1.1.RELEASE",
  "org.springframework" % "spring-webmvc" % "4.1.1.RELEASE",
  "org.springframework" % "spring-jdbc" % "4.1.1.RELEASE",
  "org.springframework" % "spring-tx" % "4.1.1.RELEASE",
  "org.springframework" % "spring-core" % "4.1.1.RELEASE",
  "org.springframework" % "spring-expression" % "4.1.1.RELEASE",
  "org.springframework" % "spring-aop" % "4.1.1.RELEASE",
  "org.springframework" % "spring-test" % "4.1.1.RELEASE" % "test",
  "org.springframework.security" % "spring-security-core" % "3.2.5.RELEASE",
  "org.springframework.security" % "spring-security-crypto" % "3.2.5.RELEASE",
  "org.springframework.security" % "spring-security-web" % "3.2.5.RELEASE",
  "org.springframework.security" % "spring-security-acl" % "3.2.5.RELEASE",
  "org.springframework.security" % "spring-security-aspects" % "3.2.5.RELEASE",
  "org.springframework.security" % "spring-security-config" % "3.2.5.RELEASE",
  "org.springframework.security" % "spring-security-taglibs" % "3.2.5.RELEASE",
  "org.hibernate" % "hibernate-entitymanager" % "4.3.6.Final",
  "xml-apis" % "xml-apis" % "1.3.04"
)

lazy val root = (project in file(".")).enablePlugins(PlayJava)
