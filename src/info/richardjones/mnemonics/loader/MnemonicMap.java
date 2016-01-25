package info.richardjones.mnemonics.loader;

import java.util.*;

public class MnemonicMap {

    private final Map<String, List<MatchingMnemonic>> map = new HashMap();

    public List<MatchingMnemonic> getValues(String mnemonic) {
        char[] charArray = mnemonic.toUpperCase().toCharArray();
        Arrays.sort(charArray);
        List<MatchingMnemonic> result = map.get(new String(charArray));
        return result == null ? new ArrayList<MatchingMnemonic>() : result;
    }

    public void add(MatchingMnemonic detail) {
        char[] charArray = detail.getMnemonic().toUpperCase().toCharArray();
        Arrays.sort(charArray);
        String key = new String(charArray);
        if (map.get(key) == null) {
            List<MatchingMnemonic> list = new ArrayList();
            list.add(detail);
            map.put(key, list);
        }
        else {
            map.get(key).add(detail);
        }
    }

    //for tests
    public Map<String, List<MatchingMnemonic>> getMap() {
        return map;
    }
}
