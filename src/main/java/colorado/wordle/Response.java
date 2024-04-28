package colorado.wordle;

import lombok.Data;

import java.util.List;

@Data
public class Response {
    private List<String> words;
}
