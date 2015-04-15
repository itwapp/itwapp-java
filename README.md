# Itwapp Java Bindings [![Build Status](https://travis-ci.org/itwapp/itwapp-java.svg)](https://travis-ci.org/itwapp/itwapp-java)

You can sign up for a InterviewApp account at http://itwapp.io.

Requirements
============

Java 1.6 and later.

Installation
============

### Maven users

Add this dependency to your project's POM:

Snapshots (Repository https://oss.sonatype.org/content/repositories/snapshots/)


    <dependency>
      <groupId>io.itwapp</groupId>
      <artifactId>itwapp-java</artifactId>
      <version>1.0-SNAPSHOT</version>
    </dependency>

Usage
=====

ItwappExample.java

    import java.util.HashMap;

    import io.itwapp.Itwapp;
    import io.itwapp.models.Interview;

    public class ItwappExample {

        public static void main(String[] args) {
            Itwapp.apiKey = "YOUR-API-KEY";
            Itwapp.secretKey = "YOUR-SECRET-KEY";
            
            Interview[] res = Interview.findAll(new HashMap<String, Object>());
            
        }
    }


See [test](https://github.com/itwapp/itwapp-java/blob/master/src/test/java/io/itwapp/) for more examples.
