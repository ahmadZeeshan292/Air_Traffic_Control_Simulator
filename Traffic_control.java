class Countries_runway_gateway_status {

    Map<States.E_Countries, Map<States.E_GatewayState, airplane>> GatewayStatus = new HashMap<>();
    Map<States.E_Countries, Map<States.E_RunwayState, airplane>> RunwayStatus = new HashMap<>();
    
    public Countries_runway_gateway_status() {
        for (States.E_Countries country : States.E_Countries.values()) {
            GatewayStatus.put(country, new HashMap<>());
            RunwayStatus.put(country, new HashMap<>());

            GatewayStatus.get(country).put(States.E_GatewayState.Unoccupied, null); 
            RunwayStatus.get(country).put(States.E_RunwayState.Unoccupied, null); // we initial all countries with null airplane
        }
    }

    public void updateGatewayStatus(States.E_Countries country, States.E_GatewayState status, airplane airplane) {
    	if(getOccupyingAirplane_Gateway(country) == null) {
        GatewayStatus.get(country).put(status, airplane);
    	}else {
            if(getOccupyingAirplane_Gateway(country) != airplane){
    		System.out.println("Permission invoked airplane exists in the Gateway Country: "+country);
            }
    	}
    }

    public void updateRunwayStatus(States.E_Countries country, States.E_RunwayState status, airplane airplane) {
    	if(getOccupyingAirplane_Runway(country) == null) {
        RunwayStatus.get(country).put(status, airplane);
        }else {
    	    if(getOccupyingAirplane_Runway(country) != airplane){
    		System.out.println("Permission invoked airplane exists in the Runway Country: "+country);
            }
    	}
    }
    
    public void removeAirplaneGateway(States.E_Countries country, airplane airplane) {
        Map<States.E_GatewayState, airplane> gatewayMap = GatewayStatus.get(country);
        for (Map.Entry<States.E_GatewayState, airplane> entry : gatewayMap.entrySet()) {
            if (entry.getValue() == airplane) {
                gatewayMap.remove(entry.getKey());
                break;
            }
        }
    }
    
    public void removeAirplaneRunway(States.E_Countries country, airplane airplane) {
        Map<States.E_RunwayState, airplane> runwayMap = RunwayStatus.get(country);
        for (Map.Entry<States.E_RunwayState, airplane> entry : runwayMap.entrySet()) {
            if (entry.getValue() == airplane) {
                runwayMap.remove(entry.getKey());
                break;
            }
        }
    } 
    public boolean isGatewayOccupied(States.E_Countries country) {
        return GatewayStatus.get(country).get(States.E_GatewayState.Occupied) != null;
    }

    public boolean isRunwayOccupied(States.E_Countries country) {
        return RunwayStatus.get(country).get(States.E_RunwayState.Occupied) != null;
    }

    public airplane getOccupyingAirplane_Gateway(States.E_Countries country) {
        return GatewayStatus.get(country).get(States.E_GatewayState.Occupied);
    }
    
    public airplane getOccupyingAirplane_Runway(States.E_Countries country) {
        return RunwayStatus.get(country).get(States.E_RunwayState.Occupied);
    }
}
