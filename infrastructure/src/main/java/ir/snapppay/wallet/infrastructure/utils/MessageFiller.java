package ir.snapppay.wallet.infrastructure.utils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.11
 */
public class MessageFiller {

    public final static MessageFiller INSTANCE = new MessageFiller();
    /**
     * regular PATTERN to detect any placeholder like ({{name}}) (without parentheses)
     */
    private static final String REGEX = "(\\{\\{)(.*?)(\\}\\})";
    /**
     * Compiled PATTERN to detect placeholders
     */
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    private MessageFiller() {

    }

    /**
     * replaces placeholders in message parameter with values of keys in map parameter.
     * for example if message be
     * `Hi {{name}}. This is a sample`
     * and map be:
     * {{"id",1}, {"height":185}, {"name": 'Mohammad'}, {"weight":90.0}}
     * the result will be
     * `Hi Mohammad. This is a sample`
     *
     * @param message message to fill its placeholders
     * @param map     map containing keys and values
     * @return message whose placeholders filled with relevant keys in map
     */
    public static String fill(final String message,
                              final Map<String, Object> map) {
        Map<String, Object> data = map != null ? map : Map.of();
        String filledMessage = "";
        if (message != null && !message.isEmpty()) {
            final StringBuilder buffer = new StringBuilder(message);
            Matcher matcher = PATTERN.matcher(buffer);
            LinkedHashMap<String, List<Replacement>> linkedHashMap = new LinkedHashMap<>();

            while (matcher.find()) {
                String parameter = matcher.group(2);
                final Object value = data.get(parameter);
                if (value != null) {
                    Replacement replacement = new Replacement(matcher.start(), matcher.end(), String.valueOf(value));
                    if (linkedHashMap.containsKey(parameter)) {
                        List<Replacement> replacements = linkedHashMap.get(parameter);
                        replacements.add(replacement);
                        linkedHashMap.put(parameter, replacements);
                    } else {
                        linkedHashMap.put(parameter, new ArrayList<>(Collections.singletonList(replacement)));
                    }
                }
            }
            filledMessage = initializeMessage(linkedHashMap, buffer);
        }
        return filledMessage;
    }

    /***
     * places values in placeholders?
     * @param linkedHashMap key values
     * @param buffer string to replace
     * @return a string whose placeholders replaced with values in {@see linkedHashMap}
     */
    private static String initializeMessage(
        final LinkedHashMap<String, List<Replacement>> linkedHashMap,
        final StringBuilder buffer
    ) {
        int pointer = 0;
        final StringBuilder messageBuilder = new StringBuilder();

        for (Map.Entry<String, List<Replacement>> entry : linkedHashMap.entrySet()) {
            for (Replacement replacement : entry.getValue()) {
                final String unmodifiedMessage = buffer.substring(pointer, replacement.getStartIndex());
                messageBuilder.append(unmodifiedMessage);
                messageBuilder.append(replacement.getReplacementValue());
                pointer = replacement.getEndIndex();
            }
        }
        messageBuilder.append(buffer.substring(pointer));

        return messageBuilder.toString();
    }

    /**
     * Holds information of placeholder and value to be replaced with
     */
    private static final class Replacement {

        /**
         * start location of placeholder
         */
        private final int startIndex;

        /**
         * end location of placeholder
         */
        private final int endIndex;

        /**
         * value to be replaced
         */
        private final String replacementValue;

        /**
         * initializes new instance of Replacement
         *
         * @param startIndexValue start location of placeholder
         * @param endIndexValue   end location of placeholder
         * @param replacementArg  value to be replaced
         */
        private Replacement(final int startIndexValue, final int endIndexValue, final String replacementArg) {
            this.startIndex = startIndexValue;
            this.endIndex = endIndexValue;
            this.replacementValue = replacementArg;
        }


        public int getStartIndex() {
            return startIndex;
        }

        public int getEndIndex() {
            return endIndex;
        }

        public String getReplacementValue() {
            return replacementValue;
        }
    }

}
