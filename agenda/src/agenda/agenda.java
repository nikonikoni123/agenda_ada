package agenda;

public class agenda {

    private static final int NOMBRE = 0;
    private static final int EMAIL = 1;
    private static final int FAVORITO = 2;

    private String[][] contactos;
    private int cantidad;
    private int capacidad;

    public agenda(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor a 0");
        }
        this.capacidad = capacidad;
        this.contactos = new String[capacidad][];
        this.cantidad = 0;
    }

    public boolean agregar(String[] c) {
        if (c == null || estaLlena() || !emailValido(c[EMAIL])) {
            return false;
        }
        contactos[cantidad] = c;
        cantidad++;
        return true;
    }

    public boolean eliminar(String nombre) {
        int pos = posicionDe(nombre);
        if (pos == -1) {
            return false;
        }
        for (int i = pos; i < cantidad - 1; i++) {
            contactos[i] = contactos[i + 1];
        }
        cantidad--;
        contactos[cantidad] = null; 
        return true;
    }

    public String[] buscar(String nombre) {
        int pos = posicionDe(nombre);
        return pos == -1 ? null : contactos[pos];
    }

    public void listar() {
        StringBuilder lista = new StringBuilder();
        for (int i = 0; i < cantidad; i++) {
            lista.append(String.join(" | ", contactos[i])).append("\n");
        }
        System.out.print(estaVacia() ? "La agenda está vacía.\n" : lista.toString());
    }

    public void listarFavoritos() {
        StringBuilder lista = new StringBuilder();
        for (int i = 0; i < cantidad; i++) {
            lista.append(esFavorito(contactos[i]) ? String.join(" - ", contactos[i]) + "\n" : "");
        }
        System.out.print(contarFavoritos() == 0 ? "No hay contactos favoritos.\n" : lista.toString());
    }

    public int contarFavoritos() {
        int favoritos = 0;
        for (int i = 0; i < cantidad; i++) {
            favoritos += esFavorito(contactos[i]) ? 1 : 0;
        }
        return favoritos;
    }

    public boolean estaLlena() {
        return cantidad == capacidad;
    }

    public boolean estaVacia() {
        return cantidad == 0;
    }

    private int posicionDe(String nombre) {
        int pos = -1;
        for (int i = 0; i < cantidad && pos == -1; i++) {
            pos = (nombre != null && nombre.equalsIgnoreCase(contactos[i][NOMBRE])) ? i : -1;
        }
        return pos;
    }

    private boolean esFavorito(String[] c) {
        return Boolean.parseBoolean(c[FAVORITO]);
    }

    private boolean emailValido(String email) {
        return email != null && email.matches("[^@\\s]+@[^@\\s]+\\.[^@\\s]+");
    }
}
