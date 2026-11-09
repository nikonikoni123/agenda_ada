import agenda.*;

public class App {
    public static void main(String[] args) throws Exception {
        agenda Agenda = new agenda(10);
        Agenda.agregar(new String[]{"Ana","ana@gmail.com","false"});
        Agenda.agregar(new String[]{"Juan","juan@gmail.com","true"});
        Agenda.agregar(new String[]{"Pedro","pedro@gmail.com","false"});
        Agenda.listar();
    }
}
