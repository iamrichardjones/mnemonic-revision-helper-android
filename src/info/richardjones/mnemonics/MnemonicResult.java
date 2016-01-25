package info.richardjones.mnemonics;

/**
 * Created by jones on 24/01/2016.
 */
public class MnemonicResult {

    private final String answer;
    private final String origin;
    private final String type;

    public MnemonicResult(String answer, String origin, String type) {
        this.answer = answer;
        this.origin = origin;
        this.type = type;
    }

    public String getAnswer() {
        return answer;
    }

    public String getOrigin() {
        return origin;
    }

    public String getType() {
        return type;
    }
}
