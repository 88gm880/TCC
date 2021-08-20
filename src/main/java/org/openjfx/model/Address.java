package org.openjfx.model;

import lombok.*;

@Builder
@Setter @Getter
@NoArgsConstructor @AllArgsConstructor
public class Address {

    private String street;

    private String number;

    private String district;

    private String complement;

}
