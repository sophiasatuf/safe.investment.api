package service;

import dao.RatingDAO;
import model.Rating;
import spark.Request;
import spark.Response;

public class RatingService {

  private RatingDAO ratingDAO = new RatingDAO();

  public RatingService() {}

  public String post(Request request, Response response) {
    int userId = Integer.parseInt(request.queryParams("userId"));
    int publicacaoId = Integer.parseInt(request.queryParams("publicacaoId"));
    int rating = Integer.parseInt(request.queryParams("rating"));

    try {
      ratingDAO.insert(new Rating(-1, userId, publicacaoId, rating));
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    return "";
  }
}
