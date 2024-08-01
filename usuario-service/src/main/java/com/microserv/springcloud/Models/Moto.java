package com.microserv.springcloud.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Moto {
    private String marca;
    private String modelo;
    private Integer usuarioId;
}
