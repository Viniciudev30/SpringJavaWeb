
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

interface Exibivel {
    void exibir();
}

class Filme implements Exibivel {
    private String titulo;
    private int ano;
    private double imdbRating;

    public Filme(String titulo, int ano, double imdbRating) {
        this.titulo = titulo;
        this.ano = ano;
        this.imdbRating = imdbRating;
    }

    public String getTitulo() { return titulo; }
    public int getAno() { return ano; }
    public double getImdbRating() { return imdbRating; }

    @Override
    public void exibir() {
        System.out.println("Título: " + titulo + " | Ano: " + ano + " | IMDb: " + imdbRating);
    }
}

class FilmeDetalhado extends Filme {
    private String genero;
    private String diretor;

    public FilmeDetalhado(String titulo, int ano, double imdbRating, String genero, String diretor) {
        super(titulo, ano, imdbRating);
        this.genero = genero;
        this.diretor = diretor;
    }

    @Override
    public void exibir() {
        super.exibir();
        System.out.println("Gênero: " + genero + " | Diretor: " + diretor);
    }
}

class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HttpClient client = HttpClient.newHttpClient();
        List<Filme> filmes = new ArrayList<>();
        Gson gson = new Gson();

        while (true) {
            System.out.print("Digite o nome do filme (ou 'sair' para encerrar): ");
            String nomeFilme = scanner.nextLine();
            if (nomeFilme.equalsIgnoreCase("sair")) break;

            try {
                String apiKey = "YOUR_OMDB_API_KEY";
                String url = "https://www.omdbapi.com/?t=" + nomeFilme.replace(" ", "+") + "&apikey=" + apiKey;

                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .GET()
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                JsonObject json = gson.fromJson(response.body(), JsonObject.class);

                if (json.has("Title")) {
                    FilmeDetalhado filme = new FilmeDetalhado(
                            json.get("Title").getAsString(),
                            json.get("Year").getAsInt(),
                            json.get("imdbRating").getAsDouble(),
                            json.get("Genre").getAsString(),
                            json.get("Director").getAsString()
                    );
                    filmes.add(filme);
                    filme.exibir();
                } else {
                    System.out.println("Filme não encontrado!");
                }
            } catch (IOException | InterruptedException | NumberFormatException e) {
                System.out.println("Erro ao buscar filme: " + e.getMessage());
            }
        }

        scanner.close();
        filmes.sort(Comparator.comparing(Filme::getTitulo));
        salvarEmArquivo(filmes);
    }

    private static void salvarEmArquivo(List<Filme> filmes) {
        try (FileWriter writer = new FileWriter("filmes.json")) {
            new Gson().toJson(filmes, writer);
            System.out.println("Filmes salvos em filmes.json");
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo: " + e.getMessage());
        }
    }
}
