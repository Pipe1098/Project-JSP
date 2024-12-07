/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;


public class Usuario {
    private String nombre, direccion, telefono,cpostal;
    private int id;
    public Usuario(){
    
    }
    public Usuario(int i, String n, String d, String cp, String t){
        id=i;
        nombre=n;
        direccion=d;
        cpostal=cp;
        telefono=t;
    }
        public Usuario(String n, String d, String cp, String t){
        nombre=n;
        direccion=d;
        cpostal=cp;
        telefono=t;
    }
    public Usuario(String t){
        telefono=t;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the cpostal
     */
    public String getCpostal() {
        return cpostal;
    }

    /**
     * @param cpostal the cpostal to set
     */
    public void setCpostal(String cpostal) {
        this.cpostal = cpostal;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public String toString(){
        return telefono + " - " + cpostal;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
}
