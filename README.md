# :taxi: Implementation of a smart taxi navigation system using A* Algorithm.

Given the position of a client and some taxis, the scope of this project is to find the closest taxi to the client and the best
route that corresponds to this taxi. 


### Table of contents:

- __TN2018-T1.pdf:__ Description of the problem.

- __client.csv:__ This file contains the coordinates of the location of each client in the following form:

<p align="center">X, Y <br>
  23.733912, 37.975687 <br>
  where X is the longitude and Y is the latitude.</p>

- __taxis.csv:__ This file contains the coordinates of the location and the ids of all taxis that are available in the following form:

  <p align="center">X, Y, id <br>
    23.741587, 37.984125, 100 <br>
  where X is the longitude, Y is the latitude and id the id of the taxi.</p>

- __nodes.csv:__ This file contains the coordinates of some parts of the streets. Each street is identified by an id and, therefore, parts with the same id exist in the same street.
