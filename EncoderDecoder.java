import java.util.*;

class EncodeDecode {
    private final Map<String, Integer> originalMap = new HashMap<>();
    private final String[] referenceTable = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R",
            "S","T","U","V","W","X","Y","Z","0","1","2","3","4","5","6","7","8","9","(",")","*","+",",","-",".","/"};

    public EncodeDecode(){}

    public Map<String, Integer> getOriginalMap() {
        return originalMap;
    }

    public String[] getReferenceTable() {
        return referenceTable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EncodeDecode that = (EncodeDecode) o;
        return originalMap.equals(that.originalMap) && Arrays.equals(referenceTable, that.referenceTable);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(originalMap);
        result = 31 * result + Arrays.hashCode(referenceTable);
        return result;
    }

    @Override
    public String toString() {
        return "EncodeDecode{" +
                "encodeMap=" + originalMap +
                ", referenceTable=" + Arrays.toString(referenceTable) +
                '}';
    }

    public void createMap(){
        for (int i = 0; i < referenceTable.length; i += 1){
            originalMap.put(referenceTable[i],i);
        }
    }

    public String decode(String phrase){
        if (originalMap.containsKey(String.valueOf(phrase.charAt(0)))) {
            int index = originalMap.get(String.valueOf(phrase.charAt(0)));
            String[] offsetTable1 = Arrays.copyOfRange(referenceTable, referenceTable.length - index, referenceTable.length);
            String[] offsetTable2 = Arrays.copyOfRange(referenceTable, 0, referenceTable.length - index);
            List<String> offsetTable = new ArrayList<>(offsetTable1.length + offsetTable2.length);
            Collections.addAll(offsetTable, offsetTable1);
            Collections.addAll(offsetTable, offsetTable2);
            Map<String, Integer> offsetMap = new HashMap<>();
            for (int i = 0; i < offsetTable.size(); i += 1) {
                offsetMap.put(offsetTable.get(i), i);
            }
            StringBuilder output = new StringBuilder();
            for (int i = 1; i < phrase.length(); i += 1) {
                if (originalMap.containsKey(String.valueOf(phrase.charAt(i)))) {
                    int j = offsetMap.get(String.valueOf(phrase.charAt(i)));
                    String letter = referenceTable[j];
                    output.append(letter);
                } else {
                    output.append(phrase.charAt(i));
                }
            }
            return output.toString();
        } else {
            return phrase;
        }
    }

    public String encode(String phrase, String offset){
        if (originalMap.containsKey(offset)) {
            int index = originalMap.get(offset);
            String[] offsetTable1 = Arrays.copyOfRange(referenceTable, referenceTable.length - index, referenceTable.length);
            String[] offsetTable2 = Arrays.copyOfRange(referenceTable, 0, referenceTable.length - index);
            List<String> offsetTable = new ArrayList<>(offsetTable1.length + offsetTable2.length);
            Collections.addAll(offsetTable, offsetTable1);
            Collections.addAll(offsetTable, offsetTable2);
            StringBuilder output = new StringBuilder();
            output.append(offset);
            for (int i = 0; i < phrase.length(); i += 1) {
                if (originalMap.containsKey(String.valueOf(phrase.charAt(i)))) {
                    int j = originalMap.get(String.valueOf(phrase.charAt(i)));
                    String letter = offsetTable.get(j);
                    output.append(letter);
                } else {
                    output.append(phrase.charAt(i));
                }
            }
            return output.toString();
        } else {
            return phrase;
        }
    }

}

public class EncoderDecoder {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("What is the message to be encoded?");
        String originalMessage = sc.nextLine();
        System.out.println("What is the offset character?");
        String offset = sc.next();
        EncodeDecode encodeDecode = new EncodeDecode();
        encodeDecode.createMap();
        String encodedMessage = encodeDecode.encode(originalMessage,offset);
        System.out.println("Encoded message: " + encodedMessage);
        String decodedMessage = encodeDecode.decode(encodedMessage);
        System.out.println("Decoded message: " + decodedMessage);
    }

}
