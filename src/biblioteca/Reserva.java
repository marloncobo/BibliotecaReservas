package biblioteca;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reserva {
    private final String codigo;
    private final Estudiante estudiante;
    private final LocalDate fecha;
    private final LocalTime horaInicio;
    private final int duracionHoras;
    private final int cantidadPersonas;
    private final Sala sala;
    private EstadoReserva estado;

    Reserva(String codigo, Estudiante estudiante, LocalDate fecha, LocalTime horaInicio, int duracionHoras, int cantidadPersonas, Sala sala, EstadoReserva estado){
        this.codigo = codigo;
        this.estudiante = estudiante;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.duracionHoras = duracionHoras;
        this.cantidadPersonas = cantidadPersonas;
        this.sala = sala;
        this.estado = estado;
    }

    void cambiarEstado(EstadoReserva nuevoEstado){
        this.estado = nuevoEstado;
    }

    public void confirmar(){
        estado.confirmar(this);
    }

    public void cancelar(){
        estado.cancelar(this);
    }

    public void finalizar(){
        estado.finalizar(this);
    }

    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getDuracionHoras() {
        return duracionHoras;
    }

    
    public Estudiante getEstudiante() {
        return estudiante;
    }
    
    public LocalDate getFecha() {
        return fecha;
    }
    
    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public Sala getSala() {
        return sala;
    }
    
    public EstadoReserva getNombreEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return "Reserva " + codigo + " | " + estudiante.getNombre()
            + " | " + fecha + " " + horaInicio + " | " + sala
            + " | Estado: " + getNombreEstado();
    }
}
