# Air Traffic Control Simulation Project

This project is an air traffic control simulation using dynamic GUI (Graphical User Interface). The simulation allows multiple planes to be managed in the sky, and their landing and movements are controlled by timers.

## Project Overview

The main objective of the project is to simulate the behavior of planes in an air traffic control system, where multiple planes can be in the sky at the same time, and the traffic control is managed via a timer for each plane. 

### Key Features:
- **Multiple Planes:** The system can handle multiple planes at once, each having its own behavior and state.
- **Dynamic GUI:** The project has a graphical interface that updates in real-time, allowing users to visually track the planes.
- **Timer-Based Control:** A timer is assigned to each plane, controlling actions such as movement, landing, and delays.
- **Landing Process:** During landing, the plane requires 10 seconds to land. If another plane enters the runway, the landing time is extended, and the plane goes back to its previous state until it can resume landing.

## Class Descriptions

### 1. `Plane`
This class represents a single plane in the simulation. Each plane has its own properties, such as:
- **ID:** A unique identifier for the plane.
- **Position:** Represents the plane’s position (x, y).
- **Status:** The current status of the plane (e.g., flying, landing, or landed).
- **Landing Time:** Tracks the remaining time until the plane has landed.

The `Plane` class is responsible for:
- Managing the plane’s state (flying, landing, or landed).
- Handling the timing logic for landing.
- Interacting with other planes and the runway.

### 2. `Runway`
The `Runway` class is used to represent the runway where planes land. It ensures that:
- Only one plane can land at a time.
- If another plane enters the runway during landing, it causes a delay for the first plane and the system adjusts accordingly.

### 3. `FlightControlTower`
This class acts as the central controller of the simulation. It:
- Manages the timer and coordination of planes’ landing.
- Ensures that planes interact appropriately when landing, adjusting the landing times and handling runway occupation.

### 4. `AirplaneGUI`
This class provides the graphical user interface for the project. It is responsible for:
- Displaying the planes’ positions and statuses on the screen.
- Allowing the user to interact with the planes, such as initiating landing procedures or simulating movements.

### 5. `Main`
This is the entry point of the application. It initializes the GUI, sets up the flight control system, and starts the simulation. The `Main` class runs the main event loop and handles initialization of planes and runway states.

## How It Works

1. **Initial Setup:** When the simulation starts, the GUI is created, and the `FlightControlTower` is initialized.
2. **Plane Movement:** Planes can be added dynamically to the simulation. Each plane moves according to its current state (flying, landing, or landed).
3. **Landing:** When a plane is about to land, it is given a 10-second timer to complete the process. If another plane enters the runway during this time, the first plane’s landing time is extended, and the second plane is prevented from landing until the runway is clear.
4. **Timer Management:** The system uses timers to manage the landing and movement of planes. If the landing is interrupted, the plane’s status reverts to its previous state, and the simulation continues.

## 📁 Project Structure
├── Main.java                        # main method /n
├── airplane.java                    # Airplane class (simulation logic) /n
├── States.java                      # Contains E_Countries and E_AirplaneState enums /n
├── Time.java                        # Handles stopwatch/clock logic /n
├── Countries_runway_gateway_status.java  # For status tracking/ traffic control /n
└── GUI/ /n
    └── TextfieldProperties.java     # Total interfaced code/ Airplane traffic control tower /n

## How to Run

1. Clone the repository.
2. Navigate to the project directory.
3. Compile and run the `Main` class.

```bash
javac Main.java
java Main

