package com.renymvc.support;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.renymvc.annotation.Controller;

public class ContextLoader implements ServletContextListener {
	
	private static final String PACKAGE_FOR_SCAN = "scanpackage";
	private static final Logger log = Logger.getGlobal();
	
	private UrlMappingHandler urlMappingHandler;

	public ContextLoader(){
		urlMappingHandler = UrlMappingHandler.INSTANCE;
	}
	
	@Override
	public void contextInitialized(ServletContextEvent paramServletContextEvent) {
		ServletContext context = paramServletContextEvent.getServletContext();
		initUrlMapping(context);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent paramServletContextEvent) {
		urlMappingHandler = null;
	}
	
	private void initUrlMapping(ServletContext context) {
		log.info("Starting to initialize url mapping");
		String packageName = context.getInitParameter(PACKAGE_FOR_SCAN);
		String classPath = this.getClass().getResource("/").getPath().substring(1); //去掉第一个\
		String rootPath = classPath + packageName.replace(".", File.separator);
		File root = new File(rootPath);
		List<File> classes = scanClassPath(root);
		try {
			ClassLoader classLoader = this.getClass().getClassLoader();
			for (File f : classes) {
				String fileName = f.getPath();
				String classFullName = fileName.substring(classPath.length(), fileName.length()-6).replace(File.separator, ".");
				Class<?> clazz = Class.forName(classFullName, false, classLoader);
				Controller controller = (Controller) clazz.getAnnotation(Controller.class);
				if (controller != null) {
					String uri = controller.value();
					if (!uri.startsWith("/")){
						uri = "/" + uri;
					}
					urlMappingHandler.add(uri, clazz.newInstance());
					log.info("Mapping "+clazz.getName()+"with url "+controller.value());
				}
			}
		} catch (ClassNotFoundException e){
			log.warning(e.getMessage());
		} catch (InstantiationException e) {
			log.warning(e.getMessage());
		} catch (IllegalAccessException e) {
			log.warning(e.getMessage());
		}
		log.info("Url mapping initialization has completed");
	}

	private List<File> scanClassPath(File root){
		List<File> list = new LinkedList<File>();
		if (root.isFile()) return list;
		File[] files = root.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				list.addAll(scanClassPath(file));
			} else {
				if (file.getName().endsWith(".class")){
					list.add(file);
				}
			}
		}
		return list;
	}
	
}
