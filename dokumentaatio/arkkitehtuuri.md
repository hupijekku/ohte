# Architecture

## Structure
Package structure:  
<img src="https://github.com/hupijekku/ohte/blob/master/dokumentaatio/kuvat/Rakenne.png">  

Package _pwg.ui_ contains the code for the user interface. Package _pwg.domain_ contains the application logic. Package _pwg.dao_ contains the code responsible for saving data to a SQL database.  
## User interface
The program has one view that changes based on user controls.  
When the program launches, the user can see several controls, including textboxes and sliders.  
When the _Generate World_-button is pressed, a procedural world will be generated based on the values in the user controls. The world will then be drawn on the left side of the window.  
If the user changes the type of the world, the controls visible to the user change accordingly.  

## Application logic
Class diagram:
<img src="https://github.com/hupijekku/ohte/blob/master/dokumentaatio/kuvat/Sovelluslogiikka.png">  
The class _WorldGenerator_ is mainly responsible for generating the world that will eventually be drawn to the interface.  
Most of the other classes work mainly as structural support classes for the WorldGenerator.  
The _Pathfinder_-class however, contains a more complex path-finding algorithm that the WorldGenerator uses to create paths for Dungeon-type worlds.  

## Long time storage
The class _Database_ in _pwg.ui_ is responsible for saving the generated worlds to a database.  

### Files
The program saves the worlds in a SQL-database file called _"pwg.db"_ that will be created to the application root folder during first launch.  
## Main functionality  
Next few main functionalities will be showcasen using sequence diagrams:  
**World generation**  
When the user presses the _Generate World_-button, the application will progress as shown:  
<img src="https://github.com/hupijekku/ohte/blob/master/dokumentaatio/kuvat/Sekvenssikaavio_luo.png">  

**Saving a world**  
When the user gives the world a valid name and presses the _Save_-button, the application will progress as shown:  
<img src="https://github.com/hupijekku/ohte/blob/master/dokumentaatio/kuvat/Sekvenssikaavio_tallenna.png">  

