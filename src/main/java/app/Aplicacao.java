package app;

import static spark.Spark.*;
import service.PublicacaoService;
import service.UserService;
import service.ClasseService;

// Aplicacao -> service -> DAO -> Banco de Dados
// Declarar rotas de recebimento dos dados front-end

public class Aplicacao {

    private static UserService userService = new UserService();
    private static ClasseService classeService = new ClasseService();
    private static PublicacaoService publicacaoService = new PublicacaoService();

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
        
        // ----------- Usuário ---------- //

        post("/user/cadastro", (request, response) -> userService.insert(request, response)); // rota
        
        post("/user/login", (request, response) -> userService.login(request, response));
        
        // ----------- Classe ---------- //
        
        post("/classe/cadastro", (request, response) -> classeService.insert(request, response));
        
        get("/classe/busca", (request, response) -> classeService.busca(request, response));
        
        get("/classe/buscaProfessor", (request, response) -> classeService.buscaProfessor(request, response));
        
        // ----------- Publicação ---------- //
        
        post("/publicacao/cadastro", (request, response) -> publicacaoService.insert(request, response));
        
        get("/publicacao/busca", (request, response) -> publicacaoService.busca(request, response));

        get("/publicacao/:publicacaoId", (request, response) -> publicacaoService.get(request, response));
/*
        get("/car/:id", (request, response) -> carService.get(request, response));

        get("/car/list", (request, response) -> carService.getAll(request, response));

        get("/car/update/:id", (request, response) -> carService.getToUpdate(request, response));

        post("/car/update/:id", (request, response) -> carService.update(request, response));

        get("/car/delete/:id", (request, response) -> carService.delete(request, response));
*/

    }
}