package cep;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class FiltroCampoCep extends DocumentFilter {

	int caracteres;

	public FiltroCampoCep(int caracteres) {
		this.caracteres = caracteres;
	}

	@Override
	public void insertString(FilterBypass fb, int offset, String str, AttributeSet atributo)
			throws BadLocationException {

		if (str == null) {
			return;
		}

		if ((fb.getDocument().getLength() + str.length()) <= caracteres && str.matches("\\d+")) {
			super.insertString(fb, offset, str, atributo);
		}
	}

	@Override
	public void replace(FilterBypass fb, int offset, int length, String str, AttributeSet atributo)
			throws BadLocationException {
		if (str == null) {
			return;
		}

		if ((fb.getDocument().getLength() + str.length() - length) <= caracteres && str.matches("\\d+")) {
			super.replace(fb, offset, length, str, atributo);
		}
	}

	@Override
	public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
		super.remove(fb, offset, length);
	}

}
