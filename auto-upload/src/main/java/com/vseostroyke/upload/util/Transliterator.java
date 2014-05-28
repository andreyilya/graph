package com.vseostroyke.upload.util;

import java.util.HashMap;
import java.util.Map;

/**
 * User: a.radkov
 * Date: 28.05.2014
 */
public final class Transliterator {

    private Transliterator(){}

    private static final Map<Character, String> charMap = new HashMap<>();

    static {
        charMap.put('А', "a");
        charMap.put('Б', "b");
        charMap.put('В', "v");
        charMap.put('Г', "g");
        charMap.put('Д', "d");
        charMap.put('Е', "e");
        charMap.put('Ё', "e");
        charMap.put('Ж', "zh");
        charMap.put('З', "z");
        charMap.put('И', "i");
        charMap.put('Й', "i");
        charMap.put('К', "k");
        charMap.put('Л', "l");
        charMap.put('М', "m");
        charMap.put('Н', "n");
        charMap.put('О', "o");
        charMap.put('П', "p");
        charMap.put('Р', "r");
        charMap.put('С', "s");
        charMap.put('Т', "t");
        charMap.put('У', "u");
        charMap.put('Ф', "f");
        charMap.put('Х', "h");
        charMap.put('Ц', "c");
        charMap.put('Ч', "ch");
        charMap.put('Ш', "sh");
        charMap.put('Щ', "shc");
        charMap.put('Ъ', "");
        charMap.put('Ы', "y");
        charMap.put('Ь', "");
        charMap.put('Э', "e");
        charMap.put('Ю', "u");
        charMap.put('Я', "ya");
        charMap.put('а', "a");
        charMap.put('б', "b");
        charMap.put('в', "v");
        charMap.put('г', "g");
        charMap.put('д', "d");
        charMap.put('е', "e");
        charMap.put('ё', "e");
        charMap.put('ж', "zh");
        charMap.put('з', "z");
        charMap.put('и', "i");
        charMap.put('й', "i");
        charMap.put('к', "k");
        charMap.put('л', "l");
        charMap.put('м', "m");
        charMap.put('н', "n");
        charMap.put('о', "o");
        charMap.put('п', "p");
        charMap.put('р', "r");
        charMap.put('с', "s");
        charMap.put('т', "t");
        charMap.put('у', "u");
        charMap.put('ф', "f");
        charMap.put('х', "h");
        charMap.put('ц', "c");
        charMap.put('ч', "ch");
        charMap.put('ш', "sh");
        charMap.put('щ', "sh");
        charMap.put('ъ', "");
        charMap.put('ы', "y");
        charMap.put('ь', "");
        charMap.put('э', "e");
        charMap.put('ю', "u");
        charMap.put('я', "ya");
        charMap.put('.', "");
        charMap.put(' ', "");
        charMap.put(',', "");
        charMap.put(':', "");
        charMap.put('\\', "");
        charMap.put('/', "");
        charMap.put('#', "");
        charMap.put('%', "");
        charMap.put('@', "");
        charMap.put('&', "");
        charMap.put('*', "");
        charMap.put('(', "");
        charMap.put(')', "");
        charMap.put('+', "");
        charMap.put('=', "");
        charMap.put('-', "");

    }

    public static String transliterate(String string) {
        StringBuilder transliteratedString = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            Character ch = string.charAt(i);
            String charFromMap = charMap.get(ch);
            if (charFromMap == null) {
                transliteratedString.append(ch);
            } else {
                transliteratedString.append(charFromMap);
            }
        }
        return transliteratedString.toString();
    }
}
