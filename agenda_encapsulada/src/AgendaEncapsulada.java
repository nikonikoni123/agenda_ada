public class AgendaEncapsulada {

    private Contacto[] contactos;          
    private int cantidad;                
    private final int capacidadInicial;    
    public AgendaEncapsulada(int capacidadInicial) {
        this.capacidadInicial = capacidadInicial;
        this.contactos = new Contacto[Math.max(1, capacidadInicial)];
        this.cantidad = 0; 
    }

    public boolean agregar(Contacto c) {
        if (c == null || !c.esValido()) {
            return false;
        }

        if (buscar(c.getEmail()) != null) {
            return false;
        }

        if (cantidad == contactos.length) {
            redimensionar();
        }
        contactos[cantidad] = c;
        cantidad++;
        return true;
    }

    public boolean eliminar(String email) {
        if (email == null) {
            return false;
        }

        int indice = -1;
        for (int i = 0; i < cantidad; i++) {
            if (contactos[i].getEmail().equalsIgnoreCase(email)) {
                indice = i;
                break;
            }
        }
        if (indice == -1) {
            return false;
        }

        for (int i = indice; i < cantidad - 1; i++) {
            contactos[i] = contactos[i + 1];
        }

        contactos[cantidad - 1] = null;
        cantidad--;
        return true;
    }

    public Contacto buscar(String email) {
        if (email == null) {
            return null;
        }
        for (int i = 0; i < cantidad; i++) {
            if (contactos[i].getEmail().equalsIgnoreCase(email)) {
                return contactos[i];
            }
        }
        return null;
    }

    public Contacto[] listar() {
        Contacto[] copia = new Contacto[cantidad];
        for (int i = 0; i < cantidad; i++) {
            copia[i] = contactos[i];
        }
        return copia;
    }

    public Contacto[] filtrarPorNombre(String prefijo) {
        if (prefijo == null) {
            return new Contacto[0];
        }
        String prefijoMin = prefijo.toLowerCase();

        int coincidencias = 0;
        for (int i = 0; i < cantidad; i++) {
            if (contactos[i].getNombre().toLowerCase().startsWith(prefijoMin)) {
                coincidencias++;
            }
        }

        Contacto[] resultado = new Contacto[coincidencias];
        int j = 0;
        for (int i = 0; i < cantidad; i++) {
            if (contactos[i].getNombre().toLowerCase().startsWith(prefijoMin)) {
                resultado[j] = contactos[i];
                j++;
            }
        }
        return resultado;
    }

    private void redimensionar() {
        Contacto[] nuevo = new Contacto[contactos.length * 2];
        for (int i = 0; i < cantidad; i++) {
            nuevo[i] = contactos[i];
        }
        contactos = nuevo;
    }

    public int getCantidad() { return cantidad; }
    public int getCapacidadInicial() { return capacidadInicial; }
}
