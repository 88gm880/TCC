package org.openjfx.model;

import lombok.*;

@Builder
@NoArgsConstructor @AllArgsConstructor
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
