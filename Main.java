// Importacao de bibliotecas pre-instaladas java.io
import java.io.IOException;
import java.util.Scanner;
import Modules.Config;
import Modules.ServerInterface;


public class Main {
    public static void main(String[] args) {
        Config config = new Config();    // Instancia do objeto config
        // Testanto o logging
        config.log("Inicializacao do sistema!", 102, "INFO : STARTING SYSTEM");
        config.log("Inicializacao concluida", 200, "INFO : FINISHED STARTING SERVER");
        config.log("Iniciando testes", 102, "DEBUG : TESTING");
        try{
            ServerInterface servidor_nucleo = new ServerInterface();
            config.log("Iniciando teste de servidor", 102, "DEBUG : TESTING");
            servidor_nucleo.web_system();
            config.log("Servidor OK", 200, "INFO : SERVER OK");
            String line = "\n----------------------------------------------------------------------------------";
            config.log(line, 0, "DEBUG");
            Scanner scan = new Scanner(System.in);
            System.out.print("Command: ");
            config.log("Aguardando comando", 400, "WARNING : WAITING COMMAND");
            String command = scan.nextLine();

            if (command.equals("server -stop")){
                config.log("Comando recebido", 202, "INFO : RECIVED COMMAND");
                servidor_nucleo.stopServer();
                config.log("Encerrando Servidor", 100, "WARNING : STOPING SERVER");
                scan.close();
            } else if (command.equals("server -restart")){
                config.log("Comando recebido", 202, "INFO : RECIVED COMMAND");
                config.log("Reiniciando Servidor", 102, "WARNING : REBOOT SERVER");
                config.rebootServer();
                config.log("Servidor Reiniciado", 100, "INFO : REBOOTED SERVER");
            } else {
                config.log("Comando recebido", 202, "INFO : RECIVED COMMAND");
                config.log("Comando invalido ou inexistente", 400, "ERROR : INVALID COMMAND");
            }
        } catch (IOException e){
            System.err.println("Erro ao iniciar servidor: " + e.getMessage());
            config.log("Erro ao iniciar servidor", 500, "ERROR : NOT START SERVER");
        }
    }
}
