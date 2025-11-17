package Modelo;

import java.io.Serializable;
/**
 * Define un contrato para todas las clases que representan un ítem
 * o entidad que tiene un monto de pago asociado.
 * * Implementa Serializable para permitir que los objetos de este tipo
 * puedan ser serializados (guardados o transferidos).
 */
public interface PorPagar extends Serializable{
    public double ObtenerMontoPago();
}
