public class Main {

    public static void main(String[] args) {
        AgendaEncapsulada agenda = new AgendaEncapsulada(2);

        check("agregar Ana", agenda.agregar(new Contacto("Ana", "ana@mail.com", "111")));
        check("agregar Andrés", agenda.agregar(new Contacto("Andrés", "andres@mail.com", "222")));
        check("agregar Beto (array lleno, redimensiona)", agenda.agregar(new Contacto("Beto", "beto@mail.com", "333")));
        check("agregar Carla", agenda.agregar(new Contacto("Carla", "carla@mail.com", "444")));
        check("cantidad = 4", agenda.getCantidad() == 4);
        check("rechaza null (regla 1)", !agenda.agregar(null));
        check("rechaza inválido (regla 1)", !agenda.agregar(new Contacto("", "sin-arroba", "000")));
        check("rechaza email duplicado (regla 2)", !agenda.agregar(new Contacto("Otra Ana", "ANA@mail.com", "999")));
        check("cantidad sigue en 4", agenda.getCantidad() == 4);
        Contacto beto = agenda.buscar("beto@mail.com");
        check("buscar Beto", beto != null && beto.getNombre().equals("Beto"));
        check("buscar inexistente devuelve null", agenda.buscar("nadie@mail.com") == null);
        Contacto[] copia = agenda.listar();
        check("listar tamaño exacto", copia.length == 4);
        check("modificar la copia NO afecta el interno", agenda.listar()[0] != null);
        check("filtrar 'an' da 2 (Ana, Andrés)", agenda.filtrarPorNombre("an").length == 2);
        check("filtrar 'z' da 0", agenda.filtrarPorNombre("z").length == 0);
        check("eliminar Andrés", agenda.eliminar("andres@mail.com"));
        check("cantidad = 3", agenda.getCantidad() == 3);
        check("Beto se corrió a la posición 1", agenda.listar()[1].getNombre().equals("Beto"));
        check("eliminar inexistente devuelve false", !agenda.eliminar("nadie@mail.com"));

        System.out.println("\nContactos actuales");
        for (Contacto c : agenda.listar()) {
            System.out.println("  " + c);
        }
    }

    private static void check(String descripcion, boolean ok) {
        System.out.println((ok ? "OK    " : "FALLA ") + descripcion);
    }
}
