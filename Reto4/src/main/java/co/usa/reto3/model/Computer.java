package co.usa.reto3.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * La entidad computer representa una tabla de la base de datos
 * que recibe distintos argumentos que establecen los valores
 * necesarios para representar un objeto computer en una tabla
 * de datos que pertenece a una base de datos relacional.
 *
 * @author
 * - Laura Ramirez
 * - Christian Prada
 */
@Entity
@Table(name = "computer")
public class Computer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; /** numero identificacion del computador */
    private String name; /** nombre asignado al computador */
    private String brand; /** marca del fabricante */
    private Integer year; /** año de fabricacion */
    private String description; /** descripcion del computador */

    @ManyToOne /* relacion muchos a uno con Category */
    @JoinColumn(name="computerCategory") /* columna de union con category */
    @JsonIgnoreProperties({"computers", "client"}) /* campos ignorados en la llamada */
    private Category category; /** categoria asignada al computador */

    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "computer") /* relacion uno a muchos con Message */
    @JsonIgnoreProperties("computer") /* columna de union con messages */
    public List<Message> messages; /** lista de mensajes asignados al computador */

    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "computer") /* relacion uno a muchos con Reservation */
    @JsonIgnoreProperties("computer") /* campos ignorados en la llamada */
    public List<Reservation> reservations; /** reservaciones asignadas al computador */

    /**
     * Retorna numero de identificacion del computador.
     *
     * @return - Integer - id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el numero de identificacion del
     * computador.
     *
     * @param id - void
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retorna el nombre del computador.
     *
     * @return - String - name
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre del computador.
     *
     * @param name - void
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retorna la marca del computador.
     *
     * @return - String - brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Establece la marca del computador.
     *
     * @param brand - void
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Retorna el año de fabricacion del
     * computador.
     *
     * @return - Integer - year
     */
    public Integer getYear() {
        return year;
    }

    /**
     * Establece el año de fabricacion del
     * computador.
     *
     * @param year - void
     */
    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     * Retorna la categoria del computador.
     *
     * @return - Category - category
     */
    public Category getCategory() { return category; }

    /**
     * Establece la categoria del computador.
     *
     * @param category - void
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Retorna la descripcion del computador.
     *
     * @return - String - description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Establece la descripcion del computador.
     *
     * @param description - void
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retorna la lista de mensajes asignados
     * al computador.
     *
     * @return - List<Message> - messages
     */
    public List<Message> getMessages() { return messages; }

    /**
     * Establece los mensajes asignados
     * al computador.
     *
     * @param messages - void
     */
    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    /**
     * Retorna las reservaciones asignadas
     * al computador.
     *
     * @return - List<Reservation> - reservation
     */
    public List<Reservation> getReservations() { return reservations; }

    /**
     * Establece las reservaciones asignadas
     * al computador.
     *
     * @param reservations - void
     */
    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
