package app;

import static spark.Spark.*;
import service.UserService;

// Aplicacao -> service -> DAO -> Banco de Dados
// Declarar rotas de recebimento dos dados front-end

public class Aplicacao {

    private static UserService userService = new UserService();

    public static void main(String[] args) {
        port(6789);
        
        // Configuração de CORS para permitir conexão com o front-end hospedado em outra porta/servidor
        options("/",
            (request, response) -> {

                String accessControlRequestHeaders = request
                        .headers("Access-Control-Request-Headers");
                if (accessControlRequestHeaders != null) {
                    response.header("Access-Control-Allow-Headers",
                            accessControlRequestHeaders);
                }

                String accessControlRequestMethod = request
                        .headers("Access-Control-Request-Method");
                if (accessControlRequestMethod != null) {
                    response.header("Access-Control-Allow-Methods",
                            accessControlRequestMethod);
                }

                return "OK";
            });

        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));

        post("/user/cadastro", (request, response) -> userService.insert(request, response)); // rota
        
        post("/user/login", (request, response) -> userService.login(request, response));
/*
        get("/car/:id", (request, response) -> carService.get(request, response));

        get("/car/list", (request, response) -> carService.getAll(request, response));

        get("/car/update/:id", (request, response) -> carService.getToUpdate(request, response));

        post("/car/update/:id", (request, response) -> carService.update(request, response));

        get("/car/delete/:id", (request, response) -> carService.delete(request, response));
*/

    }
}