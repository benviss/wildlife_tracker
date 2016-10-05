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
  - CREATE TABLE animals (id serial primary key, species varchar, endangered boolean, health varchar, age varchar);
  - CREATE TABLE sightings (id serial primary key, animal_id int, location varchar, ranger varchar, seen bigint, endangered varchar);
  - CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;


## BDD
- Create new Animal: "Deer"
- Create new Endangered Animal: "Polar Bear?"
  -Enter Health for Endangered Animal: "Healthy"
  -Enter Age for Endangered Animal: "Young"
- Create Sighting: "seen by: Rick, found at: creek, animal: deer"
- Update information for Sighting Ranger: "Ranger Rick"
- Update information for Sighting Location: "River"
- Delete Animals: delete "Deer", "Polar Bear?"
- Delete Sighting by ID: delete sighting of Deer by Ranger rick by River

## GitHub link
https://github.com/benviss/wildlife_tracker

## Licensing

* MIT

Copyright (c) 2016 **_Ben Vissotzky_**
