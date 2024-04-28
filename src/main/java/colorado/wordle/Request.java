package colorado.wordle;

import lombok.Data;

@Data
public class Request {
    private String pattern;
    private String sequence;
    private String include;
    private String exclude;
    private boolean starters;
    private boolean recent;
}
