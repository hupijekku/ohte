Ohjelmistotekniikka, harjoitustyö  
======

# Procedural World Generator  
Procedural World Generator (PWG) is a Java11/JFX desktop application for procedurally generating different types of worlds, maps and dungeons. Main use cases of the application include, but are not limited to, creating maps for game development, creating battle maps for tabletop role-playing games (such as D&D) and learning about different procedural generation methods.  

The application generates a 2d grid map generated with user defined variables that can then be exported or saved into a database for later use.  

## Documentation  
[System Requirement Specification (SRS)](https://github.com/hupijekku/ohte/blob/master/dokumentaatio/vaatimusmaarittely.md)  
[Record of work hours](https://github.com/hupijekku/ohte/blob/master/dokumentaatio/tuntikirjanpito.md)  

## Terminal commands  
### Testing
Tests can be run with command  
```
mvn test jacoco:report
```  
Report can be then found at _target/site/jacoco/index.html_  

### Running  
Program can be run from terminal with command  
```
mvn compile exec:java -Dexec.mainClass=pwg.Main
```  

### Style check  
Checkstyle can be run with command
```
mvn jxr:jxr checkstyle:checkstyle
```
Report can be then found at _target/site/checkstyle.html_  
