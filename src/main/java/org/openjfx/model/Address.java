package org.openjfx.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
public class Address {

    @Getter @Setter
    private String street;

    @Getter @Setter
    private String number;

    @Getter @Setter
    private String district;

    @Getter @Setter
    private String complement;

}
