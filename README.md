# _WildLife Tracker_

#### By _Ben Vissotzky_

## Description

_Simple website for Rangers to track wildlife they have found in the wild. _

## Setup/Installation Requirements

* _Copy the repository from GitHub_
* _Compile the .java and run in console_

## Installation Instructions
- Download repository
- Start Postgress Database
- In PSQL:
  - CREATE DATABASE wildlife_tracker;
  - \c wildlife_tracker;
  - CREATE TABLE animals (id serial primary key, species varchar, endangered boolean, health varchar, age int);
  - CREATE TABLE sightings (id serial primary key, animal_id int, location varchar, ranger varchar);
  - CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;


## BDD

## GitHub link
https://github.com/benviss/wildlife_tracker

## Licensing

* MIT

Copyright (c) 2016 **_Ben Vissotzky_**
