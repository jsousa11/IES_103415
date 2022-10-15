package com.quotes.quotes.Quote;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class APIController {
    public Set<Show> shows = new HashSet<>();
    public HashMap<String, Quote> quotes = new HashMap<>();
    public ArrayList<AllShows> movies = new ArrayList<>();



    public void add(){

        AllShows m1 = new AllShows(0,"GONE WITH THE WIND");
        AllShows m2 = new AllShows(1,"CASABLANCA");
        AllShows m3 = new AllShows(2,"TITANIC");
        movies.add(m1);
        movies.add(m2);
        movies.add(m3);

        Show s1 = new Show(3,"How I Met Your Mother");
        Show s2 = new Show(4,"Breaking Bad");
        shows.add(s1);
        shows.add(s2);


        quotes.put("GONE WITH THE WIND",   new Quote("Frankly, my dear, I don’t give a damn."));
        quotes.put("GONE WITH THE WIND",   new Quote("After all, tomorrow is another day!"));
        quotes.put("GONE WITH THE WIND",   new Quote("As God is my witness, I'll never be hungry again."));
        quotes.put("CASABLANCA",           new Quote("Louis, I think this is the beginning of a beautiful friendship"));
        quotes.put("CASABLANCA",           new Quote("Play it, Sam. Play 'As Time Goes By."));
        quotes.put("CASABLANCA",           new Quote("Round up the usual suspects."));
        quotes.put("TITANIC",              new Quote("Jack, I Want You To Draw Me Like One Of Your French Girls."));
        quotes.put("TITANIC",              new Quote("It's Not Up To You To Save Me, Jack."));
        quotes.put("TITANIC",              new Quote("You're Gonna Die An Old, Old Lady, Warm In Her Bed."));
        quotes.put("How I Met Your Mother",new Quote("Whenever I'm sad, I stop being sad and be awesome instead."));
        quotes.put("How I Met Your Mother",new Quote("It’s just, eventually, we’re all gonna move on. It’s called growing up."));
        quotes.put("How I Met Your Mother",new Quote("The future is scary, but you can’t just run back to the past because it’s familiar. Yes, it’s tempting, but it’s a mistake."));
        quotes.put("Breaking Bad",         new Quote("I Only Had You In My Heart"));
        quotes.put("Breaking Bad",         new Quote("There Will Be Consequences"));
        quotes.put("Breaking Bad",         new Quote("Stay Out Of My Territory"));

    }


    public Quote getMovieQuote(String name){
        HashMap <String, List> movieQuotes = new HashMap<>();

        movieQuotes.put("GONE WITH THE WIND", Arrays.asList("Frankly, my dear, I don’t give a damn.", "After all, tomorrow is another day!","As God is my witness, I'll never be hungry again."));
        movieQuotes.put("CASABLANCA",		  Arrays.asList("Louis, I think this is the beginning of a beautiful friendship", "Play it, Sam. Play 'As Time Goes By.","Round up the usual suspects."));
        movieQuotes.put("TITANIC",			  Arrays.asList("Jack, I Want You To Draw Me Like One Of Your French Girls.", "It's Not Up To You To Save Me, Jack.","You're Gonna Die An Old, Old Lady, Warm In Her Bed."));

        Random r = new Random();
        if (name.equals("GONE WITH THE WIND")){
            List<String> q = movieQuotes.get("GONE WITH THE WIND");
            return new Quote(q.get(r.nextInt(3)));
        }

        if (name.equals("CASABLANCA")){
            List<String> q = movieQuotes.get("CASABLANCA");
            return new Quote(q.get(r.nextInt(3)));

        } else{
            List<String> q = movieQuotes.get("TITANIC");
            return new Quote(q.get(r.nextInt(3)));
        }
    }


    public Quote getQuoteFromShow(int id){
        HashMap <String, List> showQuotes = new HashMap<>();

        showQuotes.put("Breaking Bad", 		    Arrays.asList("I Only Had You In My Heart", "There Will Be Consequences", "Stay Out Of My Territory"));
        showQuotes.put("How I Met Your Mother", Arrays.asList("Whenever I'm sad, I stop being sad and be awesome instead.","It’s just, eventually, we’re all gonna move on. It’s called growing up.", "The future is scary, but you can’t just run back to the past because it’s familiar. Yes, it’s tempting, but it’s a mistake."));
        Random r = new Random();

        if (id == 3){
            List<String> q = showQuotes.get("Breaking Bad");
            return new Quote(q.get(r.nextInt(3)));
        }
        else{
            List<String> q = showQuotes.get("How I Met Your Mother");
            return new Quote(q.get(r.nextInt(3)));
        }
    }

    @GetMapping("/api/Quote")
    public Quote Quote() {
        add();
        Random r = new Random();

        Object[] values = quotes.values().toArray();
        return (Quote) values[r.nextInt(values.length)];
    }


    @GetMapping("/api/AllShows")
    public Set show() {
        shows.clear();
        add();
        return shows;
    }


    @GetMapping("/api/QuoteFromShow")

    public Quote rnd_Quote(@RequestParam(value = "show", defaultValue = "0") int id) {

        add();

        if (id>=3){
            return getQuoteFromShow(id);

        } else{
            AllShows m = movies.get(id);
            String m_name = m.getName();
            return getMovieQuote(m.getName());
        }

    }


}
