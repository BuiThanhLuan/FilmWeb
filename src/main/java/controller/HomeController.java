package controller;

import model.Movie;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import service.MovieService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class HomeController implements IController {

    public void process(final HttpServletRequest request, final HttpServletResponse response, final ServletContext servletContext, final ITemplateEngine templateEngine) throws Exception {
        WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
        ctx.setVariable("site_name", "Mflix");
        List<String> genres = MovieService.getGenresForHeader();
        ctx.setVariable("genres", genres);
        List<Movie> list = MovieService.getMoviesForHomePage();
        ctx.setVariable("list", list);
        templateEngine.process("index", ctx, response.getWriter());
    }
}