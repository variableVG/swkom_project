package at.fhtw.swen3.services.dto;

import at.fhtw.swen3.persistence.HopArrival;
import at.fhtw.swen3.persistence.TrackingInformation;

import java.util.ArrayList;
import java.util.List;

public class TrackingInformationDTO {
    private TrackingInformation.StateEnum state;
    private List<HopArrival> visitedHops = new ArrayList<>();
    private List<HopArrival> futureHops = new ArrayList<>();
}
