package br.com.fileindexersearch.factory;

public final class SingletonServiceFactory {
	
	private static final ServiceFactory  SIGLETON = new ServiceFactory();
	
	private SingletonServiceFactory() {
		
	}
	
	public static ServiceFactory getServiceFactory() {
		return SIGLETON;
	}

}
