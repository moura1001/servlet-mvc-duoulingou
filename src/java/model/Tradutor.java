package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public final class Tradutor {

    private static Tradutor _tradutor;

    private final Map<String, String> _dicionario;
    private final String _nomeArquivo;

    private Tradutor(String arquivo) {
        _dicionario = new HashMap<>(20);
        _nomeArquivo = arquivo;
        carregarDadosDoArquivo();
    }

    public static Tradutor obterInstancia(String arquivo) {
        if (_tradutor != null) {
            return _tradutor;
        }

        _tradutor = new Tradutor(arquivo);
        return _tradutor;
    }

    public String traduzir(String palavra) {
        palavra = palavra.substring(0, 1).toUpperCase() + palavra.substring(1).toLowerCase();
        return _dicionario.getOrDefault(palavra, palavra + " (palavra não encontrada)");
    }

    private void carregarDadosDoArquivo() throws RuntimeException {
        int lineCounter = 1;
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(_nomeArquivo, Charset.forName("UTF-8")));
            // first line
            String line = reader.readLine();

            while (line != null && line.length() > 1) {
                String[] registro = line.split(";");

                if (registro.length == 2) {
                    _dicionario.put(registro[0], registro[1]);
                } else {
                    throw new RuntimeException("Registro inválido. Não possui um par palavras válido."
                            + " Valor encontrado: " + line);
                }

                // next line
                lineCounter++;
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(
                    "Erro no processamento do arquivo " + _nomeArquivo + ": " + e.getMessage());
        } catch (RuntimeException e) {
            throw new RuntimeException("Arquivo " + _nomeArquivo
                    + " é inválido. Não foi possível processar a linha " + lineCounter + ": " + e.getMessage());
        }
    }
}
