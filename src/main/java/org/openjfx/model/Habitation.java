package org.openjfx.model;

import lombok.Builder;

@Builder
public class Habitation {

    private String residenceKind;

    private boolean sewer;

    private boolean privateEletricity;

    private boolean privatePipedWater;

    private String buildingType;

    private int rooms;

    private int bedrooms;

}
