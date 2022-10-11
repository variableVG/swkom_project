package at.fhtw.swen3.services.dto;

import at.fhtw.swen3.persistence.HopArrival;
import at.fhtw.swen3.persistence.TrackingInformation;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TrackingInformationDTO {
    private TrackingInformation.StateEnum state;
    private List<HopArrival> visitedHops = new ArrayList<>();
    private List<HopArrival> futureHops = new ArrayList<>();
}
