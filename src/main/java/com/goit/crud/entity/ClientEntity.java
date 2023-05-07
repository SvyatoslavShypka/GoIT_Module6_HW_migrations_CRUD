package com.goit.crud.entity;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class ClientEntity {
    private Long clientId;
    private String clientName;
}
