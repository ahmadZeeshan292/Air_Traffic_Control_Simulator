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

The primary goal of this project was to develop an **airplane ground surface traffic control system** using the principles of **Object-Oriented Programming (OOP)**. The emphasis was not on merely achieving output but on producing a thoughtfully architected solution. Students were encouraged to develop a flexible, extensible codebase to meet the project’s complex requirements using OOP principles like **encapsulation**, **reusability**, and **abstraction**.

---

## 🛠️ Implementation

To implement this system, we first understood the problem demands: tracking aircraft, managing their routes, optimizing paths, and coordinating safe landings while avoiding collisions. Below is a breakdown of each major class and its function in the solution.

---

### ⏱ Timer Class

The `Timer` class serves two vital purposes:
- **Global Clock:** Acts as the central clock around which the simulation runs.
- **State Controller:** Tracks the airplane’s current state based on time, allowing the traffic control system to make decisions based on real-time aircraft positions.

This functionality was critical in preventing collisions and managing timed transitions between states.

---

### ✈️ Airplane Class

The `Airplane` class is the core of the system. It replicates key aircraft behaviors:
- **Attributes:** Departure, destination, speed, status, and more.
- **Integration with Timer:** Transitions aircraft states according to time intervals.
- **Key Functions:**

#### `planedistancetime(Countries_runway_gateway_status Status)`

Determines when a plane should change its status based on predefined time intervals and current air/ground conditions. The function works closely with the traffic control system to ensure safe transitions.

#### `calculate_shortest_path()`

Uses the `greedy_alg` class to compute the most efficient route between origin and destination using weighted costs. If no valid path exists, the airplane remains grounded.

#### States Enum:
```java
enum E_AirplaneState {
  Grounded,
  Destination_runway,
  Destination_gateway,
  Departure_runway,
  Departure_gateway,
  Mid_air,
  Descending
}
