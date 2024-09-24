package Modules;

// Importacao de bibliotecas pre-instaladas java.io
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
// Importacao de bibliotecas pre-instaladas java.util
import java.util.ArrayList;
import java.util.List;

// Objeto que armazena dados antes de registra-los no data base
public class Data_System{
    Config config = new Config();
    private String[] keys = {
        "investimento", "investimentos", "investir"
    };
    private String csv_file = ".\\userData.csv";

    public String get_key(int index){
        return this.keys[index];
    }
    public String showDataUser(String username, int index) {
        List<String[]> linhasCSV = new ArrayList<>();
        String divisor = ";";
    
        // Ler o arquivo CSV
        try (BufferedReader br = new BufferedReader(new FileReader(this.csv_file))) {
            String linha;
    
            while ((linha = br.readLine()) != null) {
                String[] valores = linha.split(divisor);
                linhasCSV.add(valores);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Erro ao ler o arquivo CSV";
        }
    
        // Procurar pelo nome de usuário na coluna correta
        for (String[] linha : linhasCSV) {
            // Verificar se a linha tem a coluna de username (posição 1)
            if (linha.length > 1 && linha[1].equals(username)) {
                // Verificar se o índice solicitado é válido para esta linha
                if (index < linha.length) {
                    return linha[index];
                } else {
                    return "Índice fora dos limites para este usuário";
                }
            }
        }
        return "Nenhum dado encontrado";
    }
    
    public double showDataUserNumbers(int id_username, int index) {
        List<String[]> linhasCSV = new ArrayList<>();
        String divisor = ";";
    
        try (BufferedReader br = new BufferedReader(new FileReader(this.csv_file))) {
            String linha;
    
            while ((linha = br.readLine()) != null) {
                String[] valores = linha.split(divisor);
                linhasCSV.add(valores);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        for (String[] linha : linhasCSV) {
            try {
                // Converte a coluna de ID (linha[0]) para inteiro para fazer a comparação
                int idUsuarioCsv = Integer.parseInt(linha[0]);
                if (idUsuarioCsv == id_username) {
                    try {
                        // Converte o valor da coluna 'index' para double e retorna
                        return Double.parseDouble(linha[index]);
                    } catch (NumberFormatException e) {
                        config.log("Erro ao converter valor para double: " + linha[index], 500, "ERROR : NOT CONVERTED");
                        return 0.0;  // Valor padrão no caso de erro de conversão
                    }
                }
            } catch (NumberFormatException e) {
                config = new Config();
                config.log("Erro ao converter ID de usuario para CSV", 400, "ERROR : NO REGISTER IN CSV");
            }
        }
        
        System.err.println("Nenhum dado encontrado para o usuário: " + id_username);
        return 0.0;  // Valor padrão no caso de usuário não encontrado
    }    
    
    public Boolean validationForID(int id_user){
        List<String[]> linhasCSV = new ArrayList<>();
        String divisor = ";";

        try (BufferedReader br = new BufferedReader(new FileReader(this.csv_file))){
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] valores = linha.split(divisor);
                linhasCSV.add(valores);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        for (String[] linha : linhasCSV) {
            if (linha[0].equals(id_user)){
                return true;
            }
        }
        return false;
    }
    public Boolean validationForUser(String username){
        List<String[]> linhasCSV = new ArrayList<>();
        String divisor = ";";

        try (BufferedReader br = new BufferedReader(new FileReader(this.csv_file))){
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] valores = linha.split(divisor);
                linhasCSV.add(valores);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        for (String[] linha : linhasCSV) {
            if (linha[1].equals(username)){
                return true;
            }
        }
        return false;
    }
    public Boolean validationForPassword(String password){
        List<String[]> linhasCSV = new ArrayList<>();
        String divisor = ";";

        try (BufferedReader br = new BufferedReader(new FileReader(this.csv_file))){
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] valores = linha.split(divisor);
                linhasCSV.add(valores);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        for (String[] linha : linhasCSV) {
            if (linha[3].equals(password)){
                return true;
            }
        }
        return false;
    }
}