package DAO;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.DistinctIterable;
import com.mongodb.client.MongoCollection;
import model.Movie;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.mongodb.client.model.Filters.eq;

public class MovieDAO extends AbsDAO {

    public static DistinctIterable<String> getGenres() {
        MongoCollection<Document> movies = getDB().getCollection("movies");
        return movies.distinct("genres", String.class);
    }

    public static List<Movie> getMovies(int limit) {
        MongoCollection<Document> movies = getDB().getCollection("movies");
        List<Movie> list = new ArrayList<>();
        movies.find().limit(limit).forEach(d -> list.add(docToMovie(d)));
        return list;
    }

    public List<Movie> searchMovies(Document filter, Document sort, int limit, int skip) {
        MongoCollection<Document> movies = getDB().getCollection("movies");
        List<Movie> list = new ArrayList<>();
        movies.find(filter).sort(sort).limit(limit).skip(skip).forEach(d -> list.add(docToMovie(d)));
        return list;
    }

    public static Movie getMovieByID(String id) {
        MongoCollection<Document> movies = getDB().getCollection("movies");
        Document movie = movies.find(eq("_id", new ObjectId(id))).first();
        return docToMovie(movie);
    }

    public static Movie docToMovie(Bson bson) {
        Movie movie = new Movie();
        Document document = (Document) bson;
        movie.set_id(document.getObjectId("_id").toHexString());
        movie.setTitle(MessageFormat.format("{0}", document.get("title")));
        movie.setCast((List<String>) document.get("cast"));
        movie.setPlot(document.getString("plot"));
        movie.setFullPlot(document.getString("fullplot"));
        movie.setType(document.getString("type"));
        movie.setDirectors((List<String>) document.get("directors"));
        movie.setWriters((List<String>) document.get("writers"));
        movie.setCountries((List<String>) document.get("countries"));
        movie.setGenres((List<String>) document.get("genres"));
        movie.setPoster(document.getString("poster"));
        movie.setYear(String.valueOf(document.get("year")));
        return movie;
    }
}
