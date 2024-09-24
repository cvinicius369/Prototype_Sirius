package Modules;

// Importacao de bibliotecas pre-instaladas java.io
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
// Importacao de bibliotecas pre-instaladas java.util
import java.util.Map;
import java.util.HashMap;
// Importacao de bibliotecas pre-instaladas java.net
import java.net.InetSocketAddress;
import java.net.URLDecoder;
// Importacao de bibliotecas pre-instaladas java.nio
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
// Importacao de bibliotecas pre-instaladas com.sun.net
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
// Importacao de bibliotecas text
import java.text.DecimalFormat;
// Modules

public class ServerInterface {
    private HttpServer server;
    Config config = new Config();

    public void web_system() throws IOException{
        Data_System dataSystem = new Data_System();
        config.log("Criando servidor", 102, "INFO : CREATE SERVER");
        try {
            server = HttpServer.create(new InetSocketAddress(8080), 0); // Novo servidor na porta 8080
            config.log("Servidor Criado com sucesso", 201, "INFO : SERVER CREATED");
        } catch (IOException e){
            
        }
        // manipulador para rota "/"
        server.createContext("/", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                File new_file = new File("C:\\Users\\vinic\\OneDrive\\Área de Trabalho\\Software Enginee\\Java\\Sirius 3.1\\index.html");
                byte[] response = Files.readAllBytes(new_file.toPath());
                config.log("Acessando Pagina Raiz", 302, "INFO : REDIRECT TO ROOT PAGE");
                exchange.sendResponseHeaders(200, response.length);
                OutputStream os = exchange.getResponseBody();
                os.write(response);
                os.close();
            }
        });
        // manipulador para rota "/submit"
        server.createContext("/home", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String response;
                String login = "";
                String password = "";
                boolean isAuthenticated = false; // Variável para verificar se o usuário foi autenticado
                // String correctLogin = "admin"; // Login correto (pode ser armazenado em um banco de dados)
                // String correctPassword = "1234"; // Senha correta (pode ser armazenada em um banco de dados)
        
                if ("POST".equals(exchange.getRequestMethod())) {
                    // Ler o corpo da requisição (dados enviados pelo formulário)
                    InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
                    BufferedReader br = new BufferedReader(isr);
                    StringBuilder buf = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        buf.append(line);
                    }
                    String requestBody = buf.toString();
        
                    // Processar o corpo para obter login e password
                    Map<String, String> params = parseFormData(requestBody);
                    login = params.getOrDefault("login", "");
                    password = params.getOrDefault("password", "");
                    config.log("Dados de Acesso Recbidos", 200, "WARNING : DATA COLLECTED");

                    if (dataSystem.validationForUser(login) == true && dataSystem.validationForPassword(password)){
                        isAuthenticated = true;
                        config.log("Dados de acesso validos", 200, "INFO : SUCESS ACCESS");
                    } else {
                        config.log("Dados de acesso invalidos", 403, "ERROR : ACCESS DENIED");
                    }
                    config.log("Dados de acesso \nLogin: " + login + "\nPassword: " + password + "\nLiberacao: " + isAuthenticated, 200, "INFO : ACCESS DATA");
                }
        
                if (isAuthenticated) {
                    String[] listData = { 
                        dataSystem.showDataUser(login, 0), dataSystem.showDataUser(login, 1), dataSystem.showDataUser(login, 2),
                        dataSystem.showDataUser(login, 3), dataSystem.showDataUser(login, 4), dataSystem.showDataUser(login, 5),
                        dataSystem.showDataUser(login, 6), dataSystem.showDataUser(login, 7), dataSystem.showDataUser(login, 8),
                        dataSystem.showDataUser(login, 9), dataSystem.showDataUser(login, 10)
                    };
                    config.log("Acessando /home", 302, "INFO : REDIRECT TO HOME");
                    response = Homepage.homepage(listData);

                } else {
                    config.log("Dados errados, verificar informacao", 401, "ERROR : ACCESS DENIED");
                    String[] userAcess = { login, password };
                    response = Homepage.loginUser(userAcess);
                }
                byte[] responseBytes = response.getBytes("UTF-8");
                exchange.sendResponseHeaders(200, responseBytes.length);
                OutputStream os = exchange.getResponseBody();
                os.write(responseBytes);
                os.close();
            }
            // Função para analisar os dados do formulário
            private Map<String, String> parseFormData(String formData) {
                Map<String, String> params = new HashMap<>();
                String[] pairs = formData.split("&");
                for (String pair : pairs) {
                    String[] keyValue = pair.split("=");
                    if (keyValue.length == 2) {
                        String key = URLDecoder.decode(keyValue[0], StandardCharsets.UTF_8);
                        String value = URLDecoder.decode(keyValue[1], StandardCharsets.UTF_8);
                        params.put(key, value);
                    }
                }
                return params;
            }
        });      
        server.createContext("/cdb", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                // Verifica o método da requisição
                if (exchange.getRequestMethod().equalsIgnoreCase("GET")) {
                    // Loga e processa o acesso GET
                    config.log("Acessando cdb", 302, "INFO : REDIRECT TO CDB");
                    String responseHtml = Investimentos.cdb();
                    
                    // Responde com o HTML da página CDB
                    exchange.sendResponseHeaders(200, responseHtml.getBytes(StandardCharsets.UTF_8).length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(responseHtml.getBytes(StandardCharsets.UTF_8));
                    os.close();
        
                } else if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
                    // Coleta e processa os dados enviados pelo formulário POST
                    config.log("Coletando dados", 101, "WARNING : COLLECT DATA");
        
                    // Lê os parâmetros enviados pelo formulário
                    Map<String, String> params = parsePostParameters(exchange);
                    DecimalFormat df = new DecimalFormat("#,##0.00");
        
                    // Extrai os valores dos parâmetros
                    int id_username = Integer.parseInt(params.get("id_user"));
                    double taxaCdi = Double.parseDouble(params.get("taxa_cdi"));
                    double cdiAtual = Double.parseDouble(params.get("cdi_atual"));
                    int tempoContrato = Integer.parseInt(params.get("tempo_contrato"));
                    double valorDeposito = Double.parseDouble(params.get("valor_deposito"));
        
                    // Calcula o resultado do CDB
                    double valorFinal = calcularCdb(taxaCdi, cdiAtual, tempoContrato, valorDeposito, id_username);
                    double valorSemRendimento = valorDeposito * tempoContrato;
                    double saldo = dataSystem.showDataUserNumbers(id_username, 10); // Supondo que 10 seja o índice do saldo
                    double valorTotalSaldo = valorFinal + saldo;
        
                    // Formata os valores para exibição
                    String taxaCdiFormatted = df.format(taxaCdi);
                    String cdiAtualFormatted = df.format(cdiAtual);
                    String valorDepositoFormatted = df.format(valorDeposito);
                    String valorFinalFormatted = df.format(valorFinal);
                    String valorTotalSaldoFormatted = df.format(valorTotalSaldo);
                    String saldoFormatted = df.format(saldo);
                    Srting valorSemRendimentoFormatted = df.format(valorSemRendimento);
        
                    // Loga os dados coletados
                    config.log("Dados Coletados:\nCDI Atual: " + cdiAtual + "\nTaxa do CDI: " + taxaCdi + "\nTempo de Contrato: " + tempoContrato, 200, "INFO : SERVER DATA");
                    config.log("Valor Deposito Mensal: " + valorDeposito + "\nSaldo Pre-Investimento: " + saldo + "\nValor Final: " + valorFinal, 200, "INFO : SERVER DATA");
                    config.log("Saldo Total Estimado: " + valorTotalSaldo, 200, "INFO : SERVER DATA");
        
                    // Cria uma lista com os valores formatados para enviar para a função que monta o HTML
                    String[] listValues = { taxaCdiFormatted, cdiAtualFormatted, valorDepositoFormatted, valorFinalFormatted, valorTotalSaldoFormatted, saldoFormatted };
                    int[] numberValues = { tempoContrato };
        
                    // Gera o resultado HTML
                    config.log("Imprimindo Resultados", 200, "INFO : SERVER INFO");

                    Boolean booleanValue = false;
                    String path = exchange.getRequestURI().getPath();
                    String response = "";

                    if ("/cdb".equals(path)) {
                        // Exibe o estado atual da variável booleana
                        response = Investimentos.cdb_result(listValues, numberValues, booleanValue);
                        config.log("Boolean Alterado para: " + booleanValue, 102, "DEBUG : PROCESS TESTING");
                        booleanValue = !booleanValue; // Alterna o valor da variável booleana
                        response = Investimentos.cdb_result(listValues, numberValues, booleanValue);
                        config.log("Boolean Alterado para: " + booleanValue, 102, "DEBUG : PROCESS TESTING");
                        config.log("Registro em Base de Dados Solicitado: " + booleanValue, 200, "WARNING : PROCESS TESTING");

                        if (booleanValue == true){
                            try { 
                                config.log("Registrando Arquivos", 500, "INFO : REGISTER DATA");
                                config.writeToCSV(valorTotalSaldoFormatted, 10, id_username);
                                config.log("Registro de Arquivos Finalizado", 500, "INFO : FINISHED REGISTER");
                            } catch (IOException e){
                                config.log("Erro ao registrar arquivos", 503, "ERROR : FAILED TO REGISTER");
                            }
                        }
                    } else {
                        config.log("Pagina não encontrada!", 404, "ERROR : NOT FOUND");
                        response = "<html><body><h1>404 Not Found</h1></body></html>";
                    }

                    OutputStream os = exchange.getResponseBody();
                    // Envia a resposta com o HTML gerado
                    exchange.sendResponseHeaders(200, response.getBytes(StandardCharsets.UTF_8).length);
                    os.write(response.getBytes(StandardCharsets.UTF_8));
                    os.close();
                }
            }
        });        
        server.createContext("/home/update_data", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                try {
                    config.log("Acessando Updater de Cadastro", 302, "INFO : REDIRECT TO CAD UPDATE");
                    if ("POST".equals(exchange.getRequestMethod())) {
                        String formData = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
                        Map<String, String> parsedData = parseFormData(formData);
                        String loginValidate = parsedData.get("login");
                        String[] listDataUpdate = new String[] {
                            parsedData.get("username"),
                            parsedData.get("name"),
                            parsedData.get("password"),
                            parsedData.get("age"),
                            parsedData.get("email"),
                            parsedData.get("address"),
                            parsedData.get("city"),
                            parsedData.get("state"),
                            parsedData.get("country"),
                            parsedData.get("balance")
                        };

                        String username = dataSystem.showDataUser(loginValidate, 1);
                        String id_user = dataSystem.showDataUser(username, 0);
                        int userId = Integer.parseInt(id_user); // Converte ID do usuário para número

                        for (int i = 1; i < listDataUpdate.length; i++) { // Começa de 1 para evitar sobrescrever o ID do usuário
                            config.log("Dado de índice " + i + " coletado e armazenado!", 500, "WARNING : REGISTER DATA");
                            try {
                                config.writeToCSV(listDataUpdate[i-1], i, userId); // Atualiza o arquivo CSV
                            } catch (IOException e) {
                                config.log("Erro ao escrever no CSV: " + e.getMessage(), 500, "ERROR : CSV WRITE FAILED");
                            }
                        }

                        config.log("Fim da coleta de dados", 200, "INFO : DATA COLLECTED");
                        String response = "Dados atualizados com sucesso!";
                        exchange.sendResponseHeaders(200, response.length());
                        OutputStream os = exchange.getResponseBody();
                        os.write(response.getBytes());
                        os.close();
                    } else {
                        String response = Homepage.dataUpdate();
                        exchange.sendResponseHeaders(200, response.length());
                        OutputStream os = exchange.getResponseBody();
                        os.write(response.getBytes());
                        os.close();
                    }
                } catch (Exception e) {
                    config.log("Erro no servidor: " + e.getMessage(), 500, "ERROR : SERVER FAILURE");
                    String response;
                    // Responder com um erro
                    response = "Erro interno do servidor";
                    exchange.sendResponseHeaders(500, response.getBytes().length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                }
            }
        });

        // Implementar manipulador para a rota financeControl
        server.createContext("/financeControl", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {

            }
        });
        server.setExecutor(null);
        server.start();
        config.log("Servidor iniciado na porta 8080. Acesse http://localhost:8080 no navegador.", 200, "INFO : SERVER INITIALIZATED");
    }
    public void stopServer(){
        if (server != null){
            server.stop(0);
            config.log("Servidor encerrado", 400, "INFO : CLOSE TO SERVER");
            String lines = "\n----------------------------------------------------------------------------------";
            config.log(lines, 0, "DEBUG");
        }
    }
    private Map<String, String> parsePostParameters(HttpExchange exchange) throws IOException {
        InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
        BufferedReader br = new BufferedReader(isr);
        StringBuilder buf = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            buf.append(line);
        }
    
        // Divide os parâmetros no formato key=value
        String postData = buf.toString();
        Map<String, String> parameters = new HashMap<>();
        String[] pairs = postData.split("&");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            if (keyValue.length == 2) {
                parameters.put(URLDecoder.decode(keyValue[0], "UTF-8"), URLDecoder.decode(keyValue[1], "UTF-8"));
            }
        }
        return parameters;
    }    
    private double calcularCdb(double taxaCdi, double cdiAtual, int meses, double valorMensal, int user) {
        // Exemplo simples de cálculo de rendimento CDB baseado em taxa CDI
        Data_System data_System = new Data_System();
        double lucro;
        double valorFinal = 0;
        double totalValor = data_System.showDataUserNumbers(user, 9) + valorMensal;
        for (int i = 0; i < meses; i++) {
            lucro = (totalValor * ((cdiAtual * taxaCdi) /100 )/ 100) / 12;
            valorFinal = valorFinal + valorMensal + lucro;
        }
        return valorFinal;
    }
    private static Map<String, String> parseFormData(String formData) {
        Map<String, String> dataMap = new HashMap<>();
        String[] pairs = formData.split("&");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            if (keyValue.length == 2) {
                dataMap.put(keyValue[0], keyValue[1]);
            }
        }
        return dataMap;
    }
}