package Controlador;

import DAO.ReportesDAO;
import Modelo.ModeloDetalleVenta;
import Modelo.ModeloReporteVentasPeriodo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Prueba unitaria para ControladorReportes.
 * Usa Mockito para simular (mock) la capa DAO.
 */
@ExtendWith(MockitoExtension.class)
public class ControladorReportesTest {

    @Mock
    private ReportesDAO daoImpostor;

    @InjectMocks
    private ControladorReportes controlador;

    @Test
    public void testGenerarReportePorFecha_CalculaTotalCorrectamente() throws Exception {
        System.out.println("probando la lógica de cálculo en ControladorReportes");
        List<ModeloDetalleVenta> listaFalsa = new ArrayList<>();
        ModeloDetalleVenta item1 = new ModeloDetalleVenta();
        item1.setSubtotal(100.0);
        listaFalsa.add(item1);
        ModeloDetalleVenta item2 = new ModeloDetalleVenta();
        item2.setSubtotal(50.5);
        listaFalsa.add(item2);
        when(daoImpostor.generarReportePorFecha(any(Date.class), any(Date.class)))
            .thenReturn(listaFalsa);
        ModeloReporteVentasPeriodo reporte = controlador.generarReportePorFecha(new Date(), new Date());
        assertNotNull(reporte);
        assertEquals(150.5, reporte.getTotalGeneral(), "El totalGeneral debe ser la suma de los subtotales (100.0 + 50.5)");
        assertEquals(2, reporte.getItems().size(), "La lista de items debe ser la misma que la del DAO");
    }
}