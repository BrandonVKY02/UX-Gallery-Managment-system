import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class priceTextField extends JTextField {

    public priceTextField() {
        super();
        setDocument(new DecimalDocument());
    }

    private class DecimalDocument extends PlainDocument {

        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            if (str == null) {
                return;
            }

            String currentText = getText(0, getLength());
            char[] currentTextChars = currentText.toCharArray();
            char[] insertChars = str.toCharArray();

            // Check if new text is a valid decimal number
            boolean isDigitOrDot = true;
            int decimalPointIndex = -1;
            for (int i = 0; i < insertChars.length; i++) {
                char c = insertChars[i];
                if (!Character.isDigit(c)) {
                    if (c == '.') {
                        if (decimalPointIndex != -1) {
                            isDigitOrDot = false;
                            break;
                        }
                        decimalPointIndex = currentTextChars.length + i;
                        continue;
                    } else {
                        isDigitOrDot = false;
                        break;
                    }
                }
            }

            // If new text is valid, let it be inserted
            if (isDigitOrDot) {
                StringBuilder builder = new StringBuilder(currentText);
                builder.insert(offs, insertChars);
                String newText = builder.toString();
                try {
                    Double.parseDouble(newText);
                    if (decimalPointIndex != -1) {
                        // Limit to 2 decimal places
                        int decimalPlaces = newText.length() - decimalPointIndex - 1;
                        if (decimalPlaces > 2) {
                            return;
                        }
                    }
                    super.insertString(offs, str, a);
                } catch (NumberFormatException e) {
                    // New text is not a valid decimal number
                    return;
                }
            }
        }
    }
}
