public class Pacientes {
    String cedula;
    int historial;
    String nombre;
    String apellido;
    int edad;
    String telefono;
    String descripcion;

    public Pacientes() {
    }

    public Pacientes(String cedula, String descripcion, String telefono, int edad, String apellido, String nombre, int historial) {
        this.cedula = cedula;
        this.descripcion = descripcion;
        this.telefono = telefono;
        this.edad = edad;
        this.apellido = apellido;
        this.nombre = nombre;
        this.historial = historial;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public int getHistorial() {
        return historial;
    }

    public void setHistorial(int historial) {
        this.historial = historial;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
