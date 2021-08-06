package org.openjfx.model;

import lombok.Builder;

@Builder
public class Health {

    private boolean physicalIllness;

    private boolean mentalIllness;

    private boolean medicalMonitoring;

    private boolean c; //TODO medicamento continuo

}
