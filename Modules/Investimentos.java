package Modules;

public class Investimentos {
    public static String cdb(){
        String response = "<html>" +
                        "<meta charset='UTF-8'>" +
                        "<link rel='preconnect' href='https://fonts.googleapis.com'>" +
                        "<link rel='preconnect' href='https://fonts.gstatic.com' crossorigin>" +
                        "<link href='https://fonts.googleapis.com/css2?family=Orbitron:wght@400;700&family=Source+Code+Pro:wght@300;700&display=swap' rel='stylesheet'>" +
                        "<title>Certificado de Depósito Bancário (CDB)</title>" +
                        "<body>" +
                            "<h1>Certificado de Depósito Bancário (CDB)</h1>" +
                            "<div id='cont'>"+
                                "<form action='/cdb' method='POST'>" +
                                    "<label for='taxa_cdi'>Taxa CDI (%):</label><br>" +
                                    "<input type='number' step='0.01' id='taxa_cdi' name='taxa_cdi'><br><br>" +
                                    
                                    "<label for='cdi_atual'>CDI Atual (%):</label><br>" +
                                    "<input type='number' step='0.01' id='cdi_atual' name='cdi_atual'><br><br>" +
                                    
                                    "<label for='tempo_contrato'>Tempo de Contrato (meses):</label><br>" +
                                    "<input type='number' id='tempo_contrato' name='tempo_contrato'><br><br>" +
                                    
                                    "<label for='valor_deposito'>Valor a ser Depositado Mensalmente (R$):</label><br>" +
                                    "<input type='number' step='0.01' id='valor_deposito' name='valor_deposito'><br><br>" +
                                    
                                    "<label for='id_user'>Nome de Usuário:</label><br>" +
                                    "<input type='number' id='id_user' name='id_user'><br><br>" +
                                    
                                    "<input id='calculando' type='submit' value='Calcular'>" +
                                "</form>" +
                            "</div>"+
                        "</body>" +
                        "<style>" +
                            "::selection { background-color: black; border-radius: 15px; }"+
                            "html, body { width: 100%; height: 100%; margin: 0; padding: 0; }" +
                            "body { " +
                                "color: #00ffea; background: #1e1e2f;" +
                                "font-family: 'Source Code Pro', monospace;" +
                                "display: block; justify-content: center; align-items: center; height: 100vh;" +
                            "}" +
                            "a{ text-decoration: none; color: white; }" +
                            "h1 { " +
                                "text-align: center; font-weight: bold; font-family: 'Orbitron', sans-serif; padding: 2%; " +
                                "text-transform: uppercase; font-size: 2.5em; color: #00fff7; text-shadow: 0 0 15px rgba(0, 255, 255, 0.8);" +
                            "}" +
                            "form { " +
                                "display: flex; flex-direction: column; align-items: center; background-color: rgba(255, 255, 255, 0.1); " +
                                "border-radius: 15px; padding: 20px; box-shadow: 0 0 15px rgba(0, 255, 255, 0.3);" +
                            "}" +
                            "label { " +
                                "font-size: 1.2em; margin-bottom: 10px; color: #ffffff;" +
                            "}" +
                            "input[type='number'] { " +
                                "padding: 10px; margin: 10px 0; width: 100%; border-radius: 10px; border: none; background-color: #1e1e2f; color: #00ffea;" +
                                "font-family: 'Source Code Pro', monospace;" +
                            "}" +
                            "input[type='submit'] { " +
                                "padding: 10px 20px; background-color: #00fff7; color: #1e1e2f; border: none; border-radius: 10px; " +
                                "font-size: 1.2em; cursor: pointer; transition: background-color 0.3s ease;" +
                            "}" +
                            "input[type='submit']:hover { " +
                                "background-color: #00ffea; color: #000000; transition: background-color 0.3s ease;" +
                            "}" +
                            "#cont { margin: 5% 15%; }"+
                            "#calculando:hover { text-shadow: 0 0 15px rgba(0, 255, 255, 0.8); }"+
                        "</style>" +
                        "</html>"; 
        return response;         
    }

    public static String cdb_result(String[] lista, int[] numberValues, Boolean atualiza_dado){
        String resultado = "<html>" +
                           "<meta charset='UTF-8'>" +
                           "<link rel='preconnect' href='https://fonts.googleapis.com'>" +
                           "<link rel='preconnect' href='https://fonts.gstatic.com' crossorigin>" +
                           "<link href='https://fonts.googleapis.com/css2?family=Orbitron:wght@400;700&family=Source+Code+Pro:wght@300;700&display=swap' rel='stylesheet'>" +
                           "<title>Resultado CDB</title>" +
                           "<body>" +
                           "<h1>Resultado do CDB</h1>" +
                           "<div>" +
                           "<p>Taxa CDI: " + lista[0] + "%</p>" +
                           "<p>CDI Atual: " + lista[1] + "%</p>" +
                           "<p>Tempo de Contrato: " + numberValues[0] + " meses</p>" +
                           "<p>Valor Depositado Mensalmente: R$ " + lista[2] + "</p>" +
                           "<p>Saldo antes do investimento: " + lista[5] + "</p>" +
                           "<p> Valor sem o rendimento: " + 
                           "<p><b>Valor Final Estimado do CDB: R$ " + lista[3] + "</b></p>" +
                           "<p><b>Valor Total do saldo Estimado: R$ " + lista[4] + "</b></p>" +
                           "<button id='registrar' onclick=\"location.href='/cdb'\"> Atualizar Base de Dados </button>" +
                           "</div>" +
                           "</body>" +
                           "<style>" +
                           "html, body { width: 100%; height: 100%; margin: 0; padding: 0; }" +
                           "body { " +
                           "color: #00ffea; background: #1e1e2f;" +
                           "font-family: 'Source Code Pro', monospace;" +
                           "display: block; justify-content: center; align-items: center; height: 100vh;" +
                           "}" +
                           "h1 { " +
                           "text-align: center; font-weight: bold; font-family: 'Orbitron', sans-serif; padding: 2%; " +
                           "text-transform: uppercase; font-size: 2.5em; color: #00fff7; text-shadow: 0 0 15px rgba(0, 255, 255, 0.8);" +
                           "}" +
                           "p { " +
                           "font-size: 1.2em; margin: 10px; color: #ffffff;" +
                           "}" +
                           "b { " +
                           "color: #00fff7;" +
                           "}" +
                           "div { margin: 10%; }" +
                           "</style>" +
                           "<script> alert('Valor Variavel Atual: " + atualiza_dado + "); </script>"+
                           "</html>";
        return resultado;
    }
    
}
