# m2-software-testing

## Summary
**[All you need to know](https://github.com/opsilonn/m2-software-testing#all-you-need-to-know)**

[Where to find the project](https://github.com/opsilonn/m2-software-testing#where-to-find-the-project)

[The project's concept](https://github.com/opsilonn/m2-software-testing#the-projects-concept)

[Getting Started](https://github.com/opsilonn/m2-software-testing#getting-started)


**[Advanced presentation](https://github.com/opsilonn/m2-software-testing#advanced-presentation)**

[Requirements](https://github.com/opsilonn/m2-software-testing#requirements)

[Data Migration](https://github.com/opsilonn/m2-software-testing#data-migration)

[Test Plan](https://github.com/opsilonn/m2-software-testing#test-plan)

[RoadMap](https://github.com/opsilonn/m2-software-testing#roadMap)

[Prototype](https://github.com/opsilonn/m2-software-testing#prototype)


**[Technical round-up](https://github.com/opsilonn/m2-software-testing#technical-round-up)**


**[Authors](https://github.com/opsilonn/m2-software-testing#authors)**




## All you need to know

### Where to find the project
Here is the Github repository :
 ```
https://github.com/opsilonn/efrei-m2-software-testing
 ```


### The project's concept
The goal of this project is to make a Proof Of Concept of a migration from a Java desktop application to a web-browser with a sucessful data migration, built around a data plan.


### Getting Started
The programmation is fully completed, but you still have to both configure 3 parts of the project.
Don't worry ! We've got you covered.

```
 DATABASE :
   Using XAMP ou WAMP, import the script 'edg.sql' that can be found at the root of the project.
   
 
 BACK-END :
  Create a copy of the file 'hibernate-template.cfg.xml' that can be found here : '/back-end\prototype\src\main\resources'.
  Rename it 'hibernate.cfg.xml', and rename the following parameters according to your configuration :
    - connection.username
    - connection.password, if you have any
    - connection.url, if you use a port different to 3306
   
   Then go to the folder back-end and open the windows powershell or windows command line, and run the following command:
   $mvn spring-boot:run

 
 FRONT-END :
  Create a copy of the file 'server/local.server.config.example.js'
  rename it 'server/local.server.config.js'

  To download the dependencies, open a console tab and enter :
  $npm install
  
  To make the server operational, please enter :
  $npm run dev
```


## Advanced presentation
blabla

### Requirements
blabla

### Data migration
blabla

### Test Plan
blabla


### Conception
blabla



### RoadMap
* Phase 1 : configuration
  * Assign tasks
  * Draw requirements
  * Rethink the database
  * Configure the web app'
* Phase 2 : account display
  * API : CRUD
  * Front : CRUD


### Prototype
The prototype is a Proof-Of-Concept to show that we are able to develop a website linked to a backend server that is able to create and modify a user or a contract.




## Technical round-up
The whole project was made using :
* **[XAMP](https://mariadb.org)** - MySQL server for Linux and Mac's users
* **[WAMP](https://mariadb.org)** - MySQL server for Window's users
* **[Spring.io](https://spring.io)** - Java toolbox to deploy performant and efficient Back-end servers
* **[Node.js](https://nodejs.org)** - An asynchronous event-driven JavaScript runtime designed to build scalable network applications
* **[Vue.js](https://fr.vuejs.org)** - Javascript's framework
* **[Vuetify](https://vuetifyjs.com)** - a **[Vue.js](https://fr.vuejs.org)**'s UI library
* **[Nuxt](https://nuxtjs.org)** - a **[Vue.js](https://fr.vuejs.org)**'s framework that enables to make singlepage websites (back and front on the same project)
* **[Axios](https://github.com/axios)** - Promise based HTTP client for the browser and **[Node.js](https://nodejs.org)**
* **[Vuex](https://vuex.vuejs.org)** -  State management pattern + library for **[Vue.js](https://fr.vuejs.org)** applications


## Authors
It was made by the following Efrei Paris students :
* **BEGEOT Hugues** - [his Git repository](https://github.com/opsilonn)
* **BONI François** - [his Git repository](https://github.com/scorpionsdu78)
* **DULCHE Eddy** - [his Git repository](https://github.com/DulcheE)
* **KANN William** - [his Git repository](https://github.com/williamkann)
* **QUACH Julien** - [his Git repository](https://github.com/jinkieu)
* **THONG Nicolas** - [his Git repository](https://github.com/nazuko)

See also the list of [contributors](https://github.com/opsilonn/m2-software-testing/graphs/contributors) who participated in this project.

Note : we are currently in our 5th year (2020-21), in a Software Engineering cursus.
