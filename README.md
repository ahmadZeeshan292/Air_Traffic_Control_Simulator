# ✈️ Airport Surface Traffic Control System

![Java](https://img.shields.io/badge/language-Java-blue.svg)
![OOP](https://img.shields.io/badge/paradigm-OOP-orange.svg)
![Lucidchart UML](https://img.shields.io/badge/UML-Lucidchart-blueviolet.svg)

---

## 📑 Table of Contents

- [Abstract](#abstract)
- [Implementation](#implementation)
  - [Timer Class](#timer-class)
  - [Airplane Class](#airplane-class)
  - [Traffic Control Class](#traffic-control-class)
  - [Greedy Algorithm Class](#greedy-algorithm-class)
  - [Main Class](#main-class)
- [UML Diagram](#uml-diagram)
- [Project Highlights](#project-highlights)
- [Authors](#authors)

---

## 🧠 Abstract

The primary objective of this project was to develop an airport surface traffic control system using object-oriented programming principles. The focus was on designing a flexible and maintainable architecture using concepts such as encapsulation, abstraction, and reusability. Rather than simply producing output, the goal was to apply software engineering principles in solving real-world air traffic control problems involving conflict resolution and efficient routing.

---

## 🛠️ Implementation

The project simulates air traffic on the ground, managing aircraft states, resolving collisions, and determining optimal routes. Each component plays a distinct role in achieving these tasks.

---

### ⏱ Timer Class

The Timer class functions as a global clock that regulates the entire system. It tracks airplane positions over time and is crucial for determining aircraft status transitions. It also serves as the basis for the collision detection mechanism by helping the traffic control class understand where every plane is at any given moment.

---

### ✈️ Airplane Class

The Airplane class is the centerpiece of the project. It holds all aircraft-related properties like departure, destination, speed, and status. It works in close composition with the Timer class to determine when and how an airplane should change its status. 

Key functions include:

- **planedistancetime** – This function handles airplane status transitions based on time intervals. It also checks with the traffic control class to ensure the next state can be safely entered.
  
- **calculate_shortest_path** – This function determines the most efficient route between departure and destination using the greedy algorithm class.

Airplane status can be one of the following:
- Grounded
- Departure Gateway
- Departure Runway
- Mid Air
- Descending
- Destination Runway
- Destination Gateway

These states help model the real-world journey of a plane on ground and in air.

---

### 🚦 Traffic Control Class

The traffic control system ensures that no two airplanes occupy the same taxiway or runway simultaneously. It tracks the status of each country’s runway and gateway using occupancy flags. If an airplane attempts to enter an occupied path, it is instructed to retain its current state.

The system uses data structures that map each country to:
- Runway Status (Occupied / Unoccupied)
- Gateway Status (Occupied / Unoccupied)
- Currently occupying airplane (if any)

This allows the system to safely manage airspace and taxi routes in a coordinated manner.

---

### 🧮 Greedy Algorithm Class

This class is responsible for determining the shortest path between any two countries using Dijkstra's algorithm. The system is configured with a set of known connections between countries, each with a cost value. If a direct path does not exist, it calculates the best indirect path available.

Example path setup:
- NZ → Australia (cost: 500)
- Australia → Europe (cost: 1000)
- Europe → Iceland (cost: 300)
- Pakistan → Australia (cost: 300)
- Indonesia → Iceland (cost: 600)

This helps ensure airplanes follow the most efficient routes possible.

---

### 🖥️ Main Class

This is where the full program comes together. It launches the GUI, sets up airplanes based on user input, and manages background threads for smooth simulation.

**GUI Setup:**
- The user is prompted to input departure and destination.
- After input, a dynamic panel shows each airplane’s speed, current state, and clock in real time.

**Threading:**
- GUI refreshes every 3 seconds.
- Airplane clocks and speed update every 1 second.
- Proper thread control avoids stacking, which can lead to issues like seeing clock outputs such as: 1, 3, 7, 15...

The main class ensures smooth parallel updates and resolves concurrency issues to maintain system stability.

---

## 🧩 UML Diagram

🔗 [Click here to view the UML Class Diagram on Lucidchart](https://lucid.app/lucidchart/2bbdbb34-8e03-4c6b-a953-9195f021a7cf/edit?viewport_loc=-2047%2C-1627%2C7140%2C3302%2CHWEp-vi-RSFO&invitationId=inv_6956009a-901f-403d-b440-7c6258423f85)

---

## 🚀 Project Highlights

- ✅ Applied core OOP concepts: abstraction, encapsulation, and composition
- ✅ Designed modular architecture for real-time traffic simulation
- ✅ Real-time aircraft state transitions using timers
- ✅ Collision detection and resolution logic
- ✅ Shortest-path routing with Dijkstra’s algorithm
- ✅ Dynamic Java GUI for real-time updates
- ✅ Efficient multithreading and concurrency control

---

