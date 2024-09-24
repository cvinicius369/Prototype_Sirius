package Modules;

public class Homepage {
    public static String homepage(String[] listData){
        String response = 
            "<html>" + 
                "<meta charset='UTF-8'>" + 
                "<link rel='preconnect' href='https://fonts.googleapis.com'>" +
                "<link rel='preconnect' href='https://fonts.gstatic.com' crossorigin>" +
                "<link href='https://fonts.googleapis.com/css2?family=Orbitron:wght@400;700&family=Source+Code+Pro:wght@300;700&display=swap' rel='stylesheet'>" +
                "<title>Home</title>" +
                "<body>" +
                    "<h2 id='welcome'>Bem-vindo, " + listData[2] + "!</h2>" +
                    "<p>Acesso liberado. Agora você pode ver novos elementos na página.</p>" +
                    "<div id='data_userbank'>"+
                        "<p>ID de Usuario: " + listData[0] + "<br>" +
                        "Nome de Usuario: " + listData[1] + "<br>" +
                        "Nome: " + listData[2] + "<br>" +
                        "Senha: " + listData[3] + "<br>" +
                        "Idade: " + listData[4] + "<br>" +
                        "E-mail: " + listData[5] + "<br>" +
                        "Endereco: " + listData[6] + "<br>"+
                        "Cidade: " + listData[7] + "<br>" +
                        "Estado: " + listData[8] + "<br>" +
                        "País: " + listData[9] + "<br>" +
                        "Saldo: " + listData[10] + "</p>" +
                    "</div>"+
                    // "<p>Novo elemento 2: Configurações</p>" +
                    "<div id='listOptions'>" +
                        "<ul>" +
                            "<li onclick='showSubOptionsInvest()'> Investimentos" +
                                "<ul id='subInvest' style='display: none;'>" +
                                    // Aqui o CDB é transformado em um link que redireciona para uma nova página
                                    "<li><a href='/cdb'> CDB </a></li>" +
                                    "<li> AÇÕES </li>" +
                                    "<li> VIAGENS </li>" +
                                "</ul>" +
                            "</li>" +
                            "<li onclick='showFinanControl()'> Controle Financeiro"+
                                "<ul id='finanControl' style='display: none;'>"+
                                    "<li> Calculo de Salário </li> "+
                                    "<li> Controle de Despesas </li>"+
                                    "<li> Gastos com Transporte Publico </li>"+
                                "</ul>"+
                            "</li>" +
                            "<li> Atualização de Dados "+
                                "<div>"+
                                    "<form method='POST' action='/home/update_data'  id='dataUpdater'>" +
                                        "Login Antigo: <input type='text' name='login'><br>"+
                                        "Nome de Usuário: <input type='text' name='username'><br>" +
                                        "Nome: <input type='text' name='name'><br>" +
                                        "Senha Nova: <input type='password' name='password'><br>" +
                                        "Idade: <input type='number' name='age'><br>" +
                                        "Email: <input type='email' name='email'><br>" +
                                        "Endereço: <input type='text' name='address'><br>" +
                                        "Cidade: <input type='text' name='city'><br>" +
                                        "Estado: <input type='text' name='state'><br>" +
                                        "País: <input type='text' name='country'><br>" +
                                        "Saldo em Conta: <input type='number' name='balance' step='0.01'><br><br>" +
                                        "<input type='submit' value='Atualizar'>" +
                                    "</form>" +
                                "</div>"+
                            "</li>"+
                        "</ul>" +
                    "</div>" +
                "</body>" +
                "<style>" +
                    "html, body { width: 100%; height: 100%; margin: 0; padding: 0; }" +
                    "body { " +
                        "color: #00ffea; background: #1e1e2f;" +
                        "font-family: 'Source Code Pro', monospace;" +
                        "display: block; justify-content: center; align-items: center; height: 100vh;" +
                    "}" +
                    "a{ text-decoration: none; color: white; }"+
                    "#welcome { " +
                        "text-align: center; font-weight: bold; font-family: 'Orbitron', sans-serif; padding: 2%; " +
                        "text-transform: uppercase; font-size: 2.5em; color: #00fff7; text-shadow: 0 0 15px rgba(0, 255, 255, 0.8);" +
                    "}" +
                    "#listOptions { " +
                        "list-style: none; padding: 0; margin: 0; display: flex; justify-content: center;" +
                    "}" +
                    "#listOptions ul { " +
                        "padding: 0; margin: 0; display: inline; flex-direction: column; align-items: center;" +
                    "}" +
                    "#listOptions ul li { " +
                        "padding: 10px 20px; background: grey;" +
                        "border-radius: 15px; margin: 10px; font-size: 1.2em; color: #ffffff; cursor: pointer;" +
                        "transition: background-color 0.3s ease, transform 0.3s ease; list-style: none;" +
                    "}" +
                    "#listOptions ul li:hover { " +
                        "background-color: #00fff7; transform: scale(1.05); text-shadow: 0 0 50px rgba(0, 255, 255, 0.8);" +
                    "}" +
                    "#subInvest li { padding: 5px; margin-top: 5px; background-color: rgba(255, 255, 255, 0.1); list-style: none; }" +
                    "#subInvest li:hover { background-color: rgba(255, 255, 255, 0.3); }" +
                    "#data_userbank { margin: 5%; }"+
                "</style>" +
                "<script>" +
                    "function showSubOptionsInvest() { " +
                        "var list = document.getElementById('subInvest'); " +
                        "list.style.display = (list.style.display === 'none') ? 'block' : 'none';" +
                    "}" +
                    "function showFinanControl() { " +
                        "var controlFinan = document.getElementById('finanControl'); " + 
                        "if (controlFinan.style.display == 'none') { controlFinan.style.display = 'block'; } "+
                    "}"+
                "</script>" +
            "</html>";
        return response;
    }
    public static String loginUser(String[] dataUser){
        String response = "<html>" +
                            "<meta charset='UTF-8'>" +
                            "<link rel='preconnect' href='https://fonts.googleapis.com'>" +
                            "<link rel='preconnect' href='https://fonts.gstatic.com' crossorigin>" +
                            "<link href='https://fonts.googleapis.com/css2?family=Orbitron:wght@400;700&family=Source+Code+Pro:wght@300;700&display=swap' rel='stylesheet'>" +
                            "<body>" +
                                "<h2 id='loginTitle'>Entrar</h2>" +
                                "<form method='POST' action='/home' id='loginForm'>" +
                                    "<label for='loginInput'>Login:</label> " +
                                    "<input type='text' id='loginInput' name='login' placeholder='Digite seu login'><br>" +
                                    "<label for='passwordInput'>Password:</label> " +
                                    "<input type='password' id='passwordInput' name='password' placeholder='Digite sua senha'><br>" +
                                    "<input type='submit' value='Entrar' id='submitButton'>" +
                                "</form>" +
                                "<p id='errorMessage'>" + 
                                    (dataUser[0].isEmpty() && dataUser[1].isEmpty() ? "" : "Credenciais incorretas!") +
                                "</p>" +
                            "</body>" +
                            "<style>" +
                                "::selection { background-color: black; border-radius: 15px; }"+
                                "html, body { width: 100%; height: 100%; margin: 0; padding: 0; display: flex; justify-content: center; align-items: center; }" +
                                "body { " +
                                    "background-color: #1e1e2f; color: #00ffea; font-family: 'Source Code Pro', monospace;" +
                                    "display: flex; flex-direction: column; justify-content: center; align-items: center; height: 100vh;" +
                                "}" +
                                "#loginTitle { " +
                                    "text-align: center; font-weight: bold; font-family: 'Orbitron', sans-serif; " +
                                    "text-transform: uppercase; font-size: 2.5em; color: #00fff7; text-shadow: 0 0 15px rgba(0, 255, 255, 0.8);" +
                                "}" +
                                "#loginForm { " +
                                    "display: flex; flex-direction: column; align-items: center; background-color: rgba(255, 255, 255, 0.1); " +
                                    "border-radius: 15px; padding: 20px; box-shadow: 0 0 15px rgba(0, 255, 255, 0.3);" +
                                "}" +
                                "#loginForm label { " +
                                    "font-size: 1.2em; margin-bottom: 10px; color: #ffffff;" +
                                "}" +
                                "#loginForm input[type='text'], #loginForm input[type='password'] { " +
                                    "padding: 10px; margin: 10px 0; width: 100%; border-radius: 10px; border: none; background-color: #1e1e2f; color: #00ffea;" +
                                    "font-family: 'Source Code Pro', monospace;" +
                                "}" +
                                "#submitButton { " +
                                    "padding: 10px 20px; background-color: #00fff7; color: #1e1e2f; border: none; border-radius: 10px; " +
                                    "font-size: 1.2em; cursor: pointer; transition: background-color 0.3s ease;" +
                                "}" +
                                "#submitButton:hover { " +
                                    "background-color: #00ffea; color: #000000; transition: background-color 0.3s ease;" +
                                "}" +
                                "#errorMessage { " +
                                    "color: red; margin-top: 15px; font-size: 1.1em;" +
                                "}" +
                            "</style>" +
                        "</html>";
        return response;
    }
    public static String dataUpdate(){
        String response = "";
        response = "<html>" +
                    "<body>" +
                    "<h1>Atualizar Dados de Cadastro</h1>" +
                    "<form method='POST' action='/home/update_data'>" +
                    "Nome de Usuário: <input type='text' name='username'><br>" +
                    "Nome: <input type='text' name='name'><br>" +
                    "Senha Nova: <input type='password' name='password'><br>" +
                    "Idade: <input type='number' name='age'><br>" +
                    "Email: <input type='email' name='email'><br>" +
                    "Endereço: <input type='text' name='address'><br>" +
                    "Cidade: <input type='text' name='city'><br>" +
                    "Estado: <input type='text' name='state'><br>" +
                    "País: <input type='text' name='country'><br>" +
                    "Saldo em Conta: <input type='number' name='balance' step='0.01'><br><br>" +
                    "<input type='submit' value='Atualizar'>" +
                    "</form>" +
                    "</body>" +
                    "</html>";
        return response;
    }
}
