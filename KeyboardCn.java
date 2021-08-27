/**
 * @author R&D Center - yangke
 * @version 5.0.0
 * @date 2021-04-09 16:59
 * copyright @2019 Beijing Murong IT Corp. Ltd.
 */
public class KeyboardCn {


    public static void main(String[] args) {
        System.out.println(gbEncoding("sssÑîçæ"));
        System.out.println(decodeUnicode(gbEncoding("sssÑîçæ")));
    }

    public static String gbEncoding(final String gbString) {

        char[] utfBytes = gbString.toCharArray();
        String unicodeBytes = "";
        for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {
            String hexB = utfBytes[byteIndex] + "";
            if (utfBytes[byteIndex] >= 0x4E00 && utfBytes[byteIndex] <= 0x9FA5) {
                hexB = Integer.toHexString(utfBytes[byteIndex]);
                if (hexB.length() <= 2) {
                    hexB = "00" + hexB;
                }
                unicodeBytes = unicodeBytes + "\\u" + hexB;
            } else {
                unicodeBytes += hexB;
            }

        }
        return unicodeBytes;
    }

    public static String decodeUnicode(final String dataStr) {
        int start = dataStr.indexOf("\\u");
        int end = 0;
        final StringBuffer buffer = new StringBuffer();
        while (start > -1) {

            end = dataStr.indexOf("\\u", start + 2);
            String charStr = "";
            if (end == -1) {
                charStr = dataStr.substring(start + 2, dataStr.length());
            } else {
                charStr = dataStr.substring(start + 2, end);
            }
            char letter = (char) Integer.parseInt(charStr, 16); // 16½øÖÆparseÕûÐÎ×Ö·û´®¡£
            buffer.append(new Character(letter).toString());
            start = end;
        }
        return buffer.toString();
    }

}
