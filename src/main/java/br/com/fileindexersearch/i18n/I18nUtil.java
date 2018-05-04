package br.com.fileindexersearch.i18n;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public final class I18nUtil {
	
	private final static ResourceBundle BUNDLE =  ResourceBundle.getBundle("labels");
	
	private I18nUtil() {}
	
	public static String getTextoInternacionalizado(final String chave, final String... parametros) {
		String textoI18N = getTextoInternacionalizado(chave);

		for (int i = 0; i < parametros.length; i++) {
			final String parametro = parametros[i];
			textoI18N = textoI18N.replaceAll("\\{" + i + "\\}", parametro);
		}

		return textoI18N;
	}
	public static String getTextoInternacionalizado(final String chave) {
		String textoI18N = null;
		try {		
			 textoI18N = BUNDLE.getString(chave);
		} catch (final MissingResourceException e1) {
			textoI18N = chave;
		}
		return textoI18N;
	}
	
	
	
}
