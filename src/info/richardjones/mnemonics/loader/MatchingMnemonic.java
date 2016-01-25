package info.richardjones.mnemonics.loader;

import info.richardjones.mnemonics.utils.Utils;

public class MatchingMnemonic {

    public static final MatchingMnemonic NULL = new MatchingMnemonic(Utils.EMPTY_STRING, Utils.EMPTY_STRING);

    private final String mnemonic;
    private final MatchingMnemonicDetail detail;

    public MatchingMnemonic(String acronym, String expandedAcronym) {
        this(acronym, new MatchingMnemonicDetail(expandedAcronym, Utils.UNKNOWN_STRING, Utils.UNKNOWN_STRING));
    }

    public MatchingMnemonic(String mnemonic, MatchingMnemonicDetail detail) {
        this.mnemonic = mnemonic;
        this.detail = detail;
    }

    public String getMnemonic() {
        return mnemonic;
    }

    public MatchingMnemonicDetail getDetail() {
        return detail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MatchingMnemonic that = (MatchingMnemonic) o;

        if (that != null) {
            if (!mnemonic.equals(that.mnemonic)) return false;
            if (!detail.equals(that.detail)) return false;
        }


        return true;
    }

    @Override
    public int hashCode() {
        int result = mnemonic.hashCode();
        result = 31 * result + detail.hashCode();
        return result;
    }
}
