package biblioteca;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        // 1. Crear repositorios
        RepositorioSalas repositorioSalas = new RepositorioSalasMemoria();

        RepositorioReservas repositorioReservas = new RepositorioReservasMemoria();

        // 2. Crear observadores
        ObservadorReserva notificador = new NotificadorEstudiante();

        ObservadorReserva registro = new RegistroEventos();

        // 3. Crear Facade con los observadores
        ReservaFacade facade = new ReservaFacade(
                repositorioSalas,
                repositorioReservas,
                List.of(notificador, registro)
        );

        // 4. Crear estudiante
        Estudiante estudiante = new Estudiante("123", "Carlos");

        // 5. Crear estrategia
        EstrategiaSeleccionSala estrategia = new SeleccionPorCapacidad();

        // 6. Realizar reserva
        Optional<Reserva> resultado = facade.realizarReserva(
                estudiante,
                LocalDate.of(2026, 11, 10),
                LocalTime.of(10, 0),
                2,
                4,
                estrategia
        );

        // 7. Cancelar reserva
        if (resultado.isPresent()) {

            Reserva reserva = resultado.get();
            reserva.cancelar();
            System.out.println(
                "Estado final: " + reserva.getNombreEstado()
            );
        }
    }
}