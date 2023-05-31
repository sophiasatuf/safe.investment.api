package service;

import dao.PublicacaoDAO;
import model.Publicacao;

import spark.Request;
import spark.Response;
import java.util.ArrayList;
import java.util.StringJoiner;

public class PublicacaoService {
	private PublicacaoDAO publicacaoDAO = new PublicacaoDAO();
	
	public PublicacaoService() {}
	
	// Cadastro de Publicacao
	public String insert(Request request, Response response) {
		String urlVideo = request.queryParams("urlVideo");
		String hashtags = request.queryParams("hashtags");
		String titulo = request.queryParams("titulo");
		int classeId = Integer.parseInt(request.queryParams("classeId"));
		String dataPostagem = request.queryParams("dataPostagem");
		
		try {
			publicacaoDAO.insert(new Publicacao(-1, urlVideo, hashtags, titulo, classeId, 0, 0, 0, dataPostagem));
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "";
	}
	
	// Busca de Publicacao
	public String busca(Request request, Response response){
		String stringBusca = request.queryParams("stringBusca");
		ArrayList<Publicacao> lista = new ArrayList<Publicacao>();
		
		try {
			lista = publicacaoDAO.getPublicacaoByTitulo(stringBusca);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}

    StringJoiner joiner = new StringJoiner(", ");
    for (Publicacao objeto : lista) {
      joiner.add(
        "{ \"codigo\": \"" + Integer.toString(objeto.getCodigo()) + "\""
      );
      joiner.add(" \"titulo\":  \"" + objeto.getTitulo() + "\"");
      joiner.add(" \"hashtags\":  \"" + objeto.getHashtags() + "\"");
      joiner.add(" \"urlVideo\":  \"" + objeto.getUrlVideo() + "\"");
      joiner.add(
        " \"visualizacoes\": \"" + Integer.toString(objeto.getVisualizacoes()) + "\""
      );
      joiner.add(
        " \"likes\": \"" + Integer.toString(objeto.getDislikes()) + "\""
      );
      joiner.add(
        " \"dislikes\": \"" + Integer.toString(objeto.getDislikes()) + "\""
      );
      joiner.add(" \"datapostagem\":  \"" + objeto.getDataPostagem() + "\"");
      joiner.add(
        " \"classeId\":  \"" +
        Integer.toString(objeto.getClasseId()) +
        "\" }"
      );
    }

    String resultado = joiner.toString();

    return "[" + resultado + "]";
	}
}