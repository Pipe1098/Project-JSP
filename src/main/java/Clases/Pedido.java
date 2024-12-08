/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;


import java.util.List;

public class Pedido {
    private static double g = 10, m = 7, p = 4;
    private int cantidad, idPedido, idUsuario;
    private double precio;
    private String tamanoPizza, total, fecha;

    // Ingredientes disponibles
    private static final String Tocineta = "Tocineta";
    private static final String Queso = "Queso";
    private static final String Peperoni = "Peperoni";
    private static final String Otro = "otro";


    private List<String> ingredientes;

    public String getFecha() {
        return fecha;
    }

    public Pedido(int idUsuario, String tamanoPizza, int cantidad, String totalPagar, List<String> ingredientes) {
        this.idUsuario = idUsuario;
        this.tamanoPizza = tamanoPizza;
        this.cantidad = cantidad;
        this.total = totalPagar;
        this.ingredientes = ingredientes;
    }

    public Pedido(int idUsuario, String tamanoPizza, int cantidad, String totalPagar, String fecha, List<String> ingredientes) {
        this.idUsuario = idUsuario;
        this.tamanoPizza = tamanoPizza;
        this.cantidad = cantidad;
        this.total = totalPagar;
        this.ingredientes = ingredientes;
        this.fecha=fecha;
    }


    public String getIngredientes() {

        if (ingredientes == null || ingredientes.isEmpty()) {
            return "Sin adiciones";
        }

        String var = ingredientes.toString();
        String var2= var.replace("["," ");
        return var2.replace("]"," ");
    }


    public void setIngredientes(List<String> ingredientes) {
        this.ingredientes = ingredientes;
    }


    public void eliminarIngrediente(String ingrediente) {
        this.ingredientes.remove(ingrediente);
    }

    // Métodos existentes
    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTamanoPizza() {
        return tamanoPizza;
    }

    public void setTamanoPizza(String tamanoPizza) {
        this.tamanoPizza = tamanoPizza;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotalImporte(String tamano, String ingredientes) {
        // Inicializar el total en 0
        double res = 0;

        // Calcular el precio según el tamaño de la pizza
        switch (tamano) {
            case "g":
                res += cantidad * g;
                break;
            case "m":
                res += cantidad * m;
                break;
            case "p":
                res += cantidad * p;
                break;
            default:
                throw new IllegalArgumentException("Tamaño de pizza inválido: " + tamano);
        }

        // Calcular el costo adicional por los ingredientes si vienen definidos
        if (ingredientes != null && !ingredientes.isEmpty()) {
            if (ingredientes.contains("Tocineta")) {
                res += 5;
            }
            if (ingredientes.contains("Queso")) {
                res += 4;
            }
            if (ingredientes.contains("Peperoni")) {
                res += 3;
            }
            if (ingredientes.contains("Otro")) {
                res += 1;
            }
        }


        this.total = String.format("%.2f", res) + "$";


        return this.total;
    }



    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
