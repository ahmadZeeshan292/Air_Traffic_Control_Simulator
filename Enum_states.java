class States{
    enum E_AirplaneState{
        Grounded,
        Destination_runway,
        Destination_gateway,
        Departure_runway,
        Departure_gateway,
        Mid_air,
        Descending;
    }
    enum E_Countries{
        none,
        Pakistan,
        Indonasia,
        Iceland,
        Europe,
        Australia,
        NZ;
    }
    enum E_RunwayState{
        Unoccupied,
        Occupied;
    }
    enum E_GatewayState{
        Unoccupied,
        Occupied;
    }
}
