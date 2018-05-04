package br.com.fileindexersearch.factory;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import br.com.fileindexersearch.enums.Delimiter;
import br.com.fileindexersearch.exception.FileIndexerSearchException;
import br.com.fileindexersearch.service.FileReaderService;

public class ServiceFactory {

	private static Class<? extends Object>[] typesArgs;

	ServiceFactory() {
	}

	static {
		typesArgs = new Class[3];
		typesArgs[0] = File.class;
		typesArgs[1] = Delimiter.class;
		typesArgs[2] = Boolean.class;

	}

	@SuppressWarnings("unchecked")
	public static FileReaderService getFileService(String clazz, File file, Delimiter delimiter,Boolean searchUperCase)
			throws FileIndexerSearchException {
		try {
			Class<? extends FileReaderService> classToLoad = (Class<? extends FileReaderService>) Class.forName(clazz)
					.newInstance().getClass();
			Constructor<? extends FileReaderService> constructor = classToLoad.getDeclaredConstructor(typesArgs);
			return (FileReaderService) constructor.newInstance(file, delimiter,searchUperCase);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException | InvocationTargetException e) {
			if (e.getCause() != null) {
				throw new FileIndexerSearchException(e.getCause().getMessage(), e);
			}
			throw new FileIndexerSearchException(e.getMessage(), e);
		}

	}

}
