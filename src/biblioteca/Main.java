package biblioteca;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // Dependencias
        RepositorioSalas repositorioSalas =
                new RepositorioSalasMemoria();

        RepositorioReservas repositorioReservas =
                new RepositorioReservasMemoria();

        ReservaFacade facade = new ReservaFacade(
                repositorioSalas,
                repositorioReservas,
                List.of(
                    new NotificadorEstudiante(),
                    new RegistroEventos()
                )
        );

        Estudiante estudiante =
                new Estudiante("123", "Carlos");

        EstrategiaSeleccionSala estrategia =
                new SeleccionPorCapacidad();

        // Crear primera reserva
        Reserva reserva1 = facade.realizarReserva(
                estudiante,
                LocalDate.of(2026, 11, 10),
                LocalTime.of(10, 0),
                2,
                4,
                estrategia
        ).orElseThrow();

        System.out.println("\nPRUEBA 1");
        System.out.println("Estado: " + reserva1.getNombreEstado());

        // PRUEBA 2: Cancelar una confirmada
        System.out.println("\nPRUEBA 2");
        reserva1.cancelar();
        System.out.println("Estado: " + reserva1.getNombreEstado());

        // PRUEBA 3: Confirmar una cancelada
        System.out.println("\nPRUEBA 3");

        try {
            reserva1.confirmar();
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Crear segunda reserva
        Reserva reserva2 = facade.realizarReserva(
                estudiante,
                LocalDate.of(2026, 11, 11),
                LocalTime.of(14, 0),
                2,
                4,
                estrategia
        ).orElseThrow();

        // PRUEBA 4: Finalizar una confirmada
        System.out.println("\nPRUEBA 4");
        reserva2.finalizar();
        System.out.println("Estado: " + reserva2.getNombreEstado());

        // PRUEBA 5: Cancelar una finalizada
        System.out.println("\nPRUEBA 5");

        try {
            reserva2.cancelar();
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println(
            "Estado final reserva 1: " + reserva1.getNombreEstado()
        );

        System.out.println(
            "Estado final reserva 2: " + reserva2.getNombreEstado()
        );
    }
}