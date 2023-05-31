package service;

import dao.PublicacaoDAO;
import model.Publicacao;

import spark.Request;
import spark.Response;
import java.util.ArrayList;

public class PublicacaoService {
	private PublicacaoDAO publicacaoDAO = new PublicacaoDAO();
	
	public PublicacaoService() {}
	
	// Cadastro de Publicacao
	public String insert(Request request, Response response) {
		String urlVideo = request.queryParams("urlVideo");
		String hashtags = request.queryParams("hashtags");
		String titulo = request.queryParams("titulo");
		int classeId = Integer.parseInt(request.queryParams("classeId"));
		int visualizacoes = Integer.parseInt(request.queryParams("visualizacoes"));
		int likes = Integer.parseInt(request.queryParams("likes"));
		int dislikes = Integer.parseInt(request.queryParams("dislikes"));
		String dataPostagem = request.queryParams("dataPostagem");
		
		try {
			publicacaoDAO.insert(new Publicacao(-1, urlVideo, hashtags, titulo, classeId, visualizacoes, likes, dislikes, dataPostagem));
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "";
	}
	
	// Busca de Publicacao
	public ArrayList<Publicacao> busca(Request request, Response response){
		String stringBusca = request.queryParams("stringBusca");
		ArrayList<Publicacao> resultado = new ArrayList<Publicacao>();
		
		try {
			resultado = publicacaoDAO.getPublicacaoByTitulo(stringBusca);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return resultado;
	}
}