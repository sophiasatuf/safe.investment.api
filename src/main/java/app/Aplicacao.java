package app;

import static spark.Spark.*;
import service.PublicacaoService;
import service.UserService;
import service.ClasseService;
import service.ComentarioService;
import service.UserClasseService;
import service.ProfessorService;

// Aplicacao -> service -> DAO -> Banco de Dados
// Declarar rotas de recebimento dos dados front-end

public class Aplicacao {

    private static UserService userService = new UserService();
    private static ClasseService classeService = new ClasseService();
    private static PublicacaoService publicacaoService = new PublicacaoService();
    private static ComentarioService comentarioService = new ComentarioService();
    private static UserClasseService userClasseService = new UserClasseService();
    private static ProfessorService professorService = new ProfessorService();

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

        // ----------- Professor ---------- //

        get("/professor/:professorId", (request, response) -> professorService.get(request, response));
        
        // ----------- Classe ---------- //
        
        post("/classe/cadastro", (request, response) -> classeService.insert(request, response));
        
        get("/classe/busca", (request, response) -> classeService.busca(request, response));

        get("/classe/buscaProfessor", (request, response) -> classeService.buscaProfessor(request, response));

        get("/classe/:classeId", (request, response) -> classeService.buscaPorId(request, response));
        
        // ----------- Publicaçãoo ---------- //
        
        post("/publicacao/cadastro", (request, response) -> publicacaoService.insert(request, response));
        
        get("/publicacao/busca", (request, response) -> publicacaoService.busca(request, response));

        get("/publicacao/:publicacaoId", (request, response) -> publicacaoService.get(request, response));

        get("/publicacoes", (request, response) -> publicacaoService.getPublicacoes(request, response));

        post("publicacao/like/:publicacaoId", (request, response) -> publicacaoService.incrementaLike(request, response));

        post("publicacao/dislike/:publicacaoId", (request, response) -> publicacaoService.incrementaDislike(request, response));

        post("publicacao/visualizacao/:publicacaoId", (request, response) -> publicacaoService.incrementaVisualizacao(request, response));

        // ----------- Comentário ---------- //

        post("/comentario/cadastro", (request, response) -> comentarioService.insert(request, response));

        get("/comentarios/:publicacaoId", (request, response) -> comentarioService.getComentariosPublicacao(request, response));

        post("comentario/like/:comentarioId", (request, response) -> comentarioService.incrementaLike(request, response));

        post("comentario/dislike/:comentarioId", (request, response) -> comentarioService.incrementaDislike(request, response));

        get("/comentarios/contar/:comentarioId", (request, response) -> comentarioService.getContarComentariosPublicacao(request, response));
        
        // ----------- UsuárioClasse ---------- //

        post("/userclasse/inscrever", (request, response) -> userClasseService.insert(request, response));

        get("/userclasse/isInscrito", (request, response) -> userClasseService.isInscrito(request, response));

        post("/userclasse/desinscrever", (request, response) -> userClasseService.delete(request, response));

        get("/userclasse/:userId", (request, response) -> userClasseService.buscaPorUserId(request, response));
    }
}