package Modules;

// Importacao de bibliotecas pre-instaladas java.io
import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
// Importacao de bibliotecas pre-instaladas java.util
import java.util.ArrayList;
import java.util.List;
// Importacao de bibliotecas pre-instaladas java.time
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Objeto que sera responsavel pelo registro de logs de eventos e erros
public class Config {
    // Local onde o arquivo log sera armazenado
    private final String LOG_FILE = "C:\\Users\\vinic\\OneDrive\\Área de Trabalho\\Software Enginee\\Java\\Sirius 3.1\\events.log";
    // Metodo para registrar o log
    public void log(String msg, int cod, String status){
        try (FileWriter fileWriter = new FileWriter(this.LOG_FILE, true);) {
            PrintWriter printWriter = new PrintWriter(fileWriter);
            write_log(printWriter, msg, cod, status);
        } catch (IOException e){
            System.err.println("Erro ao escrever arquivo log: " + e.getMessage());
        }
    }
    // Metodo que configura o registro de log
    public static void write_log(PrintWriter printWriter, String msg, int code, String status){
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        printWriter.printf("%s - %s - %d - %s%n", timestamp, status, code, msg);
    }

    public void writeToCSV(String newValue, int columnIndex, int rowIndex) throws IOException {
        File csvFile = new File(".\\userData.csv");
        List<String[]> lines = new ArrayList<>();
    
        // Ler o arquivo CSV
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line.split(";"));
            }
        }  

        // Atualizar o valor específico
        if (rowIndex < lines.size()) {
            String[] row = lines.get(rowIndex);
            if (columnIndex < row.length) {
                // Verificar se o valor é monetário e aplicar a formatação necessária
                if (isMonetaryValue(newValue)) {
                    newValue = roundMonetaryValue(newValue);
                }
                row[columnIndex] = newValue;
            }
        }
    
        // Escrever de volta para o arquivo CSV
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile))) {
            for (String[] row : lines) {
                bw.write(String.join(";", row));
                bw.newLine();
            }
        }
    }

    private boolean isMonetaryValue(String value) {
        // Verifica se o valor é um número monetário com base em padrões simples
        return value.matches("R\\$\\s?\\d+\\.\\d{2}") || value.matches("\\d+\\.\\d{2}");
    }

    private String roundMonetaryValue(String value) {
        try {
            // Remove "R$ " se existir e troca ponto por vírgula para o formato correto
            String cleanedValue = value.replace("R$ ", "").replace('.', ',');
            double numberValue = Double.parseDouble(cleanedValue.replace(',', '.'));
            // Arredonda o valor e remove os centavos
            numberValue = Math.floor(numberValue); // Remove os centavos, arredondando para baixo
            cleanedValue = String.format("R$ %.0f", numberValue); // Formata o valor sem centavos
            return cleanedValue.replace('.', ','); // Troca ponto por vírgula para o formato brasileiro
        } catch (NumberFormatException e) {
            return value; // Se não for um valor monetário válido, retorna o valor original
        }
    }
    public void ServerOnOff(int command) throws IOException{
        ServerInterface server = new ServerInterface();
        if (command == 1){
            server.web_system();
        } else if (command ==2) {
            rebootServer();
        } else {
            server.stopServer();
        }
    }
    public void rebootServer() throws IOException {
        ServerInterface server = new ServerInterface();
        log("Reiniciando Servidor", 102, "WARNING : REBOOT SERVER");
        server.stopServer();
        server.web_system();
        log("Servidor Reiniciado", 100, "INFO : REBOOTED SERVER");
    }
}
